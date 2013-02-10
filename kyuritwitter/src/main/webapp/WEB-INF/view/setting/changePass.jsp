<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>change user information</title>
</head>
<body>

	<s:form>

		<h4>パスワードを変更する</h4>
		<label>パスワードを入力してください</label>
		<html:text property="oldpass"  styleId="username" />
		<label>新しいパスワードを入力してください</label>
		<html:text property="newpass" styleId="password" />

		<p><s:submit styleClass="btn" property="" value="変更する" /></p>
	</s:form>

</body>
</html>