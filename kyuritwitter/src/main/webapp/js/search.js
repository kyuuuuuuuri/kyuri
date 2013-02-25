$(function(){

});


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