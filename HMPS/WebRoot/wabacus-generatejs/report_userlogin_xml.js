function userloginParticular_guid_report1_validateSaveUpdate(metadataObj){var paramsObj=getObjectByJsonString(metadataObj.metaDataSpanObj.getAttribute('validateSaveMethodDynParams'));var fontChilds=document.getElementsByName('font_userloginParticular_guid_report1');if(fontChilds==null||fontChilds.length==0) return true;var boxObj;var boxValue;var value_name;for(var i=0;i<fontChilds.length;i=i+1){if(fontChilds[i]==null) continue;boxObj=fontChilds[i];value_name=boxObj.getAttribute('value_name');if(value_name==null||value_name=='') continue;boxValue=getInputboxValueByParentFont(boxObj);if(boxValue==null) boxValue=''; if(value_name=='LoginName') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('登录名列不允许为空');return false;} } if(value_name=='Password') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('密码列不允许为空');return false;} } if(value_name=='UserName') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('权限列不允许为空');return false;} }} return true;}function userloginParticular_guid_report1_validateSaveInsert(metadataObj){var paramsObj=getObjectByJsonString(metadataObj.metaDataSpanObj.getAttribute('validateSaveMethodDynParams'));var fontChilds=document.getElementsByName('font_userloginParticular_guid_report1');if(fontChilds==null||fontChilds.length==0) return true;var boxObj;var boxValue;var value_name;for(var i=0;i<fontChilds.length;i=i+1){if(fontChilds[i]==null) continue;boxObj=fontChilds[i];value_name=boxObj.getAttribute('value_name');if(value_name==null||value_name=='') continue;boxValue=getInputboxValueByParentFont(boxObj);if(boxValue==null) boxValue=''; if(value_name=='LoginName') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('登录名列不允许为空');return false;} } if(value_name=='Password') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('密码列不允许为空');return false;} } if(value_name=='UserName') {  if(!isNotEmpty(boxValue,boxObj)){wx_alert('权限列不允许为空');return false;} }} return true;}