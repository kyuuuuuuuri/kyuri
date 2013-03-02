$(function(){

	$("#searchAll").attr("disabled","true");
	$(this).val("");

	$("#searchAllTwit").keyup(function(event){

		$("#twit_button").removeAttr("disabled");
		if($(this).val().length == 0){
			$("#searchAll").attr("disabled","true");
		}else{
			$("#searchAll").removeAttr("disabled");
		}
	});


	$("#dropDownMenu").hide();

});

function useList(id){
	var $change = $("#useAndunUseListButton");
	alert($change.text());

	$.ajax({
		type : "POST",
		url : "/kyuritwitter/userpage/doUseList",
		data : {
			'listIDAjax' : id
		},
		success : function() {
			$change
			.html('<input type="button" class="btn btn-danger" onclick="unUseList('
			+ id
			+ ')" value="このリストを削除する" />');
			},
			error : function() {
				alert("すでに登録済みです");
		}
	});
}

function unUseList(id){
	var $change = $("#useAndunUseListButton");
	alert($change.text());

	$.ajax({
		type : "POST",
		url : "/kyuritwitter/userpage/doUnUseList",
		data : {
			'listIDAjax' : id
		},
		success : function() {
			$change
			.html('<input type="button" class="btn btn-info" onclick="useList('
			+ id
			+ ')" value="このリストを使用する" />');
			},
			error : function() {
				alert("登録していません");
		}
	});
}