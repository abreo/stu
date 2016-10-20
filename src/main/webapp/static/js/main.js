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
				$(valiTags[i]).parent().find(".error-mes").remove();
				if (message != '') {
					var app="<div class='error-mes' style='width: auto;" +
						"position: absolute;z-index: 500;left: 20px;" +
						"background-color: #ef3f52;font-size: 12px;" +
						"color: whitesmoke;'>&nbsp;"+message+"&nbsp;</div>";
					$(valiTags[i]).parent().append(app);
					$(valiTags[i]).css("border-color","red");
					$(valiTags[i]).addClass("error-vali");
					check = false;
				} else {
					$(valiTags[i]).css("border-color","green");
					$(valiTags[i]).removeClass("error-vali");
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
		if($(this).hasClass("error-vali")){
			$(this).parent().find(".error-mes").remove();
			$(this).css("border-color","#DCDCDC");
			$(this).removeClass("error-vali");
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