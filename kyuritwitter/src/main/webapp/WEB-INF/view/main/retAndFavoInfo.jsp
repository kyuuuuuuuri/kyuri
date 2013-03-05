<div class="retAndFavoArea">

<table>
		<thead>

			<tr>
				<c:if test="${favoNum != 0 }">
					<th class="favoInfo"><s:link
							href="/followlist/showFavoList/${murid}">
							<span>${favoNum}</span>件お気に入り
					</s:link></th>
				</c:if>

				<c:if test="${retNum != 0 }">
					<th class="retInfo"><s:link
							href="/followlist/showRetList/${murid}">
							<span>${retNum}</span>件リツイート
					</s:link></th>
				</c:if>
			</tr>
		</thead>

	</table>
</div>