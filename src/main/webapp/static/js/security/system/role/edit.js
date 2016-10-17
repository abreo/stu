	$(function(){
		var id=GetQueryString("id");
		var url=getContextPath()+"/role/security/info.ajax";
		var param={};
		param.url=url;
		param.data={"id":id};
		initForm(param);
		$('#btn-submit').click(function(){
			if($('#dataForm').validate()){
				var dataJson = $("#dataForm").serializeJson();
				//alert(JSON.stringify(dataJson));
				$.ajax({
					type:'post',
					url:getContextPath()+'/user/security/update.ajax',
					data : JSON.stringify(dataJson),
					contentType:'application/json',
					dataType : 'json',
					success : function(data) {
						if(data.code == 200){
							top.TopLobibox('notify','success',{
								msg:data.message,
								delay:1000
							});
							mclose();
						}
						else{
							$("<div class='hidden need-remove-sound'></div>").sound("sound5.ogg");
							Lobibox.alert('error', {
								msg:data.message
							});
						}
					},
					error : function(errorThrown) {
						$("<div class='hidden need-remove-sound'></div>").sound("sound5.ogg");
						Lobibox.alert('error', {
							title:errorThrown.status+'错误',
			    			msg:'错误信息:'+errorThrown.statusText
						});
					}
				});
			}
		});
	});