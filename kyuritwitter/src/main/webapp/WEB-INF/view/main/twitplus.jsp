<div class="twitplus">
	<c:forEach var="tubuyaki" items="${murmurList}">

		<div id="${tubuyaki.murmurid}p" class="twitmainplus">
			<span class="pImg">
				<html:img src="${pageContext.request.contextPath}/main/showUserImg/${tubuyaki.tuser.userid}" width="50" height="50" />
			</span>
			<span class="usernick">
				<s:link href="userpage/${tubuyaki.tuser.usernick}"><span class="">${tubuyaki.tuser.usernick}</span></s:link>
			</span>
			<span class="username">${tubuyaki.tuser.username}</span>
			<p class="twitid">${f:h(tubuyaki.murmur)}</p>
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

			<span class="favorite twit_info twit_info_link" onclick="favoriteclickInrep(${tubuyaki.murmurid})">${f:h(favoriteMsg)}</span>

			<!-- 自分のつぶやきじゃない場合リツイートと返信をつける -->
			<c:if test="${fFlag==0 && tubuyaki.tuser.userid!=mine}">
			<s:link href="/main/retwit/${tubuyaki.murmurid }" styleClass="twit_info twit_info_link">リツイート</s:link>
			</c:if>

			<!-- 自分のつぶやきだった場合削除リンクをつける -->
			<c:if test="${tubuyaki.tuser.userid==mine}">
			<s:link href="/main/delete/${tubuyaki.murmurid}" styleClass="twit_info twit_info_link">削除</s:link>
			</c:if>

		</div>

		<p class="timeLine_border"></p>

	</c:forEach>
	</div>