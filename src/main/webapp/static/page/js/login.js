function loginIn(){
	var url = contextPath + "/login/in";
	var data = getFormJson("#formData");
	sendPost(url,data,function(result){
		if(result.success && result.msg.length>(contextPath.length+1)){
			window.location.href = result.msg;
		}else{
			window.location.href = contextPath + "/index/test";
		}
	},function(error){console.log(error);});
}