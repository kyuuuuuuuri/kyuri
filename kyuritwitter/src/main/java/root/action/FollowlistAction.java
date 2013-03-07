package root.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import root.SuperAction;
import root.entity.Blockid;
import root.entity.Favolite;
import root.entity.Follow;
import root.entity.Retweets;
import root.entity.Tuser;
import root.form.FollowlistForm;

public class FollowlistAction extends SuperAction {
	private final String followPageJsp = "followpage.jsp";

	@ActionForm
	@Resource
	protected FollowlistForm followlistForm;

	public List<Follow> followList = new ArrayList<Follow>();
	public List<Tuser> followedList = new ArrayList<Tuser>();
	public List<Favolite> favoliteList = new ArrayList<Favolite>();
	public List<Retweets> retweetsList = new ArrayList<Retweets>();
	public int followCheck;
	public List<Integer> fc_userid = new ArrayList<Integer>();
	public long followcheckcount = 0;

	@Resource
	HttpServletRequest req;

	public List<Tuser> tuserList = new ArrayList<Tuser>();

	@Execute(validator = false)
	public String index(){

		//ログインしているユーザのユーザID
		int userid = userDto.userID;
		mine = userid;

		menuFlag = 3;
		String followFlag = followlistForm.followOrUnfollow;

		int useridToShow;
		useridToShow = followlistForm.id;

//		System.out.println(followFlag);

		if(followFlag.equals("follow")){

			return "followpage?id=" + useridToShow;

		}else if(followFlag.equals("followed")){

			return "followedlist?id=" + useridToShow;
		}

		return "";

	}

	@Execute(validator = false)
	public String followpage() {
		//ログインしているユーザのユーザID
		int userid = userDto.userID;
		mine = userid;

		menuFlag = 3;

		int useridToShow;
		useridToShow = followlistForm.id;

		//今探そうとしているユーザのデータ
		mydata = tuserService.findByID(useridToShow, userid);

		followList = followService.findFollowUser(useridToShow, userid);

		return followPageJsp;

	}

	//ajaxload
	@Execute(validator = false)
	public String followOld() {
		//ログインしているユーザのユーザID
		int userid = userDto.userID;
		int useridToShow;
		int lastId;

		String useridToShowStr = req.getParameter("mydataId");
		useridToShow = Integer.parseInt(useridToShowStr);

		String lastIdStr = req.getParameter("lastFollowId");
		lastId = Integer.parseInt(lastIdStr);

		//今探そうとしているユーザのデータ
		mydata = tuserService.findByID(useridToShow, userid);

		followList = followService.findFollowUserOld(useridToShow, userid, lastId);

		return "oldFollowInfo.jsp";
	}

	//フォローされているユーザを表示する
	@Execute(validator = false)
	public String followedlist() {

		//ログインしているユーザのユーザID
		int userid = userDto.userID;
		mine = userid;
		menuFlag = 3;

		int useridToShow;
		useridToShow = followlistForm.id;

		//今探そうとしているユーザのデータ
		mydata = tuserService.findByID(useridToShow, userid);

		followList = followService.findFollowedUser(useridToShow, userid);

		return "followedpage.jsp";
	}

	//フォローされているユーザを表示するAjaxload
	@Execute(validator = false)
	public String followedlistOld() {

		int userid = userDto.userID;
		int useridToShow;
		int lastId;

		String useridToShowStr = req.getParameter("mydataId");
		useridToShow = Integer.parseInt(useridToShowStr);

		String lastIdStr = req.getParameter("lastFollowId");
		lastId = Integer.parseInt(lastIdStr);

		//今探そうとしているユーザのデータ
		mydata = tuserService.findByID(useridToShow, userid);

		followList = followService.findFollowedUserOld(useridToShow, userid, lastId);

		return "oldFollowedInfo.jsp";
	}

	//
	@Execute(validator = false)
	public String recommendList() {

		return "";
	}

	//フォローする
	@Execute(validator = false)
	public String toFollow() {
		int userid = userDto.userID;
		String useridStr = req.getParameter("followUserId");
		System.out.println(useridStr + "きゅうり");
		int toFollowUserId = Integer.parseInt(useridStr);

		//ブロックされていたら何もしない
		Blockid bl = blockidService.findBlockid(userid, toFollowUserId);
		if(bl != null){
			return null;
		}

		//すでにフォローしていたら何もしない
		Follow fol = followService.delFollow(toFollowUserId, userid);

		if (fol != null) {
//			System.out.println("おかしいよ");
			return null;
		} else {
			//フォローしていなかったらフォロワ―をinsertする。
			Follow foluser = new Follow();
			foluser.userid = userid;
			foluser.fuserid = toFollowUserId;

			followService.insert(foluser);

			//フォロー数を更新
			Tuser tuser = tuserService.findById(userid);
			tuser.follow += 1;
			tuserService.updateTuserAfterFollow(tuser);

			//フォロワ―数を更新
			Tuser tuser2 = tuserService.findById(toFollowUserId);
			tuser2.followed += 1;
			tuserService.updateTuserAfterBeFollowed(tuser2);
		}

		return null;
	}

	@Execute(validator = false)
	public String toUnFollow() {
		int userid = userDto.userID;
		String useridStr = req.getParameter("unFollowUserId");
//		System.out.println(useridStr + "きゅうり");
		int toFollowUserId = Integer.parseInt(useridStr);

		//フォローしてなかったら何もしない
		Follow fol = followService.delFollow(toFollowUserId, userid);

		if (fol == null) {
			System.out.println("フォローしてないよ");
			return null;
		} else {

			followService.delete(fol);

			//フォロー数を更新
			Tuser tuser = tuserService.findById(userid);
			tuser.follow -= 1;
			tuserService.updateTuserAfterFollow(tuser);

			//フォロワ―数を更新
			Tuser tuser2 = tuserService.findById(toFollowUserId);
			tuser2.followed -= 1;
			tuserService.updateTuserAfterBeFollowed(tuser2);
		}
		return null;
	}
	//フォローユーザへ
	@Execute(validator = false, urlPattern="followlist/{userid}/{followOrUnfollow}")
	public String toFollowPage(){
		int id = followlistForm.userid;
		String followFlag = followlistForm.followOrUnfollow;
		System.out.println("moromoro" + followFlag);

		return "/followlist?id=" + id + "&followOrUnfollow=" + followFlag +"?redirect=true";
	}

	//フォロワーユーザへ
	@Execute(validator  = false, urlPattern="followedlist/{userid}/{followOrUnfollow}")
	public String toBefollowedPage(){
		int id = followlistForm.userid;
		String followFlag = followlistForm.followOrUnfollow;
		System.out.println("moromoro" + followFlag);


		return "/followlist?id=" + id + "&followOrUnfollow=" + followFlag + "?redirect=true";
	}
	//ユーザ個々のつぶやきを表示する
	@Execute(validator = false, urlPattern = "userpage/{userni}")
	public String showdata() {

		String nick = followlistForm.userni;

		return "/userpage?userni="+ nick +"?redirect=true";

	}

	public String searchAll(){

		String searchWord = followlistForm.searchWord;

		return "/searchtwit?searchWord=" + searchWord + "?redirect=true";
	}

	@Execute(validator = false)
	public String searchUsershort(){
		String searchUser = req.getParameter("searchword");

		int userid = userDto.userID;

		tuserList = tuserService.tuserSearch(searchUser, userid, 3, 0);
		if(tuserList.isEmpty()){
		}

		return "/main/searchUser.jsp";
	}

	@Execute(validator = false , urlPattern="search/{searchUser}")
	public String searchUserAll(){
		String searchUsernick = followlistForm.searchUser;

		return "/search?search="+ searchUsernick +"?redirect=true";
	}

	//ユーザがフォローしているリストを出力
	@Execute(validator = false, urlPattern = "toList/{userid}")
	public String showListUserMake(){

		int thisUserId = followlistForm.userid;

		return "/userpage/toList?userid=" + thisUserId;
	}

	//お気に入りされているユーザ一覧
	@Execute(validator = false, urlPattern="showFavoList/{id}")
	public String showFavoList() {
		int userid = userDto.userID;
		mine = userid;

		menuFlag = 1;

		int murmurid = followlistForm.id;
		favoliteList = favoliteService.findFavoUser(murmurid,userid);


		return "favoUserList.jsp";
	}

	//リツイートされているユーザ一覧
	@Execute(validator = false, urlPattern="showRetList/{id}")
	public String showRetList() {
		int userid = userDto.userID;
		mine = userid;
		menuFlag = 1;

		int murmurid = followlistForm.id;
		retweetsList = retweetsService.findRetweetUser(murmurid, userid);

		return "retweetUserList.jsp";
	}

}
