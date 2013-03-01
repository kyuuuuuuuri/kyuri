package root.action;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javasource.passwordhash;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;
import org.seasar.struts.util.ResponseUtil;

import root.SuperAction;
import root.entity.Follow;
import root.entity.InListUser;
import root.entity.ListFollow;
import root.entity.Murmur;
import root.entity.Tlist;
import root.entity.Tuser;
import root.form.SettingForm;

public class SettingAction extends SuperAction {

	@ActionForm
	@Resource
	protected SettingForm settingForm;

	public ServletContext application;

	private final int RESIZE_INT = 200;

	public Tuser mydata = new Tuser();

	public List<ListFollow> listFollow = new ArrayList<ListFollow>();

	public List<Murmur> murmurList = new ArrayList<Murmur>();

	public List<Follow> followList = new ArrayList<Follow>();

	public Tlist tlist = new Tlist();

	@Resource
	HttpServletRequest req;

	@Execute(validator = false, urlPattern = "setting")
	public String index() {
		int userid = userDto.userID;
		mine = userid;

		mydata = tuserService.findById(userid);

		return "changeUser.jsp";
	}

	//upload file
	@Execute(validator = false)
	public String upload() throws FileNotFoundException, IOException {
		int userid = userDto.userID;
		mine = userid;

		String filename = this.settingForm.file.getFileName();
		InputStream fileData = this.settingForm.file.getInputStream();
		BufferedInputStream fileDataBuffer = new BufferedInputStream(fileData);

		String path = application.getRealPath("/img/profileImg/" + filename);
		OutputStream profileImgPath = new FileOutputStream(path);
		BufferedOutputStream outBuf = new BufferedOutputStream(profileImgPath);

		int contents;
		while ((contents = fileDataBuffer.read()) != -1) {
			outBuf.write(contents);
		}

		outBuf.flush();
		outBuf.close();
		fileDataBuffer.close();

		resize(path);

		return "changeImg.jsp";
	}

	public void resize(String filePath) throws IOException {
		BufferedImage sourceImage = ImageIO.read(new File(filePath));
		int height = sourceImage.getHeight();
		int width = sourceImage.getWidth();
		double resizeHeight = 0;
		double resizeWidth = 0;
		BufferedImage resizeImage;
		BufferedImage subImage = null;

		AffineTransformOp ato = null;
		//String newPath = application.getRealPath("/img/profileImg/" + userDto.userID+".jpg");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BufferedOutputStream os = new BufferedOutputStream(bos);

		if (height > RESIZE_INT && width > RESIZE_INT) {
			if (height > width) {
				resizeWidth = RESIZE_INT;
				resizeHeight = (int) ((double) RESIZE_INT / width * (double) height);
			} else {
				resizeHeight = RESIZE_INT;
				resizeWidth = (int) ((double) RESIZE_INT / height * (double) width);
			}

			//リサイズ処理
			resizeImage = new BufferedImage(width, height, sourceImage.getType());
			ato = new AffineTransformOp(
					AffineTransform.getScaleInstance((double) resizeWidth / width,
							(double) resizeHeight / height),
					AffineTransformOp.TYPE_BILINEAR);
			ato.filter(sourceImage, resizeImage);
			//画像の切り取り
			subImage = resizeImage.getSubimage(0, 0, RESIZE_INT, RESIZE_INT);

		} else if (height > RESIZE_INT && width < RESIZE_INT) {
			subImage = sourceImage.getSubimage(0, 0, width, RESIZE_INT);

		} else if (height < RESIZE_INT && width > RESIZE_INT) {
			subImage = sourceImage.getSubimage(0, 0, RESIZE_INT, height);
		} else {
			subImage = sourceImage;
		}

		//jpgに変換
		subImage.flush();
		ImageIO.write(subImage, "jpg", os);
		os.flush();
		os.close();

		//databaseに格納
		byte[] data = bos.toByteArray();
		tuserService.tuserImgUpdate(data, userDto.userID);

		File originImg = new File(filePath);
		if (originImg.exists()) {
			originImg.delete();
		} else {
			System.out.println("ファイルが見つかりません");
		}
	}

	//ユーザプロフィール画像
	@Execute(validator = false)
	public String showUserImgSetting() {
		int userid = userDto.userID;
		mine = userid;
		Tuser userImg = tuserService.getTuserImg(userid);
		String filename = userImg.userid + ".jpg";
		if (userImg != null) {
			ResponseUtil.download(filename, userImg.profileimg);
		}

		return null;
	}

	//	//changenamejsp
	//	@Execute(validator=false)
	//	public String changeUsername(){
	//		mydata = tuserService.findById(userDto.userID);
	//
	//		return "changeUser.jsp";
	//	}

	@Execute(validator = true, input = "changeUser.jsp")
	public String changeUsernameSubmit() {
		String newUserName = settingForm.username;
		int skey = 0;
		int userid = userDto.userID;
		mine = userid;
		//System.out.println("morokyumorokyu" + newUserName);

		if (newUserName.isEmpty()) {
			System.out.println("null");
			throw new ActionMessagesException("新しい名前を入力してください");
		} else {
			if (!(settingForm.keycheck == null)) {
				skey = Integer.parseInt(settingForm.keycheck);
			}

			tuserService.tuserNameUpdate(userDto.userID, newUserName, skey);

		}
		return "changeUser.jsp";
	}

	public String successMsg;

	@Execute(validator = true, input = "changePass.jsp")
	public String changePasswordSubmit() {
		System.out.println("パスワードチェックに入ったよ");
		String oldpass = settingForm.oldpass;
		String newpass = settingForm.newpass;

		int userid = userDto.userID;
		mine = userid;

		Tuser tuser = tuserService.findById(userid);
		passwordhash e = new passwordhash();

		String pass_hash = e.getpassword(oldpass);
		System.out.println(pass_hash + " " + tuser.passWord);

		if (pass_hash.equals(tuser.passWord)) {
			newpass = e.getpassword(newpass);
			tuser.passWord = newpass;
			System.out.println("OKだよ" + newpass);
			successMsg = "パスワードを変更しました";
			tuserService.updatePassWord(tuser);

		} else {
			System.out.println("だめだよ");
			throw new ActionMessagesException("パスワードが正しくありません", false);
		}

		return "changePass.jsp";
	}

	@Execute(validator = false)
	public String checkOldPass() {
		int userid = userDto.userID;
		String checkPass = req.getParameter("oldpass");
		Tuser tuser = tuserService.findById(userid);
		String pass = tuser.passWord;
		mine = userid;

		//パスワード暗号化
		passwordhash e = new passwordhash();
		String pass_hash = e.getpassword(checkPass);
		System.out.println(pass + "morokyu" + pass_hash);

		if (pass == pass_hash) {
			ResponseUtil.write("OK");
//			System.out.println("passOKOK");
		} else {

		}

		return null;
	}

	@Execute(validator = false)
	public String changeUsername() {
		int userid = userDto.userID;
		mine = userid;
		return "changeUser.jsp";
	}

	@Execute(validator = false)
	public String changeUserImg() {
		int userid = userDto.userID;
		mine = userid;
		return "changeImg.jsp";
	}

	@Execute(validator = false)
	public String changePassword() {
		int userid = userDto.userID;
		mine = userid;

		return "changePass.jsp";
	}

	//リストの一覧を返す
	@Execute(validator = false)
	public String makeList() {
		int userid = userDto.userID;
		mine = userid;

		listFollow = listFollowService.findListFollowByUserid(userid);

		return "makeList.jsp";
	}

	//listを作る
	@Execute(validator = true, input = "makeList")
	public String makeListName() {
		int userid = userDto.userID;
		mine = userid;
		System.out.println("makeListに入った");

		String ListName = settingForm.listName;
		String secretFlag = settingForm.keycheck;
		String listDesc = settingForm.listDesc;

		Tlist tlist = new Tlist();
		ListFollow listFollow = new ListFollow();

		tlist.listname = ListName;
		tlist.listdesc = listDesc;
		tlist.userid   = userid;

		if (secretFlag != null) {
			tlist.secretFlag = Integer.parseInt(secretFlag);
		} else {
			tlist.secretFlag = 0;
		}

		int listid = tlistService.insertTlist(tlist);

		listFollow.listid = listid;
		listFollow.followThisUserid = userid;
		listFollowService.insert(listFollow);

		return "makeList";
	}

	//listに登録されているユーザのつぶやきを表示する
	@Execute(validator = false, urlPattern = "showListUser/{listid}")
	public String showListUser() {
		menuFlag = 1;
		int page   = 0;
		int listid = settingForm.listid;
		int userid = userDto.userID;
		mine = userid;

		List<Integer> useridList = new ArrayList<Integer>();
		useridList = inListUserService.findUserInTheList(listid);

		murmurList = murmurService.mainListPager(userid, page, useridList, LIMIT);

		return "mainListpage.jsp";
	}

	//listに登録するユーザを設定するページ
	@Execute(validator = false)
	public String setList() {
		int userid = userDto.userID;
		mine = userid;
		int listid = settingForm.listid;

		tlist = tlistService.findById(listid);

		followList = followService.findFollowListAndMakeList(userid, listid, LIMIT);

		return "setList.jsp";
	}

	//listにユーザを登録する,ajax処理
	@Execute(validator = false)
	public String setUserToList() {
		int userid = userDto.userID;
		mine = userid;

		InListUser inlistuser = new InListUser();

		String listidStr = req.getParameter("listid");
		String listUserIdStr = req.getParameter("userid");
		int listid = Integer.parseInt(listidStr);
		int listUserid = Integer.parseInt(listUserIdStr);


		if(inListUserService.existUserInList(listid, listUserid)){
			 throw new Error("このユーザはすでに登録されています");
		}else{
			inlistuser.listid = listid;
			inlistuser.listUserid = listUserid;
			inListUserService.insert(inlistuser);
		}

		return null;
	}

	//listからユーザを削除,ajax処理
	@Execute(validator = false)
	public String delUserFromList(){

		int userid = userDto.userID;
		mine = userid;

		InListUser inlistuser = new InListUser();

		String listidStr = req.getParameter("listid");
		String listUserIdStr = req.getParameter("userid");
		int listid = Integer.parseInt(listidStr);
		int listUserid = Integer.parseInt(listUserIdStr);


		if(!(inListUserService.existUserInList(listid, listUserid))){
			 throw new Error("登録されていないユーザを削除しようとしています");
		}else{
			inlistuser = inListUserService.delUserInList(listid, listUserid);
			inListUserService.delete(inlistuser);
		}

		return null;
	}

	//ユーザ個々のつぶやきを表示する
	@Execute(validator = false, urlPattern = "userpage/{userni}")
	public String showdata() {

		String nick = settingForm.userni;

		return "/userpage?userni=" + nick + "?redirect=true";
	}


}
