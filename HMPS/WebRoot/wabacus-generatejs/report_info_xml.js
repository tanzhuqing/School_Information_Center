function infopageedit_guid_report1_validateSaveUpdate(metadataObj){var paramsObj=getObjectByJsonString(metadataObj.metaDataSpanObj.getAttribute('validateSaveMethodDynParams'));var fontChilds=document.getElementsByName('font_infopageedit_guid_report1');if(fontChilds==null||fontChilds.length==0) return true;var boxObj;var boxValue;var value_name;for(var i=0;i<fontChilds.length;i=i+1){if(fontChilds[i]==null) continue;boxObj=fontChilds[i];value_name=boxObj.getAttribute('value_name');if(value_name==null||value_name=='') continue;boxValue=getInputboxValueByParentFont(boxObj);if(boxValue==null) boxValue=''; if(value_name=='title') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('公告标题列不允许为空');return false;} } if(value_name=='type') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('公告类型列不允许为空');return false;} } if(value_name=='publishDate') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('发布日期列不允许为空');return false;} }} return true;}function infopageedit_guid_report1_validateSaveInsert(metadataObj){var paramsObj=getObjectByJsonString(metadataObj.metaDataSpanObj.getAttribute('validateSaveMethodDynParams'));var fontChilds=document.getElementsByName('font_infopageedit_guid_report1');if(fontChilds==null||fontChilds.length==0) return true;var boxObj;var boxValue;var value_name;for(var i=0;i<fontChilds.length;i=i+1){if(fontChilds[i]==null) continue;boxObj=fontChilds[i];value_name=boxObj.getAttribute('value_name');if(value_name==null||value_name=='') continue;boxValue=getInputboxValueByParentFont(boxObj);if(boxValue==null) boxValue=''; if(value_name=='title') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('公告标题列不允许为空');return false;} } if(value_name=='type') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('公告类型列不允许为空');return false;} } if(value_name=='publishDate') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('发布日期列不允许为空');return false;} }} return true;}function info_guid_report1_loadSlaveReports(pageid,reportid,selectedTrObjArr,deselectedTrObjArr){var reportguid=getComponentGuidById(pageid,reportid);var trObj=null;if(selectedTrObjArr==null||selectedTrObjArr.length<=0){   selectedTrObjArr=getAllSelectedTrObjs(pageid,reportid);} if(selectedTrObjArr==null||selectedTrObjArr.length<=0){  var tableObj=document.getElementById(reportguid+'_data');  if(tableObj!=null){      var trChilds=tableObj.getElementsByTagName('TR');      if(trChilds!=null){          var trObjTmp;          for(var k=0,len=trChilds.length;k<len;k++){              trObjTmp=trChilds[k];              if(!isListReportDataTrObj(trObjTmp)){continue;}              var trtype=trObjTmp.getAttribute('EDIT_TYPE');if(trtype==null||trtype!='add'){trObj=trObjTmp;break;}          }          if(trObj!=null){var selectBoxObjTmp=getSelectRadioBoxObj(trObj);selectBoxObjTmp.checked=true;doSelectedDataRowChkRadio(selectBoxObjTmp,'false');          }      }  }}else{  var trObjTmp;  for(var k=selectedTrObjArr.length-1;k>=0;k--){      trObjTmp=selectedTrObjArr[k];      var trtype=trObjTmp.getAttribute('EDIT_TYPE');if(trtype==null||trtype!='add'){trObj=trObjTmp;break;}  }}if(trObj==null){  clearCurrentSlaveTrObjForReport('info','report1');  var slaveReportSpanObjTmp=null;var serverurl='';var staticlinkparams='';serverurl='/HMPS/ShowReport.wx?DISPLAY_TYPE=1&PAGEID='+pageid;staticlinkparams='';refreshComponent(serverurl+'&report2_PARENTREPORT_NODATA=true&SLAVE_REPORTID=report2'+staticlinkparams);}else{if(!shouldRefreshSlaveReportsForThisRow(trObj)) return false;var linkparams=getRefreshSlaveReportsHrefParams(trObj);if(linkparams==null||linkparams==''){wx_alert('没有取到刷新从报表数据的动态参数，刷新失败');return false;}var serverurl='/HMPS/ShowReport.wx?DISPLAY_TYPE=1&'+linkparams+'&PAGEID='+pageid;var staticlinkparams;staticlinkparams='';refreshComponent(serverurl+'&SLAVE_REPORTID=report2'+staticlinkparams);}}function info_guid_report2_validateSaveUpdate(metadataObj){var paramsObj=getObjectByJsonString(metadataObj.metaDataSpanObj.getAttribute('validateSaveMethodDynParams'));var fontChilds=document.getElementsByName('font_info_guid_report2');if(fontChilds==null||fontChilds.length==0) return true;var boxObj;var boxValue;var value_name;for(var i=0;i<fontChilds.length;i=i+1){if(fontChilds[i]==null) continue;boxObj=fontChilds[i];value_name=boxObj.getAttribute('value_name');if(value_name==null||value_name=='') continue;boxValue=getInputboxValueByParentFont(boxObj);if(boxValue==null) boxValue=''; if(value_name=='title') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('公告标题列不允许为空');return false;} } if(value_name=='type') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('公告类型列不允许为空');return false;} } if(value_name=='publishDate') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('发布日期列不允许为空');return false;} }} return true;}function info_guid_report1_onload(){info_guid_report1_loadSlaveReports('info','report1',null,null);}