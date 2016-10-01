	$(function(){
		var id=GetQueryString("id");
		var url=getContextPath()+"/user/security/info.ajax";
		var param={};
		param.url=url;
		param.data={"id":id};
		initForm(param);
		var t=window.setInterval(function(){
			if($('#photo').val()!=""){
				$('#photo-img').attr('src',$('#photo').val());
			}
			else if($('#id').val()!=""){
				window.clearInterval(t);
			}
		}, 500);
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
							top.TopLobibox('success',{
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
	function mclose(){
		var index = window.parent.parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.parent.layer.close(index); //再执行关闭 
	}