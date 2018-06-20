
var gScript = {}

/**
 * 为String类构造trim方法
 * **/
gScript.trim = function(str) {
	return $.trim(str);
}

/**
 * 获取密码随机数
 * **/
gScript.getPwdRandom = function(rootPath){
	var random = "";
	$.ajax({  
		type : "post",  
		url : rootPath + "/PubSelect/getPwdRandom",  
        async : false,  
        dataType: "text",
        success : function(data){
        	random = data;
        }  
	}); 
	return random;
}

/**
 * 将密码加密后再和随机数加密后返回
 * **/
gScript.getPwdMd5 = function(pwd, rootPath){
	var random = gScript.getPwdRandom(rootPath);
	pwd = hex_md5(pwd);
	pwd = hex_md5(pwd + random);
	return pwd;
}


/**
 * 获取指定长度的随机数
 * len 长度
 * **/
gScript.getRandomStr = function(len){
	// 生成字符串从此序列中取
	var base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	var sb = "";
	for ( var i = 0; i < len; i++) {
		var number = parseInt(Math.random() * base.length);
		sb += base.charAt(number);
	}
	return sb;
}

/**
 * 获取指定长度的随机数
 * len 长度
 * **/
gScript.getRandomInt = function(len){
	// 生成字符串从此序列中取
	var base = "0123456789";
	var sb = "";
	for ( var i = 0; i < len; i++) {
		var number = parseInt(Math.random() * base.length);
		sb += base.charAt(number);
	}
	return sb;
}

/**
 * 验证字符串是否含有特殊字符
 * **/
gScript.checkStr = function(val, name){
	var reg="^[0-9a-zA-Z-_@]+$";
	var res = val.match(reg);
	if(res == null){
		gAlertErr("您填写的“" + name + "”格式不正确");
		return false;
	}
	return true;
}

/**
 * 判断值是否输入
 * **/
gScript.checkIsInput = function (val, name){
	if (gScript.trim(val) == ""){
		gAlertErr("请填写“" + name + "”");
    	return false;
    }
    return true;
}

/**
 * 判断数字输入及小数位
 * **/
gScript.checkNumber = function (val ,len, name){
	if (!gScript.checkIsInput(val, name)){
		return false;
	}
	// 过滤科学计数法，先直接判断有没有E或e吧
	if(val.indexOf("e") != -1 || val.indexOf("E") != -1){
		gAlertErr("对不起，您填写的“" + name + "”格式不正确，请填写数字");
    	return false;
	}
	if (isNaN(val)){
		gAlertErr("对不起，您填写的“" + name + "”格式不正确，请填写数字");
    	return false;
    }
    if (parseFloat(val)<=0){
    	gAlertErr("对不起，您填写的“" + name + "”格式不正确，请填写大于0的数字");
    	return false;
    }
    var mm0 = "^[0-9]*[1-9][0-9]*$"
	var mm1 = "^[0-9]+(.[0-9]{0,1})?$"
    var mm2 = "^[0-9]+(.[0-9]{0,2})?$"
    var mm3 = "^[0-9]+(.[0-9]{0,3})?$"
    var res = null;
    if (len == 0){
    	res = val.match(mm0);
    }else if (len == 1){
    	res = val.match(mm1);
    }else if (len == 2){
    	res = val.match(mm2);
    }else{
    	res = val.match(mm3);
    }
    
    if (null == res){
    	if (len > 0){
    		gAlertErr("对不起，您填写的“" + name + "”格式不正确，最多只能保留" + len + "位小数");
    	}else{
    		gAlertErr("对不起，您填写的“" + name + "”格式不正确，必须是整数");
    	}
    	return false;
    }
    return true;
}

/**
 * 验证手机号
 * **/
gScript.checkMobile = function(val, name){
	var reg =/^0{0,1}(13[0-9]|15[0-9]|17[0-9]|18[0-9])[0-9]{8}$/; 
	if(!reg.test($.trim(val))){
		gAlertErr("您填写的“" + name + "”不正确");
		return false;
	}
	return true;
}

/**
 * 验证邮箱
 * **/
gScript.checkEmail = function(val, name){
	var reg = /^([a-zA-Z0-9_-_\.])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	if(!reg.test($.trim(val))){
		gAlertErr("您填写的“" + name + "”不正确");
		return false;
	}
	return true;
}