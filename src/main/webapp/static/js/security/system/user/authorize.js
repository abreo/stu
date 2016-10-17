var userId=parseInt(GetQueryString("id"));
$(document).ready(function(){
	$.ajax({
		type : 'post',
		url : getContextPath()+'/role/security/getUserRoles.ajax',
		data : {
			userId:userId
		},
		dataType : 'json',
		success : function(data) {
			if (data.code != 200) {
				top.TopLobibox('alert','warning', {
					title : 'warning',
					msg : data.message,
					sound : 'sound2.ogg'
				});
				parent.layer.close(window.parent.layer.getFrameIndex(window.name));
			} else {
				var app="";
				for(var i=0,len=data.data.left.length;i<len;i++){
					app=app+"<option value='"+data.data.left[i].id+"'>"+data.data.left[i].rolename+"</option>";
				}
				$('#select1').append(app);
				app="";
				for(var i=0,len=data.data.right.length;i<len;i++){
					app=app+"<option value='"+data.data.right[i].id+"'>"+data.data.right[i].rolename+"</option>";
				}
				$('#select2').append(app);
			}
		},
		error : function(errorThrown) {
			top.TopLobibox('alert','error', {
				title:errorThrown.status+'错误',
    			msg:'错误信息:'+errorThrown.statusText,
    			sound : 'sound2.ogg'
			});
			parent.layer.close(window.parent.layer.getFrameIndex(window.name));
		}
	});
	
	
	$("#add").click(function(){	
		$("#select1 option:selected").appendTo("#select2");
	});
	$("#add_all").click(function(){
		$("#select1 option").appendTo("#select2");
	});
	$("#remove").click(function(){
		$("#select2 option:selected").appendTo("#select1");
	});	
	$("#remove_all").click(function(){
		$("#select2 option").appendTo("#select1");
	});	
	$("#select1").dblclick(function(){
		$("#select1 option:selected").appendTo("#select2");	
	});
	$("#select2").dblclick(function(){
	    $("#select2 option:selected").appendTo("#select1");	
	});
	
	$('#btn-submit').click(function(){
		var s=$('#select2 option');
		var param={},roleIds=[];
		for(var i=0,len=s.length;i<len;i++){
			roleIds.push(parseInt($(s[i]).val()));
		}
		param.userId=userId;
		param.roleIds=roleIds;
		$.ajax({
			type : 'post',
			url : getContextPath()+'/role/security/authorize.ajax',
			data : JSON.stringify(param),
			contentType:'application/json',
			dataType : 'json',
			success : function(data) {
				if (data.code == 200) {
					top.TopLobibox('notify','success', {
						title:'授权成功',
		    			msg:'返回信息:'+data.message
					});
					parent.layer.close(window.parent.layer.getFrameIndex(window.name));
				} else {
					top.TopLobibox('alert','warning', {
						title : 'warning',
						msg : data.message,
						sound : 'sound2.ogg'
					});
					parent.layer.close(window.parent.layer.getFrameIndex(window.name));
				}
			},
			error : function(errorThrown) {
				top.TopLobibox('alert','error', {
					title:errorThrown.status+'错误',
	    			msg:'错误信息:'+errorThrown.statusText,
	    			sound : 'sound2.ogg'
				});
				parent.layer.close(window.parent.layer.getFrameIndex(window.name));
			}
		});
	});
});