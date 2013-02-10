$(function(){

	init();

	//入力フォームの色設定
	$('textarea[value=""]').val("ツイートする")
	.css("color", "#969696");
	$("textarea").focus(function(){
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
	$("textarea").blur(function(){
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

});

function init(){
	//ボタンを隠す
	$("#gps").hide();
	$("#pict").hide();
	$("#twit_button").hide();
	$("#input_text_size").hide();

	//＠のついた文字列をリンク付けする
	$('p').each(function(){
		var text=$(this).text();
		txt = $(this).text().replace (/(＠)([A-Za-z0-9]{4,20})/, '<a href="showdata/$2">$1$2</a>');
		$(this).html(txt);
	});

	$("#twit_button").attr("disabled","true");
}


function OpenWin(index){
	var selecter;
	selecter="#"+ index;
	var userid=$(selecter).attr("style");
	userid="newwindow/"+userid;
	win=window.open(userid,"new","width=400,height=400, menubar=no, toolbar=no, location=no");
	win.moveTo(50,50);
}

//replyanメソッド
function replyan(usernick){
	var selecter;
	selecter="#"+ usernick;
	var nick="＠"+$(selecter).text()+"　";
	$("input[name='tubuyaki']").val(nick).css("color", "#000");
	$("input[name='tubuyaki']").focus();
}

