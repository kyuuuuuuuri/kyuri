<%@ page contentType="text/html; charset=utf-8" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>


		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/entrypage.css" />


		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>

		<script type="text/javascript">
		$(function(){
			$('#login_form').load('${f:url('sendLogin')} #login form');
			});

		</script>

		<title>entry</title>
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container">
						<ul class="nav">
							<li><s:link href="/login/"><span class="header_font">アカウントをお持ちの場合はログイン</span></s:link></li>
						</ul>
					</div>
				</div>
			</div>

		<div>
		<div>
			<pre>
				<h4>Twitterに参加しました。早速ログインしてみましょう</h4>
				<span id="login_form"></span>

			</pre>
		</div>
		</div>
	</body>
</html>
