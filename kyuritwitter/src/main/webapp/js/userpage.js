$(function(){

	searchUser();

});


function searchUser(){

	var searchWord;

	$("#searchAllTwit").keyup(function(event) {
		searchWord = $("#searchAllTwit").val();
		//alert(searchWord);

		if(searchWord == ""){
			$("#dropDownMenu").hide();
		}else {
			$.ajax({
				type : "POST",
				url : "searchUsershort",
				data : {
					'searchword' : searchWord
				},
				dataType : "html",
				success : function(data, dataType) {
					$("#dropDownMenu").html(data);
					$("#searchMore").attr("href", "search/"+searchWord);
					$("#dropDownMenu").show();
				},
				error : function() {
					alert("問題が発生しました");
				}
			});
		}
	});

}

//openメソッド
function openRep(id){

	if($('.open_details_twit', "#"+id).attr("id") == id+"open"){
		$('.open_details_twit', "#"+id).attr("id", id+"close").text("閉じる");
		$(".twitplus").remove();

		$.ajax({
			type:"POST",
			url:"repListAfter",
			data:{
				"tubuyakiId":id
			},
			dataType:"html",
			success: function(data,dataType){
				// alert("OK" + id);
				$("#"+id).append(data);
				initWhenAjaxDo();

				beforeQuery(id);
			},
			error: function(){
				alert("問題が発生しました");
			}
		});


	}else if($('.open_details_twit', "#"+id).attr("id") == id+"close"){
		$('.open_details_twit', "#"+id).attr("id", id+"open").text("開く");

		$(".twitplus").remove();

		$("#" + id).css({
			"margin-top" : "0px"
		});
		$("#" + id + "r").css("margin-bottom","0px");
		$("#"+ id +"r").hide().css("margin-bottom","0px");


	}else{
		alert("問題が起こりました");
	}

}

// ユーザをブロックする
function block(id) {
	if (confirm("本当にこのユーザをブロックしますか？")) {

		$.ajax({
			type : "POST",
			url : "blockUser",
			data : {
				"blockUserid" : id
			},
			dataType : "text",
			success : function(data, dataType) {
				$("#blockButton")
				.attr("id", "unBlockButton")
				.attr("class", "btn btn-success")
				.attr("onclick","unBlock(" + id + ")")
				.attr("value", "ブロックを解除する");
			},
			error : function() {
				alert("問題が発生しました");
			}
		});

	} else {
		return false;
	}
}

//ブロックを解除する
function unBlock(id){
	$.ajax({
		type:"POST",
		url:"unBlockUser",
		data:{
			"blockUserid":id
		},
		dataType:"text",
		success: function(data,dataType){
			$("#unBlockButton")
			.attr("id", "unfollowSub")
			.attr("class","btn btn-danger")
			.attr("onclick", "block(" + id + ")")
			.attr("value","ブロックする");
		},
		error: function(){
			alert("問題が発生しました");
		}
	});
}

function follow(id){

	$.ajax({
		type:"POST",
		url:"toFollow",
		data:{
			"followUserId":id
		},
		dataType:"text",
		success: function(data,dataType){
			$("#followSub")
			.attr("id", "unfollowSub")
			.attr("class","btn btn-danger")
			.attr("onclick", "unfollow(" + id + ")")
			.attr("value","フォロー解除");
		},
		error: function(){
			alert("問題が発生しました");
		}
	});
}

function unfollow(id){

	if(confirm("本当にフォローから外しますか？")){
		$.ajax({
			type : "POST",
			url : "toUnFollow",
			data : {
				"unFollowUserId" : id
			},
			dataType : "text",
			success : function(data, dataType) {
				$("#unfollowSub").attr("id", "followSub").attr(
						"class", "btn btn-primary").attr("onclick","follow(" + id + ")").attr("value", "フォロー");
			},
			error : function() {
				alert("問題が発生しました");
			}
		});
	}else{
		return false;
	}

}