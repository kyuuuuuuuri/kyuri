$(function(){

	init();

	$("#uploadSubmit").submit(function(){
		alert("ok");
		return false;
	});

	$("#change1").click(function(){
		$(this).attr('class','active');
		$("#change2").attr('class','');
		$("#change3").attr('class','');
	});

	$("#change2").click(function(){
		$(this).attr('class','active');
		$("#change1").attr('class','');
		$("#change3").attr('class','');
		});

	$("#change3").click(function(){
		$(this).attr('class','active');
		$("#change2").attr('class','');
		$("#change1").attr('class','');
		});

	$("#username").focus(function(){

		//$(this).val("");
		//$("#changeUsernameSubmit").attr("disabled","true");

			$("#username").each(function() {
			$(this).keyup(function(event) {

				if ($(this).val().length == 0) {
					$("#changeUsernameSubmit").attr("disabled", "true");
				} else {
					$("#changeUsernameSubmit").removeAttr("disabled");
				}
			});
		});
	});

});

function init(){

}
