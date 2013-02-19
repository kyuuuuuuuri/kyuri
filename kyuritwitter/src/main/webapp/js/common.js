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

});