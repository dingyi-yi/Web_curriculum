var $UserInfoTr;
var ARID;

$(document).ready(indexInfo());

/*ajax get请求*/
function ajaxGet(url,data,successF){
	$.ajax({
		type:"GET",
		url:url,
		data:data,
		success:successF
	})
}


/*更新表格中的序号*/
function updateTableIndex($tbody){

	for(var i=0;i<$tbody.children().length;i++){
		$tbody.children().eq(i).children().eq(0).html(i+1);
	}

}


/*用户信息删除函数*/
function deleteUserInfoF(){
	$tr=$(this).parent().parent();
	var $tobdy=$tr.parent();
	var usName=$tr.children().eq(2).html();
	var usId=$tr.children().eq(1).html();
	if(confirm("是否要删除"+usName)){
		$.ajax({
			type:"GET",
			url:"/deleteuserinfo",
			data:{
				usId:usId
			},
			success:function (result){
				alert(result.msg);
				if(result.code===0){
					return  false;
				}

				$tr.remove();
				updateTableIndex($tobdy);
			}

		})

	}

	return false;
}

/*用户信息表编辑函数*/
function editorUserInfoF(){
	$("#editorUserInfoModal").modal('show');

	var $tr=$(this).parent().parent();
	$UserInfoTr=$tr;
	$tr.attr("id",$tr.children().eq(1).html());
	$("#editorUserInfoModal #editorUsId").html($tr.children().eq(1).html())
	return false;
}

/*构造用户信息表的行*/
function createUserInfoTd(index,usId,usName,usPassword,birthday,email,money){

	var lastTd="<a class=\"text-success\" id=\"editorUserInfo\" >编辑</a>"+"<a class=\"text-danger \" id=\"deleteUserInfo\" style=\"margin-left: 10px\">删除</a>";
	var $newtr=$('<tr></tr>')
		.append('<td>'+index+'</td>')
		.append('<td>'+usId+'</td>')
		.append('<td>'+usName+'</td>')
		.append('<td>'+usPassword+'</td>')
		.append('<td>'+email+'</td>')
		.append('<td>'+birthday+'</td>')
		.append('<td>'+money+'</td>')
		.append('<td>'+lastTd+'</td>')
		.appendTo("#userInfoTable>tbody")

	$newtr.find('#deleteUserInfo').click(deleteUserInfoF)
	$newtr.find('#editorUserInfo').click(editorUserInfoF)


	return $newtr;
}

/*绘制用户文章棒棒图*/
function createArticleChart(lables,data){

	var ctx = document.getElementById("ArticleChart").getContext('2d');
	var ArticleChart = new Chart(ctx, {
		// 要创建的图表类型
		type:"bar",

		// 数据集
		data: {
			labels: lables,
			datasets: [{
				label: "用户文章数",
				backgroundColor: 'rgb(255, 99, 132)',
				borderColor: 'rgb(255, 99, 132)',
				data: data,
			}]
		},
		options:{
			scales:{
				yAxes:[{
					ticks:{
						min:0,
						suggestedMax:5,
						stepSize: 1,
					}
				}]
			}
		}

	});
}


/*构造文章信息表的行*/
function createArticleTable(index,usId,usName,articleNum){

	var lasttd="<a id='artManageHref' class='text-success'>进入文章管理</a>"
	$('<tr id='+usId+'></tr>')
		.append('<td>'+index+'</td>')
		.append('<td>'+usName+'</td>')
		.append('<td>'+articleNum+'</td>')
		.append('<td>'+lasttd+'</td>')
		.appendTo("#articleInfoTable>tbody")
		.find("#artManageHref").click(UserArtManagement)

}

/*进入文章管理响应函数*/
function UserArtManagement(){

	var $mainBox=$("#main");
	$mainBox.empty();
	$mainBox.load("/article-userArtManage.html")

	var usID=$(this).parent().parent().attr('id');

	$.ajax({
		type:"GET",
		url:"/userArticleManagement",
		data:{
			usId:usID
		},
		success:function (result){


			var userInfo=result.data.UserInfo;
			var articleList=result.data.ArticleList;
			settingUserInfo(userInfo.usName,userInfo.email,
				userInfo.birthday,userInfo.money,userInfo.usId);

			for(var i=0;i<result.code;i++){
				createUserArtTable(i+1,articleList[i].arId,articleList[i].title,articleList[i].content);
			}
		}
	})
}

/*完善-用户文章管理-中用户个人信息*/
function settingUserInfo(usName,email,birthday,money,usId){
	$("#UserArtMan_usName").parent().parent().attr("id",usId);
	$("#UserArtMan_usName").html(usName);
	$("#UserArtMan_email").html(email);
	$("#UserArtMan_birthday").html(birthday);
	$("#UserArtMan_money").html(money);
	return true;
}

/*构造用户个人文章列表的表格*/
function createUserArtTable(index,arId,title,content){
	//操作栏
	var lastTd='<a class="text-success" id="editorUserArticle">编辑</a><a class="text-danger" style="margin-left: 10px" id="deleteUserArticle">删除</a>';

	var $tr=$('<tr id='+arId+'></tr>')
		.append('<td>'+index+'</td>')
		.append('<td>'+title+'</td>')
		.append('<td>'+content+'</td>')
		.append('<td>'+lastTd+'</td>')
		.appendTo("#userArticleTable>tbody")
	$tr.find("#editorUserArticle").click(editorUserArticleF);
	$tr.find("#deleteUserArticle").click(deleteUserArticleF);

	return $tr;

}


/*用户文章信息编辑*/
function editorUserArticleF(){

	var $tr=$(this).parent().parent();
	ARID=$tr.attr("id");

	$("#userArticleModalTitle").html("修改文章");
	$("#userArticleTitle").val($tr.children().eq(1).html());
	$("#userArticleContent").val($tr.children().eq(2).html())


	$("#userArticleModal").modal('show');
	return true;
}

/*用户文章信息删除*/
function deleteUserArticleF(){

	var $tr=$(this).parent().parent();
	var $tbody=$tr.parent();
	var arId=$tr.attr("id");
	var usId=$("#UserArtMan_usName").parent().parent().attr("id");
	if(confirm("是否要删除这篇文章")!==true){
		return false;
	}
	ajaxGet("/deleteUserArticle",{usId:usId,arId:arId},function (){
		$tr.remove();
		updateTableIndex($tbody);
	})

}

/*首页内容，默认为管理员信息*/
function indexInfo(){
	$("#main").empty()
	$("#main").load("/administrator-info.html");
	$("#navLink2").html("个人中心");
}

$(function(){
	

	/******************************************************************************************/
	/*管理员个人信息*/
	$("#administratorInfo").click(function (){
		$("#main").empty()
		$("#main").load("/administrator-info.html");
		$("#navLink2").html("个人中心");
	});

	/*用户信息管理*/
	$("#userInfo").click(function (){
		$("#main").empty();
		$("#main").load("/user-info.html");
		$("#navLink2").html("用户管理");
		$.ajax({
			type:"GET",
			url:"/pageLoad",
			date:{},
			success:function (result){
				if(result.code===0)
				{
					return false;
				}
				var $tr=$("#userInfoTable>tbody").empty();

				for(var i=0;i<result.code;i++){
					var userinfo=result.data[i];
					createUserInfoTd(i+1,userinfo.usId,userinfo.usName,userinfo.usPassword,
						userinfo.birthday,userinfo.email,userinfo.money);
				}
				return true;
			}
		})
	});

	/*文章管理*/
	$("#articleIndex").click(function (){
		$("#main").empty();
		$("#main").load("/article-index.html");
		$("#navLink2").html("文章管理");
		$.ajax({
			type:"GET",
			url:"/articleInfoLoad",
			data:{},
			success:function (result){

				var lables=[];
				var data=[];
				for (var i=0;i<result.code;i++){
					lables.push(result.data[i].usName);
					data.push(result.data[i].articleNum);
					createArticleTable(i+1,result.data[i].usId,result.data[i].usName,result.data[i].articleNum);
				}
				createArticleChart(lables,data);
				return true;
			}
		})
	});



	/******************************************************************************************/
	/*修改密码*/
	$("#changePassword").click(function(){
		$('#changePasswordModal').modal('show')
	});

	/*校验旧密码*/
	$("#oldPassword").on(" input propertychange",function(){
		var oldPassword=getAdPassword().replaceAll(" ","");
		var oldInput=$("#oldPassword").val();
		if(oldInput===oldPassword){
			$("#oldPasswordLabel").text("OK");
		}else {
			$("#oldPasswordLabel").text("旧密码不正确");
		}
	});

	/*校验新密码*/
	$("#confirmNewPassword").on(" input propertychange",function(){
		var newPassword=$("#newPassword").val();
		var confirmNP=$("#confirmNewPassword").val();
		if(newPassword===confirmNP){
			$("#confirmNewPasswordLabel").text("OK");
		}else {
			$("#confirmNewPasswordLabel").text("两次密码不一致");
		}
	});

	/*提交修改密码*/
	$("#subPasswordChange").click(function (){
		$.ajax({
			type:"GET",
			url:"/changePassword",
			data:{
				adId:getAdId(),
				newPassword:$("#confirmNewPassword").val()
			},

			success:function (data){

				if(data.code===0){
					alert(data.msg);
				}else {
					alert(data.msg);
					// 获取表单
					var frm = $("#frmAppId");
					$("#refreshPassword").val($("#confirmNewPassword").val());
					frm.submit(); // 对表单进行提交
				}
			}
		});
		$("#changePasswordModal").modal('hide');
	});


	/******************************************************************************************/
	/*管理员修改个人信息提交*/
	$("#subModifySelfInfo").click(function (){
		var $AdNewName=$("#AdNewName");
		var $AdNewBirthday=$("#AdNewBirthday");
		var $AdNewEmail=$("#AdNewEmail");

		var $tBody=$("#AdminSelfInfoTable>tbody");

		var newName=$AdNewName.val().length!==0?$AdNewName.val():$tBody.find("td").eq(1).html();
		var newBirthday=$AdNewBirthday.val().length!==0?$AdNewBirthday.val():$tBody.find("td").eq(3).html();
		var newEmail=$AdNewEmail.val().length!==0?$AdNewEmail.val():$tBody.find("td").eq(4).html();
		console.log(newEmail);
		ajaxGet(
			"/updateSelfInfo",
			{
				adId:$tBody.find("td").eq(0).html(),
				newName:newName,
				newBirthday:newBirthday,
				newEmail:newEmail},
			function (resultData){
				alert(resultData.msg);
				if(resultData.code!==1){
					return false;
				}
				var frm = $("#frmAppId");
				$("#refreshPassword").val(getAdPassword().replaceAll(' ',''));
				frm.submit(); // 对表单进行提交

		});

		$AdNewName.val("");
		$AdNewBirthday.val("");
		$AdNewEmail.val("");
		$("#ModifySelfInfoModal").modal('hide');
	});




	/******************************************************************************************/
	/*新增用户信息提交*/
	$("#subAddUserInfo").click(function (){
		$.ajax({
			type:"GET",
			url:"/insertions",
			data:{
				usName:$("#addUsName").val(),
				usPassword:$("#addUsPassword").val(),
				birthday:$("#addUsBirthday").val(),
				email:$("#addUsEmail").val()
			},

			success:function (result){
				alert(result.msg);
				if(result.code===0){
					return false;
				}
				var $tr=createUserInfoTd(1,result.data.usId,result.data.usName,result.data.usPassword,
					result.data.birthday,result.data.email,result.data.money);
				updateTableIndex($tr.parent());
				return true;
			}
		});
		$(this).attr("data-dismiss","modal");

	});


	/*用户信息编辑提交响应*/
	$("#subEditorUserInfo").click(function (){
		//获取该行
		var usId=$("#editorUserInfoModal #editorUsId").html()
		var $tr=$UserInfoTr;
		$(this).attr("data-dismiss","modal");

		//获取输入值
		var $usName=$("#editorUsName");
		var $usPassword=$("#editorUsPassword");
		var $birthday=$("#editorUsBirthday");
		var $email=$("#editorUsEmail");
		var $money=$("#editorUsMoney");


		var	usName=$usName.val().length!==0?$usName.val():$tr.children().eq(2).html();
		var	usPassword=$usPassword.val().length!==0?$usPassword.val():$tr.children().eq(3).html();
		var	email= $email.val().length!==0?$email.val():$tr.children().eq(4).html();
		var	birthday= $birthday.val().length!==0?$birthday.val():$tr.children().eq(5).html();
		var	money= $money.val().length!==0?$money.val():$tr.children().eq(6).html();


			$.ajax({
				type: "GET",
				url: "/updataUserInfo",
				data: {
					usId: usId,
					usName: usName,
					usPassword: usPassword,
					email: email,
					birthday: birthday,
					money: money
				},
				success: function (result) {
					alert(result.msg);
					if (result.code === 0) {
						return false;
					}
					$tr.children().eq(2).html(usName);
					$tr.children().eq(3).html(usPassword);
					$tr.children().eq(4).html(email);
					$tr.children().eq(5).html(birthday);
					$tr.children().eq(6).html(money);
					return true;
				},

			});

	});






	/******************************************************************************************/
	/*取消用户文章--新增/编辑---模态框*/
	$("#dismissUserArticleModal,#userArticleModalHidden").click(function (){
		$(this).attr("data-dismiss","modal");
		$("#userArticleTitle").val("");
		$("#userArticleContent").val("")

	});

	/*用户文章--编辑/新增 -- 提交响应*/
	$("#subUserArticle").click(function (){
		$(this).attr("data-dismiss","modal");

		if($("#userArticleModalTitle").html()==="添加一篇文章"){
			insertUserArticle();
			return true;
		}
		updataUserArticle();
		return true;
	});

	/*处理用户文章--新增*/
	var insertUserArticle=function (){
		var $userArticleTitle=$("#userArticleTitle")
		var $userArticleContent=$("#userArticleContent")

		var usId=$("#UserArtMan_usName").parent().parent().attr("id");

		ajaxGet(
			"/insertUserArticle",
			{
				usId:usId,
				title:$userArticleTitle.val(),
				content:$userArticleContent.val()
			},
			function (result){
				alert(result.msg);
				if(result.code===0){
					return false;
				}
				var $tr=createUserArtTable(1, result.data.arId, result.data.title,result.data.content);
				updateTableIndex($tr.parent());
				return true;
			});
		$userArticleTitle.val("");
		$userArticleContent.val("");
	}

	/*处理用户文章--编辑*/
	var updataUserArticle=function (){
		var $userArticleTitle=$("#userArticleTitle");
		var $userArticleContent=$("#userArticleContent");

		var $tr=$("#"+ARID);

		var title=$userArticleTitle.val().length!==0?$userArticleTitle.val():$tr.children().eq(1).html();
		var content=$userArticleContent.val().length!==0?$userArticleContent.val():$tr.children().eq(2).html();

		ajaxGet("/updateUserArticle",{arId: ARID,title:title,content:content},function (resultdata){
			alert(resultdata.msg);
			if(resultdata.msg===0){
				return false;
			}
			$tr.children().eq(1).html(title);
			$tr.children().eq(2).html(content);
		});
		updateTableIndex($tr.parent());
		$userArticleTitle.val("");
		$userArticleContent.val("");

	}



});

