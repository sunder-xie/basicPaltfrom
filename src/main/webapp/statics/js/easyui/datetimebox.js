$.extend($.fn.datagrid.defaults.editors, {
	datetimebox : {
		init : function(container, options) {
			var input = $('<input class="easyui-datebox">').appendTo(
					container);
			return input.datebox({
				formatter : function(date) {
					return new Date(date).format("yyyy-MM-dd");
				}
			});
		},
		getValue : function(target) {
			return $(target).parent().find('input.combo-value').val();
		},
		setValue : function(target, value) {
			$(target).datebox("setValue", value);
		},
		resize : function(target, width) {
			var input = $(target);
			if ($.boxModel == true) {
				input.width(width - (input.outerWidth() - input.width()));
			} else {
				input.width(width);
			}
		}
	}
});
Date.prototype.format = function (format) 
{
    var o = {
        "M+": this.getMonth() + 1, //month 
        "d+": this.getDate(),    //day 
        "h+": this.getHours(),   //hour 
        "m+": this.getMinutes(), //minute 
        "s+": this.getSeconds(), //second 
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter 
        "S": this.getMilliseconds() //millisecond 
    };
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
    (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
      RegExp.$1.length == 1 ? o[k] :
        ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};