$(function(){

	inits();
	mouseHoverEvents();
	scrollEvents();
	searchUser();
	DoModal();
});

function inits(){

	$("div.repform").hide();
	$("#dropDownMenu").hide();

	//＠のついた文字列をリンク付けする
	//hashタグにリンク付けする
	$('p').each(function(){
		var text = $(this).text();
		text = text.replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/"/g,"&quot;").replace(/'/g,"&#039;");
		text = text.replace (/(@)([A-Za-z0-9]{4,20})/g, '<a href="userpage/$2">$1$2</a>');
		text = text.replace(/(\s#)([a-zA-Zあ-んア-ン_]+)/g,'<a href="showHashData/$2">$1$2</a>');
		text = text.replace(/(https?|ftp)(:\/\/[-_.!~*\'()a-zA-Z0-9;\/?:\@&=+\$,%#]+)/g,'<a href="$1$2">$1$2</a>');
		$(this).html(text);
	});
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
		text = text.replace(/(\s#)([a-zA-Zあ-んア-ン_]+)/g,'<a href="showHashData/$2">$1$2</a>');
		text = text.replace(/(https?|ftp)(:\/\/[-_.!~*\'()a-zA-Z0-9;\/?:\@&=+\$,%#]+)/g,'<a href="$1$2">$1$2</a>');
		$(this).html(text);
		});

}


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


//openメソッド
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

function initWhenAjaxDos(){

	$("div.repform").hide();

	//＠のついた文字列をリンク付けする
	//hashタグにリンク付けする
	$('p').each(function(){
		var text = $(this).text();
		text = text.replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/"/g,"&quot;").replace(/'/g,"&#039;");
		text = text.replace (/(@)([A-Za-z0-9]{4,20})/g, '<a href="userpage?userni=/$2">$1$2</a>');
		text = text.replace(/(\s#)([a-zA-Zあ-んア-ン_]+)/g,'<a href="showHashData/$2">$1$2</a>');
		text = text.replace(/(https?|ftp)(:\/\/[-_.!~*\'()a-zA-Z0-9;\/?:\@&=+\$,%#]+)/g,'<a href="$1$2">$1$2</a>');
		$(this).html(text);
	});

}

function mouseHoverEvents(){

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

function scrollEvents(){

	var currentLocation;
	var scrollHeight;
	//var clientHeight = document.body.clientHeight;
	var clientHeight;

	$(window).scroll(function(){
		clientHeight = window.innerHeight
		scrollHeight = document.body.scrollHeight || document.docomentElement.scrollHeight;
		currentLocation = $(window).scrollTop();
		var remain = scrollHeight - clientHeight - currentLocation;
		if(remain <= 10){
			var page =$("#lastLineS").attr("class");
			getOldSearchTwit(page);
		}
	});
}

function getOldSearchTwit(page){
	page = Number(page);
	newpage = page + 1;
	$("#lastLineS").attr("class", newpage);
	//alert(newpage);
	$.ajax({
		type:"POST",
		url: "searchAjax",
		data:{
			'page':newpage
		},
		dataType:"html",
		success: function(data, dataType){
			$("#lastLineS").before(data);
			initWhenAjaxDos();
			mouseHoverEvents();
		},
		error: function(){
			alert("問題が発生しました");
		}
	});
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
