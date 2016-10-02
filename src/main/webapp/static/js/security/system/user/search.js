$(function(){
	$('#btn').click(function(){
		var param=$('#form1').serializeJson();
		window.parent.$('input[name="loginname"]').val(param.loginname);
		window.parent.$('input[name="username"]').val(param.username);
		window.parent.$('input[name="organization-checkbox#int"]').prop('checked',true);
		parent.layer.close(window.parent.layer.getFrameIndex(window.name));
	});
});