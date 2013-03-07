
	<c:forEach var="followList" items="${followList}">
		<div class="twitmain">
		<span class="pImg">
					<html:img src="${pageContext.request.contextPath}/main/showUserImg/${followList.ftuser.userid}"
							width="50" height="50" />
		</span>
			<s:link href="userpage/${followList.ftuser.usernick}"> ${followList.ftuser.usernick} </s:link>

			<p>${followList.ftuser.newMur}</p>
			<p class="newTwitTime">
			<fmt:formatDate value="${followList.ftuser.newMurD}" pattern="yyyy年MM月dd日 HH時mm分ss秒" /></p>

			<div class="followButton">
				<c:if test="${followList.ftuser.userid != mine }">
					<c:if test="${followList.ftuser.ffollowList[0].userid != mine}">
						<input id ="${followList.ftuser.userid}followSub" type="button" class="btn btn-primary" onclick="follow(${followList.ftuser.userid})" value="フォロー" />
					</c:if>
					<c:if test="${followList.ftuser.ffollowList[0].userid == mine}">
						<input id="${followList.ftuser.userid}unfollowSub" type="button" class="btn btn-danger" onclick="unfollow(${followList.ftuser.userid})" value="フォロー解除" />
					</c:if>
				</c:if>
			</div>
		</div>
		<p class="timeLine_border"></p>

	</c:forEach>
