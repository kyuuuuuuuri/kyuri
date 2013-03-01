$(function(){

	var $pict = $("#pict"),
		$imgSelect = $("#fileSelect"),
		$imgForm = $("#uploadForm"),
		$iframe = $('iframe[name="ajaxPostImage"]');

	$pict.click(function(){
		$imgSelect.click();

		return false;

	});
	$imgSelect.change(function(){
		$imgForm.submit();
		$iframe.show();
		$pict.attr("class", "btn btn-warning");

	});

	$imgForm.submit(function(){
		// submitされた時点で、loadイベントをbind
		$iframe.unbind().bind('load', function() {
			var response = $iframe.contents();
			var imgUrl = response.find('#imgFileUrl').text();
			$("#imgHiddenUrl").val(imgUrl);

			// responseを調べて、送信後の処理を完結させる
			console.log(response);

			deleteImg();
		});

	});

});

function deleteImg(){
	var $iframe = $('iframe[name="ajaxPostImage"]'),
		response = $iframe.contents(),
		$deleteimg = response.find("#deleteImg"),
		$pict = $("#pict");

	$deleteimg.bind("click", function(){

		$.ajax({
			type:"POST",
			url:"deleteImg",
			data:{
				"deleteurl":$("#imgHiddenUrl").val()
			},
			dataType:"html",
			success: function(data,dataType){
				$iframe.hide();
				$pict.attr("class", "btn");
				$("#imgHiddenUrl").val("");
				$("#reset").click();
			},
			error: function(){
				alert("問題が発生しました");
			}
		});
	});
}

