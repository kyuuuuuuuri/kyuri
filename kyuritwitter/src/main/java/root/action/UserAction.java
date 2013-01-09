package root.action;

import javasource.passwordhash;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import root.SuperAction;
import root.entity.Tuser;
import root.form.UserForm;


public class UserAction extends SuperAction{

	@ActionForm
	@Resource
	public UserForm userForm;

    private final String userIndexJsp = "userInput.jsp";


    @Execute(validator=false)
    public String index() {
    	return "index.jsp";
    }

    //jspファイルに渡すtuser
    public Tuser tuser = new Tuser();

    //ユーザ登録で入力されたものを取得して処理する
    @Execute(validator=true,input=userIndexJsp)
    public String account(){
    	String pass = userForm.pass;
    	//String checkPass = userForm.checkPass;
    	String userNick = userForm.userNick;
    	String userName = userForm.userName;

//    	//パスワードとパスワード(確認)が一致しているかをチェック
//    	if(!pass.equals(checkPass)){
//    		throw new ActionMessagesException("パスワードが一致しません。確認してください。", false);
//    	}
////
    	tuser.username = userName;
    	tuser.usernick = userNick;
    	tuser.mailad = userForm.mailAd;
    	tuser.passWord = pass;

    	return "entrypage.jsp";

	}

    //新しいユーザのDB登録を実行する
    @Execute(validator=false, input="")
    public String registerNewUser(){
    	//final String algorithmName = "SHA-256";
		passwordhash e = new passwordhash();

		String pass_hash = e.getpassword(userForm.pass);

    	tuser.username = userForm.userName;
    	tuser.usernick = userForm.userNick;
    	tuser.mailad = userForm.mailAd;
    	tuser.passWord = pass_hash;
    	tuser.follow=0;
    	tuser.followed=0;
    	tuser.skey=0;
    	tuser.postNum=0;

    	System.out.println(tuser.username+"kyuuri" + pass_hash);
    	//tuserService.insert(tuser);


    	return "entrysuccess.jsp";
    }

    //loginpageを送る
    @Execute(validator=false)
    public String sendLogin(){
    	return "/login/index.jsp";
    }

    @Resource
    HttpServletRequest req;

    //ajaxcheck
    @Execute(validator=false)
    public String username_check(){
    	String usernamePattern="^[-a-zA-Z0-9_ぁ-んァ-ヶ亜-黑ａ-ｚＡ-Ｚ０-９！”＃＄％＆’（）＊＋－．，／：；＜＝＞？＠￥＾＿～｛｜｝「」]+$";
    	String userparam = req.getParameter("param");
    	//System.out.println("kyuuri" + userparam);
    	//すでに使われているユーザ名なのか検索
    	if(userparam.length() < 4 && userparam.length()>0){

    		ResponseUtil.write("<span id='ajax_username' class='alert_css'>4文字以上で入力してください</span>");
    		return null;
    	}else if(userparam.length()>20){
    		ResponseUtil.write("<span id='ajax_username' class='alert_css'>20文字以下で入力してください</span>");
    		return null;
    	}else if(userparam.length()==0){
    		ResponseUtil.write("<span id='ajax_username' class='alert_css'>入力してください</span>");
    		return null;
    	}
    	if(!userparam.matches(usernamePattern)){
    		ResponseUtil.write("<span id='ajax_username' class='alert_css'>正しい文字列で入力してください</span>");
    		return null;
    	}


    	ResponseUtil.write("<span id='ajax_username' class='success_css'>確認しました</span>");
    	return null;

    }

    //ajaxcheck
    @Execute(validator=false)
    public String usernick_check(){
    	String userparam = req.getParameter("param");
    	System.out.println("kyuuri" + userparam);
    	Tuser result =tuserService.findByName(userparam);
    	String usernickPattern ="^[-a-zA-Z0-9_]+$";


    	if(userparam.length() < 4 && userparam.length()>0){
    		ResponseUtil.write("<span id='ajax_usernick' class='alert_css'>4文字以上で入力してください</span>");return null;
    	}else if(userparam.length()>20){
    		ResponseUtil.write("<span id='ajax_usernick' class='alert_css'>20文字以下で入力してください</span>");return null;
    	}else if(userparam.length()==0){
    		ResponseUtil.write("<span id='ajax_usernick' class='alert_css'>入力してください</span>");return null;
    	}
    	if(!userparam.matches(usernickPattern)){
    		ResponseUtil.write("<span id='ajax_usernick' class='alert_css'>半角英数字で入力してください</span>");
    		return null;
    	}
       	if(result!=null){
    		ResponseUtil.write("<span id='ajax_usernick' class='alert_css'>このユーザ名はすでに使われています。</span>");
    		return null;
    	}
    	ResponseUtil.write("<span id='ajax_usernick' class='success_css'>使用できます</span>");
    	return null;
    }

    //ajaxcheck
    @Execute(validator=false)
    public String password_check(){
    	String userparam = req.getParameter("param");
    	String passwordPattern ="^[-a-zA-Z0-9_]+$";

    	System.out.println("kyuuri" + userparam);
    	if(userparam.length() < 4 && userparam.length()>0){
    		ResponseUtil.write("<span id='ajax_password' class='alert_css'>4文字以上で入力してください</span>");return null;
    	}else if(userparam.length()>8){
    		ResponseUtil.write("<span id='ajax_password' class='alert_css'>8文字以下で入力してください</span>");return null;
    	}else if(userparam.length()==0){
    		ResponseUtil.write("<span id='ajax_password' class='alert_css'>入力してください</span>");return null;
    	}
    	if(!userparam.matches(passwordPattern)){
    		ResponseUtil.write("<span id='ajax_password' class='alert_css'>半角英数字で入力してください</span>");return null;
    		}

    	ResponseUtil.write("<span id='ajax_password' class='success_css'>確認しました</span>");
    	return null;
    }

    //ajaxcheck
    @Execute(validator=false)
    public String mailad_check(){
    	String emailPattern = "^[a-zA-Z0-9!$&*.=^`|~#%'+\\/?_{}-]+@([a-zA-Z0-9_-]+\\.)+[a-zA-Z]{2,4}$";
    	String emailparam = req.getParameter("param");
    	if(emailparam.length()>100){
    		ResponseUtil.write("<span id='ajax_mailad' class='alert_css'>アドレスが長すぎます</span>");
    		return null;
    	}
    	if(!emailparam.matches(emailPattern)){
    	ResponseUtil.write("<span id='ajax_mailad' class='alert_css'>メールアドレスを入力してください</span>");
    	return null;
    	}
    	ResponseUtil.write("<span id='ajax_mailad' class='success_css'>メールアドレス確認しました</span>");
    	return null;
    }


	//エントリーページへ遷移する
	@Execute(validator=false)
	public String entrypage(){
		return "entrypage.jsp";
	}

	//ログインページへ遷移する
	@Execute(validator=false)
	public String login(){
		return "../login/index.jsp";
	}

}
