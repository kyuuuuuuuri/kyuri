<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Content-Style-Type" content="text/css">
		<meta http-equiv="Content-Script-Type" content="text/javascript">
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" />

		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>


		<title><tiles:getAsString name="title" /></title>


	</head>
	<body>

			<tiles:insert page="/WEB-INF/view/common/header.jsp" />


		<div class="container-fluid">
			<div class="maintwitline">
				<div class="container">
					<div class="row">
						<div class="span4">
						<tiles:insert page="/WEB-INF/view/common/menu.jsp" />
					</div>
					<div class="span8">
						<tiles:insert attribute="content" />
					</div>
				</div>

			</div>
		</div>

		<div id="footer">
			<tiles:insert page="/WEB-INF/view/common/footer.jsp" />
		</div>
		</div>

	</body>
</html>