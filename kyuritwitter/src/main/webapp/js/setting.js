$(function(){

//	init();
//
//	$("#uploadSubmit").submit(function(){
//		alert("ok");
//		return false;
//	});
//
//	$("#change1").click(function(){
//		$(this).attr('class','active');
//		$("#change2").attr('class','');
//		$("#change3").attr('class','');
//	});
//
//	$("#change2").click(function(){
//		$(this).attr('class','active');
//		$("#change1").attr('class','');
//		$("#change3").attr('class','');
//		});
//
//	$("#change3").click(function(){
//		$(this).attr('class','active');
//		$("#change2").attr('class','');
//		$("#change1").attr('class','');
//
//	});
//
//	$("#changePass").click(function(){
//		alert("passini");
//	});
//
//	$("#username").focus(function(){
//
//		//$(this).val("");
//		//$("#changeUsernameSubmit").attr("disabled","true");
//
//		$("#username").each(function() {
//			$(this).keyup(function(event) {
//
//				if ($(this).val().length == 0) {
//					$("#changeUsernameSubmit").attr("disabled", "true");
//				} else {
//					$("#changeUsernameSubmit").removeAttr("disabled");
//				}
//			});
//		});
//	});

});

function init(){

}

function passInit(){
	//$("#changePass").attr("disabled", "true");

//	var old = $("#oldpass").val();
//	var newP = $("#password").val();
//
//	$("#changePass").click(function(){
//
//		if(old == "" || newP == ""){
//			alert("空の項目があります");
//
//		}else if(old.length > 8 || old.length < 4 || newP > 8 || newP < 4){
//			alert("パスワードは4文字以上8文字以下で入力してください");
//
//		}else if(!(old.match(/^[-a-zA-Z0-9_]+$/)) || !(newP.match(/^[-a-zA-Z0-9_]+$/))){
//			alert("パスワードを正しく入力してください");
//
//		}else{
//			//oldpassがあっているかチェック
//			$.ajax({
//				type:"POST",
//				url:"checkOldPass",
//				data:{'oldpass':old},
//				dataType:"text",
//				success: function(data){
//					if(data=="OK"){
//
//					}else{
//						alert("パスワードが正しくありません");
//
//					}
//				},
//				error: function(){
//					alert("問題が発生しました");
//
//				}
//			});
//		}
//
//	});
}