package root.form;

import org.apache.struts.upload.FormFile;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Minlength;
import org.seasar.struts.annotation.Required;

public class SettingForm extends CommonForm{

	public FormFile file;

	@Required(target="changeUsernameSubmit")
	@Minlength(target="changeUsernameSubmit", minlength = 4)
	@Maxlength(target="changeUsernameSubmit", maxlength = 20)
	@Mask(target="changeUsernameSubmit", mask="^[-a-zA-Z0-9_ぁ-んァ-ヶ亜-黑ａ-ｚＡ-Ｚ０-９！”＃＄％＆’（）＊＋－．，／：；＜＝＞？＠￥＾＿～｛｜｝「」]+$")
	public String username;

	@Required(target="changePasswordSubmit")
	@Minlength(target="changePasswordSubmit", minlength = 4)
	@Maxlength( target="changePasswordSubmit", maxlength = 8)
	@Mask( target="changePasswordSubmit", mask = "^[-a-zA-Z0-9_]+$")
	public String oldpass;

	@Required(target="changePasswordSubmit")
	@Minlength( target="changePasswordSubmit", minlength = 4)
	@Maxlength( target="changePasswordSubmit", maxlength = 8)
	@Mask( target="changePasswordSubmit", mask = "^[-a-zA-Z0-9_]+$")
	public String newpass;

	public String keycheck;

}
