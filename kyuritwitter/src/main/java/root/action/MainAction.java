package root.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javasource.GetTwitFromSolr;
import javasource.SetTwitToSolr;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;
import org.seasar.struts.util.ResponseUtil;

import root.SuperAction;
import root.dto.SearchDto;
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

	public List<SearchDto> searchDto;

	@Execute(validator = false)
	public String index() {
		return "main";
	}

	private SetTwitToSolr setTwitToSolr;

	//jspファイルに渡すつぶやきリストを格納する変数
	public List<Murmur> murmurList = new ArrayList<Murmur>();

	//メインページ表示メソッド(自分のつぶやき＋他人のつぶやき
	@Execute(validator = false, urlPattern = "main")
	public String main() throws IOException {
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		BufferedOutputStream os = new BufferedOutputStream(bos);


		int userid = userDto.userID;
		mine = userid;

//		String newPath = application.getRealPath("/img/profileImg/" + userDto.userID + ".jpg");

		//ユーザ自身のデータ
		mydata = tuserService.findById(userid);
		//userProfileImg
		//byte[] profileImg = tuserService.getTuserImg(userid).profileimg;
		//System.out.println("kyuuri" + profileImg);

//		InputStream is = new ByteArrayInputStream(profileImg);
//		File outputImageFile = new File(newPath);

//		try {
//			os = new BufferedOutputStream(new FileOutputStream(outputImageFile));
//			int c;
//			while ((c = is.read()) != -1) {
//				os.write(c);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (os != null) {
//				try {
//					os.flush();
//					os.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}

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
		murmurList = murmurService.mainListPager(LIMIT, page, murmur_userid);
		//前ページがあるかどうかを判定
		hasPrev = murmurService.hasPrev(page);
		//次のページがあるかどうかを判定
		hasNext = murmurService.hasNext(LIMIT, this.total, page);

		return mainPageJsp;
	}

	//つぶやくメソッド
	@Execute(validator = true, input = "main")
	public String ins_tubuyaki() {
		setTwitToSolr = new SetTwitToSolr();

		//Formに渡されたつぶやきを格納する変数
		String mur = mainForm.tubuyaki;
		//登録済みかユーザチェック
		if (mur == null) {
			throw new ActionMessagesException("なにか入力してください", false);
		}

		System.out.println(mur);

		int userid = userDto.userID;

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
		System.out.println(newMurmurId + "moro");
		setTwitToSolr.setTwit(murmur, null);


		return "main";
	}

	//ユーザ個々のつぶやきを表示する
	@Execute(validator = false, urlPattern = "showdata/{userni}")
	public String showdata() {

		fFlag = 1;

		int userid = userDto.userID;
		mine = userid;

		//ページ番号の取得
		int page = IntegerConversionUtil.toPrimitiveInt(this.mainForm.page);

		//選択されたユーザ自身のデータ
		mydata = tuserService.findByName(mainForm.userni);

		if (mydata == null) {
			fFlag = 0;
			return "/main";
		}

		//ページング
		//総件数を取得
		this.total = murmurService.Count(mydata.userid);

		murmurList = murmurService.listPager(LIMIT, page, mydata.userid);

		//前ページがあるかどうかを判定
		hasPrev = murmurService.hasPrev(page);
		//次のページがあるかどうかを判定
		hasNext = murmurService.hasNext(LIMIT, total, page);

		return mainPageJsp;

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

	//ユーザ情報を新しいウィンドウで開く
	public Tuser user_window;
	public List<Murmur> newWindoTwit = new ArrayList<Murmur>();

	//newWindows method
	@Execute(validator = false, urlPattern = "newwindow/{userid1}")
	public String newwindow() {

		//newWindow user_data
		user_window = tuserService.findById(mainForm.userid1);

		//newWindo user_data twit(3)
		newWindoTwit = murmurService.listPager(3, 0, mainForm.userid1);

		return "window.jsp";
	}

	@Execute(validator = false)
	public String searchAll(){
		System.out.println("moro"+ mainForm.searchWord);
		GetTwitFromSolr getTwitFromSolr = new GetTwitFromSolr();
		searchDto = new ArrayList<SearchDto>();
		searchDto = getTwitFromSolr.getAllTwit(mainForm.searchWord);
		for(int i =0 ; i<searchDto.size();i++){
			System.out.println(searchDto.get(i).getTwit());
		}
		return mainPageJsp;
	}

}
