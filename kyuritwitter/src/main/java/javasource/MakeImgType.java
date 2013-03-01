package javasource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.lang.RandomStringUtils;

public class MakeImgType {

	private String ImgName;

	public ServletContext application;

	public String makeImg(String filename) throws IOException{

		BufferedImage sourceImage = ImageIO.read(new File(filename));

		String imgNameLocal = makeImgName();
		System.out.println(imgNameLocal+"moro");
		String newimgPath = application.getRealPath("/img/twitImg/");

		try {
			OutputStream out = new FileOutputStream(newimgPath + imgNameLocal + ".jpg");
			ImageIO.write(sourceImage, "jpg", out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ImgName = imgNameLocal + ".jpg";

		return ImgName;
	}

	//ファイル名をランダムで生成する
	public String makeImgName(){
		String imageName;
		SecureRandom nameSize = new SecureRandom();
		//String newimgPath;

		//画像のファイル名を書きだす
		int sizenum = nameSize.nextInt(5)+3;
		imageName = RandomStringUtils.randomAlphabetic(sizenum);

		//newimgPath = application.getRealPath("/img/twitImg/"+ imageName +".jpg");

//		String path = application.getRealPath("/img/twitImg/" + imageName);
//
//		File file = new File(path);
//
//		if(file.exists()){
//			makeImgName();
//		}
		return imageName;
	}

}
