$(function(){

	init();
	//button_flag();

	$('#username').each(function(){
		$(this).bind('keyup', check_username(this));
	});
	$('#usernick').each(function(){
		$(this).bind('keyup', check_usernick(this));
	});
	$('#password').each(function(){
		$(this).bind('keyup', check_password(this));
	});
	$('#mailad').each(function(){
		$(this).bind('keyup', check_mailad(this));
	});
	$('#secret_answer').keyup(function(){
		var $answer = $('#secret_answer'),
		$answer_span = $("#answer_span"),
		str = $answer.val();
		if(str.length == 0){
			$answer_span.attr("class", "alert_css").text("秘密の質問の答えを入力してください");
		}else if(str.length > 60){
			$answer_span.attr("class", "alert_css").text("60文字以下で入力してください");
		}else{
			$answer_span.attr("class", "success_css").text("確認しました");
		}
	});

	$(".user_reg").submit(function() {
		if ($('#ajax_username').attr('class') == "success_css" &&
				$('#ajax_usernick').attr('class') == "success_css" &&
				$('#ajax_password').attr('class') == "success_css" &&
				$('#ajax_mailad').attr('class') == "success_css" &&
				$('#answer_span').attr('class') == "success_css"
		){
			alert("確認しました");
			return true;
		}else{
			alert("入力値を確認してください");
			return false;
			}
	});

});

function init(){
	checkAjax('username');
	checkAjax('usernick');
	checkAjax('password');
	checkAjax('mailad');

}


function button_flag(){
	alert($('#ajax_username').attr('class'));
	if($('#ajax_username').attr('class') == "success_css" &&
			$('#ajax_usernick').attr('class') == "success_css" &&
			$('#ajax_password').attr('class') == "success_css" &&
			$('#ajax_mailad').attr('class') == "success_css"
	){
		alert("OK");

	};
}

function checkAjax(selector){

	var javaurl = selector + "_check";
	var this_selector= "#" + selector;
	var return_selector = "#" + selector + "_span";
	var str = $(this_selector).val();

	$.ajax({
		   type: "POST",
		   url: javaurl,
		   data: {'param': str},
		   success: function(msg){
			   $(return_selector).html(msg);

		   }
		 });
}


function check_username(elm){
	var v, old = elm.value;
	return function(){
		if(old != (v=elm.value)){
			str = $(this).val();
			//$("#username_span").text(str);
			$.ajax({
				   type: "POST",
				   url: "username_check",
				   data: {'param': str},
				   success: function(msg){
					   $("#username_span").html(msg);

				   }
				 });

		}
	}
}
function check_usernick(elm){
	var v, old = elm.value;
	return function(){
		if(old != (v=elm.value)){
			str = $(this).val();
			//$("#usernick_span").text(str);
			$.ajax({
				   type: "POST",
				   url: "usernick_check",
				   data: {'param': str},
				   success: function(msg){
					   $("#usernick_span").html(msg);
				   }
				 });
		}
	}
}
function check_password(elm){
	var v, old = elm.value;
	return function(){
		if(old != (v=elm.value)){
			str = $(this).val();
			//$("#password_span").text(str);
			$.ajax({
				   type: "POST",
				   url: "password_check",
				   data: {'param': str},
				   success: function(msg){
					   $("#password_span").html(msg);
				   }
				 });
		}
	}
}
function check_mailad(elm){
	var v, old = elm.value;
	return function(){
		if(old != (v=elm.value)){
			str = $(this).val();
			$.ajax({
				type: "POST",
				url: "mailad_check",
				data: {'param': str},
				success: function(msg){
					$("#mailad_span").html(msg);
				}
			});
		}
	}
}
