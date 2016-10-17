(function($) {
	$.fn.serializeJson = function() {
		var serializeObj = {};
		$(this.serializeArray())
				.each(
						function() {
							if (this.name.indexOf("-checkbox") > 0) {
								if (typeof (serializeObj[this.name.substring(0,
										this.name.indexOf("-checkbox"))]) == 'undefined') {
									if (this.name.indexOf("#int") > 0) {
										serializeObj[this.name.substring(0,
												this.name.indexOf("-checkbox"))] = [ parseInt($
												.trim(this.value)) ];
									} else {
										serializeObj[this.name.substring(0,
												this.name.indexOf("-checkbox"))] = [ $
												.trim(this.value) ];
									}
								} else {
									if (this.name.indexOf("#int") > 0) {
										serializeObj[this.name.substring(0,
												this.name.indexOf("-checkbox"))]
												.push(parseInt($
														.trim(this.value)));
									} else {
										serializeObj[this.name.substring(0,
												this.name.indexOf("-checkbox"))]
												.push($.trim(this.value));
									}
								}
							} else {
								if (this.name.indexOf("#int") > 0) {
									serializeObj[this.name] = parseInt($
											.trim(this.value));
								} else {
									serializeObj[this.name] = $
											.trim(this.value);
								}
							}
						});
		return serializeObj;
	};

	$.fn.sound = function(sound) {
		$(this).html(
				'<audio autoplay="autoplay">'
						+ '<source src="/stu/static/sounds/' + sound
						+ '" type="audio/wav"/>'
						+ '<source src="/stu/static/sounds/' + sound
						+ '" type="audio/mpeg"/></audio>');
	};

	$.fn.validate = function() {
		var check = true;
		var valiTags = $(this).find('[validation]');
		for (var i = 0, len = valiTags.length; i < len; i++) {
			var valiStrs = $(valiTags[i]).attr('validation');
			if (typeof (valiStrs) != 'undefined') {
				var valiStr = valiStrs.split(',');
				var message = '';
				for (var j = 0, lenj = valiStr.length; j < lenj; j++) {
					switch (valiStr[j]) {
					case 'required':
						if ($.trim($(valiTags[i]).val()) == '') {
							message = message + '不能为空!';
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
					$(valiTags[i]).parent().addClass('has-error');
					$(valiTags[i]).parent().removeClass('has-success');
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
		if (!check) {
			$("<div class='hidden need-remove-sound'></div>").sound("sound2.ogg");
		}
		return check;
	};
})(jQuery);
$(document).ready(function() {
	$("form").find('[validation]').bind("input propertychange", function() {
		if($(this).parent().hasClass('has-error')){
			$(this).attr('title', '');
			$(this).parent().removeClass('has-error');
			$(this).tooltip('destroy');
			//var idd=$(this).attr('aria-describedby');
			//$(this).removeAttr('aria-describedby');
			//$(this).removeAttr('data-original-title');
			//$('#'+idd).remove();
			//$(this).next().remove();
		}
	});
});

var checkSelected=function(){
	var selects=data_table.bootstrapTable('getSelections');
	var selected=selects[0];
	if(selected==undefined){
		top.TopLobibox('notify','info', {
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
				$("<div class='hidden need-remove-sound'></div>").sound("sound2.ogg");
				Lobibox.alert('warning', {
					msg : data.message
				});
			} else {
				for ( var key in data.data) {
					$("#" + key).val(data.data[key]);
					if (typeof ($("#" + key).attr("disabled")) != "undefined") {
						$("#" + key).attr('title', data.data[key]);
					}
				}
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