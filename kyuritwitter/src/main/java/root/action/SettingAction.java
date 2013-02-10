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

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

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

	@Execute(validator = false)
	public String index(){
		int userid = userDto.userID;
		mine=userid;

		return "index.jsp";
	}


	//upload file
	@Execute(validator = false)
	public String upload() throws FileNotFoundException, IOException{

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

		return "index.jsp";
	}

//	public static void main(String[] args) {
//
//		SettingAction action = new SettingAction();
//		try {
//			action.resize("C:\\Users\\myao\\git\\kyuritwitter_git\\kyuritwitter\\src\\main\\webapp\\img\\profileImg/tumblr_m4r85jjZBv1qbyxr0o1_500.jpg");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public void resize(String filePath) throws IOException{
		BufferedImage sourceImage = ImageIO.read(new File(filePath));
		int height = sourceImage.getHeight();
		int width = sourceImage.getWidth();
		double resizeHeight = 0;
		double resizeWidth = 0;
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
		}

		//リサイズ処理
		BufferedImage resizeImage = new BufferedImage(width, height, sourceImage.getType());
		AffineTransformOp ato = null;
		ato = new AffineTransformOp(
				AffineTransform.getScaleInstance((double)resizeWidth / width,
						(double) resizeHeight / height),
						AffineTransformOp.TYPE_BILINEAR);
		ato.filter(sourceImage, resizeImage);
		//画像の切り取り
		BufferedImage subImage =  resizeImage.getSubimage(0, 0, RESIZE_INT, RESIZE_INT);

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
		int id = userDto.userID;
		Tuser userImg = tuserService.getTuserImg(id);
		String filename = userImg.userid+".jpg";
		if(userImg != null){
			ResponseUtil.download(filename, userImg.profileimg);
		}

		return null;
	}

	//changenamejsp
	@Execute(validator=false)
	public String changeUsername(){

		return "changeUser.jsp";
	}

	@Execute(validator=false)
	public String changeUsernameSubmit(){
		String newUserName = settingForm.username;
		System.out.println(newUserName);
		if(newUserName == null){
			throw new ActionMessagesException("新しい名前を入力してください", false);
		}else{
			tuserService.tuserNameUpdate(userDto.userID, newUserName);
		}
		return "";
	}

	@Execute(validator=false)
	public String changePasswordSubmit(){

		return "";
	}

	@Execute(validator=false)
	public String changeUserImg(){
		return "changeImg.jsp";
	}

	@Execute(validator=false)
	public String changePassword(){
		return "changePass.jsp";
	}

}
