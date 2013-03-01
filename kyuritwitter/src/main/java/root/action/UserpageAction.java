package root.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import root.SuperAction;
import root.entity.Follow;
import root.entity.Murmur;
import root.entity.Tuser;
import root.form.UserpageForm;

public class UserpageAction extends SuperAction{


	@ActionForm
	@Resource
	protected UserpageForm userpageForm;

	public int fFlag = 0;

	public String imgShowUrl;

	@Resource
	HttpServletRequest req;


	//jspファイルに渡すつぶやきリストを格納する変数
	public List<Murmur> murmurList = new ArrayList<Murmur>();


	@Execute(validator = false, urlPattern = "{userni}")
	public String index() {
		String userni = userpageForm.userni;
		int userid = userDto.userID;
		mine = userid;
		menuFlag = 2;

		//System.out.println(userni + "" + userid + " 入ったよ");
		murmurList = murmurService.listPager(LIMIT, 0, userni, userid);

		mydata = tuserService.findByName(userni,userid);

		return "userMypage.jsp";
	}

	@Execute(validator = false)
	public String userpage(){
		return "";
	}


	//ツイート画像を表示する
	@Execute(validator = false)
	public String showTwitImg(){
		String imgUrl = req.getParameter("imgurl");
		imgShowUrl = imgUrl;

		return "ImgModal.jsp";
	}


	//ユーザプロフィール画像
	@Execute(validator=false, urlPattern="showUserImg/{userid}")
	public String showUserImg(){
		int id = userpageForm.userid;
		Tuser userImg = tuserService.getTuserImg(id);
		String filename = userImg.userid+".jpg";
		if(userImg != null){
			ResponseUtil.download(filename, userImg.profileimg);
		}

		return null;
	}

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
			System.out.println("こんなのっておかしいよ");
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
		System.out.println(useridStr + "きゅうり");
		int toFollowUserId = Integer.parseInt(useridStr);

		//すでにフォローしていたら何もしない
		Follow fol = followService.delFollow(toFollowUserId, userid);

		if (fol == null) {
			System.out.println("こんなのっておかしいよ");
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

	//ユーザが作成したリストを出力する
	public String showListUserMake(){
		
		
		return "";
	}


	//返信リストを返すafter
	@Execute(validator = false)
	public String repListAfter() {
		int userid = userDto.userID;
		String murId = req.getParameter("tubuyakiId");

		List<Murmur> murmur = murmurService.zibunJoinAfterList(Integer.parseInt(murId), userid);
		if (murmur == null) {
			return null;
		}
		if (murmur.isEmpty()) {
			return null;
		}

		murmurList = murmur;

		return "twitplus.jsp";
	}

	//返信リストを返すbefore
	@Execute(validator = false)
	public String repListBefore() {
		int userid = userDto.userID;
		String murId = req.getParameter("tubuyakiId");
		System.out.println(murId);
		List<Murmur> murmur = murmurService.zibunJoinBeforeList(Integer.parseInt(murId), userid);

		if (murmur.isEmpty()) {
			return null;
		}
		murmurList = murmur;

		return "twitplus.jsp";
	}



	//フォローユーザへ
	@Execute(validator = false, urlPattern="followlist/{userid}/{followOrUnfollow}")
	public String toFollowPage(){
		int id = userpageForm.userid;
		String followFlag = userpageForm.followOrUnfollow;
		System.out.println("moromoro" + followFlag);

		return "/followlist?id=" + id + "&followOrUnfollow=" + followFlag +"?redirect=true";
	}

	//フォロワーユーザへ
	@Execute(validator  = false, urlPattern="followedlist/{userid}/{followOrUnfollow}")
	public String toBefollowedPage(){
		int id = userpageForm.userid;
		String followFlag = userpageForm.followOrUnfollow;
		System.out.println("moromoro" + followFlag);


		return "/followlist?id=" + id + "&followOrUnfollow=" + followFlag + "?redirect=true";
	}

	//ユーザ個々のつぶやきを表示する
	@Execute(validator = false, urlPattern = "userpage/{userni}")
	public String showdata() {

		String nick = userpageForm.userni;

		return "/userpage?userni="+ nick +"?redirect=true";

	}

	//検索する
	public String searchAll(){

		String searchWord = userpageForm.searchWord;

		return "/searchtwit?searchWord=" + searchWord + "?redirect=true";
	}

	public List<Tuser> tuserList = new ArrayList<Tuser>();

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
		String searchUsernick = userpageForm.searchUser;

		return "/search?search="+ searchUsernick +"?redirect=true";
	}



}
