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

	//repform
	changeRepform();



});

function init(){
	//ボタンを隠す
	$("#gps").hide();
	$("#pict").hide();
	$("#twit_button").hide();
	$("#input_text_size").hide();

	$('#twit_textarea').val("ツイートする")
	.css("color", "#969696");

	$("div.repform").hide();

	//＠のついた文字列をリンク付けする
	//hashタグにリンク付けする
	$('p').each(function(){
		var text=$(this).text();
		txt = $(this).text().replace (/(@)([A-Za-z0-9]{4,20})/g, '<a href="showdata/$2">$1$2</a>');
		txt = txt.replace(/(\s#)([a-zA-Zあ-んア-ン_]+)/g,'<a href="showHashData/$2">$1$2</a>');
		txt = txt.replace(/(https?|ftp)(:\/\/[-_.!~*\'()a-zA-Z0-9;\/?:\@&=+\$,%#]+)/g,'<a href="$1$2">$1$2</a>');
		$(this).html(txt);
	});

	//hashタグにリンク付けする


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

//replyformメソッド
function changeRepform(){
	$('.open_details_twit').toggle(function(){
		var id = $(".twitmain").attr("id");
		var repUser = $(".usernickLink","#"+id).text();

		$("#" + id).css({
			"margin-top" : "10px"
		});
		$("#" + id + "r").show();
		$("#" + id + "r").css("margin-bottom","10px");
		$(".rep_textarea").val("@"+ repUser + " ");
		$(this).text("閉じる");
		replyForm(id);
	},
	function(){
		var id = $(".twitmain").attr("id");
		$("#" + id).css({
			"margin-top" : "0px"
		});
		$("#" + id + "r").css("margin-bottom","0px");
		$("#"+ id +"r").hide().css("margin-bottom","0px");
		$(this).text("開く");
	});
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
