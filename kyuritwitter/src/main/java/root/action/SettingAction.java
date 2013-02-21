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
import root.entity.Tuser;
import root.form.SettingForm;


public class SettingAction extends SuperAction{

	@ActionForm
	@Resource
	protected SettingForm settingForm;

	public ServletContext application;

	private final int RESIZE_INT = 200;

	public Tuser mydata = new Tuser();

	@Execute(validator = false, urlPattern="setting")
	public String index(){
		int userid = userDto.userID;
		mine=userid;

		mydata = tuserService.findById(userid);

		return "changeUser.jsp";
	}


	//upload file
	@Execute(validator = false)
	public String upload() throws FileNotFoundException, IOException{
		int userid = userDto.userID;
		mine=userid;

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

	public void resize(String filePath) throws IOException{
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

		if(height > RESIZE_INT && width > RESIZE_INT){
			if(height > width){
				resizeWidth = RESIZE_INT;
				resizeHeight = (int)((double)RESIZE_INT / width * (double)height);
			}else{
				resizeHeight = RESIZE_INT;
				resizeWidth = (int)((double)RESIZE_INT / height * (double)width);
			}

			//リサイズ処理
			resizeImage = new BufferedImage(width, height, sourceImage.getType());
			ato = new AffineTransformOp(
					AffineTransform.getScaleInstance((double)resizeWidth / width,
							(double) resizeHeight / height),
							AffineTransformOp.TYPE_BILINEAR);
			ato.filter(sourceImage, resizeImage);
			//画像の切り取り
			subImage =  resizeImage.getSubimage(0, 0, RESIZE_INT, RESIZE_INT);

		}else if(height > RESIZE_INT && width < RESIZE_INT){
			subImage =  sourceImage.getSubimage(0, 0, width, RESIZE_INT);

		}else if(height < RESIZE_INT && width > RESIZE_INT){
			subImage =  sourceImage.getSubimage(0, 0, RESIZE_INT, height);
		}else{
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
		if(originImg.exists()){
			originImg.delete();
		}else{
			System.out.println("ファイルが見つかりません");
		}
	}

	//ユーザプロフィール画像
	@Execute(validator=false)
	public String showUserImgSetting(){
		int userid = userDto.userID;
		mine=userid;
		Tuser userImg = tuserService.getTuserImg(userid);
		String filename = userImg.userid+".jpg";
		if(userImg != null){
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

	@Execute(validator=true, input = "changeUser.jsp")
	public String changeUsernameSubmit(){
		String newUserName = settingForm.username;
		int skey=0;
		int userid = userDto.userID;
		mine = userid;
		//System.out.println("morokyumorokyu" + newUserName);

		if(newUserName.isEmpty()){
			System.out.println("null");
			throw new ActionMessagesException("新しい名前を入力してください");
		}else{
			if(!(settingForm.keycheck == null)){
				skey = Integer.parseInt(settingForm.keycheck);
			}

			tuserService.tuserNameUpdate(userDto.userID, newUserName, skey);

		}
		return "changeUser.jsp";
	}

	public String successMsg;
	@Execute(validator=true, input = "changePass.jsp")
	public String changePasswordSubmit(){
		System.out.println("パスワードチェックに入ったよ");
		String oldpass = settingForm.oldpass;
		String newpass = settingForm.newpass;

		int userid = userDto.userID;
		mine = userid;

		Tuser tuser = tuserService.findById(userid);
		passwordhash e = new passwordhash();

		String pass_hash = e.getpassword(oldpass);
		System.out.println(pass_hash + " " + tuser.passWord);

		if(pass_hash.equals(tuser.passWord)){
			newpass = e.getpassword(newpass);
			tuser.passWord = newpass;
			System.out.println("OKだよ" + newpass);
			successMsg = "パスワードを変更しました";
			tuserService.updatePassWord(tuser);

		}else{
			System.out.println("だめだよ");
			throw new ActionMessagesException("パスワードが正しくありません", false);
		}


		return "changePass.jsp";
	}

	@Resource
	HttpServletRequest req;

	@Execute(validator=false)
	public String checkOldPass(){
		int userid = userDto.userID;
		String checkPass = req.getParameter("oldpass");
		Tuser tuser =tuserService.findById(userid);
		String pass = tuser.passWord;
		mine=userid;

		//パスワード暗号化
		passwordhash e = new passwordhash();
		String pass_hash = e.getpassword(checkPass);
		System.out.println(pass+ "morokyu" + pass_hash);

		if(pass == pass_hash){
			ResponseUtil.write("OK");
			System.out.println("passOKOK");
		}else{

		}


		return null;
	}

	@Execute(validator=false)
	public String changeUsername(){
		int userid = userDto.userID;
		mine=userid;
		return "changeUser.jsp";
	}

	@Execute(validator=false)
	public String changeUserImg(){
		int userid = userDto.userID;
		mine=userid;
		return "changeImg.jsp";
	}

	@Execute(validator=false)
	public String changePassword(){
		int userid = userDto.userID;
		mine=userid;

		return "changePass.jsp";
	}

}
