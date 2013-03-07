
	<c:forEach var="followList" items="${followList}">
		<div class="twitmain">
		<span class="pImg">
					<html:img src="${pageContext.request.contextPath}/main/showUserImg/${followList.tuser.userid}"
							width="50" height="50" />
					</span>
			<s:link href="userpage/${followList.tuser.usernick}"> ${followList.tuser.usernick} </s:link>
			<p>${followList.tuser.newMur}</p>
			<p class="newTwitTime"><fmt:formatDate value="${followList.tuser.newMurD}" pattern="yyyy年MM月dd日 HH時mm分ss秒" /></p>

			<div class="followButton">
				<c:if test="${followList.tuser.userid != mine }">
					<c:if test="${followList.tuser.ffollowList[0].userid != mine}">
						<input id ="${followList.tuser.userid}followSub" type="button" class="btn btn-primary" onclick="follow(${followList.tuser.userid})" value="フォロー" />
					</c:if>
					<c:if test="${followList.tuser.ffollowList[0].userid == mine}">
						<input id="${followList.tuser.userid}unfollowSub" type="button" class="btn btn-danger" onclick="unfollow(${followList.tuser.userid})" value="フォロー解除" />
					</c:if>
				</c:if>
			</div>
		</div>
		<p class="timeLine_border"></p>

	</c:forEach>
