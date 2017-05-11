/**
 * datagrid编辑框扩展
 * 类型：datetimebox,combogrid
 */ 
$.extend($.fn.datagrid.defaults.editors, {
	/*扩展为datetimebox*/
	datetimebox: {
	    init: function(container, options){
	         var input = $('<input class="easyuidatetimebox">').appendTo(container);
			 return input.datetimebox({
			     formatter:function(date){
			         return new Date(date).format("yyyy-MM-dd hh:mm:ss");
			         }
			     });
	    },
	    destroy: function (target) {
		    $(target).datetimebox('destroy');
		},
		getValue: function(target){
		     return $(target).parent().find('input.combo-value').val();
		},
		setValue: function(target, value){
		     $(target).datetimebox("setValue",value);
		},
		resize: function(target, width){
		    var input = $(target);
		    if ($.boxModel == true){
		        input.width(width - (input.outerWidth() - input.width()));
		    } else {
		        input.width(width);
		    }
		}
	},
	/*扩展为combogrid*/
	combogrid: {
		init: function (container, options) {
		    var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container);
			input.combogrid(options);
			return input;
		},
		destroy: function (target) {
		    $(target).combogrid('destroy');
		},
		getValue: function (target) {
		    return $(target).combogrid('getValue');
		},
		setValue: function (target, value) {
		    $(target).combogrid('setValue', value);
		},
		resize: function (target, width) {
		    $(target).combogrid('resize', width);
		}
	},
	/*品牌编辑控件*/
	brand: {
		init: function (container, options) {
		    var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container);
			input.brand(options);
			return input;
		},
		destroy: function (target) {
		    $(target).brand('destroy');
		},
		getValue: function (target) {
		    return $(target).brand('getValue');
		},
		setValue: function (target, value) {
		    $(target).brand('setValue', value);
		},
		resize: function (target, width) {
		    $(target).brand('resize', width);
		}
	},
	/*只读文本*/
	textReadonly: {
        init: function (container, options) {
            var input = $('<input type="text" readonly="readonly" class="datagrid-editable-input">').appendTo(container);
            return input;
        },
        getValue: function (target) {
            return $(target).val();
        },
        setValue: function (target, value) {
            $(target).val(value);
        },
        resize: function (target, width) {
            var input = $(target);
            if ($.boxModel == true) {
                input.width(width - (input.outerWidth() - input.width()));
            } else {
                input.width(width);
            }
        }
    },
});

/**
 *纵向扩展自动合并单元格，并在排序后重新合并 
 *合并字段要注意排列顺序，会根据前面的字段判断是否允许合并
 *用法：
  onLoadSuccess: function(){
		$(this).datagrid("autoMergeCells",["workshopId"]);
  }
   或者 $(this).datagrid("autoMergeCells");
   onSortColumn:function(sort, order){
   		$(this).datagrid("autoMergeCells",[sort]);
   }
 */
$.extend($.fn.datagrid.methods, {
	autoMergeCells: function (jq, fields) {
		//判断该单元格是否要加入合并
		function setMerges(merges, before, row, field, i, j) {
			if (!before || row[field] != before[field]) return;
			//根据前面的行判断行是否允许合并
			if (j > 0) {
				var beforeCell = $.grep(merges, function(item){
	    			return item.field == fields[j-1] && item.lastIndex == i;
	    		});
				if (!beforeCell[0]) return;
			}
			//合并行
			var cell = $.grep(merges, function(item){
    			return item.field == field && item.lastIndex == i-1;
    		});
			if (cell[0]) {
    			cell[0].rowspan = cell[0].rowspan + 1;
    			cell[0].lastIndex = i;
    		} else {
        		merges.push({index:i-1,field:field,rowspan:2,lastIndex:i});
    		}
		}
		
        return jq.each(function () {
            var target = $(this);
            if (!fields) {
                fields = target.datagrid("getColumnFields");
            }
            var rows = target.datagrid("getRows");
            var before, merges = [], maxrow = 0;
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                for (var j = 0; j < fields.length; j++) {
                	setMerges(merges, before, row, fields[j], i, j);
                }
                before = row;
            }
            $.each(merges, function(i, item) {
            	target.datagrid('mergeCells', item);
            });
        });
    }
});
