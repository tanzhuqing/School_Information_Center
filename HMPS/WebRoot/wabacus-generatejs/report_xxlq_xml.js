function xxlq_wh_guid_report1_validateSaveUpdate(metadataObj){var paramsObj=getObjectByJsonString(metadataObj.metaDataSpanObj.getAttribute('validateSaveMethodDynParams'));var fontChilds=document.getElementsByName('font_xxlq_wh_guid_report1');if(fontChilds==null||fontChilds.length==0) return true;var boxObj;var boxValue;var value_name;for(var i=0;i<fontChilds.length;i=i+1){if(fontChilds[i]==null) continue;boxObj=fontChilds[i];value_name=boxObj.getAttribute('value_name');if(value_name==null||value_name=='') continue;boxValue=getInputboxValueByParentFont(boxObj);if(boxValue==null) boxValue=''; if(value_name=='title') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('标题：列不允许为空');return false;} } if(value_name=='rq') {  if(!isDate(boxValue,boxObj)){wx_alert(''+boxValue+'格式不对，必须为yyyy-MM-dd格式');return false;} } if(value_name=='sj') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('{3}列不允许为空');return false;} } if(value_name=='content') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('{1}列不允许为空');return false;} }} return true;}function xxlq_wh_guid_report1_validateSaveInsert(metadataObj){var paramsObj=getObjectByJsonString(metadataObj.metaDataSpanObj.getAttribute('validateSaveMethodDynParams'));var fontChilds=document.getElementsByName('font_xxlq_wh_guid_report1');if(fontChilds==null||fontChilds.length==0) return true;var boxObj;var boxValue;var value_name;for(var i=0;i<fontChilds.length;i=i+1){if(fontChilds[i]==null) continue;boxObj=fontChilds[i];value_name=boxObj.getAttribute('value_name');if(value_name==null||value_name=='') continue;boxValue=getInputboxValueByParentFont(boxObj);if(boxValue==null) boxValue=''; if(value_name=='title') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('标题：列不允许为空');return false;} } if(value_name=='rq') {  if(!isDate(boxValue,boxObj)){wx_alert(''+boxValue+'格式不对，必须为yyyy-MM-dd格式');return false;} } if(value_name=='sj') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('{3}列不允许为空');return false;} } if(value_name=='content') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('{1}列不允许为空');return false;} }} return true;}