//function printsetup() {
//	var form = window.document.forms[0];
//    form.wb.execwb(8,1); 
//}
//
//function printpreview() {
//	//window.document.getElementById("wb").execwb(7,1);
//	var form = window.document.forms[0];
//    wb.execwb(7,1);
//
//}
//
//function printit() { 
//		if (confirm('ȷ����ӡ��')) { 
//			if(!+[1,]){
//				var form = window.document.forms[0];
//		        form.wb.execwb(6,1);
//
//				//window.document.getElementById("wb").
//		     }else{
//		    	pagesetup_null();
//		    	window.print();
//		     }
//		}
//		
//}

var LODOP;

function Preview() {
	CreatePrintPage();
	LODOP.PREVIEW();	
};

function Print(){
	CreatePrintPage();
	LODOP.PRINT();
}

function Setup() {		
	CreatePrintPage();
	LODOP.PRINT_SETUP();		
};
	
function Design() {		
	CreatePrintPage();
	LODOP.PRINT_DESIGN();		
};
//<img id = 'log' align='right' border='0' width='5%' height = '5%' src='$!{env.imgUrl}/images/xpp/log.png' />
var url = appUrl+"/images/xpp/log.png";
function CreatePrintPage() {
	LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
	LODOP.PRINT_INITA(10,10,775,460,"�����걨");
	LODOP.ADD_PRINT_IMAGE(0,0,65,65,"<img border='0' src='"+imgUrl+"/images/xpp/log.png'/>");  
	LODOP.SET_PRINT_STYLEA(1,"Stretch",2);
	LODOP.SET_PRINT_STYLEA(1,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(1,"HOrient",3);
	LODOP.SET_PRINT_STYLEA(1,"VOrient",3);
	LODOP.ADD_PRINT_HTM(65,20,700,340,document.getElementById("event").innerHTML);
	LODOP.SET_PRINT_STYLEA(2,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(2,"ItemType",4);
	LODOP.SET_PRINT_STYLEA(2,"HOrient",3);
	LODOP.SET_PRINT_STYLEA(2,"VOrient",3);
//	LODOP.ADD_PRINT_LINE(53,23,52,725,0,1);
//	LODOP.SET_PRINT_STYLEA(3,"ItemType",1);
//	LODOP.SET_PRINT_STYLEA(3,"HOrient",3);
//	LODOP.ADD_PRINT_LINE(414,23,413,725,0,1);
//	LODOP.SET_PRINT_STYLEA(4,"ItemType",1);
//	LODOP.SET_PRINT_STYLEA(4,"HOrient",3);
//	LODOP.SET_PRINT_STYLEA(4,"VOrient",1);
//	LODOP.ADD_PRINT_TEXT(30,33,200,22,"������Ϣ");
//	LODOP.SET_PRINT_STYLEA(5,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(1080,576,144,22,"ҳ�ţ���#ҳ/��&ҳ");
	LODOP.SET_PRINT_STYLEA(3,"ItemType",2);
	LODOP.SET_PRINT_STYLEA(3,"HOrient",1);

};	

//function getLodop(oOBJECT,oEMBED){
//	/**************************
//	  ������������������;��������ĸ�������Ϊ�ؼ�ʵ����
//	  IEϵ�С�IE�ں�ϵ�е����������oOBJECT��
//	  ���������(Firefoxϵ�С�Chromeϵ�С�Operaϵ�С�Safariϵ�е�)����oEMBED��
//	**************************/
//
//	    var LODOP=oEMBED;
//	    
//		try{		     
//		     if (navigator.appVersion.indexOf("MSIE")>=0) LODOP=oOBJECT;
//
//		     if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
//			 if (navigator.userAgent.indexOf('Firefox')>=0)
//	  	         document.documentElement.innerHTML=strHtml3+document.documentElement.innerHTML;
//			 if (navigator.appVersion.indexOf("MSIE")>=0) document.write(strHtml1); else
//			 document.documentElement.innerHTML=strHtml1+document.documentElement.innerHTML;
//		     } else if (LODOP.VERSION<"6.0.1.0") {
//			 if (navigator.appVersion.indexOf("MSIE")>=0) document.write(strHtml2); else
//			 document.documentElement.innerHTML=strHtml2+document.documentElement.innerHTML; 
//		     }
//		     //*****���¿հ�λ���ʺϵ���ͳһ����:*********	     
//
//
//		     //*******************************************
//		     return LODOP; 
//		}catch(err){
//		     document.documentElement.innerHTML="Error:"+strHtml1+document.documentElement.innerHTML;
//		     return LODOP; 
//		}
//	}
