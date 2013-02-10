<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>

<html>
	<head>

	</head>
	<body>

			<tiles:insert page="/WEB-INF/view/common/header.jsp" />

		<div class="maintwitline">
			<div class="container">
				<div class="row">

					<div class="span4">
						<tiles:insert page="/WEB-INF/view/common/menu.jsp" />
						</div>


						<div class="span7 offset1">
							<tiles:insert attribute = "content" />
						</div>
				</div>
				<div id="footer">
					<tiles:insert page="/WEB-INF/view/common/footer.jsp" />
				</div>
			</div>
		</div>

</body>
</html>