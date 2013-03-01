<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Content-Style-Type" content="text/css">
		<meta http-equiv="Content-Script-Type" content="text/javascript">

		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" />

		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

		<script type="text/javascript" src="${f:url('/js/jquery.js')}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/searchtwit.js"></script>

		<title>メインページ</title>
	</head>
	<body>
		<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
		<tiles:put name="title" value="searchtwit" />
		<tiles:put name="content" type="string">

		<!-- つぶやきが一件もなかった場合 -->
		<c:if test="${empty murmurList }">
			<div>
				表示すべきつぶやきが一件もありません
			</div>
		</c:if>

		<!-- つぶやきが一件以上ある場合 -->
		<c:if test="${!empty murmurList}">
		<div id="timeLine">
			<div id="twitTitle"><h4>ツイート</h4></div>
			<p class="timeLine_border"></p>

			<c:forEach var="tubuyaki" items="${murmurList}" varStatus="status">

						<div id="${tubuyaki.murmurid}" class="twitmain">
							<span class="pImg">
							<html:img src="${pageContext.request.contextPath}/main/showUserImg/${tubuyaki.tuser.userid}" width="50" height="50" />
							</span> <span class="usernick">
							<s:link href="userpage/${tubuyaki.tuser.usernick}">
								<span class="usernickLink">${tubuyaki.tuser.usernick}</span>
							</s:link>
							</span> <span class="username">${tubuyaki.tuser.username}</span>
							<p class="twitid">${f:br(tubuyaki.murmur)}</p>
							<span id="${tubuyaki.murmurid}open"
								class="open_details_twit twit_info_link" onclick="openRep(${tubuyaki.murmurid})">開く</span>
								<span class="date twit_info">
								<fmt:formatDate value="${tubuyaki.dateTime}" pattern="yyyy年MM月dd日 HH時mm分ss秒" />
							</span>
							<span class="favorite twit_info twit_info_link">お気に入りに登録</span>
							<!-- 自分のつぶやきじゃない場合リツイートと返信をつける -->
							<c:if test="${tubuyaki.tuser.userid != mine}">
								<s:link href="/main/retwit/${tubuyaki.murmurid }"
									styleClass="twit_info twit_info_link">リツイート</s:link>
								<span class="twit_info" onclick="changeRepform(${tubuyaki.murmurid})">返信</span>
							</c:if>

							<!-- 自分のつぶやきだった場合削除リンクをつける -->
							<c:if test="${tubuyaki.tuser.userid==mine}">
								<s:link href="/main/delete/${tubuyaki.murmurid}"
									styleClass="twit_info twit_info_link">削除</s:link>
							</c:if>

						</div>
						<div id="${tubuyaki.murmurid}r" class="repform">

							<s:form styleClass="rep_form">
								<textarea class="rep_textarea" name="tubuyaki" rows="1"></textarea>
								<br>
								<span class="rep_text_size">140</span>
								<s:submit styleClass="btn btn-info rep_twit_btn"
									property="ins_tubuyaki">ツイート</s:submit>
							</s:form>
						</div>
						<p class="timeLine_border"></p>

					</c:forEach>
			<div id="lastLineS" class="0">　</div>
		</div>

		</c:if>

</tiles:put>
</tiles:insert>
</body>
</html>