package root.action;

import java.util.HashMap;
import java.util.Map;

import javasource.passwordhash;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import root.SuperAction;
import root.entity.Tuser;
import root.form.LoginForm;

public class LoginAction extends SuperAction {

	@ActionForm
	@Resource
	protected LoginForm loginForm;

	public String greeting;

	public int mine = 0;

	public String username;

	@Execute(validator = false)
	public String index() {

		return "index.jsp";
	}

	@Execute(validator = false)
	public String login_register() {
		return "/user/index.jsp";
	}

	@Execute(validator = true, input = "/login/index.jsp", urlPattern = "main")
	public String loginSubmit() {

		String userName = loginForm.UserName;
		//String pass=loginForm.Pass;
		passwordhash e = new passwordhash();
		String pass = e.getpassword(loginForm.Pass);

		//ユーザが存在するか検証
		Tuser result = tuserService.findByNameForCheck(userName);

		if (result == null || (!pass.equals(result.passWord))) {//ユーザ名が登録されていなかったら…
			throw new ActionMessagesException("パスワードとユーザ名が一致しません", false);
		}

		//ユーザＩＤをセッション登録する
		userDto.userID = result.userid;

		return "/main?redirect=true";
	}

	//ユーザ新規登録するページへの遷移
	@Execute(validator = false)
	public String userentry() {
		return "/user/";
	}

	//パスワードを新しく設定するページに移動する
	@Execute(validator = false)
	public String toSetPass() {
		return "forForgetPass.jsp";
	}

	@Execute(validator = true, input="forForgetPass.jsp")
	public String toMakeNewpass(){

		int questionId = loginForm.question;
		String question;
		String usernick = loginForm.UserName;
		String answer   = loginForm.answer;

		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "出身はどこですか？");
		map.put(2, "好きな映画はなんですか？");
		map.put(3, "飼っている犬の名前はなんですか？");

		question = map.get(questionId);

		System.out.println(question + "もろきゅうパス");

		Tuser tuser = tuserService.findByNameForCheck(usernick);

		if(tuser != null){
			if (!tuser.secretquestion.equals(question) || !tuser.secretanswer.equals(answer)) {
				throw new ActionMessagesException("秘密の質問と答えが違います", false);
			}
		}else{
			throw new ActionMessagesException("秘密の質問と答えが違います", false);
		}

		username = usernick;
		userDto.usernick = usernick;

		return "makePass.jsp";
	}

	@Execute(validator = false)
	public String valiTomakeNewPass(){
		username = userDto.usernick;

		return "makePass.jsp";
	}

	@Execute(validator = true, input="valiTomakeNewPass")
	public String setPass(){

		String pass      = loginForm.Pass;
		String usernick  = loginForm.UserName;

		username = usernick;

		passwordhash passwordHash = new passwordhash();
		pass = passwordHash.getpassword(pass);

		System.out.println(pass + " " + usernick + "morokyu");

		Tuser tuser = tuserService.findByNameForCheck(usernick);
		tuser.passWord = pass;
		tuserService.updatePassWord(tuser);

		userDto.usernick=null;

		return "/user/entrysuccess.jsp";
	}

	//mainpageへ移動する
	@Execute(validator = false)
	public String tomain() {
		//int mine=0;
		if (userDto.userID != null) {
			int userid = userDto.userID;
			mine = userid;
		}
		return "/main/";
	}

	//検索ページへ移動する
	@Execute(validator = false)
	public String tosearch() {
		return "/search/";
	}

	//ログインページへ移動する
	@Execute(validator = false)
	public String tologin() {
		return "index";
	}
}
