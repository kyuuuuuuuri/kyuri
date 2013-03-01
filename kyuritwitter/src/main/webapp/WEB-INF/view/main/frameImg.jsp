<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>

<html>
	<head>
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/iframe.css" />
		<script type="text/javascript" src="${f:url('/js/jquery.js')}"></script>
	</head>
	<body>

	<div id="imgFileUrl">${imgShowUrl}</div>
	<div><html:img src="${pageContext.request.contextPath}/img/twitImg/${imgShowUrl}" width="50" height="50" /></div>
	<div id="deleteImg">削除</div>

	</body>
</html>