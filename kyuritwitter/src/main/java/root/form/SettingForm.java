package root.form;

import org.apache.struts.upload.FormFile;
import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Minlength;
import org.seasar.struts.annotation.Required;

public class SettingForm extends CommonForm{

	public FormFile file;

	@Required(target="changeUsernameSubmit", arg0 = @Arg(key = "名前", resource = false))
	@Minlength(target="changeUsernameSubmit", minlength = 4, arg0 = @Arg(key = "名前", resource = false))
	@Maxlength(target="changeUsernameSubmit", maxlength = 20, arg0 = @Arg(key = "名前", resource = false))
	@Mask(target="changeUsernameSubmit"
			, mask="^[-a-zA-Z0-9_ぁ-んァ-ヶ亜-黑ａ-ｚＡ-Ｚ０-９！”＃＄％＆’（）＊＋－．，／：；＜＝＞？＠￥＾＿～｛｜｝「」]+$"
			, arg0 = @Arg(key = "名前", resource = false))
	public String username;

	@Required(target="changePasswordSubmit", arg0 = @Arg(key = "パスワード", resource = false))
	@Minlength(target="changePasswordSubmit", minlength = 4, arg0 = @Arg(key = "パスワード", resource = false))
	@Maxlength( target="changePasswordSubmit", maxlength = 8, arg0 = @Arg(key = "パスワード", resource = false))
	@Mask( target="changePasswordSubmit", mask = "^[-a-zA-Z0-9_]+$", arg0 = @Arg(key = "パスワード", resource = false))
	public String oldpass;

	@Required(target="changePasswordSubmit", arg0 = @Arg(key = "新しいパスワード", resource = false))
	@Minlength( target="changePasswordSubmit", minlength = 4, arg0 = @Arg(key = "新しいパスワード", resource = false))
	@Maxlength( target="changePasswordSubmit", maxlength = 8, arg0 = @Arg(key = "新しいパスワード", resource = false))
	@Mask( target="changePasswordSubmit", mask = "^[-a-zA-Z0-9_]+$", arg0 = @Arg(key = "新しいパスワード", resource = false))
	public String newpass;

	public String keycheck;

	@Required(target="makeListName", arg0 = @Arg(key = "リスト名", resource = false))
	@Maxlength( target="makeListName", maxlength = 30, arg0 = @Arg(key = "リスト名", resource = false))
	public String listName;

	public int listid;

	@Maxlength( target="makeListName", maxlength = 50, arg0 = @Arg(key = "リスト名", resource = false))
	public String listDesc;

}
