$(function(){

	mainInit();
	menu();
	scrollEvent();
	twitsubmit();
});

function mainInit(){
	//ボタンを隠す
	$("#gps").hide();
	$("#pict").hide();
	$("#twit_button").hide();
	$("#input_text_size").hide();
	$("#existNewtwit").hide();
	$(".gpsmenu").hide();


	$('#twit_textarea').val("ツイートする")
	.css("color", "#969696");

}

function twitsubmit(){

	$("#twitter_form").submit(function(){
		//alert("submit cansel");
		var topId     = $(".twitmain:first").attr("id");
		var tubuyaki  = $("#twit_textarea").val();
		var location  = $("#address").text();
		var latitude  = $("#latitude").val();
		var longitude = $("#longitude").val();
		var imgurl    = $("#imgHiddenUrl").val();
		alert(imgurl);

		$.ajax({
			type:"POST",
			url: "ajaxSubmit",
			data:{
				"tubuyaki":tubuyaki,
				"Location":location,
				"topId":topId,
				"Latitude":latitude,
				"Longitude":longitude,
				"imgurl":imgurl
			},
			dataType:"html",
			success: function(data, dataType){
				alert("成功しました");
				if($("#existNewtwit").get(0)){
					$("#existNewtwit").replaceWith(data);
					mainInit();
					initWhenAjaxDo();
					mouseHoverEvent();

				}else if($("#noTwit").get(0)){
					$("#noTwit").replaceWith(data);
					mainInit();
					initWhenAjaxDo();
					mouseHoverEvent();
				}else{
					$("#twitTitle").after(data);
					initWhenAjaxDo();
					mouseHoverEvent();
				}
			},
			error: function(){
				alert("問題が発生しました");
			}
		});
		return false;
	});
}

window.onload = function(){
	setInterval("checkNewTwit()",30*1000);
};


function menu(){
	$("#twit_textarea").focus(function(){
		$(this).attr("rows", "5");
		if(this.value=="ツイートする"){
			$(this).val("").css("color", "#000");
			if($("#address").text() ==""){
				$("#gps").show();
			}else{
				$(".gpsmenu").show();
			}
			$("#pict").show();
			$("#twit_button").show();
			$("#input_text_size").show();

			$("#twit_textarea").each(function(){
				var size;
				$(this).keyup(function(event){
					size = 140-$(this).val().length;
					$("#input_text_size").text(size);
					$("#twit_button").removeAttr("disabled");
					if(size == 140 || size < 0){
						$("#twit_button").attr("disabled","true");
					}
				});
			});
		}
	});

	$("#gps").click(function(){

			$(this).hide();
			$(".gpsmenu").show();

			getGps();
	});

	$("#noGPS").click(function(){
		$(".gpsmenu").hide();
		$("#address").text("");
		$("#latitude").val("");
		$("#longitude").val("");
		$("#gps").show();
	});

	$("#twit_textarea").blur(function(){
		$(this).css("background-color", "#fff");

		if(this.value==""){
			$("#gps").hide();
			$(".gpsmenu").hide();
			$("#pict").hide();
			$("#twit_button").hide();
			$("#input_text_size").hide();
			$(this).val("ツイートする").attr("rows", "1")
			.css("color","#969696");
		}
		if(this.value!="ツイートする"){
			$(this).css("color","#000");
			if($("#address").text() ==""){
				$("#gps").show();
			}else{
				$(".gpsmenu").show();
			}
			$("#pict").show();
			$("#twit_button").show();
			$("#input_text_size").show();
		}
	});

}

//つぶやきの更新があるかどうかをチェックする
function checkNewTwit(){
	topId = $(".twitmain:first").attr("id");

	$.ajax({
		type:"POST",
		url: "checkNewTwit",
		data:{
			'topId':topId
		},
		dataType:"text",
		success: function(data, dataType){
			if($("#existNewtwit").get(0)){

			}else{
				if(data!=null){
					$("#twitTitle").after(data);
					getNewTwit();
				}
			}
		}
	});
}

function getNewTwit(){

	topId = $(".twitmain:first").attr("id");
	$("#existNewtwit").click(function(){
		$.ajax({
			type:"POST",
			url: "NewTwitList",
			data:{
				'topId':topId
			},
			dataType:"html",
			success: function(data, dataType){
				//alert("yess");

				$("#existNewtwit").replaceWith(data);
				initWhenAjaxDo();
				mouseHoverEvent();

			},
			error: function(){
				alert("問題が発生しました");
			}
		});
	});
}

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
	var lastid = $(".twitmain:last").attr("id");
	//alert(lastid);
	$.ajax({
		type:"POST",
		url: "loadOldTwit",
		data:{
			'lastId':lastid
		},
		dataType:"html",
		success: function(data, dataType){

			$("#lastLine").before(data);
			initWhenAjaxDo();
			mouseHoverEvent();
		},
		error: function(){
		}
	});
}

