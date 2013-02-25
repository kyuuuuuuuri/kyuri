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

function follow(id){

	$.ajax({
		type:"POST",
		url:"toFollow",
		data:{
			"followUserId":id
		},
		dataType:"text",
		success: function(data,dataType){
			$("#" + id + "followSub")
			.attr("id", id + "unfollowSub")
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
	$.ajax({
		type:"POST",
		url:"toUnFollow",
		data:{
			"unFollowUserId":id
		},
		dataType:"text",
		success: function(data,dataType){
			$("#" + id + "unfollowSub")
			.attr("id", id + "followSub")
			.attr("class","btn btn-primary")
			.attr("onclick", "follow(" + id + ")")
			.attr("value","フォロー");
		},
		error: function(){
			alert("問題が発生しました");
		}
	});
}