package root.action;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javasource.ChangeLocationNum;
import javasource.GetFacetFromSolr;
import javasource.GetTwitFromSolr;
import javasource.MakeHashList;
import javasource.MakeImgType;
import javasource.SetTwitToSolr;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.imageio.ImageIO;
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
import root.entity.Retweets;
import root.entity.Tuser;
import root.form.MainForm;


public class MainAction extends SuperAction {

	private final String mainPageJsp = "mainpage.jsp";

	public ServletContext application;

	@ActionForm
	@Resource
	protected MainForm mainForm;

	@Resource
	HttpServletRequest req;

	//jspファイルに渡すつぶやきリストを格納する変数
	public List<Murmur> murmurList = new ArrayList<Murmur>();

	//トレンドリストを格納
	public List<String> trandList  = new ArrayList<String>();

	//おすすめユーザの格納
	public List<Tuser> recommendUserNull = new ArrayList<Tuser>();
	public List<Follow> recommendUser = new ArrayList<Follow>();

	public Tuser mydata = new Tuser();

	public int fFlag = 0;

	private SetTwitToSolr setTwitToSolr;

	public int retNum;
	public int favoNum;
	public int murid;

	// JdbcManagerのインスタンスを取得
	JdbcManager jdbcManager = SingletonS2Container.getComponent("jdbcManager");

	public List<SearchDto> searchDto;

	@Execute(validator = false, urlPattern = "main")
	public String index() {
		return "main";
	}

	//メインページ表示メソッド(自分のつぶやき＋他人のつぶやき

	@Execute(validator = false, urlPattern = "main")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String main() {
		int userid = userDto.userID;
		mine = userid;

//		List<Follow> recommendList = new ArrayList<Follow>();

		GetFacetFromSolr getFacetFromSolr = new GetFacetFromSolr();

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

		//おすすめユーザの出力
		recommendUser = followService.recommendUser(murmur_userid, userid);

		//自分自身の番号を加える
		murmur_userid.add(userid);

		if(recommendUser == null){
			System.out.println("moromoro");
			recommendUserNull = tuserService.recommend(murmur_userid);
		}

//		recommendList = followService.recommendUser(murmur_userid);
//
//		if(recommendList != null){
//			for(Follow f : recommendList){
//				if(!(recommendUser.containsKey(f.ftuser.usernick))){
//					ArrayList<String> valueList = new ArrayList<String>();
//					valueList.add(f.tuser.usernick);
//
//					recommendUser.put(f.ftuser.usernick, valueList);
//				}
//			}
//		}

//		this.total = murmurService.listCount(murmur_userid);

		trandList = getFacetFromSolr.makeTrand();

		murmurList = murmurService.mainListPager(LIMIT, page, murmur_userid, userid);

		return mainPageJsp;
	}

	//ajaxload
	@Execute(validator = false)
	public String loadOldTwit() {
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
	@Execute(validator = false)
	public String insertTwit() {
		int userid = userDto.userID;

		String[] hashArray;

		setTwitToSolr = new SetTwitToSolr();

		List<String> hashList = new ArrayList<String>();

		//Formに渡されたつぶやきを格納する変数
		String mur = mainForm.tubuyaki;
		//登録済みかユーザチェック
		if (mur == null) {
			throw new ActionMessagesException("なにか入力してください", false);
		}

		String hash = "[　|\\s](#)([a-zA-Zあ-んア-ン_]+)";
		Pattern p = Pattern.compile(hash);
		Matcher matcher = p.matcher(mur);

		while (matcher.find()) {
			hashList.add(matcher.group(2));
		}

		hashArray = new String[hashList.size()];

		if (hashList.size() != 0) {
			for (int i = 0; i < hashList.size(); i++) {
				hashArray[i] = hashList.get(i);
			}
		}
		for (int i = 0; i < hashArray.length; i++) {
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
	public String checkNewTwit() {

		int id=0;
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
		if(topId != null){
			id = Integer.parseInt(topId);

			if (murmurService.existNewTwit(id, murmur_userid)) {
				ResponseUtil.write("<div id='existNewtwit' class='existNewtwit'>新しいつぶやきがあります</div>");
				return null;
			}

		}else if(murmurService.existNewTwitForNotwit(murmur_userid)){
			ResponseUtil.write("<div id='existNewtwit' class='existNewtwit'>新しいつぶやきがあります</div>");
		}

		return null;
	}

	@Execute(validator = false)
	public String NewTwitList() {
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
		murmurList = murmurService.selectNewTwit(id, murmur_userid, userid);

		return "showOldTwit.jsp";
	}

	@Execute(validator = false)
	public String ins_tubuyaki_rep() {

		String topId = req.getParameter("topIdrep");
		String ParentTutuyakiId = req.getParameter("thisId");
		String mur = req.getParameter("tubuyaki");
		setTwitToSolr = new SetTwitToSolr();

		int ParentTutuyakiIdInt = Integer.parseInt(ParentTutuyakiId);

		Murmur checkMur = murmurService.findById(ParentTutuyakiIdInt);
		//もし、リツイートされたつぶやきだったら、リツイート元のidを取ってくる
		if(checkMur.retwitflag != null){
			ParentTutuyakiIdInt = checkMur.retwitflag;
		}
//		System.out.println(topId + ParentTutuyakiId + mur + "もろきゅう");

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
		murmur.beforeid = ParentTutuyakiIdInt;
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

		if (murmurForSolr == null) {
			System.out.println("nullだよnullだよ");
		}

		if (hashArray == null) {
			setTwitToSolr.setTwit(murmurForSolr, null);

		} else {
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
		murmurList = murmurService.selectNewTwit(id, murmur_userid, userid);

		return "main";
	}

	@Execute(validator = false)
	public String ajaxSubmit() {

		int id = 0;
		setTwitToSolr = new SetTwitToSolr();
		String[] hashArray;
		List<String> hashList = new ArrayList<String>();
		int userid = userDto.userID;
		mine = userid;
		Murmur murmur = new Murmur();
		String topId  = null;

		//Formに渡されたつぶやきを格納する変数
		String mur       = req.getParameter("tubuyaki");
		//GPS情報
		String Location  = req.getParameter("Location");
		       topId     = req.getParameter("topId");
		String latitude  = req.getParameter("Latitude");
		String longitude = req.getParameter("Longitude");
		String imgurl    = req.getParameter("imgurl");

		if(topId != null){
			id = Integer.parseInt(topId);
		}

		murmur.gpslatitude  = (double) 0;
		murmur.gpslongitude = (double) 0;
		if (!(latitude.isEmpty() || longitude.isEmpty())) {

			ChangeLocationNum changeL = new ChangeLocationNum();

			//gpsのロケーション情報を格納する
			murmur.gpslatitude = changeL.changetoDouble(latitude);
			murmur.gpslongitude = changeL.changetoDouble(longitude);
			murmur.gpslocation = Location;

		}

		//画像情報を格納する
		if(!(imgurl.isEmpty())){
			murmur.imageurl = imgurl;
		}


		//登録済みかユーザチェック
		if (mur == null) {
			throw new ActionMessagesException("なにか入力してください", false);
		}

		String hash = "[　|\\s](#)([a-zA-Zあ-んア-ン_]+)";
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
//		for (int i = 0; i < hashArray.length; i++) {
//			System.out.println(hashArray[i]);
//		}

		//新しいつぶやきをデータベースに格納する
		murmur.userid = userid;
		murmur.murmur = mur;
		murmur.dateTime = null;
		murmur.favoritenum=0;
//		murmur.retwitflag = 0;
//		murmur.beRetwitednum = 0;
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
		if(id != 0){
			murmurList = murmurService.selectNewTwit(id, murmur_userid, userid);
		}else{
			murmurList = murmurService.selectNewTwitFirstTwit(murmur_userid, userid);
		}

		return "showOldTwit.jsp";
	}

	public String imgShowUrl;

	//ajaxで画像をアップロードするメソッドだよ
	@Execute(validator = false)
	public String imagePost() throws IOException {

		String fileName = mainForm.imageFile.getFileName();
		//		String fileInfoJson = null;

		InputStream fileData = this.mainForm.imageFile.getInputStream();
		BufferedInputStream fileDataBuffer = new BufferedInputStream(fileData);

		String path = application.getRealPath("/img/tempoImg/" + fileName);
		OutputStream profileImgPath = new FileOutputStream(path);
		BufferedOutputStream outBuf = new BufferedOutputStream(profileImgPath);

		int contents;
		while ((contents = fileDataBuffer.read()) != -1) {
			outBuf.write(contents);
		}

		outBuf.flush();
		outBuf.close();
		fileDataBuffer.close();

		/** ---- 一時的なファイルに書き込むのはここまで ---- */

		BufferedImage sourceImage = ImageIO.read(new File(path));

		MakeImgType mit = new MakeImgType();
		String LocalImgShowUrl = mit.makeImgName();

		String newPath = application.getRealPath("/img/twitImg/" + LocalImgShowUrl + ".jpg");

		try {
			OutputStream out = new FileOutputStream(newPath);
			ImageIO.write(sourceImage, "jpg", out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		File delFile = new File(path);
		delFile.delete();

		imgShowUrl = LocalImgShowUrl + ".jpg";

		return "frameImg.jsp";
	}

	//画像を消去しちゃうぞ
	@Execute(validator = false)
	public String deleteImg(){

		String url = req.getParameter("deleteurl");
		url = application.getRealPath("/img/twitImg/" + url);
		File file = new File(url);

		if(file.exists()){
			file.delete();
		}else {
			System.out.println("この画像は存在しないんだ");
		}

		return null;
	}

	//お気に入りに登録する
	@Execute(validator = false)
	public String doFavorite() {
		String murmurid = req.getParameter("murmurid");
		int murmuridInt = Integer.parseInt(murmurid);
		Murmur murmur = new Murmur();
		Favolite favolite = new Favolite();
		int userid = userDto.userID;

		murmur = murmurService.findById(murmuridInt);
		if(murmur.retwitflag != null){
			murmuridInt = murmur.retwitflag;
		}

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

	//お気に入りリストを返そう
	@Execute(validator = false)
	public String BeFavorite() {

		return null;
	}

	//retwitをする
	@Execute(validator = false)
	public String Retwit() {

		String murmurid = req.getParameter("murmurid");
		int murmuridInt = Integer.parseInt(murmurid);
		int userid = userDto.userID;

		if(retweetsService.existRetweetNum(userid, murmuridInt)){
			 throw new Error("すでにリツイートしています");
		}

		Murmur mur = murmurService.findById(murmuridInt);

		Murmur retMur = new Murmur();

		retMur.userid       = mur.userid;
		retMur.murmur       = mur.murmur;
		retMur.dateTime     = mur.dateTime;
		retMur.imageurl     = mur.imageurl;
		retMur.gpslatitude  = mur.gpslatitude;
		retMur.gpslongitude = mur.gpslongitude;
		retMur.gpslocation  = mur.gpslocation;
		//リツイートということを表すフラグ
		retMur.retwitflag   = mur.murmurid;
		retMur.retweetuser = userid;

		murmurService.insert(retMur);

		Retweets retweet = new Retweets();
		retweet.murmurid = murmuridInt;
		retweet.userid  = userid;

		retweetsService.insert(retweet);

		return null;
	}

	//retwitを取り消す
	@Execute(validator = false)
	public String canselRetwit() {

		String murmurid = req.getParameter("murmurid");
		int murmuridInt = Integer.parseInt(murmurid);
		int userid = userDto.userID;

		Murmur mur = murmurService.findById(murmuridInt);
		if(mur.retwitflag == null){
			System.out.println(mur.retwitflag);
			//リツイートしている行を抜き出す
			mur = murmurService.findUserRetweetId(mur.murmurid, userid);
		}

		if(mur == null){
			 throw new Error("値がおかしいです");
		}

		if(!(retweetsService.existRetweetNum(userid, mur.retwitflag))){
			System.out.println(userid + " " + mur.murmurid + "moromoromorokyuri");
			 throw new Error("リツイートしていません");
		}

		Retweets retweet = new Retweets();
		retweet = retweetsService.findRetweetNumForDel(userid, mur.retwitflag);
		retweetsService.delete(retweet);

		murmurService.delete(mur);

		ResponseUtil.write("#"+mur.murmurid);

		return null;

	}

	//お気に入り,リツイートリストを返そう
	@Execute(validator = false)
	public String BeRetwited() {
		String murmuridStr = req.getParameter("tubuyakiId");
		int murmurid = Integer.parseInt(murmuridStr);
		Murmur mur = murmurService.findById(murmurid);

		if(mur.retwitflag!=null){
			murmurid = mur.retwitflag;
		}
		murid = murmurid;

		favoNum = (int) favoliteService.findFavoNum(murmurid);
		retNum  = (int) retweetsService.findRetweetNum(murmurid);
		if(favoNum == 0 && retNum == 0){
			return null;
		}
//		System.out.println(favoNum + " " + retNum + "kyuri");

		return "retAndFavoInfo.jsp";
	}

	//返信リストを返すafter
	@Execute(validator = false)
	public String repListAfter() {
		int userid = userDto.userID;
		String murId = req.getParameter("tubuyakiId");
		int murIdInt = Integer.parseInt(murId);
		Murmur mur = new Murmur();

		mur = murmurService.findById(murIdInt);

		if(mur.retwitflag != null){
			mur = murmurService.findById(mur.retwitflag);
			murIdInt = mur.murmurid;
		}

		List<Murmur> murmur = murmurService.zibunJoinAfterList(murIdInt, userid);


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
		int murIdInt = Integer.parseInt(murId);

		Murmur mur = new Murmur();

		mur = murmurService.findById(murIdInt);

		if(mur.retwitflag != null){
			mur = murmurService.findById(mur.retwitflag);
			murIdInt = mur.murmurid;
		}

		List<Murmur> murmur = murmurService.zibunJoinBeforeList(murIdInt, userid);

		if (murmur.isEmpty()) {
			return null;
		}
		murmurList = murmur;

		return "twitplus.jsp";
	}

	//ユーザ個々のつぶやきを表示する
	@Execute(validator = false, urlPattern = "userpage/{userni}")
	public String showdata() {

		String nick = mainForm.userni;

		return "/userpage?userni=" + nick + "?redirect=true";
	}

	//ユーザプロフィール画像
	@Execute(validator = false, urlPattern = "showUserImg/{userid}")
	public String showUserImg() {
		int id = mainForm.userid;
		Tuser userImg = tuserService.getTuserImg(id);
		String filename = userImg.userid + ".jpg";
		if (userImg != null) {
			ResponseUtil.download(filename, userImg.profileimg);
		}
		return null;
	}

	//ユーザプロフィール画像
	@Execute(validator = false)
	public String showUserImgSetting() {
		int id = userDto.userID;
		Tuser userImg = tuserService.getTuserImg(id);
		String filename = userImg.userid + ".jpg";
		if (userImg != null) {
			ResponseUtil.download(filename, userImg.profileimg);
		}
		return null;
	}

	//ツイート画像を表示する
	@Execute(validator = false)
	public String showTwitImg(){
		String imgUrl = req.getParameter("imgurl");
		imgShowUrl = imgUrl;

		return "ImgModal.jsp";
	}

	//検索ユーザを代入するとこだよ
	public List<Tuser> tuserList = new ArrayList<Tuser>();

	@Execute(validator = false)
	public String searchUsershort() {
		String searchUser = req.getParameter("searchword");

		int userid = userDto.userID;

		tuserList = tuserService.tuserSearch(searchUser, userid, 3, 0);
		if (tuserList.isEmpty()) {
		}

		return "searchUser.jsp";
	}

	@Execute(validator = false, urlPattern = "search/{searchUser}")
	public String searchUserAll() {
		String searchUsernick = mainForm.searchUser;

		return "/search?search=" + searchUsernick + "?redirect=true";
	}

	//フォローユーザへ
	@Execute(validator = false, urlPattern = "followlist/{userid}/{followOrUnfollow}")
	public String toFollowPage() {
		int id = mainForm.userid;
		String followFlag = mainForm.followOrUnfollow;
		System.out.println("moromoro" + followFlag);

		return "/followlist?id=" + id + "&followOrUnfollow=" + followFlag + "?redirect=true";
	}

	//フォロワーユーザへ
	@Execute(validator = false, urlPattern = "followedlist/{userid}/{followOrUnfollow}")
	public String toBefollowedPage() {
		int id = mainForm.userid;
		String followFlag = mainForm.followOrUnfollow;
		System.out.println("moromoro" + followFlag);

		return "/followlist?id=" + id + "&followOrUnfollow=" + followFlag + "?redirect=true";
	}

	//
	@Execute(validator = false)
	public String repform() {
		return "repform.jsp";
	}

	//solrを検索する
	@Execute(validator = false)
	public String searchAll() {

		String searchWord = mainForm.searchWord;
		userDto.searchWord = searchWord;

		return "/searchtwit?redirect=true";
	}

	@Execute(validator = false)
	public String searchAjax() {

		int userid = userDto.userID;

		fFlag = 1;
		//System.out.println("morokyumain");
		int id;
		String pagePram = req.getParameter("page");
		int page = Integer.valueOf(pagePram);

		List<Integer> idList = new ArrayList<Integer>();

		GetTwitFromSolr getTwitFromSolr = new GetTwitFromSolr();
		searchDto = new ArrayList<SearchDto>();
		searchDto = getTwitFromSolr.getAllTwit(userDto.searchWord, page * 10);

		if (!searchDto.isEmpty()) {
			for (int i = 0; i < searchDto.size(); i++) {
				id = Integer.valueOf(searchDto.get(i).getId());
				idList.add(id);
			}
			murmurList = murmurService.SelectListSearch(idList,userid);
		}

		return "showOldTwit.jsp";
	}

	//hashタグリストを出力する
	@Execute(validator = false, urlPattern = "showHashData/{hashtag}")
	public String showHashData() {
		int userid = userDto.userID;
		menuFlag = 1;
		int id;
		String hash = mainForm.hashtag;
		List<Integer> idList = new ArrayList<Integer>();
		mine = userid;

		GetTwitFromSolr getTwitFromSolr = new GetTwitFromSolr();
		searchDto = new ArrayList<SearchDto>();
		searchDto = getTwitFromSolr.getHashTwit(hash);

		for (int i = 0; i < searchDto.size(); i++) {
			id = Integer.valueOf(searchDto.get(i).getId());
			idList.add(id);
		}

		murmurList = murmurService.SelectListSearch(idList, userid);

		return "hashpage.jsp";
	}

}
