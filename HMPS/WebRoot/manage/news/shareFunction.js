function MM_openChildWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

function ReplaceComma(myform){
  	var rExp = /\'/gi;
	for(var i=0;i<myform.elements.length;i++){
  		var etype=myform.elements[i].type;

		switch(etype)
		{
		   case "text" :
		      myform.elements[i].value=myform.elements[i].value.replace(rExp,'‘');
			  break;
		   case "hidden" :
              if(myform.elements[i].name=='content'){
                  //alert('I met content field, ignore..');
                  break;//把word编辑器中的内容不加处理，放到服务器端由Java处理。因为JavaScript处理大的文本时太慢
              }
		      myform.elements[i].value=myform.elements[i].value.replace(rExp,'‘');
			  break;
		   case "textarea" :
              myform.elements[i].value=myform.elements[i].value.replace(rExp,'‘');
			  break;
		   case "password" :
		      myform.elements[i].value=myform.elements[i].value.replace(rExp,'‘');
			  break;
		   default :
		      break;
		}

  	}
}

function openchildwindow(loadpos){
   window.open(loadpos,"_blank","toolbar=no,resizable=no,scrollbars=no,dependent,width=350,height=327");
}

function openwindow(loadpos,width,height){
   window.open(loadpos,"_blank","toolbar=no,resizable=no,scrollbars=yes,dependent,width="+width+",height="+height);
}
function openwindowResizable(loadpos,width,height){
   window.open(loadpos,"_blank","toolbar=no,resizable=yes,scrollbars=yes,dependent,width="+width+",height="+height);
   }

function newopenwindow(loadpos){
   window.open(loadpos,"_blank");
}

function returnLength(str){
	return str.replace(/[^\x00-xff]/g,'**').length
}


function isNumber(str){
  var checkOK = "0123456789";
  var checkStr = str;
  var allValid = true;
  var decPoints = 0;
  var allNum = "";
  var allNum_dec=0;

  for (i = 0;  i < checkStr.length;  i++)
  {
    ch = checkStr.charAt(i);
    for (j = 0;  j < checkOK.length;  j++)
      if (ch == checkOK.charAt(j))
        break;
    if (j == checkOK.length)
    {
      allValid = false;
      break;
    }
    if (ch == ".")
    {
      allNum += ".";
      decPoints++;
    }
    else if (ch != ",")
      allNum += ch;
  }
  return (allValid);
}

function myalltrim(aa) {
mylen=aa.length;
mystart=0;
myend=mylen-mystart;
for (i = 0; i < mylen; i++) {
if (aa.substr(i,1)==" ") {mystart=i+1;} else {break;}
}
if (mystart<mylen-1) {
for (i = mylen-1; i >mystart; i--) {
if (aa.substr(i,1)==" ") {myend=i;} else {break;};
}
}
return(aa.substr(mystart,myend-mystart));
}

function checkNumber(number){
  if (  myalltrim(number.value)!="" && !isNumber(myalltrim(number.value)))
	{ alert("请输入有效的数字！");
	      number.focus();
	      number.value="";
			return false;
	}
}

function isMoney(str){
  var checkOK = "0123456789.";
  var checkStr = str;
  var allValid = true;
  var decPoints = 0;
  var allNum = "";
  var allNum_dec=0;

  for (i = 0;  i < checkStr.length;  i++)
  {
    ch = checkStr.charAt(i);
    for (j = 0;  j < checkOK.length;  j++)
      if (ch == checkOK.charAt(j))
        break;
    if (j == checkOK.length)
    {
      allValid = false;
      break;
    }
    if (ch == ".")
    {
      allNum += ".";
      decPoints++;
    }
    else if (ch != ",")
      allNum += ch;
  }
  return (allValid);
}

function checkMoney(number){
  if (  myalltrim(number.value)!="" && !isMoney(myalltrim(number.value)))
	{ alert("请输入有效的金额！");
	      number.focus();
	      number.value="";
			return false;
	}
}


function windowOpener(loadpos,a_width,a_height)
{

	    var target_win = "";



               target_win = window.open(loadpos,"_blank","toolbar=no,resizable=yes,scrollbars=no,dependent,width="+a_width+",height="+a_height);

				//maximize(target_win);


}

function windowOpenerTop(loadpos,a_width,a_height)
{

	    var target_win = "";



               target_win = window.open(loadpos,"_top","toolbar=no,resizable=yes,scrollbars=no,dependent,width="+a_width+",height="+a_height);

				//maximize(target_win);


}
function windowOpenerAbout(loadpos,a_width,a_height)
{

	    var target_win = "";



               target_win = window.open(loadpos,"_about","toolbar=no,resizable=yes,scrollbars=yes,dependent,width="+a_width+",height="+a_height);

				//maximize(target_win);


}


function maximize(a_win){
    if(is1024){
		a_win.moveTo(100,100);
	    a_win.resizeTo(800, 600);
	}else{
	    a_win.moveTo(0,0);
	    a_win.resizeTo(screen.availWidth, screen.availHeight);
	}
}

//翻页模板
function refer(page)
{
  formPage.pages.value=page;
  document.formPage.submit();
}
//判断跳转页
function is_num(form_value,MAX_NUM){
  if(((event.keyCode>57)||(event.keyCode<48))&&(event.keyCode!=13)){
      alert("你输入的不是数字！");
      return false;
   }
   if((form_value=="")&&((event.keyCode-48)>parseInt(MAX_NUM))){
     alert("你输入的数字超出范围");
      return false;
   }
   if((form_value!="")&&(parseInt(form_value+""+(event.keyCode-48).toString())>parseInt(MAX_NUM))){
     alert("你输入的数字超出范围");
      return false;

   }
   return true;
}



//检测输入的是否是数字
function checkNumber(number){
  if (  myalltrim(number.value)!="" && !isNumber(myalltrim(number.value)))
	{ alert("请输入有效的数字！");
	      number.focus();
	      number.value="";
		return false;
	}
}



