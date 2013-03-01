$(function(){

});


function insertToList(userid, listid){
	$change = $("div#" + userid +" div.changeListButton");
	alert($change.val() + userid);
	$.ajax({
		type : "POST",
		url : "setUserToList",
		data : {
			'userid' : userid,
			'listid' : listid
		},
		success : function() {
				$change
				.html('<input type="button" class="changeUserToListButton btn btn-danger" onclick="deleteFromList(' + userid + ',' + listid + ')" value="リストから削除" />');
		},
		error : function() {
			alert("このユーザはすでに登録されています");
		}
	});
}

function deleteFromList(userid, listid){
	$change = $("div#" + userid +" div.changeListButton");
	alert($change.val() + userid);
	$.ajax({
		type : "POST",
		url : "delUserFromList",
		data : {
			'userid' : userid,
			'listid' : listid
		},
		success : function() {
			$change
			.html('<input type="button" class="changeUserToListButton btn btn-info" onclick="insertToList(' + userid + ',' + listid + ')" value="リストに追加" />');
		},
		error : function() {
			alert("登録されていないユーザを削除しようとしています");
		}
	});
}
