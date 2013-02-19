<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/setting.js"></script>

<title>change user information</title>
</head>
<body>

	<s:form>

		<html:errors />

		<label>名前を変更する</label>
		<html:text property="username" styleId="username" value = "${mydata.username}" />

		<label>公開レベルを設定する</label>
		<html:checkbox styleId="keycheck" property="keycheck" value="1" />
		<span>非公開にする</span>

		<p>
			<s:submit styleId="changeUsernameSubmit" styleClass="btn btn-info"
				property="changeUsernameSubmit" value="変更する" />
		</p>
	</s:form>

</body>
</html>