	var data_table=$('#data_table');
    $(function(){
    	$.ajax({
			type:'post',
			url:getContextPath()+'/organization/owntree.ajax',
			dataType : 'json',
			success:function(data){
				var app="";
				for(var i=0,len=data.ids.length;i<len;i++){
					app=app+"<input type='checkbox' name='organization-checkbox#int' data-id='"+data.ids[i]+"' value='"+data.ids[i]+"' checked>";
				}
				$('#param-form').append(app);
				$('#tree').treeview({
					data:data.organizations,
					levels:1,
					nodeIcon:'fa fa-institution',
					onNodeSelected:function(event,data){
						//alert(JSON.stringify($('#param-form').serializeJson()));
						$('#param-form input[name="organization-checkbox#int"]').removeAttr('checked');
						$('#param-form input[data-id="'+data.id+'"]').prop('checked',true);
						$('#param-form input[name="loginname"]').val('');
						$('#param-form input[name="username"]').val('');
						//alert($('#param-form input[name="organization"]').val());
						//data_table.bootstrapTable('refresh');
						data_table.bootstrapTable('selectPage',1);
					}
				});
				data_table.bootstrapTable({
					method:'post',
					classes:'table table-bordered',
					url:getContextPath()+'/user/security/list.ajax',
					pagination:'true',
					sortName:'id',
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
						if(typeof(param.organization)=='undefined'){
							param.organization=[-1];
						}
						if($.trim(param.loginname)==''){
							 delete param.loginname;
						}
						if($.trim(param.username)==''){
							 delete param.username;
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
							top.TopLobibox('error', {
				    			title:'获取数据失败',
				    			msg:data.message,
				    			delay:1500
				    		}); 
						}
						else{
							if(data.rows.length==0&&data.total>0){
								data_table.bootstrapTable('refresh');
//								this.pageNumber=this.pageNumber-1;
//								data_table.bootstrapTable('refresh');
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
				        field: 'loginname',
				        title: '登录名',
				        align: 'center',
		                valign: 'middle'
				    },{
				    	field: 'username',
				        title: '用户名',
				        align: 'center',
		                valign: 'middle'
				    },{
				    	field: 'gender',
				        title: '性别',
				        align: 'center',
		                valign: 'middle'
				    },{
				    	field: 'birthday',
				        title: '生日',
				        align: 'center',
		                valign: 'middle'
				    },{
				    	field: 'phonenum',
				        title: '电话',
				        align: 'center',
		                valign: 'middle'
				    },{
				    	field: 'email',
				        title: '邮箱',
				        align: 'center',
		                valign: 'middle'
				    },{
				    	field: 'organization.organizationname',
				        title: '机构',
				        align: 'center',
		                valign: 'middle'
				    }]
				});
			}
		});
    	
		
		$('#search_btn').click(function(){
			layer.open({
			    type: 2,
			    title: '查找',
			    shadeClose: true,
			    shade: 0.5,
			    area: ['500px', '250px'],
			    content: getContextPath()+'/page/security_system_user_search?',
			    end:function(){
			    	$('#tree').treeview('expandAll', { silent: true });
			    	data_table.bootstrapTable('selectPage',1);
			    }
		    });
		});
		
		$('#edit_btn').click(function(){
			if(!checkSelected()){
				return;
			}
			parent.layer.open({
			    type: 2,
			    title: '编辑',
			    shadeClose: true,
			    shade: 0.5,
			    area: ['800px', '350px'],
			    content: getContextPath()+'/page/security_system_user_edit?id='+data_table.bootstrapTable('getSelections')[0].id, //iframe的url
			    end:refreshTable
		    });
		});
		
		$('#del_btn').click(function(){
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
							url:getContextPath()+'/user/security/delete.ajax',
							dataType:'json',
							data:{
								id:data_table.bootstrapTable('getSelections')[0].id
							},
							success:function(data){
								if(data.code==200){
									top.TopLobibox('success', {
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
							}
						});
					}
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
    var checkSelected=function(){
    	var selects=data_table.bootstrapTable('getSelections');
		var selected=selects[0];
		if(selected==undefined){
			top.TopLobibox('info', {
    			title:'未选择',
    			msg:'请选择一条记录',
    			delay:1500,
    			soundPath:'/stu/static/sounds/'
    		});
			return false;
		}
		else{
			return true;
		}
    };