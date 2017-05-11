/**
 * Æ·ÅÆÉ¸Ñ¡¿ò
 * @param $
 */
(function($) {
	$.parser.plugins.push("brand");
	
	$.fn.brand = function(options, param) {
		if (typeof options == "string") {
			var method = $.fn.brand.methods[options];
            if (method){
                return method(this, param);
            	return $.fn.combobox.apply(this, arguments);
            }
		}
		options = options || {};
		
		return this.each(function() {
			var jq = $(this);
			var opts = $.extend({}, $.fn.combobox.parseOptions(this), options);
			var myopts = $.extend(true, {
				data: data,
				valueField: 'id',
				textField: 'name',
				editable: false,
				onSelect: function(record) {
					$.data(this, 'getSelected', record);
				}
			}, opts);

			$.fn.combobox.call(jq, myopts);
		});
	}
	
	$.fn.brand.methods = {
		getSelected: function(jq, param) {
			return $.data(jq[0], 'getSelected');
		}
	}
})(jQuery);

var data = [ {
	'id' : '',
	'name' : 'È«²¿'
}, {
	'id' : 'X',
	'name' : 'ÏãÆ®Æ®'
}, {
	'id' : 'M',
	'name' : 'MECO'
}, {
	'id' : 'L',
	'name' : 'À¼·¼Ô°'
} ];