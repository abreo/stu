/**
 * 获取contextPath
 * @returns
 */
function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}

function refreshTable(){
	var data_table = $("#data_table");
	data_table.bootstrapTable("refresh");
}

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

function initForm(param,tag)
{
     $.ajax({
    	type:'post',
    	url:param.url,
    	data:param.data,
    	dataType:'json',
    	success:function(data){
    		if(data.code==403){
    			alert(data.message);
    		}
    		else{
    			for(var key in data.data){
    				$("#"+key).val(data.data[key]);
    			}
    		}
    	},
    	error:function(){
    		alert("异常!");
    	}
     });
}