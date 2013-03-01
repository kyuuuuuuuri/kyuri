package root.form;

import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Minlength;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;
import org.seasar.struts.annotation.Validwhen;


public class LoginForm extends CommonForm{

	@Required(target="loginSubmit, toMakeNewpass", arg0 = @Arg(key = "ユーザネーム", resource = false))
	@Minlength(target="loginSubmit, toMakeNewpass", minlength = 4,  arg0 = @Arg(key = "ユーザネーム", resource = false))
	@Maxlength(target="loginSubmit, toMakeNewpass", maxlength = 20,  arg0 = @Arg(key = "ユーザネーム", resource = false))
	@Mask(target="loginSubmit, toMakeNewpass", mask = "^[-a-zA-Z0-9_]+$",  arg0 = @Arg(key = "ユーザネーム", resource = false))
	public String UserName;

	@Required(target="loginSubmit",  arg0 = @Arg(key = "パスワード", resource = false))
	@Minlength(target="loginSubmit", minlength = 4,  arg0 = @Arg(key = "パスワード", resource = false))
	@Maxlength(target="loginSubmit",maxlength = 8, arg0 = @Arg(key = "パスワード", resource = false))
	@Mask(target="loginSubmit", mask = "^[a-zA-Z0-9]+$", arg0 = @Arg(key = "パスワード", resource = false))
	public String Pass;

	@Required(target="setPass" , arg0 = @Arg(key = "確認パスワード", resource = false))
	@Minlength(target="setPass", minlength = 4,arg0 = @Arg(key = "確認パスワード", resource = false))
	@Maxlength(target="setPass",maxlength = 8, arg0 = @Arg(key = "確認パスワード", resource = false))
	@Mask(target="setPass", mask = "^[a-zA-Z0-9]+$", arg0 = @Arg(key = "確認パスワード", resource = false))
	@Validwhen(target="setPass", test="(Pass == checkPass)", msg=@Msg(key="パスワードが一致しません。", resource=false))
	public String checkPass;

	@Required(target="toMakeNewpass")
	public int question;

	@Required(target="toMakeNewpass", arg0 = @Arg(key = "秘密の答え", resource = false))
	public String answer;


}
