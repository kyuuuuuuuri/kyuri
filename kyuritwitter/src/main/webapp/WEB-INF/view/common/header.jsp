<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>

<html>
	<head>

	</head>
	<body>


		<c:if test="${ mine == 0 }">
			<div class="navbar navbar-inverse navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container">
						<ul class="nav">
							<li><s:link href="/main/"><span class="header_font">ホーム</span></s:link></li>
							<li class="divider-vertical"></li>
							<li><s:link href="/user/"><span class="header_font">ユーザ登録</span></s:link></li>
							<li class="divider-vertical"></li>
							<li><s:link href="/login/"><span class="header_font">ログイン</span></s:link></li>
						</ul>
					</div>
				</div>
			</div>
		</c:if>

		<c:if test="${ mine != 0 }">
			<div class="navbar navbar-inverse navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container">
						<ul class="nav">
							<li><s:link href="/main/"><span class="header_font">ホーム</span></s:link></li>

							<li><s:link href="/search/"><span class="header_font">友人を検索</span></s:link></li>

							<li><s:link href="logout"><span class="header_font">ログアウト</span></s:link></li>
						</ul>
					</div>
				</div>
			</div>
		</c:if>

	</body>
</html>

