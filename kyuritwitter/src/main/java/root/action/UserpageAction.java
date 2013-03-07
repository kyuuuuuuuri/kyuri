package root.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import root.SuperAction;
import root.entity.Blockid;
import root.entity.Favolite;
import root.entity.Follow;
import root.entity.ListFollow;
import root.entity.Murmur;
import root.entity.Tlist;
import root.entity.Tuser;
import root.form.UserpageForm;

public class UserpageAction extends SuperAction{

	@ActionForm
	@Resource
	protected UserpageForm userpageForm;

	public int fFlag = 0;

	public String imgShowUrl;

	//userが作成したリストを出力する
	public List<Tlist> tlistList = new ArrayList<Tlist>();

	public Tlist showTlist = new Tlist();

	public List<ListFollow> listFollow = new ArrayList<ListFollow>();

	public List<Tuser> tuserList = new ArrayList<Tuser>();

	public int UserUseThisList;


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

		//ブロックされていたら何もしない
		Blockid bl = blockidService.findBlockid(userid, toFollowUserId);
		if(bl != null){
			return null;
		}

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
		int toFollowUserId = Integer.parseInt(useridStr);



		//すでにフォローしていたら何もしない
		Follow fol = followService.delFollow(toFollowUserId, userid);

		if (fol == null) {
			System.out.println("おかしい");
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

	//ユーザがフォローしているリストを出力
	@Execute(validator = false, urlPattern = "toList/{userid}")
	public String showListUserMake(){
		int userid = userDto.userID;
		mine = userid;
		int thisUserId = userpageForm.userid;
		menuFlag = 2;
		mydata = tuserService.findById(thisUserId);
//		System.out.println(userid+"lemon");

		listFollow = listFollowService.findListFollowByUserAndMyInfo(thisUserId, userid);

		return "UserList.jsp";
	}

	//listに登録されているユーザのつぶやきを表示する
	@Execute(validator = false, urlPattern = "showListUser/{listid}")
	public String showListUser() {
		menuFlag = 4;
		int page   = 0;
		int listid = userpageForm.listid;
		int userid = userDto.userID;
		mine = userid;

		List<Integer> useridList = new ArrayList<Integer>();
		useridList = inListUserService.findUserInTheList(listid);

		if(listFollowService.existListFollow(listid, userid)){
			UserUseThisList = 1;
		}else{
			UserUseThisList = 0;
		}

		showTlist = tlistService.findByListId(listid);

		murmurList = murmurService.mainListPager(LIMIT, page, useridList, userid);

		return "mainListpage.jsp";
	}

	//listに登録されているユーザを表示する
	@Execute(validator = false,urlPattern="userInList/{listid}")
	public String userInList(){
		int userid = userDto.userID;
		mine = userid;
		int listid = userpageForm.listid;
		menuFlag = 4;

		if(listFollowService.existListFollow(listid, userid)){
			UserUseThisList = 1;
		}else{
			UserUseThisList = 0;
		}

		showTlist = tlistService.findById(listid);

		tuserList = tuserService.tuserListFollowByUser(listid,userid);

		return "userInList.jsp";
	}

	//listを登録する
	@Execute(validator = false)
	public String doUseList(){
		System.out.println("morokyu");


		String listidStr = req.getParameter("listIDAjax");

		System.out.println("morokyu" + listidStr);
		int listidInt = Integer.parseInt(listidStr);
		int userid = userDto.userID;

		ListFollow listF = new ListFollow();
		listF.listid = listidInt;
		listF.followThisUserid = userid;

		if(listFollowService.existListFollow(listidInt, userid)){
			throw new Error("もうすでに登録してます");
		}

		listFollowService.insert(listF);

		return null;
	}

	//listを削除する
	@Execute(validator = false)
	public String doUnUseList(){

		String listidStr = req.getParameter("listIDAjax");
		int listid = Integer.parseInt(listidStr);
		int userid = userDto.userID;

		ListFollow listF = new ListFollow();
		listF.listid = listid;
		listF.followThisUserid = userid;

		if(!(listFollowService.existListFollow(listid, userid))){
			throw new Error("登録されていないユーザを削除しようとしています");
		}

		listF = listFollowService.findListFollowForDelete(listid, userid);

		listFollowService.delete(listF);

		return null;
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

	//お気に入りに登録する
	@Execute(validator = false)
	public String doFavorite() {
		String murmurid = req.getParameter("murmurid");
		int murmuridInt = Integer.parseInt(murmurid);
		Murmur murmur = new Murmur();
		Favolite favolite = new Favolite();
		int userid = userDto.userID;

		//murmurServiceのお気に入りフラグ
		murmur.murmurid = murmuridInt;
		murmur.favoritenum = +1;
		murmurService.updateFavoriteFlagTrue(murmur);

		//favoriteTableに挿入する
		favolite.murmurid = murmuridInt;
		favolite.userid = userid;
		favoliteService.insert(favolite);

		return null;
	}

	//お気に入りから外す
	@Execute(validator = false)
	public String canselFavorite() {
		String murmurid = req.getParameter("murmurid");
		int murmuridInt = Integer.parseInt(murmurid);
		Murmur murmur = new Murmur();
		Favolite favolite = new Favolite();
		int userid = userDto.userID;
		List<Favolite> favoliteList = new ArrayList<Favolite>();

		//murmurServiceのお気に入りフラグ
		murmur.murmurid = murmuridInt;
		murmur.favoritenum = -1;
		murmurService.updateFavoriteFlagTrue(murmur);

		//favoriteTableに挿入する
		favolite.murmurid = murmuridInt;
		favolite.userid = userid;
		favoliteList = favoliteService.findDeleteFavoList(favolite);
		if (!favoliteList.isEmpty()) {
			favoliteService.deleteById(favoliteList);
		}
		return null;
	}

	//ブロックする
	@Execute(validator = false)
	public String blockUser(){
		int userid = userDto.userID;
		int blockUserid;

		String useridStr = req.getParameter("blockUserid");
		blockUserid = Integer.parseInt(useridStr);

		//フォローしていたら消す
		Follow fol = followService.delFollow(blockUserid, userid);

		if (fol != null) {
			followService.delete(fol);
		}

		//フォローされていたら消す
		fol = followService.delFollow(userid, blockUserid);

		if(fol != null){
			followService.delete(fol);
		}

		//ブロックに追加する
		Blockid bl = new Blockid();
		bl.userid = userid;
		bl.blockUserid = blockUserid;
		blockidService.insert(bl);

		return null;
	}

	//ブロックを解除
	@Execute(validator = false)
	public String unBlockUser(){
		int userid = userDto.userID;
		int blockedUserid;
		String useridStr = req.getParameter("blockUserid");
		blockedUserid = Integer.parseInt(useridStr);

		Blockid bl = blockidService.findBlockid(userid, blockedUserid);
		if(bl == null){
			return null;
		}

		blockidService.delete(bl);

		return null;
	}


	//お気に入りのタイムラインを表示する
	@Execute(validator = false, urlPattern="favoliteLine/{userid}")
	public String favoliteLine() {
		int userid = userpageForm.userid;
		murmurList = murmurService.findAllOrderById();

		return "";
	}

	//リプライのラインを表示する
	@Execute(validator = false, urlPattern="replyLine/{userid}")
	public String replyLine() {
		int userid = userpageForm.userid;
//		murmurList = murmurService.findById();
		return "";
	}

	//フォローユーザへ
	@Execute(validator = false, urlPattern="followlist/{userid}/{followOrUnfollow}")
	public String toFollowPage(){
		int id = userpageForm.userid;
		String followFlag = userpageForm.followOrUnfollow;
//		System.out.println("moromoro" + followFlag);

		return "/followlist?id=" + id + "&followOrUnfollow=" + followFlag +"?redirect=true";
	}

	//フォロワーユーザへ
	@Execute(validator  = false, urlPattern="followedlist/{userid}/{followOrUnfollow}")
	public String toBefollowedPage(){
		int id = userpageForm.userid;
		String followFlag = userpageForm.followOrUnfollow;
//		System.out.println("moromoro" + followFlag);


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

	//上部に表示するやつ
	@Execute(validator = false)
	public String searchUsershort(){
		String searchUser = req.getParameter("searchword");

		int userid = userDto.userID;

		tuserList = tuserService.tuserSearch(searchUser, userid, 3, 0);
		if(tuserList.isEmpty()){
		}

		return "/main/searchUser.jsp";
	}

	//ユーザを検索する
	@Execute(validator = false , urlPattern="search/{searchUser}")
	public String searchUserAll(){
		String searchUsernick = userpageForm.searchUser;

		return "/search?search="+ searchUsernick +"?redirect=true";
	}



}
