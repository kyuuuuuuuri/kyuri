<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

		<script type="text/javascript" src="${f:url('/js/jquery.js')}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/newTwitCheck.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/image.js"></script>



		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=ja"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" />



<!--<tiles:insert page="/WEB-INF/view/common/header.jsp"  />-->
		<title>メインページ</title>
	</head>
	<body>
		<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
		<tiles:put name="title" value="mainpage" />
		<tiles:put name="content" type="string">

		<div id="timeLine">
			<div id="twitTitle"><h4>ツイート</h4></div>

			<p class="timeLine_border"></p>

			<c:if test="${empty murmurList }">
				<div id="noTwit" class="noTwit">
					表示すべきつぶやきが一件もありません
				</div>
			</c:if>


			<c:if test="${!empty murmurList}">
				<c:forEach var="tubuyaki" items="${murmurList}">

						<div id="${tubuyaki.murmurid}" class="twitmain">
							<span class="pImg">
							<html:img src="${pageContext.request.contextPath}/main/showUserImg/${tubuyaki.tuser.userid}"
									width="50" height="50" />
							</span>
							<span class="usernick">
							<s:link href="userpage/${tubuyaki.tuser.usernick}">
								<span class="usernickLink">${tubuyaki.tuser.usernick}</span>
							</s:link>
							</span>
							<span class="username">${tubuyaki.tuser.username}</span>
							<p class="twitid">
							${f:h(tubuyaki.murmur)}
							</p>
							<c:if test="${not empty tubuyaki.imageurl}" >
								<a href="${tubuyaki.imageurl}" data-toggle="modal">${tubuyaki.imageurl}</a><br>
							</c:if>

							<c:if test="${not empty tubuyaki.retweetuser}">
								<c:set var = "WhoRetweetThis" value="${tubuyaki.retuser.usernick} がリツイートしました" />
							</c:if>
							<c:if test="${empty tubuyaki.retweetuser}">
								<c:set var = "WhoRetweetThis" value="" />
							</c:if>
							<p class="retwitInfo"><b>${f:h(WhoRetweetThis)}</b></p>

							<span id="${tubuyaki.murmurid}open" class="open_details_twit twit_info_link" onclick="openRep(${tubuyaki.murmurid})">開く</span>
							<span class="date twit_info">
							<fmt:formatDate value="${tubuyaki.dateTime}" pattern="yyyy年MM月dd日 HH時mm分ss秒" />
							</span>
							<c:if test="${not empty tubuyaki.gpslocation}">
							<span class="twit_info">${tubuyaki.gpslocation }</span>
							</c:if>
							<c:if test="${empty tubuyaki.favolite[0].userid || empty tubuyaki.favoliteReVar[0].userid}">
								<c:set var = "favoriteMsg" value="お気に入りに登録" />
							</c:if>
							<c:if test="${not empty tubuyaki.favolite[0].userid || not empty tubuyaki.favoliteReVar[0].userid}">
								<c:set var = "favoriteMsg" value="★お気に入りを取り消す" />
							</c:if>

							<span class="favorite twit_info twit_info_link" onclick="favoriteclick(${tubuyaki.murmurid})">${f:h(favoriteMsg)}</span>
							<!-- 自分のつぶやきじゃない場合リツイートと返信をつける -->
							<c:if test="${tubuyaki.tuser.userid != mine}">
							<c:if test="${empty tubuyaki.retweets[0].userid}">
								<c:set var = "retweetMsg" value="リツイート" />
							</c:if>
							<c:if test="${not empty tubuyaki.retweets[0].userid || tubuyaki.retweetuser == mine}">
								<c:set var = "retweetMsg" value="リツイートを取り消す" />
							</c:if>
								<span class="retweet twit_info twit_info_link" onclick="retweet(${tubuyaki.murmurid})">${f:h(retweetMsg)}</span>


								<span class="twit_info twit_info_link" onclick="changeRepform(${tubuyaki.murmurid})">返信</span>
							</c:if>

							<!-- 自分のつぶやきだった場合削除リンクをつける -->
							<c:if test="${tubuyaki.tuser.userid==mine}">
								<s:link href="/main/delete/${tubuyaki.murmurid}" styleClass="twit_info twit_info_link">削除</s:link>
							</c:if>

						</div>
						<div id="${tubuyaki.murmurid}r" class="repform">

							<s:form styleClass="rep_form" onsubmit="repSubmit(${tubuyaki.murmurid}); return false;">
								<textarea class="rep_textarea" name="tubuyaki" rows="1"></textarea>
								<br>
								<html:hidden styleClass = "topId" property="topIdrep" value=""/>
								<html:hidden property="thisId" value="${tubuyaki.murmurid}"/>
								<span class="rep_text_size">140</span>
								<s:submit styleClass="btn btn-info rep_twit_btn"
									property="ins_tubuyaki_rep">ツイート</s:submit>
							</s:form>
						</div>
						<p class="timeLine_border"></p>

				</c:forEach>
			</c:if>
			<div id="lastLine" class="0">　</div>
		</div>

</tiles:put>
</tiles:insert>
</body>
</html>