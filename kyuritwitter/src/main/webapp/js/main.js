$(function(){

	init();

	$("#twit_textarea").focus(function(){
		$(this).attr("rows", "5");
		if(this.value=="ツイートする"){
			$(this).val("").css("color", "#000");
			$("#gps").show();
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
	$("#twit_textarea").blur(function(){
		$(this).css("background-color", "#fff");
		if(this.value==""){
			$("#gps").hide();
			$("#pict").hide();
			$("#twit_button").hide();
			$("#input_text_size").hide();
			$(this).val("ツイートする").attr("rows", "1")
			.css("color","#969696");
		}
		if(this.value!="ツイートする"){
			$(this).css("color","#000");
			$("#gps").show();
			$("#pict").show();
			$("#twit_button").show();
			$("#input_text_size").show();
		}
	});

	mouseHoverEvent();
	scrollEvent();


});

window.onload = function(){
	setInterval("checkNewTwit()",30*1000);
};

function init(){

	//ボタンを隠す
	$("#gps").hide();
	$("#pict").hide();
	$("#twit_button").hide();
	$("#input_text_size").hide();
	$("#existNewtwit").hide();

	$('#twit_textarea').val("ツイートする")
	.css("color", "#969696");

	$("div.repform").hide();

	//＠のついた文字列をリンク付けする
	//hashタグにリンク付けする
	$('.twitid').each(function(){
		var text = $(this).text();
		text = text.replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/"/g,"&quot;").replace(/'/g,"&#039;");
		text = text.replace (/(@)([A-Za-z0-9]{4,20})/g, '<a href="showdata/$2">$1$2</a>');
		text = text.replace(/(\s#)([a-zA-Zあ-んア-ン_]+)/g,'<a href="showHashData/$2">$1$2</a>');
		text = text.replace(/(https?|ftp)(:\/\/[-_.!~*\'()a-zA-Z0-9;\/?:\@&=+\$,%#]+)/g,'<a href="$1$2">$1$2</a>');
		$(this).html(text);
	});

	$("#twit_button").attr("disabled","true");
}

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
		},
		error: function(){
			alert("問題が発生しました");
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

function initWhenAjaxDo(){

	$("div.repform").hide();

	//＠のついた文字列をリンク付けする
	//hashタグにリンク付けする
	$('p').each(function(){
		//var text=$(this).text();
		var text = $(this).text();
		text = text.replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/"/g,"&quot;").replace(/'/g,"&#039;");
		text = text.replace (/(@)([A-Za-z0-9]{4,20})/g, '<a href="showdata/$2">$1$2</a>');
		text = text.replace(/(\s#)([a-zA-Zあ-んア-ン_]+)/g,'<a href="showHashData/$2">$1$2</a>');
		text = text.replace(/(https?|ftp)(:\/\/[-_.!~*\'()a-zA-Z0-9;\/?:\@&=+\$,%#]+)/g,'<a href="$1$2">$1$2</a>');
		$(this).html(text);
		});

}

function mouseHoverEvent(){

	$(".twitmain").hover(
			function(){
				var id= $(this).attr("id");
				$("#" + id).css("background-color","#EBEBEB");
				$("#" + id + " .twit_info").css("visibility","visible");
			},
			function(){
				var id= $(this).attr("id");
				$("#" + id).css("background-color","#F4F4F4");
				$("#" + id + " .twit_info").css("visibility","hidden");

			}
		);
}

function OpenWin(index){
	var selecter;
	selecter="#"+ index;
	var userid=$(selecter).attr("style");
	userid="newwindow/"+userid;
	win=window.open(userid,"new","width=400,height=400, menubar=no, toolbar=no, location=no");
	win.moveTo(50,50);
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
	alert(lastid);
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
			alert("問題が発生しました");
		}
	});
}


//replyanメソッド
function replyan(usernick){
	var selecter;
	selecter="#"+ usernick;
	var nick="＠"+$(selecter).text()+"　";
	$("input[name='tubuyaki']").val(nick).css("color", "#000");
	$("input[name='tubuyaki']").focus();
}

//replyformメソッド
function changeRepform(id){
	var repUser = $(".usernickLink","#"+id).text();

	if($('.open_details_twit', "#"+id).attr("id") == id+"open"){
		$('.open_details_twit', "#"+id).attr("id", id+"close").text("閉じる");

		$("#" + id).css({
			"margin-top" : "10px"
		});
		$("#" + id + "r").show();
		$("#" + id + "r").css("margin-bottom","10px");
		$(".rep_textarea", "#" + id + "r").val("@"+ repUser + " ");
		replyForm(id);
	}else if($('.open_details_twit', "#"+id).attr("id") == id+"close"){
		$('.open_details_twit', "#"+id).attr("id", id+"open").text("開く");

		$("#" + id).css({
			"margin-top" : "0px"
		});
		$("#" + id + "r").css("margin-bottom","0px");
		$("#"+ id +"r").hide().css("margin-bottom","0px");
	}else{
		alert("問題が起こりました");
	}

}

function replyForm(id){
	var size = 140 - $(".rep_textarea", "#"+id+"r").val().length;
	$(".rep_text_size", "#"+id+"r" ).text(size);

	$(".rep_textarea", "#" + id + "r").focus(function(){
		$(this).attr("rows", "3");

		$(".rep_textarea", "#"+id+"r").each(function() {

		$(this).keyup(function(event) {

			size = 140 - $(this).val().length;
			$(".rep_text_size", "#"+id+"r" ).text(size);
			$(".rep_twit_btn", "#"+id+"r" ).removeAttr("disabled");

			if (size == 140 || size < 0) {
				$(".rep_twit_btn", "#"+id+"r" ).attr("disabled", "true");
			}
			});
		});
	});

	$(".rep_textarea", "#" + id + "r").blur(function(){
		var repUser = $(".usernickLink","#"+id).text();
		//$(this).css("background-color", "#fff");
		if(this.value == "@"+repUser+" "){

			$(this).attr("rows", "1");
		}
		if(this.value!="@"+repUser+" "){

		}
	});
}
