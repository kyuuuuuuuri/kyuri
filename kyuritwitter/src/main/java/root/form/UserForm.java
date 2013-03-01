package root.form;

import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.EmailType;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Minlength;
import org.seasar.struts.annotation.Required;


public class UserForm {

	@Required(target="changeUsernameSubmit", arg0 = @Arg(key = "ユーザネーム", resource = false))
	@Minlength(target="changeUsernameSubmit", minlength = 4, arg0 = @Arg(key = "ユーザネーム", resource = false))
	@Maxlength(target="changeUsernameSubmit", maxlength = 20, arg0 = @Arg(key = "ユーザネーム", resource = false))
	@Mask(target="changeUsernameSubmit",
			mask="^[-a-zA-Z0-9_ぁ-んァ-ヶ亜-黑ａ-ｚＡ-Ｚ０-９！”＃＄％＆’（）＊＋－．，／：；＜＝＞？＠￥＾＿～｛｜｝「」]+$",
			arg0 = @Arg(key = "ユーザネーム", resource = false))
	public String userName;

	@Required(target="changeUsernameSubmit", arg0 = @Arg(key = "ニックネーム", resource = false))
	@Minlength(target="changeUsernameSubmit", minlength = 4, arg0 = @Arg(key = "ニックネーム", resource = false))
	@Maxlength(target="changeUsernameSubmit", maxlength = 20, arg0 = @Arg(key = "ニックネーム", resource = false))
	@Mask( target="changeUsernameSubmit", mask = "^[-a-zA-Z0-9_]+$", arg0 = @Arg(key = "ニックネーム", resource = false))
	public String userNick;

	@Required(target="changeUsernameSubmit", arg0 = @Arg(key = "パスワード", resource = false))
	@Minlength( target="changeUsernameSubmit", minlength = 4, arg0 = @Arg(key = "パスワード", resource = false))
	@Maxlength( target="changeUsernameSubmit", maxlength = 8, arg0 = @Arg(key = "パスワード", resource = false))
	@Mask( target="changeUsernameSubmit", mask = "^[-a-zA-Z0-9_]+$", arg0 = @Arg(key = "パスワード", resource = false))
	public String pass;

	//public String checkPass;

	@Required(target="changeUsernameSubmit", arg0 = @Arg(key = "メールアドレス", resource = false))
	@EmailType(target="changeUsernameSubmit", arg0 = @Arg(key = "メールアドレス", resource = false))
	@Maxlength(target="changeUsernameSubmit", maxlength = 100, arg0 = @Arg(key = "メールアドレス", resource = false))
	public String mailAd;

	@Required(target="changeUsernameSubmit")
	public int question;

	@Required(target="changeUsernameSubmit", arg0 = @Arg(key = "秘密の答え", resource = false))
	@Maxlength(target="changeUsernameSubmit", maxlength = 60, arg0 = @Arg(key = "秘密の答え", resource = false))
	public String answer;


}
