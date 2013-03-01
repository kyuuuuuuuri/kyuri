<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Content-Style-Type" content="text/css">
		<meta http-equiv="Content-Script-Type" content="text/javascript">
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/entrypage.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />


		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>

		<title>You forget pass word ?</title>
	</head>

	<body>

	<pre>

		<s:form>
			<html:errors />

					<label>${username}さん</label>
					<html:hidden property = "UserName" value="${username}"></html:hidden>

					<p><span>新しいパスワードを設定してください</span></p>

					<p><input type="text" id="passWord" name="Pass" class="form_style" value="" /></p>

					<span>もう一度パスワードを入力してください</span>

					<p><input type="text" id="passWord" name="checkPass" class="form_style" value="" /></p>

					<s:submit property = "setPass" styleClass="btn btn-primary">パスワードを設定する</s:submit>


		</s:form>

	</pre>


	</body>
</html>