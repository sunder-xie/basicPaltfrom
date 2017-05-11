var selectedId;
var selectedName;
document.domain = "zjxpp.com";
$(document).ready(function() {

	$('#orgTree').tree(
		{
			animate : true,
			checkbox:true,
			url : appUrl
			+ '/newOrgAction!getOrgTreeListByAjax.jspa?flag=Y&node=0',
			onBeforeExpand : function(node, param) {
				$('#orgTree').tree('options').url = appUrl
						+ "/newOrgAction!getOrgTreeListByAjax.jspa?node="
						+ node.id;
				
			},
			queryParams:{},
			onClick : function(node) {// 单击事件
				$(this).tree('toggle', node.target);
				if (!node.state) {
					add(node.text, node.attributes);
				}
			}
		});
	
	$('#ss').searchbox({
		width:230,
	    searcher:function(value,name){
	    	$('#orgTree').tree('options').url = 
	    		appUrl+ '/newOrgAction!getOrgTreeListByAjax.jspa?flag=Y&node=0&orgNameUrl='+encodeURIComponent(value.trim()),
			$('#orgTree').tree('reload');
	    },
	    prompt:'请输入组织名查询'
	});
});
function submit(){
	//获取所有选中的节点
	var nodes = $('#orgTree').tree('getChecked');
	var orgName='';
	var orgId='';
	//累加字符串
	for(var i=0;i<nodes.length;i++){
		orgId+=nodes[i].id+',';
		orgName+=nodes[i].text+',';
	}
	//去除末尾的逗号
	orgId=orgId.substr(0,orgId.length-1);
	orgName=orgName.substr(0,orgName.length-1);
	select(orgId,orgName);
}
//全选/反选 
function treeChecked(selected, treeMenu) {
	var roots = $('#' + treeMenu).tree('getRoots');//返回tree的所有根节点数组
	if (selected.checked) {
		for ( var i = 0; i < roots.length; i++) {
			var node = $('#' + treeMenu).tree('find', roots[i].id);//查找节点
			$('#' + treeMenu).tree('check', node.target);//将得到的节点选中
		}
	} else {
		for ( var i = 0; i < roots.length; i++) {
			var node = $('#' + treeMenu).tree('find', roots[i].id);
			$('#' + treeMenu).tree('uncheck', node.target);
		}
	}
}

function select(selectedId, selectedName) {
	document.getElementById("orgId").value = selectedId;
	document.getElementById("orgName").value = selectedName;
	window.parent.returnValue(selectedId, selectedName);
	window.parent.closeOrgTree();
}
