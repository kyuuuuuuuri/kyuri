<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>

<div class="sidebar">
	<c:if test="${menuFlag==0}">

		<table class="table table-bordered">
			<thead>
				<tr>
					<th colspan="3"><span class="pImg"><html:img
								src="${pageContext.request.contextPath}/main/showUserImg/${mydata.userid}"
								width="50" height="50" /></span> <span class="pUsernick">${mydata.usernick}</span></th>
				</tr>
			</thead>
			<tr>
				<td><p>${mydata.follow }</p>
					<s:link href="followlist/${mydata.userid}/follow" >フォロー</s:link></td>
				<td><p>${mydata.followed }</p>
					<s:link href="followedlist/${mydata.userid}/followed" >フォロワー</s:link></td>
				<td><p>${mydata.postNum }</p>
					<s:link href="userpage/${mydata.usernick}" >投稿数</s:link></td>
			</tr>
			<tr id="twitFormtrId">
				<td colspan="3">

						<s:form styleId="twitter_form">
							<textarea id="twit_textarea" name="tubuyaki" rows="1"></textarea>
							<span id="gps" class="btn">GPS</span>

							<div class="gpsmenu btn-group">

							<button class="btn btn-success dropdown-toggle" data-toggle="dropdown">GPS<span class="caret"></span></button>
								<ul class="dropdown-menu">
									<li><a id="address"></a></li>
									<li class="divider"></li>
									<li><a id="noGPS">GPSを無効にする</a></li>
								</ul>
							</div>
							<html:hidden styleId = "latitude" property="latitude" value=""></html:hidden>
							<html:hidden styleId = "longitude" property="longitude" value=""></html:hidden>
							<html:hidden styleId = "imgHiddenUrl" property = "imgUrl" value=""></html:hidden>

						<span id="pict" class="btn">pict</span>
							<span id="input_text_size">140</span>
							<s:submit styleId="twit_button" styleClass="btn btn-info"
								property="insertTwit">ツイート</s:submit>
						</s:form>
						<div id="imgPost">
							<form id="uploadForm" action="imagePost" method="post" enctype="multipart/form-data" target="ajaxPostImage">
								<input type="file" id="fileSelect" name="imageFile" />
								<input type="reset" id="reset" />
							</form>
						</div>
							<iframe name="ajaxPostImage" width="200" height="70"></iframe>

				</td>
			</tr>
		</table>

		<div id="trand">
		<p id="trandTitle">トレンド</p>
		<c:forEach var="trand" items="${trandList}">

			<div class="trandBody"><s:link href="/searchtwit/trandSearch/${trand}">${trand}</s:link></div>
		</c:forEach>
		</div>

		<div id="recommend">
		<div id="recommendTitle">おすすめユーザ / <s:link styleId="moreRecommend" href="recommendAll">もっと見る</s:link></div>
		<c:if test="${not empty recommendUser }">

			<c:forEach var="recom" items="${recommendUser}">
				<div><s:link href="userpage/${recom.ftuser.usernick}">${recom.ftuser.usernick}</s:link></div>

				<div class="followdesc">${recom.tuser.usernick} がフォローしています</div>

			</c:forEach>

		</c:if>

		<c:if test="${empty recommendUser }">
		<c:forEach var="recom" items="${recommendUserNull}">
			<div><s:link href="userpage/${recom.usernick}">${recom.usernick}</s:link></div>
		</c:forEach>
		</c:if>

		</div>
	</c:if>
	<c:if test="${menuFlag == 1 }">
		menu2
	</c:if>

	<c:if test="${menuFlag == 2 }">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th colspan="3"><span class="pImg">
					<html:img src="${pageContext.request.contextPath}/main/showUserImg/${mydata.userid}" width="50" height="50" /></span>
					<span class="pUsernick">${mydata.usernick}</span></th>
				</tr>
			</thead>
			<tr><td><span>${mydata.follow }</span>　
				<s:link href="followlist/${mydata.userid}/follow" >フォロー</s:link></td></tr>

			<tr><td><span>${mydata.followed }</span>　
				<s:link href="followedlist/${mydata.userid}/followed" >フォロワー</s:link></td></tr>

			<tr><td><span>${mydata.postNum }</span>　
				<s:link href="userpage/${mydata.usernick}" >投稿数</s:link></td></tr>
			<tr><td><span><s:link href="toList/${mydata.userid}">リスト</s:link></span></td></tr>
			<tr><td><span><s:link href="favoliteLine/">お気に入り</s:link></span></td></tr>
			<tr><td><span><s:link href="replyLine/">返信関連</s:link></span></td></tr>

		</table>
	</c:if>

	<c:if test="${menuFlag==3 }">

		<table class="table table-bordered">
			<thead>
				<tr>
					<th colspan="3"><span class="pImg">
					<html:img src="${pageContext.request.contextPath}/main/showUserImg/${mydata.userid}" width="50" height="50" /></span>
					<span class="pUsernick">${mydata.usernick}</span></th>
				</tr>
			</thead>
			<tr><td><span>${mydata.follow }</span>　
				<s:link href="followlist/${mydata.userid}/follow" >フォロー</s:link></td></tr>

			<tr><td><span>${mydata.followed }</span>　
				<s:link href="followedlist/${mydata.userid}/followed" >フォロワー</s:link></td></tr>

			<tr><td><span>${mydata.postNum }</span>　
				<s:link href="userpage/${mydata.usernick}" >投稿数</s:link></td></tr>
			<tr><td><span><s:link href="/userpage/toList/${mydata.userid}">リスト</s:link></span></td></tr>
			<tr><td><span><s:link href="favoliteLine/${mydata.userid}">お気に入り</s:link></span></td></tr>
			<c:if test="${mydata.userid == mine}">
			<tr><td><span><s:link href="replyLine/${mydata.userid}">返信関連</s:link></span></td></tr>
			</c:if>
		</table>

	</c:if>

	<c:if test="${menuFlag==4}">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th colspan="3"><span class="pImg">
					<html:img src="${pageContext.request.contextPath}/main/showUserImg/${showTlist.userid}" width="50" height="50" /></span>
					<span>${showTlist.listname}</span>
					<!--  <span class="pUsernick">がこのリストを作成しました</span></th>-->
				</tr>
			</thead>
			<tr><td>
			<div id="useAndunUseListButton">
				<c:if test="${showTlist.userid != mine }">
				<c:if test="${UserUseThisList == 0 }">
					<span class="btn btn-info" onclick="useList(${showTlist.listid})" >このリストを使用する</span>
				</c:if>
				<c:if test="${UserUseThisList == 1 }">
					<span class="btn btn-danger" onclick="unUseList(${showTlist.listid})" >このリストを使用しない</span>
				</c:if>
				</c:if>
			</div>

			</td></tr>
			<tr><td>
				<s:link href="showListUser/${showTlist.listid}">ツイート</s:link>
			</td></tr>
			<tr><td>
				<s:link href="userInList/${showTlist.listid}">このリストに登録されているユーザ</s:link>
			</td></tr>
		</table>

	</c:if>
</div>