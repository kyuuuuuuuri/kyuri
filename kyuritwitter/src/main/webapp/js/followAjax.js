function scrollEvent(){

	var currentLocation;
	var scrollHeight;
	//var clientHeight = document.body.clientHeight;
	var clientHeight;

	$(window).scroll(function(){
		clientHeight = window.innerHeight
		scrollHeight = document.body.scrollHeight || document.docomentElement.scrollHeight;
		currentLocation = $(window).scrollTop();
		var remain = scrollHeight - clientHeight - currentLocation;
		if(remain <= 20){
			var page =$("#lastLine").attr("class");
			getOldTwit();
		}
	});
}

function getOldTwit(){
	var lastid = $(".twitmain:last").attr("id"),
		mydataId   = $("#twitTitle").text();

	alert(lastid);
	$.ajax({
		type:"POST",
		url: "followOld",
		data:{
			'lastFollowId':lastid,
			'mydataId': mydataId
		},
		dataType:"html",
		success: function(data, dataType){

			$("#lastLine").before(data);
			initWhenAjaxDo();
			mouseHoverEvent();

		},
		error: function(){
			alert("問題が発生しました");
		}
	});
}