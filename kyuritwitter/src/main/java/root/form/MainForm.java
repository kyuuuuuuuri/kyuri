package root.form;

import org.apache.struts.upload.FormFile;
import org.seasar.struts.annotation.IntegerType;

public class MainForm extends CommonForm {


	public String tubuyaki;

	public String topIdrep;

	public String thisId;

	@IntegerType
	public String page;

	public int userid;
	public int userid1;

	public int tubuyakiid;

	public Integer delete_id;

	public int retwit_tubuyaki;

	public String rep_user;

	public String hashtag;

	//画像関連の
	public FormFile imageFile;
	public String imgUrl;


	//座標の位置をあれする
	public double longitude;
	public double latitude;

}
