	$(function(){
		var id=GetQueryString("id");
		var url=getContextPath()+"/user/security/info.ajax";
		var param={};
		param.url=url;
		param.data={"id":id};
		initForm(param);
	});