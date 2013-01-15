<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="${f:url('/js/jquery.js')}"></script>

<title>Dolteng Auto Generated</title>
</head>
<body>
<h1>Hello World!</h1>
<span id="message"></span>
<input type="button" value="hello"
    onclick="$('#message').load('hello',{'greeting':'Hello'});"/>
</body>
</html>