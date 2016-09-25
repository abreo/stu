(function($) {
	$.fn.serializeJson = function() {
		var serializeObj = {};
		$(this.serializeArray()).each(function() {
			serializeObj[this.name] = this.value;
		});
		return serializeObj;
	};
	
	$.fn.validate = function() {
		var check = true;
		var valiTags = $(this).find('.need-validate');
		for (var i = 0, len = valiTags.length; i < len; i++) {
			var valiStrs = $(valiTags[i]).attr('validation');
			if (typeof (valiStrs) != 'undefined') {
				var valiStr = valiStrs.split(',');
				var message = '';
				for (var j = 0, lenj = valiStr.length; j < lenj; j++) {
					switch (valiStr[j]) {
					case 'required':
						if ($.trim($(valiTags[i]).val()) == '') {
							message = message + '必输项!';
						}
						break;
					case 'cellphone':
						var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
						if ($(valiTags[i]).val() != ''
								&& !reg.test($(valiTags[i]).val())) {
							message = message + '手机号码格式有误!';
						}
						break;
					default:
						break;
					}
				}
				if (message != '') {
					$(valiTags[i]).attr('title', message);
					$(valiTags[i]).parent().removeClass('has-success');
					$(valiTags[i]).parent().addClass('has-error');
					$(valiTags[i]).tooltip('show');
					check = false;
				} else {
					$(valiTags[i]).attr('title', '');
					$(valiTags[i]).parent().removeClass('has-error');
					$(valiTags[i]).parent().addClass('has-success');
					$(valiTags[i]).tooltip('destroy');
				}
			}
		}
		return check;
	};
})(jQuery);

$(function() {
	 $(".need-validate").bind("input propertychange", function() {
		 if($(this).attr('title')!=''){
			 $(this).attr('title', '');
			 $(this).parent().removeClass('has-error');
			 $(this).parent().addClass('has-success');
			 $(this).tooltip('destroy');
		 }
	 });
})

/**
 * 获取contextPath
 * 
 * @returns
 */
function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}

function refreshTable() {
	var data_table = $("#data_table");
	data_table.bootstrapTable("refresh");
}

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function initForm(param, tag) {
	$.ajax({
		type : 'post',
		url : param.url,
		data : param.data,
		dataType : 'json',
		success : function(data) {
			if (data.code == 403) {
				alert(data.message);
			} else {
				for ( var key in data.data) {
					$("#" + key).val(data.data[key]);
				}
			}
		},
		error : function() {
			alert("异常!");
		}
	});
}