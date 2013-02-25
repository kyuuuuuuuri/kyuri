

<!-- つぶやきが一件もなかった場合 -->
<c:if test="${empty tuserList }">
	<div>
		ユーザがいません
	</div>
</c:if>

<c:if test="${!empty tuserList}">

<c:forEach var="userlist" items="${tuserList}">

	<div>
		<html:link href="userpage/${userlist.usernick}">${userlist.usernick}</html:link>

	</div>

	<p class="searchUser_border"></p>

</c:forEach>
	<div>
		<html:link styleId="searchMore" href="search/">もっと見る</html:link>
	</div>

</c:if>