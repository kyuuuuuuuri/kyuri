$(function(){

	var $deleteImg = $('#deleteImg'),
		$imgFileUrl = $('#imgFileUrl');


	$deleteImg.click(function(){
		$.ajax({
			type:"POST",
			url:"repListAfter",
			data:{
				"deleteurl":$imgFileUrl.text();
			},
			dataType:"html",
			success: function(data,dataType){

			},
			error: function(){
				alert("問題が発生しました");
			}
		});
	});


});

