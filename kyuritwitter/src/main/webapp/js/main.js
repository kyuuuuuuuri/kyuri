$(function(){

	init();
	mouseHoverEvent();
	searchUser();
	DoModal();

});

function DoModal(){
	$('[data-toggle="modal"]').click(function(e) {
		e.preventDefault();
		var url = $(this).attr('href');
		if (url.indexOf('#') == 0) {
			$(url).modal('open');
		} else {
			$.ajax({
				type : "POST",
				url : "showTwitImg",
				data : {
					'imgurl' : url
				},
				dataType : "html",
				success : function(data, dataType) {
					$('<div class="modal hide fade">' + data + '</div>').modal();
				},
				error : function() {
					alert("問題が発生しました");
				}
			});
		}
	});
}


// 検索エンジンに文字を入れたとき、ユーザを一緒に検索する
function searchUser(){

	var searchWord;

	$("#searchAllTwit").keyup(function(event) {
		searchWord = $("#searchAllTwit").val();
		//alert(searchWord);

		if(searchWord == ""){
			$("#dropDownMenu").hide();
		}else {
			$.ajax({
				type : "POST",
				url : "searchUsershort",
				data : {
					'searchword' : searchWord
				},
				dataType : "html",
				success : function(data, dataType) {
					$("#dropDownMenu").html(data);
					$("#searchMore").attr("href", "search/"+searchWord);
					$("#dropDownMenu").show();
				},
				error : function() {
					alert("問題が発生しました");
				}
			});
		}
	});

}

// リぷをしたときのつぶやき格納
function repSubmit(id){

	$(".topId").val( $(".twitmain:first").attr("id"));
	var thisId = id;
	var tubuyaki = $(".rep_textarea", "#" + id + "r").val();
	// alert(tubuyaki + topId + thisId);

		$.ajax({
			type:"POST",
			url: "ins_tubuyaki_rep",
			data:{
				'topIdrep':topIdrep,
				'thisId':thisId,
				'tubuyaki':tubuyaki
			},
			dataType:"text",
			success: function(data, dataType){
				alert("成功しました");
				if($("#existNewtwit").get(0)){
					$("#existNewtwit").replaceWith(data);
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

}

function init(){

	$("div.repform").hide();
	$("#dropDownMenu").hide();
	$("#imgPost").hide();
	$("iframe").hide();

	// ＠のついた文字列をリンク付けする
	// hashタグにリンク付けする
	$('.twitid').each(function(){
		var text = $(this).text();
		text = text.replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/"/g,"&quot;").replace(/'/g,"&#039;");
		text = text.replace (/(@)([A-Za-z0-9]{4,20})/g, '<a href="userpage/$2">$1$2</a>');
		text = text.replace(/(\s#)([a-zA-Zあ-んア-ン_]+)/g,'<a href="/kyuritwitter/main/showHashData/$2">$1$2</a>');
		text = text.replace(/(https?|ftp)(:\/\/[-_.!~*\'()a-zA-Z0-9;\/?:\@&=+\$,%#]+)/g,'<a href="$1$2">$1$2</a>');
		$(this).html(text);
	});

	$("#twit_button").attr("disabled","true");
}

//reTwit
function retweet(id){
	var $retweet = $("#" + id + " .retweet");

	if($retweet.text() == "リツイート"){
		$.ajax({
			type:"POST",
			url:"Retwit",
			data:{
				"murmurid":id
			},
			dataType:"text",
			success: function(data,dataType){
				alert("リツイートしました");
				$retweet.text("リツイートを取り消す");
			},
			error: function(){
				alert("問題が発生しました");
			}
		});
	}

	if($retweet.text() == "リツイートを取り消す"){
		$.ajax({
			type:"POST",
			url:"canselRetwit",
			data:{
				"murmurid":id
			},
			dataType:"text",
			success: function(data,dataType){
				alert("リツイートを削除しました"+data);
				$(data).remove();
			},
			error: function(){
				alert("問題が発生しました");
			}
		});
	}
}

// お気に入りに登録
function favoriteclick(id){
	// alert("お気に入り！"+id);
	if($(".favorite", "#"+id).text()=="お気に入りに登録"){
		$.ajax({
			type:"POST",
			url:"doFavorite",
			data:{
				"murmurid":id
			},
			dataType:"text",
			success: function(data,dataType){
				$(".favorite", "#"+id).text("★お気に入りを取り消す");
			},
			error: function(){
				alert("問題が発生しました");
			}
		});
	}

	if($(".favorite", "#"+id).text()=="★お気に入りを取り消す"){
		$.ajax({
			type:"POST",
			url:"canselFavorite",
			data:{
				"murmurid":id
			},
			dataType:"text",
			success: function(data,dataType){
				$(".favorite", "#"+id).text("お気に入りに登録");
			},
			error: function(){
				alert("問題が発生しました");
			}
		});
	}
}

// お気に入りに登録
function favoriteclickInrep(id){
	// alert("お気に入り！"+id);
	if($(".favorite", "#"+id +"r").text()=="お気に入りに登録"){
		$.ajax({
			type:"POST",
			url:"doFavorite",
			data:{
				"murmurid":id
			},
			dataType:"text",
			success: function(data,dataType){
				$(".favorite", "#"+id).text("★お気に入りを取り消す");
			},
			error: function(){
				alert("問題が発生しました");
			}
		});
	}

	if($(".favorite", "#"+id + "r").text()=="★お気に入りを取り消す"){
		$.ajax({
			type:"POST",
			url:"canselFavorite",
			data:{
				"murmurid":id
			},
			dataType:"text",
			success: function(data,dataType){
				$(".favorite", "#"+id).text("お気に入りに登録");
			},
			error: function(){
				alert("問題が発生しました");
			}
		});
	}
}


// gps情報を取得する
function getGps(){

	navigator.geolocation.getCurrentPosition(
			function(position){
				var latlng = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
				var geocoder = new google.maps.Geocoder();
				$("#latitude").val(position.coords.latitude);
				$("#longitude").val(position.coords.longitude);

				if(geocoder){
					geocoder.geocode({
						'latLng' : latlng
					},
					function(results, status) {
						if (status == google.maps.GeocoderStatus.OK) {
							$("#address").text(results[5].formatted_address);
						} else {
							alert("Geocode faild due to : "+ status);
						}
					});
				}
			});
}


function initWhenAjaxDo(){

	$("div.repform").hide();
	$("#twit_textarea").attr("row", "1");


	// ＠のついた文字列をリンク付けする
	// hashタグにリンク付けする
	$('p').each(function(){
		// var text=$(this).text();
		var text = $(this).text();
		text = text.replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/"/g,"&quot;").replace(/'/g,"&#039;");
		text = text.replace (/(@)([A-Za-z0-9]{4,20})/g, '<a href="userpage?userni=$2">$1$2</a>');
		text = text.replace(/(\s#)([a-zA-Zあ-んア-ン_]+)/g,'<a href="/kyuritwitter/main/showHashData/$2">$1$2</a>');
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
	$("#address").hover(
		function(){
			$(this).css(
				"background-color","#0088cc"
			);
		},
		function(){
			$(this).css(
				"background-color","#ffffff"
			);
		});
}

// replyanメソッド
function replyan(usernick){
	var selecter;
	selecter="#"+ usernick;
	var nick="＠"+$(selecter).text()+"　";
	$("input[name='tubuyaki']").val(nick).css("color", "#000");
	$("input[name='tubuyaki']").focus();
}

// openメソッド
function openRep(id){

	if($('.open_details_twit', "#"+id).attr("id") == id+"open"){
		$('.open_details_twit', "#"+id).attr("id", id+"close").text("閉じる");
		$(".twitplus").remove();

		$.ajax({
			type:"POST",
			url:"repListAfter",
			data:{
				"tubuyakiId":id
			},
			dataType:"html",
			success: function(data,dataType){
				// alert("OK" + id);
				$("#"+id).append(data);
				initWhenAjaxDo();

				beforeQuery(id);
			},
			error: function(){
				alert("問題が発生しました");
			}
		});


	}else if($('.open_details_twit', "#"+id).attr("id") == id+"close"){
		$('.open_details_twit', "#"+id).attr("id", id+"open").text("開く");

		$(".twitplus").remove();

		$("#" + id).css({
			"margin-top" : "0px"
		});
		$("#" + id + "r").css("margin-bottom","0px");
		$("#"+ id +"r").hide().css("margin-bottom","0px");


	}else{
		alert("問題が起こりました");
	}
}

//お気に入りリストとふぁぼ情報を取ってくる
function retAndFavoInfo(id){
	$.ajax({
		type:"POST",
		url:"BeRetwited",
		data:{
			"tubuyakiId":id
		},
		dataType:"html",
		success: function(data,dataType){

			$("#"+id).prepend(data);
			beforeQuery(id);
		},
		error: function(){
			alert("問題が発生しました");
		}
}

function beforeQuery(id){
	// alert("before");
	var repUser = $(".usernickLink","#"+id).text();

	$.ajax({
		type:"POST",
		url:"repListBefore",
		data:{
			"tubuyakiId":id
		},
		dataType:"html",
		success: function(data,dataType){
			// alert("OK");

			$("#"+id).prepend(data);
			initWhenAjaxDo();

			$("#" + id).css({
				"margin-top" : "10px"
			});
			$("#" + id + "r").show();
			$("#" + id + "r").css("margin-bottom","10px");
			$(".rep_textarea", "#" + id + "r").val("@"+ repUser + " ");
			replyForm(id);
		},
		error: function(){
			alert("問題が発生しました");
		}
	});
}

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


//返信フォームの中身
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
		// $(this).css("background-color", "#fff");
		if(this.value == "@"+repUser+" "){

			$(this).attr("rows", "1");
		}
		if(this.value!="@"+repUser+" "){

		}
	});
}
