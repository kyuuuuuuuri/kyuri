package root.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import root.SuperAction;
import root.entity.Follow;
import root.entity.Tuser;
import root.form.FollowlistForm;

public class FollowlistAction extends SuperAction {
	private final String followPageJsp = "followpage.jsp";

	@ActionForm
	@Resource
	protected FollowlistForm followlistForm;

	public List<Follow> followList = new ArrayList<Follow>();
	public List<Tuser> followedList = new ArrayList<Tuser>();
	public int followCheck;
	public List<Integer> fc_userid = new ArrayList<Integer>();
	public long followcheckcount = 0;


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

		System.out.println(followFlag);

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
		mydata = tuserService.findById(useridToShow);

		followList = followService.findFollowUser(useridToShow, userid);

		return followPageJsp;

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
		mydata = tuserService.findById(useridToShow);

		followList = followService.findFollowedUser(useridToShow, userid);

		return "followedpage.jsp";
	}

	@Resource
	HttpServletRequest req;

	//フォローする
	@Execute(validator = false)
	public String toFollow() {
		int userid = userDto.userID;
		String useridStr = req.getParameter("followUserId");
		System.out.println(useridStr + "きゅうり");
		int toFollowUserId = Integer.parseInt(useridStr);

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

		//すでにフォローしていたら何もしない
		Follow fol = followService.delFollow(toFollowUserId, userid);

		if (fol == null) {
			System.out.println("おかしいよ");
			return null;
		} else {
			//フォローしていなかったらフォロワ―をinsertする。

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
		int murmurid = followlistForm.id;
		
		
		return "";
	}
	
	//リツイートされているユーザ一覧
	@Execute(validator = false)
	public String showRetList() {
		
		return "";
	}

}
