<%@page pageEncoding="UTF-8"%>


	<s:form>
		<html:errors/>

			<p><input type="text" name="userName" class="style form_style" id="username" placeholder="名前"/></p>

			<p><input type="text" name="userNick" class="style form_style" id="usernick" placeholder="ユーザ名"/></p>

			<p><input type="password" class = "style1 form_style"  id="myinput" name="pass" placeholder="パスワード"/></p>

			<p><input type="text" name="mailAd" class="style form_style" id="mail" placeholder="メールアドレス"/></p>

			<p class="login_buton" ><input type="submit" name="account" id="account" value="登録"></p>
	</s:form>

