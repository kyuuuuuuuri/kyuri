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
		<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" ></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/userpage.js"></script>


		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=ja"></script>


<!--<tiles:insert page="/WEB-INF/view/common/header.jsp"  />-->
		<title>メインページ</title>
	</head>
	<body>
		<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
		<tiles:put name="title" value="followedpage" />
		<tiles:put name="content" type="string">


		<div id="userdata">
			<span id = "userImg"><html:img src="${pageContext.request.contextPath}/main/showUserImg/${mydata.userid}" width="100" height="100" /></span>
			<p id = "usernickSuper">${mydata.usernick }</p>
			<div id="followButton">
			<c:if test = "${mydata.userid != mine}">
				<c:if test = "${mydata.ffollowList[0].userid != mine}">
					<input id ="followSub" type="button" class="btn btn-primary" onclick="follow(${mydata.userid})" value="フォロー" />
				</c:if>
				<c:if test = "${mydata.ffollowList[0].userid == mine}">
					<input id="unfollowSub" type="button" class="btn btn-danger" onclick="unfollow(${mydata.userid})" value="フォロー解除" />
				</c:if>
				<c:if test="${empty mydata.blockedUserList[0].userid}">
					<input id ="blockButton" type="button" class="btn btn-danger" onclick="block(${mydata.userid})" value="ブロックする" />
				</c:if>
				<c:if test="${not empty mydata.blockedUserList[0].userid}">
					<input id ="unBlockButton" type="button" class="btn btn-success" onclick="unBlock(${mydata.userid})" value="ブロックを解除する" />
				</c:if>

			</c:if>
			</div>

		</div>

		<!-- つぶやきが一件もなかった場合 -->
		<c:if test="${empty murmurList }">
			<div>
				つぶやきが一件もありません
			</div>
		</c:if>

		<!-- つぶやきが一件以上ある場合 -->
		<c:if test="${!empty murmurList}">
		<div id="timeLine">
			<div id="twitTitle"><h4>ツイート</h4></div>


			<p class="timeLine_border"></p>

			<c:forEach var="tubuyaki" items="${murmurList}">

						<div id="${tubuyaki.murmurid}" class="twitmain">
							<span class="pImg">
							<html:img src="${pageContext.request.contextPath}/main/showUserImg/${tubuyaki.tuser.userid}"
									width="50" height="50" />
							</span>
							<span class="usernick">
							<s:link href="?userni=${tubuyaki.tuser.usernick}">
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

							<span id="${tubuyaki.murmurid}open" class="open_details_twit twit_info_link" onclick="openRep(${tubuyaki.murmurid})">開く</span>
							<span class="date twit_info">
							<fmt:formatDate value="${tubuyaki.dateTime}" pattern="yyyy年MM月dd日 HH時mm分ss秒" />
							</span>
							<c:if test="${not empty tubuyaki.gpslocation}">
							<span class="twit_info">${tubuyaki.gpslocation }</span>
							</c:if>
							<c:if test="${empty tubuyaki.favolite}">
								<c:set var = "favoriteMsg" value="お気に入りに登録" />
							</c:if>
							<c:if test="${not empty tubuyaki.favolite}">
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
								<span class="twit_info" onclick="changeRepform(${tubuyaki.murmurid})">返信</span>

							</c:if>

							<!-- 自分のつぶやきだった場合削除リンクをつける -->
							<c:if test="${tubuyaki.tuser.userid==mine}">
								<s:link href="/main/delete/${tubuyaki.murmurid}"
									styleClass="twit_info twit_info_link">削除</s:link>
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
			<div id="lastLine" class="0">　</div>
		</div>

		</c:if>

</tiles:put>
</tiles:insert>
</body>
</html>