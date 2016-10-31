var data_table=$('#data_table');
$(function(){
	data_table.bootstrapTable({
		method:'post',
		classes:'table table-bordered',
		url:getContextPath()+'/role/security/list.ajax',
		pagination:'true',
		sortName:'seq',
		sortOrder:'asc',
		sidePagination:'server',
		idField:'id',
		contentType:'application/json',
		queryParamsType:'undefined',
		clickToSelect:true,
		height:$('body').height()-5,
		toolbar:'#toolbar',
		showColumns:true,
		pageList:[10,20,30,40,50,100,200],
		queryParams:function(params){
			var param = $('#param-form').serializeJson();
			if($.trim(param.rolename)==''){
				 delete param.rolename;
			}
			var postData = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				      pageSize: params.pageSize,   //页面大小
				      pageNumber: params.pageNumber,  //页码
				      sortName: params.sortName,  //排序列名
				      sortOrder: params.sortOrder,//排位命令（desc，asc）
				      param: param
				    };
			return postData;
		},
		onLoadSuccess:function(data){
			if(data.code!=200){
				top.TopLobibox('notify','error', {
	    			title:'获取数据失败',
	    			msg:data.message,
	    			delay:1500
	    		}); 
			}
			else{
				if(data.rows.length==0&&data.total>0){
					data_table.bootstrapTable('refresh');
//					this.pageNumber=this.pageNumber-1;
//					data_table.bootstrapTable('refresh');
				}
			}
		},
		onLoadError:function(status){
			$("<div class='hidden need-remove-sound'></div>").sound("sound5.ogg");
			Lobibox.alert('error', {
				title:'错误',
    			msg:'错误信息:'+status
			});
		},
	    columns: [ {
	    	field: 'state',
	        radio: true
	    },{
			field: 'iconcls',
			title: '图标',
			align: 'center',
			valign: 'middle',
			formatter: function(value){
				if(typeof(value)=='undefined'){
					value='fa-user';
				}
				return "<i class='fa "+value+"'></i>";
			}
		},{
			field: 'rolename',
			title: '名称',
			align: 'center',
			valign: 'middle'
		},{
	    	field: 'description',
	        title: '描述',
	        align: 'center',
            valign: 'middle'
	    },{
	    	field: 'cdatetime',
	        title: '创建时间',
	        align: 'center',
            valign: 'middle'
	    },{
	    	field: 'udatetime',
	        title: '更新时间',
	        align: 'center',
            valign: 'middle'
	    }]
	});
	
	$('#edit_btn').click(function(){
		if(!checkSelected()){
			return;
		}
		var height=$(parent).height(),
			width=$(parent).width();
		if(width>500) width=500;
		if(height>500) height=500;
		parent.layer.open({
			type: 2,
			title: '编辑',
			shadeClose: true,
			shade: 0.5,
			area: [width+'px', height+'px'],
			content: getContextPath()+'/page/security_system_role_edit?id='+data_table.bootstrapTable('getSelections')[0].id, //iframe的url
			btn:['确认','取消'],
			yes:function(index, layero){
				var body = parent.layer.getChildFrame('body', index);
				var dataForm=body.find('#dataForm');
				if($(dataForm).validate()){
					var dataJson=body.find(dataForm).serializeJson();
					$.ajax({
						type:'post',
						url:getContextPath()+'/role/security/update.ajax',
						data : JSON.stringify(dataJson),
						contentType:'application/json',
						dataType : 'json',
						success : function(data) {
							if(data.code == 200){
								Lobibox.notify('success', {
									msg:data.message,
									delay:1500,
									soundPath:'/stu/static/sounds/'
								});
								refreshTable();
							}
							else{
								$("<div class='hidden need-remove-sound'></div>").sound("sound5.ogg");
								Lobibox.alert('error', {
									msg:data.message
								});
							}
							parent.layer.close(index);
						},
						error : function(errorThrown) {
							parent.layer.close(index);
							$("<div class='hidden need-remove-sound'></div>").sound("sound5.ogg");
							Lobibox.alert('error', {
								title:errorThrown.status+'错误',
								msg:'错误信息:'+errorThrown.statusText
							});
						}
					});
				}
				//layero.selector
				//parent.layer.close(index);
				//var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
				//console.log(body.html()) //得到iframe页的body内容
				//body.find('input').val('Hi，我是从父页来的');
			},
			btn2:function(index, layero){
				parent.layer.close(index);
			}
		});
	});

	$('#del_btn').click(function () {
		if(!checkSelected()){
			return;
		}
		$("<div class='hidden need-remove-sound'></div>").sound("sound4.ogg");
		Lobibox.confirm({
			title:"确认",
			msg:"确定要删除？",
			callback:function($this, type, ev){
				if(type==='yes'){
					$.ajax({
						type:'post',
						url:getContextPath()+'/role/security/delete.ajax',
						dataType:'json',
						data:{
							id:data_table.bootstrapTable('getSelections')[0].id
						},
						success:function(data){
							if(data.code==200){
								top.TopLobibox('notify','success', {
									msg:data.message,
									delay:1500
								});
								data_table.bootstrapTable('refresh');
							}
							else{
								$("<div class='hidden need-remove-sound'></div>").sound("sound2.ogg");
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
			}
		});
	});

	$('#save_btn').click(function () {
		var height=$(parent).height(),
			width=$(parent).width();
		if(width>500) width=500;
		if(height>500) height=500;
		parent.layer.open({
			type: 2,
			title: '新增',
			shadeClose: true,
			shade: 0.5,
			area: [width+'px', height+'px'],
			content: getContextPath()+'/page/security_system_role_save', //iframe的url
			btn:['确认','取消'],
			yes:function(index, layero){
				var body = parent.layer.getChildFrame('body', index);
				var dataForm=body.find('#dataForm');
				if($(dataForm).validate()){
					var dataJson=body.find(dataForm).serializeJson();
					$.ajax({
						type:'post',
						url:getContextPath()+'/role/security/save.ajax',
						data : JSON.stringify(dataJson),
						contentType:'application/json',
						dataType : 'json',
						success : function(data) {
							if(data.code == 200){
								Lobibox.notify('success', {
									msg:data.message,
									delay:1500,
									soundPath:'/stu/static/sounds/'
								});
								refreshTable();
								parent.layer.close(index);
							}
							else{
								$("<div class='hidden need-remove-sound'></div>").sound("sound5.ogg");
								Lobibox.alert('error', {
									msg:data.message
								});
							}
						},
						error : function(errorThrown) {
							parent.layer.close(index);
							$("<div class='hidden need-remove-sound'></div>").sound("sound5.ogg");
							Lobibox.alert('error', {
								title:errorThrown.status+'错误',
								msg:'错误信息:'+errorThrown.statusText
							});
						}
					});
				}
				//layero.selector
				//parent.layer.close(index);
				//var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
				//console.log(body.html()) //得到iframe页的body内容
				//body.find('input').val('Hi，我是从父页来的');
			},
			btn2:function(index, layero){
				parent.layer.close(index);
			}
		});
	});

	$(window).resizeEnd({
		delay : 200
	}, function() {
		data_table.bootstrapTable('resetView',{
			height:$('body').height()-5
		});
	});
});