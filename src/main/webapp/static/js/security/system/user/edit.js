	var pro=function (thisNode) {
		var parentNode=$('#tree').treeview('getParent', thisNode);
		if(typeof(parentNode.nodeId)=='undefined') return;
		$('#tree').treeview('expandNode', [ parentNode.nodeId, { silent: true } ]);
		pro(parentNode);
	};
    $(function(){
		$.ajax({
			type:'post',
			url:getContextPath()+'/organization/owntree.ajax',
			dataType : 'json',
			success:function(data) {
				$('#tree').treeview({
					data: data.organizations,
					levels: 0,
					nodeIcon: 'fa fa-institution',
					onNodeSelected: function (event, data) {
						$('#organizationid').val(data.id);
						$('#organizationname').val(data.text);
						$(this).hide();
					},
					onNodeUnselected: function (event, data) {
						$('#organizationid').val('');
						$('#organizationname').val('');
						$(this).hide();
					}
				});
			}
		});

		var id=GetQueryString("id");
		var url=getContextPath()+"/user/security/info.ajax";
		var param={};
		param.url=url;
		param.data={"id":id};
		initForm(param);

		var iff=true;
		var inte=window.setInterval(function () {
			if(!iff){
				window.clearInterval(inte);
			}
			else{
				if($('input[name="id"]').val()!=''){
					if($('#tree').html()!=''){
						iff=false;
						var thisNode=$('#tree').treeview('getNode',parseInt($('#organizationid').val()));
						if(typeof(thisNode.nodeId)=='undefined'){
							return;
						}
						$('#organizationname').val(thisNode.text);
						$('#tree').treeview('selectNode', [ thisNode.nodeId, { silent: true } ]);
						pro(thisNode);
					}
				}
			}
		},500);

		$('#organizationname').click(function (event) {
			//$('#tree').treeview('collapseAll', { silent: true });
			$('#tree').show();
			//$('#tree').treeview('expandAll', { silent: true });
		});
		$('#organizationname').parent().parent().mouseleave(function () {
			$('#tree').hide();
		});

		// $('form').bind("click",function(e){
		// 	var e = e || window.event; //浏览器兼容性
		// 	var elem = e.target || e.srcElement;
		// 	while (elem) { //循环判断至跟节点，防止点击的是div子元素
		// 		if (elem.id && elem.id == 'organizationname') {
		// 			$('#tree').show();
		// 			return;
		// 		}
		// 		if(elem.tagName=='SPAN'||elem.tagName=='LI'||elem.tagName=='UL'){
		// 			return;
		// 		}
		// 		if(elem.id && elem.id == 'tree'){
		// 			return;
		// 		}
		// 		elem = elem.parentNode;
		// 	}
		// 	$('#tree').hide();
		// });

	});