
			<c:forEach var="tubuyaki" items="${murmurList}">

						<div id="${tubuyaki.murmurid}" class="twitmain">
							<span class="pImg"> <html:img
									src="${pageContext.request.contextPath}/main/showUserImg/${tubuyaki.tuser.userid}"
									width="50" height="50" />
							</span>
							<span class="usernick">
							<s:link href="#">
								<span class="usernickLink">${tubuyaki.tuser.usernick}</span>
							</s:link>
							</span>
							<span class="username">${tubuyaki.tuser.username}</span>
							<p class="twitid">
							${f:h(tubuyaki.murmur)}
							</p>
							<span id="${tubuyaki.murmurid}open" class="open_details_twit twit_info_link" onclick="changeRepform(${tubuyaki.murmurid})">開く</span>
							<span class="date twit_info">
							<fmt:formatDate value="${tubuyaki.dateTime}" pattern="yyyy年MM月dd日 HH時mm分ss秒" />
							</span>
							<span class="favorite twit_info twit_info_link">お気に入りに登録</span>
							<!-- 自分のつぶやきじゃない場合リツイートと返信をつける -->
							<c:if test="${fFlag==0 && tubuyaki.tuser.userid!=mine}">
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