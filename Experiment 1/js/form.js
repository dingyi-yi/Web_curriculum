//打开信息提示框
function formalert(info){
	

	var json2=JSON.stringify(info);
	//window.open("alert.html?json="+escape(json2));
	window.open("alert.html?json="+escape(json2),'newWin','newwindow', 'height=100', 'width=300', 'top=0', 'left=0', 'toolbar=no', 'menubar=no', 'scrollbars=no', 'resizable=no','location=no', 'status=no');
    return true;
}

function check() {
	var MyId=document.getElementById("MyId").value;
	var MyName=document.getElementById("MyName").value;
	var gender=document.getElementById("gender").value;
	var CardId=document.getElementById("CardId").value;
	var degree=document.getElementById("degree").value;
	var email=document.getElementById("Email").value;
	var Selfintroduction=document.getElementById("Selfintroduction").value;
	var Intrests=document.getElementsByName("Intrests");
	var Intrest=[];
	var emailreg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
	var cardidreg=/\d{15}|\d{18}/;
		
	if(MyName.length==0)
	{
		alert("输入姓名");
		return false;
	}
	if(MyName.length>10){
		alert("名字长度应当小于10");
		return false;
	}
	if(emailreg.test(email)==false){
		alert("邮箱格式不正确");
		return false;
	}
	if(cardidreg.test(CardId)==false){
		alert("身份证号码格式不正确");
		return false;
	}	
			
	for(var ints in Intrests){
		if(Intrests[ints].checked==true){
			Intrest.push(Intrests[ints].value);
			//alert(Intrests[ints].value);
		}
	}	
		
	//封装json
	var json1={
		MyId:MyId,
		MyName:MyName,
		gender:gender,
		CardId:CardId,
		degree:degree,
		email:email,
		Selfintroduction:Selfintroduction,
		Intrest:Intrest
	};
	formalert(json1);
}


//实时监听inputarea		
function getValue(){
	var Selfintroduction=document.getElementById("Selfintroduction").value;
	var used=document.getElementById("used");
	var residual=document.getElementById("residual");
	used.value=Selfintroduction.length;
	residual.value=100-Selfintroduction.length;
	
}

