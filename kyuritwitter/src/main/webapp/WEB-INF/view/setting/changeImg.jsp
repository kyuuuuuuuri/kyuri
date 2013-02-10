<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>change user img</title>
</head>
<body>

	<div class="userimg">
		<html:img src="showUserImgSetting" width="100" height="100" />
	</div>
	<s:form method="POST" enctype="multipart/form-data">
		<html:file property="file" />
		<html:submit styleId = "uploadSubmit" styleClass = "btn" property="upload" value="変更する" />
	</s:form>
</body>
</html>