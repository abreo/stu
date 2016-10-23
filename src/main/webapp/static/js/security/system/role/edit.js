	$(function(){
		var id=GetQueryString("id");
		var url=getContextPath()+"/role/security/info.ajax";
		var param={};
		param.url=url;
		param.data={"id":id};
		initForm(param,function () {
			var iconclsVal=$('#iconcls').val();
			if(iconclsVal!=''){
				$('#iconclsShowSpan').html("<i class='fa "+iconclsVal+" fa-2x pull-left fa-border'></i>");
			}
			else{
				$('#iconclsShowSpan').html("未设置");
			}
		});

		$('#select-icon-btn').click(function () {
			var height=$(document).height(),
				width=$(document).width();
			layer.open({
				type: 2,
				title: '选择图标',
				shadeClose: true,
				shade: 0.5,
				area: [width+'px', height+'px'],
				content: getContextPath()+'/page/normal_icon?',
				end:function () {
					var iconclsVal=$('#iconcls').val();
					if(iconclsVal!=''){
						$('#iconclsShowSpan').html("<i class='fa "+iconclsVal+" fa-2x pull-left fa-border'></i>");
					}
					else{
						$('#iconclsShowSpan').html("未设置");
					}
				}
			});
		});
	});