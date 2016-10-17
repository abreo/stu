var data_table=$('#data_table');
$(function(){
	data_table.bootstrapTable({
		method:'post',
		classes:'table table-bordered',
		url:getContextPath()+'/role/security/list.ajax',
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
		parent.layer.open({
		    type: 2,
		    title: '编辑',
		    shadeClose: true,
		    shade: 0.5,
		    area: ['500px', '300px'],
		    content: getContextPath()+'/page/security_system_role_edit?id='+data_table.bootstrapTable('getSelections')[0].id, //iframe的url
		    end:refreshTable,
		    btn:['确认','取消'],
		    yes:function(index, layero){
		    	alert('yes');
		    	//layero.selector
		    	//parent.layer.close(index);
		        var body = parent.layer.getChildFrame('body', index);
		        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
		        console.log(body.html()) //得到iframe页的body内容
		        body.find('input').val('Hi，我是从父页来的');
		    },
		    btn2:function(index, layero){
		    	alert('no');
		    }
	    });
	});
});