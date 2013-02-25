package root.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javasource.ChangeLocationNum;
import javasource.GetTwitFromSolr;
import javasource.MakeHashList;
import javasource.SetTwitToSolr;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;
import org.seasar.struts.util.ResponseUtil;

import root.SuperAction;
import root.dto.SearchDto;
import root.entity.Favolite;
import root.entity.Follow;
import root.entity.Murmur;
import root.entity.Tuser;
import root.form.MainForm;

//ソート用

public class MainAction extends SuperAction {

	private final String mainPageJsp = "mainpage.jsp";

	public ServletContext application;

	@ActionForm
	@Resource
	protected MainForm mainForm;

	public Tuser mydata = new Tuser();

	public int fFlag = 0;

	// JdbcManagerのインスタンスを取得
		JdbcManager jdbcManager=SingletonS2Container.getComponent("jdbcManager");


	public List<SearchDto> searchDto;

	@Execute(validator = false, urlPattern = "main")
	public String index() {
		return "main";
	}

	private SetTwitToSolr setTwitToSolr;

	//jspファイルに渡すつぶやきリストを格納する変数
	public List<Murmur> murmurList = new ArrayList<Murmur>();


	//メインページ表示メソッド(自分のつぶやき＋他人のつぶやき
	@Execute(validator = false, urlPattern = "main")
	public String main(){
		int userid = userDto.userID;
		mine = userid;

//		String newPath = application.getRealPath("/img/profileImg/" + userDto.userID + ".jpg");

		//ユーザ自身のデータ
		mydata = tuserService.findById(userid);


		List<Integer> murmur_userid = new ArrayList<Integer>();

		//page番号
		int page = IntegerConversionUtil.toPrimitiveInt(this.mainForm.page);

		//ユーザのフォローをリスト化
		List<Follow> followResult = followService.findUserFollow(userid);

		if (followResult != null) {
			for (Follow f : followResult) {
				murmur_userid.add(f.fuserid);
			}
		}
		murmur_userid.add(userid);

		this.total = murmurService.listCount(murmur_userid);

		// 以下ページング処理
		murmurList = murmurService.mainListPager(LIMIT, page, murmur_userid,userid);
		//前ページがあるかどうかを判定
		hasPrev = murmurService.hasPrev(page);
		//次のページがあるかどうかを判定
		hasNext = murmurService.hasNext(LIMIT, this.total, page);

		return mainPageJsp;
	}

	@Resource
	HttpServletRequest req;

	//ajaxload
	@Execute(validator = false)
	public String loadOldTwit(){
		//System.out.println("ajaxdo");
		int userid = userDto.userID;
		mine = userid;
		String lastId = req.getParameter("lastId");
		int lastIdint = Integer.parseInt(lastId);
		//System.out.println(page+"moromoro");

		List<Integer> murmur_userid = new ArrayList<Integer>();

		//ユーザのフォローをリスト化
		List<Follow> followResult = followService.findUserFollow(userid);

		if (followResult != null) {
			for (Follow f : followResult) {
				murmur_userid.add(f.fuserid);
			}
		}
		murmur_userid.add(userid);

		this.total = murmurService.listCount(murmur_userid);

		// 以下ページング処理
		murmurList = murmurService.selectOldTwit(lastIdint, murmur_userid, userid);

		return "showOldTwit.jsp";
	}

	//つぶやくメソッド
	@Execute(validator =false)
	public String insertTwit() {

		setTwitToSolr = new SetTwitToSolr();
		String[] hashArray;
		List<String> hashList = new ArrayList<String>();
		int userid = userDto.userID;

		//Formに渡されたつぶやきを格納する変数
		String mur = mainForm.tubuyaki;
		//登録済みかユーザチェック
		if (mur == null) {
			throw new ActionMessagesException("なにか入力してください", false);
		}

		String hash ="[　|\\s](#)([a-zA-Zあ-んア-ン_]+)";
		Pattern p = Pattern.compile(hash);
		Matcher matcher = p.matcher(mur);
			while (matcher.find()) {

				for (int i = 0; i <= matcher.groupCount(); i++) {
					System.out.println(matcher.group(i));
				}
				hashList.add(matcher.group(2));
			}

			hashArray = new String[hashList.size()];

		if (hashList.size() != 0) {
			for (int i = 0; i < hashList.size(); i++) {
				hashArray[i] = hashList.get(i);
			}
		}
		for(int i =0; i<hashArray.length;i++){
			System.out.println(hashArray[i]);
		}


		Murmur murmur = new Murmur();

		//新しいつぶやきをデータベースに格納する
		murmur.userid = userid;
		murmur.murmur = mur;
		murmur.dateTime = null;
		int newMurmurId = murmurService.insertMurmur(murmur);

		//自分の最新のつぶやきを更新する
		Tuser tuser = tuserService.findById(userid);
		tuser.newMur = mur;
		tuser.postNum += 1;
		tuser.newMurD = null;
		tuserService.updateTuserAfterTwit(tuser);

		murmur = murmurService.findById(newMurmurId);

		//solrに登録する
		setTwitToSolr.setTwit(murmur, hashArray);


		return "main";
	}

	@Execute(validator = false)
	public String checkNewTwit(){

		fFlag = 1;
		int userid = userDto.userID;
		mine = userid;

		List<Integer> murmur_userid = new ArrayList<Integer>();

		//ユーザのフォローをリスト化
		List<Follow> followResult = followService.findUserFollow(userid);

		if (followResult != null) {
			for (Follow f : followResult) {
				murmur_userid.add(f.fuserid);
			}
		}
		murmur_userid.add(userid);
		//System.out.println(userid);


		String topId = req.getParameter("topId");
		int id = Integer.parseInt(topId);
		if(murmurService.existNewTwit(id, murmur_userid)){

			ResponseUtil.write("<div id='existNewtwit' class='existNewtwit'>新しいつぶやきがあります</div>");
			return null;
		}

		return null;
	}

	@Execute(validator = false)
	public String NewTwitList(){
		int userid = userDto.userID;
		mine = userid;
		fFlag = 1;

		List<Integer> murmur_userid = new ArrayList<Integer>();

		//ユーザのフォローをリスト化
		List<Follow> followResult = followService.findUserFollow(userid);

		if (followResult != null) {
			for (Follow f : followResult) {
				murmur_userid.add(f.fuserid);
			}
		}
		murmur_userid.add(userid);

		String topId = req.getParameter("topId");
		int id = Integer.parseInt(topId);
		System.out.println("入りました" + id + "morokyu");
		murmurList = murmurService.selectNewTwit(id, murmur_userid,userid);

		return "showOldTwit.jsp";
	}

	@Execute(validator = false)
	public String ins_tubuyaki_rep(){

		String topId=req.getParameter("topIdrep");
		String ParentTutuyakiId = req.getParameter("thisId");
		String mur = req.getParameter("tubuyaki");
		setTwitToSolr = new SetTwitToSolr();

		System.out.println(topId + ParentTutuyakiId + mur + "もろきゅう");

		int id = Integer.parseInt(topId);
		String[] hashArray;
		int userid = userDto.userID;
		mine = userid;
		Murmur murmur = new Murmur();

		if (mur == null) {
			throw new ActionMessagesException("なにか入力してください", false);
		}

		//hashリスト
		MakeHashList makehashlist = new MakeHashList();

		hashArray = makehashlist.makehash(mur);

		//新しいつぶやきをデータベースに格納する
		murmur.userid = userid;
		murmur.murmur = mur;
		murmur.dateTime = null;
		murmur.beforeid = Integer.parseInt(ParentTutuyakiId);
		int newMurmurId = murmurService.insertMurmur(murmur);
		System.out.println(newMurmurId);

		//自分の最新のつぶやきを更新する
		Tuser tuser = tuserService.findById(userid);
		tuser.newMur = mur;
		tuser.postNum += 1;
		tuser.newMurD = null;
		tuserService.updateTuserAfterTwit(tuser);

		//solrに登録する
		Murmur murmurForSolr = murmurService.findById(newMurmurId);

		if(murmurForSolr == null){
			System.out.println("nullだよnullだよ");
		}

		if(hashArray==null){
			setTwitToSolr.setTwit(murmurForSolr, null);

		}else{
			setTwitToSolr.setTwit(murmurForSolr, hashArray);
		}

		//リストの更新
		List<Integer> murmur_userid = new ArrayList<Integer>();

		//ユーザのフォローをリスト化
		List<Follow> followResult = followService.findUserFollow(userid);

		if (followResult != null) {
			for (Follow f : followResult) {
				murmur_userid.add(f.fuserid);
			}
		}
		murmur_userid.add(userid);

		//出力リスト
		murmurList = murmurService.selectNewTwit(id, murmur_userid,userid);

		return "main";
	}

	@Execute(validator = false)
	public String ajaxSubmit(){

		setTwitToSolr = new SetTwitToSolr();
		String[] hashArray;
		List<String> hashList = new ArrayList<String>();
		int userid = userDto.userID;
		mine = userid;
		Murmur murmur = new Murmur();

		//Formに渡されたつぶやきを格納する変数
		String mur = req.getParameter("tubuyaki");
		//GPS情報
		String Location = req.getParameter("Location");
		String topId=req.getParameter("topId");
		int id = Integer.parseInt(topId);
		String latitude = req.getParameter("Latitude");
		String longitude = req.getParameter("Longitude");
		murmur.gpslatitude = (double) 0;
		murmur.gpslongitude =(double) 0;
		if(!(latitude.isEmpty() || longitude.isEmpty())){

			ChangeLocationNum changeL = new ChangeLocationNum();

			//gpsのロケーション情報を格納する
			murmur.gpslatitude = changeL.changetoDouble(latitude);
			murmur.gpslongitude = changeL.changetoDouble(longitude);
			murmur.gpslocation = Location;

		}


		//登録済みかユーザチェック
		if (mur == null) {
			throw new ActionMessagesException("なにか入力してください", false);
		}

		String hash ="[　|\\s](#)([a-zA-Zあ-んア-ン_]+)";
		Pattern p = Pattern.compile(hash);
		Matcher matcher = p.matcher(mur);

			while (matcher.find()) {

				for (int i = 0; i <= matcher.groupCount(); i++) {
					System.out.println(matcher.group(i));
				}
				hashList.add(matcher.group(2));
			}

			hashArray = new String[hashList.size()];

		if (hashList.size() != 0) {
			for (int i = 0; i < hashList.size(); i++) {
				hashArray[i] = hashList.get(i);
			}
		}
		for(int i =0; i<hashArray.length;i++){
			System.out.println(hashArray[i]);
		}


		//新しいつぶやきをデータベースに格納する
		murmur.userid = userid;
		murmur.murmur = mur;
		murmur.dateTime = null;
		int newMurmurId = murmurService.insertMurmur(murmur);

		//自分の最新のつぶやきを更新する
		Tuser tuser = tuserService.findById(userid);
		tuser.newMur = mur;
		tuser.postNum += 1;
		tuser.newMurD = null;
		tuserService.updateTuserAfterTwit(tuser);

		//solrに登録する
		murmur = murmurService.findById(newMurmurId);
		setTwitToSolr.setTwit(murmur, hashArray);

		//リストの更新
		List<Integer> murmur_userid = new ArrayList<Integer>();

		//ユーザのフォローをリスト化
		List<Follow> followResult = followService.findUserFollow(userid);

		if (followResult != null) {
			for (Follow f : followResult) {
				murmur_userid.add(f.fuserid);
			}
		}
		murmur_userid.add(userid);

		//出力リスト
		murmurList = murmurService.selectNewTwit(id, murmur_userid,userid);

		return "showOldTwit.jsp";
	}

	//お気に入りに登録する
	@Execute(validator = false)
	public String doFavorite(){
		String murmurid = req.getParameter("murmurid");
		int murmuridInt = Integer.parseInt(murmurid);
		Murmur murmur = new Murmur();
		Favolite favolite = new Favolite();
		int userid = userDto.userID;

		//murmurServiceのお気に入りフラグ
		murmur.murmurid = murmuridInt;
		murmur.favoritenum = + 1;
		murmurService.updateFavoriteFlagTrue(murmur);

		//favoriteTableに挿入する
		favolite.murmurid = murmuridInt;
		favolite.userid = userid;
		favoliteService.insert(favolite);

		return null;
	}

	//お気に入りから外す
	@Execute(validator = false)
	public String canselFavorite(){
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
		if(!favoliteList.isEmpty()){
			favoliteService.deleteById(favoliteList);
		}
		return null;
	}

	//お気に入りリストを返そう
	@Execute(validator = false)
	public String BeFavorite(){

		return null;
	}

	//我お気に入りしているか
	public String userFavorite(){

		return null;
	}

	//retwitをする
	@Execute(validator = false)
	public String Retwit(){
		return null;
	}

	//retwitを取り消す
	@Execute(validator = false)
	public String canselRetwit(){

		return null;
	}

	//retwitされているのか,リツイートリストを返そう
	@Execute(validator = false)
	public String BeRetwited(){
		return null;
	}

	//返信リストを返すafter
	@Execute(validator = false)
	public String repListAfter(){
		int userid = userDto.userID;
		String murId = req.getParameter("tubuyakiId");
		System.out.println("afterはいったよ" + murId);

		List<Murmur> murmur = murmurService.zibunJoinAfterList(Integer.parseInt(murId) , userid);
		if(murmur == null){
			System.out.println("値がないよ");
			return null;
		}
		if(murmur.isEmpty()){
			System.out.println("値がないよ");
			return null;
		}

		murmurList = murmur;

		return "twitplus.jsp";
	}

	//返信リストを返すbefore
	@Execute(validator = false)
	public String repListBefore(){
		int userid = userDto.userID;
		String murId = req.getParameter("tubuyakiId");
		System.out.println(murId);
		List<Murmur> murmur = murmurService.zibunJoinBeforeList(Integer.parseInt(murId), userid);

		if(murmur.isEmpty()){
			return null;
		}
		murmurList = murmur;

		return "twitplus.jsp";
	}


	//ユーザ個々のつぶやきを表示する
	@Execute(validator = false, urlPattern = "userpage/{userni}")
	public String showdata() {

		String nick = mainForm.userni;

		return "/userpage?userni="+ nick +"?redirect=true";

	}

	//ユーザプロフィール画像
	@Execute(validator=false, urlPattern="showUserImg/{userid}")
	public String showUserImg(){
		int id = mainForm.userid;
		Tuser userImg = tuserService.getTuserImg(id);
		String filename = userImg.userid+".jpg";
		if(userImg != null){
			ResponseUtil.download(filename, userImg.profileimg);
		}

		return null;
	}


	//ユーザプロフィール画像
	@Execute(validator=false)
	public String showUserImgSetting(){
		int id = userDto.userID;
		Tuser userImg = tuserService.getTuserImg(id);
		String filename = userImg.userid+".jpg";
		if(userImg != null){
			ResponseUtil.download(filename, userImg.profileimg);
		}

		return null;
	}

//	//ユーザ情報を新しいウィンドウで開く
//	public Tuser user_window;
//	public List<Murmur> newWindoTwit = new ArrayList<Murmur>();
//
//	//newWindows method
//	@Execute(validator = false, urlPattern = "newwindow/{userid1}")
//	public String newwindow() {
//
//		//newWindow user_data
//		user_window = tuserService.findById(mainForm.userid1);
//
//		//newWindo user_data twit(3)
//		newWindoTwit = murmurService.listPager(3, 0, mainForm.userid1);
//
//		return "window.jsp";
//	}

	//検索ユーザを代入するとこだよ
	public List<Tuser> tuserList = new ArrayList<Tuser>();

	@Execute(validator = false)
	public String searchUsershort(){
		String searchUser = req.getParameter("searchword");
		System.out.println(searchUser+ "もきゅ");
		int userid = userDto.userID;

		tuserList = tuserService.tuserSearch(searchUser, userid, 3, 0);
		if(tuserList.isEmpty()){
			System.out.println("検索結果なかったよ…");
		}

		return "searchUser.jsp";
	}

	@Execute(validator = false , urlPattern="search/{searchUser}")
	public String searchUserAll(){
		String searchUsernick = mainForm.searchUser;

		return "/search?search="+ searchUsernick +"?redirect=true";
	}

	//フォローユーザへ
	@Execute(validator = false, urlPattern="followlist/{userid}")
	public String toFollowPage(){
		int id = mainForm.userid;

		return "/followlist?id=" + id + "?redirect=true";
	}

	//フォロワーユーザへ
	@Execute(validator  = false, urlPattern="followedlist/{userid}")
	public String toBefollowedPage(){
		int id = mainForm.userid;

		return "/followlist/followedlist?id=" + id + "?redirect=true";
	}


	//
	@Execute(validator=false)
	public String repform(){
		return "repform.jsp";
	}
//
//	//solrを検索する
	@Execute(validator = false)
	public String searchAll(){
		menuFlag = 1;
		int id;
		mine = userDto.userID;

		List<Integer> idList = new ArrayList<Integer>();
		userDto.searchWord = mainForm.searchWord;
		if (mainForm.searchWord == null) {
			throw new ActionMessagesException("なにか入力してください", false);
		}

		GetTwitFromSolr getTwitFromSolr = new GetTwitFromSolr();
		searchDto = new ArrayList<SearchDto>();
		searchDto = getTwitFromSolr.getAllTwit(mainForm.searchWord,0);
		if(!searchDto.isEmpty()){
			for (int i = 0; i < searchDto.size(); i++) {
				id = Integer.valueOf(searchDto.get(i).getId());
				idList.add(id);
			}
			murmurList = murmurService.SelectListSearch(idList);
		}

		return "searchTwit.jsp";
	}

	@Execute(validator = false)
	public String searchAjax(){

		fFlag = 1;
		//System.out.println("morokyumain");
		int id;
		String pagePram = req.getParameter("page");
		int page = Integer.valueOf(pagePram);

		List<Integer> idList = new ArrayList<Integer>();

		GetTwitFromSolr getTwitFromSolr = new GetTwitFromSolr();
		searchDto = new ArrayList<SearchDto>();
		searchDto = getTwitFromSolr.getAllTwit(userDto.searchWord, page*10);

		if(!searchDto.isEmpty()){
			for (int i = 0; i < searchDto.size(); i++) {
				id = Integer.valueOf(searchDto.get(i).getId());
				idList.add(id);
			}
			murmurList = murmurService.SelectListSearch(idList);
		}

		return "showOldTwit.jsp";
	}

	//hashタグリストを出力する
	@Execute(validator = false, urlPattern="showHashData/{hashtag}")
	public String showHashData(){
		menuFlag=1;
		int id;
		String hash = mainForm.hashtag;
		List<Integer> idList = new ArrayList<Integer>();
		mine = userDto.userID;


		GetTwitFromSolr getTwitFromSolr = new GetTwitFromSolr();
		searchDto = new ArrayList<SearchDto>();
		searchDto = getTwitFromSolr.getHashTwit(hash);

		for(int i=0; i<searchDto.size(); i++){
			id = Integer.valueOf(searchDto.get(i).getId());
			idList.add(id);
		}

		murmurList = murmurService.SelectListSearch(idList);

		return mainPageJsp;
	}
//
//	@Execute(validator = false, urlPattern ="userpage/{userid}")
//	public String toUserpage(){
//		int id = mainForm.userid;
//
//		return "/userpage?userid="+ id;
//	}

}
