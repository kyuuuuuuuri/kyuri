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
								width="50" height="50" /></span> <span class="pUsernick">${mydata.usernick
							}</span></th>
				</tr>
			</thead>
			<tr>
				<td><p>${mydata.follow }</p>
					<s:link href="/followlist/followpage/${mydata.userid}"
						style="text-decoration: none">フォロー</s:link></td>
				<td><p>${mydata.followed }</p>
					<s:link href="/followlist/followedlist/${mydata.userid}"
						style="text-decoration: none">フォロワー</s:link></td>
				<td><p>${mydata.postNum }</p>
					<s:link href="showdata/${mydata.userid}"
						style="text-decoration: none">投稿数</s:link></td>
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

							<span id="pict" class="btn">pict</span>
							<span id="input_text_size">140</span>
							<s:submit styleId="twit_button" styleClass="btn btn-info"
								property="insertTwit">ツイート</s:submit>
						</s:form>

				</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${menuFlag==1 }">
		menu2
	</c:if>

</div>