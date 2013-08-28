/*************************************************************
 * �ļ�˵����
 *		��������ȫ�ֱ����ͺ��������Թ��ͻ��˵��á�
 *************************************************************/

 /**
  * ����ɱ༭����ķ���ģʽ
  */
 var WX_ACCESSMODE_READ='read';//���ģʽ��ֻ��editabledetail����������Ч
 
 var WX_ACCESSMODE_ADD='add';//���ģʽ����editabledetail��form����������Ч
 
 var WX_ACCESSMODE_UPDATE='update';//�޸�ģʽ����editabledetail��form����������Ч
 
 var WX_ACCESSMODE_READONLY='readonly';//ֻ��ģʽ�������пɱ༭����������Ч
 
 /**
  * ����ɱ༭����ı��涯������
  */
 var WX_SAVETYPE_SAVE='save';//�������
 
 var WX_SAVETYPE_DELETE='delete';//ɾ������

/**
 * ���ͬһҳ�������������Զ��б���/���б�ѡ�е��ж���
 * �˶���Ϊһ����JAVA�е�Map<reportGuid,Map<trid,trObj>>��������reportGuidΪ�����guid��Map<trid,trObj>Ϊ�˱������б�ѡ�е�<tr/>������<tr/>��idΪ����<tr/>����Ϊֵ��
 * ���Ҫ��ȡĳ���������б�ѡ�е��ж���ʱ��ֻ�������������<report/>��guid�õ���ѡ���е�<tr/>���󼯺ϣ�ѭ�����Ϳ��Եõ����б�ѡ���еĶ���
 * �����ѡ�е��ж����ǿɱ༭�����Զ��б���������<tr/>����trObj.getAttribute('EDIT_TYPE')=='add'
 * һ������ѡ�лص������п��ܻ���Ҫȡ����Щ��ѡ���ж���
 * ����getAllSelectedTrObjs(pageid,reportid)������Ϊ��ȡĳ������/������ѡ���ж���
 */
var WX_selectedTrObjs;

/**
 * ˢ��ĳ���������ʾ
 * @param pageid ҳ��ID
 * @param componentid Ҫˢ�µ���������Ϊ�ջ�Ϊpageid����ˢ������ҳ�����ʾ
 * @param isReset �Ƿ����ñ�ˢ�������״̬
 */
function refreshComponentDisplay(pageid,componentid,isReset)
{
	var componentguid=getComponentGuidById(pageid,componentid);
	var cmetaDataObj=getComponentMetadataObj(componentguid);
	if(cmetaDataObj==null) return;
	var url=getComponentUrl(pageid,cmetaDataObj.refreshComponentGuid,cmetaDataObj.metaDataSpanObj.getAttribute('slave_reportid'));
	if(url==null||url=='') return;
	if(isReset===true)
	{
		url=resetComponentUrl(pageid,componentid,url,null);
	}
	refreshComponent(url);
}

/**
 *����������onKeyPress�¼�
 */
function onInputBoxKeyPress(event)
{
 	var intKey=-1;
	var srcObj;
	if(window.event)
	{
		intKey=window.event.keyCode;
		srcObj=window.event.srcElement;
	}else
	{
		intKey=event.which;
		srcObj=event.target;
	}
	if(intKey==13)
	{
		var frmObj=getParentElementObj(srcObj,'FORM');
		if(frmObj!=null)
		{
			var idx=0;
			for(;idx<frmObj.elements.length;idx++)
			{
				if(frmObj.elements[idx]==srcObj)
				  break;
			}
			if(idx==frmObj.elements.length) return false;
			var nextObj;
			var times=0;
			while(true)
			{//������ת����ǰ��������һ���������
			   idx=(idx+1)%frmObj.elements.length;
				nextObj=frmObj.elements[idx];
				if(++times==frmObj.elements.length||nextObj==srcObj) break;//�ֻص����ʼ�������������
				if(!nextObj||nextObj.disabled)
				{//��ǰ������ǽ���״̬����ȡ��һ��
					continue;
				}
				nextObj.focus();
				break;
			}
		}
		return false;
	}else
 	{
 		return true;
 	}
}


/**
 * ��ȡĳ����������ѡ���е�<tr/>����
 * @param pageid ��������ҳ��ID
 * @param reportid ����id
 * @return ���ر�����ѡ���еĶ������飬�Ұ��кŴ�С�����ź���
 */
function getAllSelectedTrObjs(pageid,reportid)
{
	if(WX_selectedTrObjs==null) return null; 
	var reportguid=getComponentGuidById(pageid,reportid);
	var selectTrObjs=WX_selectedTrObjs[reportguid];
	if(selectTrObjs==null) return null;
	var tridPrex=null;
	var maxRownum=-1;//�������ѡ�е����������к�
	var idxTmp;
	for(var key in selectTrObjs)
	{
		idxTmp=key.lastIndexOf('_tr_');
		if(idxTmp<0) continue;
		var myrownum=parseInt(key.substring(idxTmp+'_tr_'.length),10);//ȡ����tr���к�
		if(myrownum>maxRownum) maxRownum=myrownum;
		if(tridPrex==null) tridPrex=key.substring(0,idxTmp+'_tr_'.length);
	}
	var resultsArr=new Array();
	if(maxRownum<0)
	{//���û��ȡ������кţ�����û��ѡ���У���selectTrObjsΪ�գ�����<tr/>��id���Ϸ�
		for(var key in selectTrObjs)
		{
			resultsArr[resultsArr.length]=selectTrObjs[key];
		}
	}else
	{
		for(var i=0;i<=maxRownum;i++)
		{//��ѡ���и����кŴ�С�������������
			if(selectTrObjs[tridPrex+i]!=null) resultsArr[resultsArr.length]=selectTrObjs[tridPrex+i];
		}
	}
	return resultsArr;
}

/**
 * �ж�ĳ���Ƿ�ѡ��
 */
function isSelectedRow(trObj)
{
	if(WX_selectedTrObjs==null) return false;
	if(trObj==null||!isListReportDataTrObj(trObj)) return false;
	var trid=trObj.getAttribute('id');
	var reportguid=trid.substring(0,trid.lastIndexOf('_tr_'));//�����guid
  	if(reportguid=='') return false;
	var allSelectedTrObjs=WX_selectedTrObjs[reportguid];
	if(allSelectedTrObjs==null||allSelectedTrObjs[trid]==null) return false;
	return true;
}

/**
 * ѡ���ж���
 * @param trObj ��ѡ�е�<tr/>����
 * @param invokeOnloadMethod �Ƿ���Ҫִ����ѡ�еĻص��������������õĻ����Զ����ɵģ�����ˢ�´ӱ���ȣ���Ĭ��Ϊ��ִ�У�����true��ִ��
 */
function selectReportDataRow(trObj,invokeOnloadMethod)
{
	if(trObj==null) return;
	var trid=trObj.getAttribute("id");
	if(trid==null||trid=='') return;
  	var reportguid=trid.substring(0,trid.lastIndexOf('_tr_'));//�����guid
  	if(reportguid=='') return;
  	var rowselecttype=getRowSelectType(reportguid);
  	if(rowselecttype!='checkbox'&&rowselecttype!='radiobox'&&rowselecttype!='single'&&rowselecttype!='multiply') return;
	if(rowselecttype=='checkbox'||rowselecttype=='radiobox')
	{//ͨ����ѡ���ѡ�������ѡ��
		var selectBoxObjTmp=null;
		if(rowselecttype=='checkbox')
		{
			selectBoxObjTmp=getSelectCheckBoxObj(trObj);
		}else
		{
			selectBoxObjTmp=getSelectRadioBoxObj(trObj);
		}
		if(selectBoxObjTmp==null) return;
		selectBoxObjTmp.checked=true;
		var tmp='false';
		if(invokeOnloadMethod==true) tmp='true';
		doSelectedDataRowChkRadio(selectBoxObjTmp,tmp);
	}else
	{//��ͨ��ѡ��
		if(!isListReportDataTrObj(trObj)) return;//���ǿ�ѡ�е���
		initRowSelect();
		var deselectedTrObjsArr=null;
		if(rowselecttype=='single')
		{//���ֻ�ܵ���ѡ��
			deselectedTrObjsArr=deselectAllDataRow(reportguid);
		}
		selectDataRow(trObj,true);
		if(invokeOnloadMethod==true)
		{
			var selectedTrObjArr=new Array();
			selectedTrObjArr[0]=trObj;
			deselectedTrObjsArr=getRealDeselectedTrObjs(selectedTrObjArr,deselectedTrObjsArr);
			invokeRowSelectedMethods(getPageIdByComponentGuid(reportguid),getComponentIdByGuid(reportguid),selectedTrObjArr,deselectedTrObjsArr);
		}
	}
}

/**
 * ȡ��ĳ�е�ѡ��״̬
 */
function deselectReportDataRow(trObj,invokeOnloadMethod)
{
	if(trObj==null||!isSelectedRow(trObj)) return;
	var trid=trObj.getAttribute("id");
	if(trid==null||trid=='') return;
  	var reportguid=trid.substring(0,trid.lastIndexOf('_tr_'));//�����guid
  	if(reportguid=='') return;
  	var rowselecttype=getRowSelectType(reportguid);
  	if(rowselecttype!='checkbox'&&rowselecttype!='radiobox'&&rowselecttype!='single'&&rowselecttype!='multiply') return;
	if(rowselecttype=='checkbox')
	{
		var selectBoxObjTmp=getSelectCheckBoxObj(trObj);
		if(selectBoxObjTmp==null) return;
		selectBoxObjTmp.checked=false;//���˸�ѡ����Ϊ��ѡ��״̬
		var tmp='false';
		if(invokeOnloadMethod==true) tmp='true';
		doSelectedDataRowChkRadio(selectBoxObjTmp,tmp);
	}else
	{
		deselectDataRow(trObj);
		if(rowselecttype=='radiobox')
		{
			var selectBoxObjTmp=getSelectRadioBoxObj(trObj);
			if(selectBoxObjTmp!=null) selectBoxObjTmp.checked=false;
		}
		if(invokeOnloadMethod==true)
		{
			var deselectedTrObjsArr=new Array();
			deselectedTrObjsArr[0]=trObj;
			invokeRowSelectedMethods(getPageIdByComponentGuid(reportguid),getComponentIdByGuid(reportguid),null,deselectedTrObjsArr);
		}
	}
}

/*******************************************************************************
 *���汾�ν��з���������ɾ�Ĳ��������ݣ������ڱ����Ļص�������aftersave���ʵ�
 *WX_ALL_SAVEING_DATAΪһMap���󣬼�Ϊ��ǰ��������б���guid����Ϊ���ܰ󶨱�����������
 *                              ֵΪ��Ӧ��������������ݣ���Ϊһ��Array����������ÿ��Object�����ű����һ�м�¼����Object���Բ�����Ϊ��������ֵΪֵ
 *		���ڿɱ༭ϸ���������ͣ���Array���������������ֻ��һ��Object������Ϊ���ֱ�������һ��ֻ������ɾ����һ����¼��
 *      ���������Զ��б���������ж��Object������Ϊ������ͬʱ����ɾ���Ķ�����¼��							
 *      ���ĳ�������ڱ���ʱ�����û��Լ���ӵ�WX_CUSTOMIZE_DATAS��׼����������ݣ������Ǵ����һ��������Object�����У���������Array�����С�
 *			��ʱ�����ϸ����������䱣��ֵ��Array����Ҳ������Ԫ���ˡ�
 *		���ÿ����¼��Object������һ������ļ�WX_TYPE,����ֵΪadd��update��delete��customize֮һ��ǰ�������Ǳ�ʾ�Ե�ǰ��¼�������ֲ�����
 *          �����customize��ʾ��ǰ��¼�Ǵ�������û��Զ���ı������ֵ�������EditableDetailReportType���������ͣ�������ͨ�������û��Զ��屣�����ݣ�
 *                                                                    ��WX_TYPEֵΪcustomize.add��customize.update��customize.delete
 ******************************************************************************/
var WX_ALL_SAVEING_DATA;

/**
 * @param dynDefaultValues����ӵ�ǰ����ʱĳ����ĳЩ�е�Ĭ��ֵ�����ڿɱ༭�б������͵�������/��ѡ�򣬻�����ָ��Ĭ��ֵ��<td/>�е���ʾlabel����Ϊ�����ǵ��ʱ�Ż���ʾ���������Ҫ����ʾĬ��ֵ��Ӧ��label����
 *									ָ����ʽΪjson�ַ�������ʽ������ʾ:
 *										{col1:value1,col1$label:label1,col3:value3,...}��
 *										����col1��col3Ϊ��Ӧ�е�column��������ֵ�����ڿɱ༭�б�����������ѡ�����Ҫָ��ĳ����Ĭ��ֵ��Ӧ����ʾֵ����ͨ������column$label����ָ��
 *			��������¼�¼�����ĸ�����ܣ���ο������ýӿڷ�������ؽ���
 */
function addListReportNewDataRow(pageid,reportid,dynDefaultValues)
{
	var reportguid=getComponentGuidById(pageid,reportid);
	addNewDataRow(pageid,reportguid,dynDefaultValues);
}

/**
 * ����һ������������ֵ��������ɱ༭�������ͼ���ѯ������������Ч��
 * @param isConditionbox Ҫ����ֵ��������Ƿ��ǲ�ѯ���������true���ǲ�ѯ���������false���ɱ༭����༭�����
 * @param newvaluesObj ��������Ҫ������ֵ���������Ӧ����ֵ��ͨ��json��֯����ʽΪ��{name1:"newvalue1",name2:"newvalue2",...}�����е�name�����������
 *								�����ǰ�����ò�ѯ����������ֵ�������е�name1��name2�ȷֱ�Ϊ��������ڲ�ѯ����<condition/>��name���ԣ�
 *								�����ǰ�����ÿɱ༭�������ϵ�������ֵ�������е�name1��name2�ȷֱ�ΪҪ������ֵ����������е�<col/>��column���ԡ�
 *							newvalue1��newvalue2��ΪΪ�������õ���ֵ
 * @param conditionsObj �˲���ֻ������editablelist2/listform���ֱ������͵ı༭���������ֵ��Ч�����ڶ�λ��Щ������Ӧ������ֵ�����ûָ�������������������м�¼������Щ�е�ֵ��
 *             �˲���Ҳ��ͨ��json��ʽ��֯�����ֻ��һ�����������ʽΪ{name:"column1",value:"value1",matchmode:"equals"}����Ϊ[{name:"column1",value:"value1",matchmode:"equals"}]��
 *												   ����ж�����������ʽΪ��[{name:"column1",value:"value1",matchmode:"equals"},{name:"column2",oldvalue:"value2",matchmode:"include"},...]
 *					���ڴ˷����ĸ���ʹ��˵������ο������ýӿڷ�������ؽ���
 */
function setReportInputBoxValue(pageid,reportid,isConditionbox,newvaluesObj,conditionsObj)
{
	var reportguid=getComponentGuidById(pageid,reportid);
	if(isConditionbox)
	{//��ǰ�����ò�ѯ����������ֵ
		setConditionInputBoxValue(reportguid,newvaluesObj);
	}else
	{//�༭�����
		var metadataObj=getReportMetadataObj(reportguid);
		if(metadataObj.reportfamily==ReportFamily.EDITABLELIST2||metadataObj.reportfamily==ReportFamily.LISTFORM)
		{
			setEditableListReportColValue(reportguid,newvaluesObj,conditionsObj,false);
		}else if(metadataObj.reportfamily==ReportFamily.EDITABLEDETAIL2)
		{
			setEditableDetail2ReportColValue(reportguid,newvaluesObj,false);
		}else if(metadataObj.reportfamily==ReportFamily.EDITABLEDETAIL||metadataObj.reportfamily==ReportFamily.FORM)
		{
			setEditableDetailReportColValue(reportguid,newvaluesObj,false);
		}
	}
}

/**
 * ����һ�������༭���ϵ�ֵ�������пɱ༭�������Ͷ���Ч
 * ����������������setReportInputBoxValue()������ȫ��ͬ
 */
function setEditableReportColValue(pageid,reportid,newvaluesObj,conditionsObj)
{
	var reportguid=getComponentGuidById(pageid,reportid);
	var metadataObj=getReportMetadataObj(reportguid);
	if(metadataObj.reportfamily==ReportFamily.EDITABLELIST2||metadataObj.reportfamily==ReportFamily.LISTFORM)
	{
		setEditableListReportColValue(reportguid,newvaluesObj,conditionsObj,true);
	}else if(metadataObj.reportfamily==ReportFamily.EDITABLEDETAIL2)
	{
		setEditableDetail2ReportColValue(reportguid,newvaluesObj,true);
	}else if(metadataObj.reportfamily==ReportFamily.EDITABLEDETAIL||metadataObj.reportfamily==ReportFamily.FORM)
	{
		setEditableDetailReportColValue(reportguid,newvaluesObj,true);
	}
}

/**
 * ���ò�ѯ����������ֵ
 * @param paramname Ҫ������ֵ�Ĳ�ѯ����<condition/>��name����ֵ
 * @param paramvalue Ϊ�˲�ѯ������������õ���ֵ
 */
function setInputboxValueForCondition(pageid,reportid,paramname,paramvalue)
{
	if(paramname==null||paramname=='') return;
	if(paramvalue==null) paramvalue='';
	var reportguid=getComponentGuidById(pageid,reportid);
	var newvalue='{'+paramname+':"'+jsonParamEncode(paramvalue)+'"}';
	setConditionInputBoxValue(reportguid,getObjectByJsonString(newvalue));
}

/**
 * ���ÿɱ༭����ϸ��������ͨ��ĳ��������ֵ������edtaibledetail/form����������editabledetail2��
 * @param paramname ��<col/>��column��������ֵ
 * @param paramvalue Ϊ������������õ���ֵ
 */
function setInputboxValueForDetailReport(pageid,reportid,paramname,paramvalue)
{
	if(paramname==null||paramname=='') return;
	if(paramvalue==null) paramvalue='';
	var reportguid=getComponentGuidById(pageid,reportid);
	var newvalue='{'+paramname+':"'+jsonParamEncode(paramvalue)+'"}';
	setEditableDetailReportColValue(reportguid,getObjectByJsonString(newvalue),false);
}

/**
 * ���ÿɱ༭�����Զ��б���editablelist2���б��listform��ĳ���ϵ���ֵ
 * @param trObj Ҫ������ֵ���ж���
 * @param newvaluesObj Ҫ���õ��е�column����Ӧ����ֵ����json�ַ�����֯���������setReportInputBoxValue()�����е�newvaluesObj��ʽһ�£�
 *						  ���ﲻ��ָ��������������Ϊ�Ѿ�������Ҫ����ֵ��<tr/>������ 
 */
function setEditableListReportColValueInRow(pageid,reportid,trObj,newvaluesObj)
{
	var reportguid=getComponentGuidById(pageid,reportid);
	if(!isEditableListReportTr(reportguid,trObj)) return;
	setEditableListReportColValueInRowImpl(reportguid,trObj,newvaluesObj,true);
}

/**
 * ��ȡ�ɱ༭����ĸ����¾�����
 * @param columnsObj ָ��Ҫ��ȡ�����ݵ�<col/>��column���ϣ�ָ����ʽΪ��{column1:true,column2:true,......}������column1��column2��ΪҪ��ȡ���ݵ�<col/>��column����ֵ
 *						���û��ָ������ָ��Ϊnull����ȡ����������
 *	@return	����ǿɱ༭ϸ�������򷵻�һ��Object����������������Ҫ��ȡ�������ݶ�������������������column:���<col/>��column��������ֵ��value��ŵ�ǰ�е��������ݣ�oldvalue��ŵ�ǰ�е�ԭʼ����
 *				����ǿɱ༭�����б�����б�����򷵻�Array��������ÿ��Ԫ���Ƕ�Ӧһ�м�¼�����ݶ������ݽṹ������ϸ�������Object����һ��
 * ��������������������setEditableReportColValue()��������Ӧ������ͬ
 */
function getEditableReportColValues(pageid,reportid,columnsObj,conditionsObj)
{
	var reportguid=getComponentGuidById(pageid,reportid);
	var metadataObj=getReportMetadataObj(reportguid);
	if(metadataObj.reportfamily==ReportFamily.EDITABLELIST||
		metadataObj.reportfamily==ReportFamily.EDITABLELIST2||
		metadataObj.reportfamily==ReportFamily.LISTFORM)
	{
		return getEditableListReportColValues(pageid,reportguid,columnsObj,conditionsObj);
	}else if(metadataObj.reportfamily==ReportFamily.EDITABLEDETAIL2)
	{
		return getEditableDetail2ReportColValues(pageid,reportguid,columnsObj);
	}else if(metadataObj.reportfamily==ReportFamily.EDITABLEDETAIL||metadataObj.reportfamily==ReportFamily.FORM)
	{
		return getEditableDetailReportColValues(pageid,reportguid,columnsObj);
	}
	return null;
}

/**
 * ��ȡָ������ָ���е�����
 * @param columnsObj ����ҳ�ӿڷ�����columnsObj��ȫһ�������ֵΪnull�����ȡ���������е�����
 */
function getEditableListReportColValuesInRow(trObj,columnsObj)
{
	return getEditableListReportColValuesInRowImpl(trObj,columnsObj);
}

/**
 * ����һ�������ɱ༭���������
 * paramsObj�ṹΪ
 *		pageid:"pageid",
 *		transaction:"trans_level",
 *		savingReportIds:[{reportid:"reportid1",reporttype:"reportype1",updatetype:"save"},{reportid:"reportid2",reporttype:"reportype2",updatetype:"delete|all"},...]
 *		updatetype��save��delete����ȡֵ������editablelist2/listform���������Զ��б�����deleteʱ������Ը���|all��ʾɾ����ҳ���ϴ˱����������ݣ����ûָ������ֻɾ��ѡ�е��С�
 */
function saveEditableReportData(paramsObj)
{
	//stopSaveForDemo();return;
	saveEditableReportDataImpl(paramsObj);
}

/**
 * ɾ���ɱ༭�����Զ��б����������
 * @param trObjArray ��ɾ�����ж�����ж������飬��ɾ�����п��������е��У�Ҳ��������������
 */
function deleteEditableListReportRows(pageid,reportid,trObjArray)
{
	//stopSaveForDemo();return;
	if(trObjArray==null) return;
	var reportguid=getComponentGuidById(pageid,reportid);
	deleteEditableListReportRowsImpl(reportguid,convertToArray(trObjArray));
}

/**
 * ���ĳ��������÷������˵�JAVA����д����ڵ���JAVA��ʱ�ᴫ��˱��������
 *
 * @param pageid Ҫ�����ı�������ҳ��<page/>��id���ԣ��������
 * @param reportid Ҫ�����ı���ID���������
 * @param serverClassName Ҫ���õķ����������ȫ�޶��������������
 * @param shouldRefreshPage �����걨�����ݺ��Ƿ�Ҫˢ�±�����ʾ����ѡ������Ĭ��ֵΪtrue
 * @param conditionsObj ����editablelist2/listform���ֱ������ͣ�ָ��Ҫ������Щ�е����ݵ����������ָ࣬����ʽ�������ȡ�����������ֱ�������������ʱ��conditionsObj������ȫһ�£���ѡ����
 * @param beforeCallbackMethod ��ȡ���������ݺ�׼�������̨����ǰִ�У������ڴ˷�����ȡ���������ݣ�������ֹ���õ�ִ��
 * @param afterCallbackMethod ����������������Ҫִ�еĿͻ��˻ص�����
 */
function invokeServerActionForReportData(pageid,reportid,serverClassName,shouldRefreshPage,conditionsObj,beforeCallbackMethod,afterCallbackMethod)
{
	if(pageid==null||pageid=='')
	{
		wx_alert('pageid��������Ϊ�գ�����ָ��ҳ��ID');
		return;
	}
	if(reportid==null||reportid=='')
	{
		wx_alert('reportid��������Ϊ�գ�����ָ������ID');
		return;
	}
	if(serverClassName==null||serverClassName=='')
	{
		wx_alert('serverClassName��������Ϊ�գ�����ָ��Ҫ���õķ����������ȫ�޶�����');
		return;
	}
	if(shouldRefreshPage==null) shouldRefreshPage=true;
	invokeServerActionForReportDataImpl(pageid,reportid,serverClassName,shouldRefreshPage,conditionsObj,beforeCallbackMethod,afterCallbackMethod);
}

/**
 * ���ҳ���ҳ����ĳ��������÷������˵Ĳ����������������
 * 	����Ĵ�������IDֻӰ��ִ��ʱ����ˢ��ҳ��ʱˢ�·�Χ���������ĸ�����ĳ�ʼ�������е��ô˷��������࣬�Լ������������ˢ���ĸ������
 * @param pageid ҳ��ID
 * @param componentid ��ǰ��������Ե������id�����Ϊ�գ����൱���������ҳ��
 * @param datas ���÷������˷���ʱ��������ݣ�������һ������Ҳ������һ�����飬�����һ�����������ʽΪ{a1:'b1',a2:'b2',...}�������һ���������飬���ʽΪ[{a1:'b1',a2:'b2',...},{c1:'d1',c2:'d2',...}]
 */
function invokeServerActionForComponent(pageid,componentid,serverClassName,datas,shouldRefreshPage,callbackMethod)
{
	if(pageid==null||pageid=='')
	{
		wx_alert('pageid��������Ϊ�գ�����ָ��ҳ��ID');
		return;
	}
	if(serverClassName==null||serverClassName=='')
	{
		wx_alert('serverClassName��������Ϊ�գ�����ָ��Ҫ���õķ����������ȫ�޶�����');
		return;
	}
	if(shouldRefreshPage==null) shouldRefreshPage=true;
	invokeServerActionForComponentImpl(pageid,componentid,serverClassName,datas,shouldRefreshPage,callbackMethod);
}

/**
 * ����componentid����������Ǳ������������������������õ�ServerSQLActionButton��ť�¼�����������ִ�������õ�SQL���
 * ע�⣺���ͻ��˵��õ�ServerSQLActionButton��ť���õ�shouldRefreshPage��callbackMethod��Ч������ͨ�����ﴫ��������������ΪshouldRefreshPage�ڿͻ�����Ҫʹ�ã�callbackMethod�������ﴫ�Ƚ����
 *	@param componentid �����õİ�ť���������ID
 * @param buttonname �����ð�ť��Ӧ<button/>��ǩ��name����
 * @param datas �������������������
 */
function invokeComponentSqlActionButton(pageid,componentid,buttonname,datas,shouldRefreshPage,callbackMethod)
{
	if(buttonname==null)
	{
		wx_alert('����ָ�������ð�ť��name');
		return;
	}
	invokeServerActionForComponent(pageid,componentid,'button{'+buttonname+'}',datas,shouldRefreshPage,callbackMethod);
}

/**
 * ���β������ڿͻ�����������˷���һ����ͨ��������ĳ������򱨱�ҳ��û�й�ϵ
 * ���ǵ���һ�η�������serverClassName���executeServerAction()������������datas����
 *
 * @param datas  ���÷������˷���ʱ��������ݣ�������һ������Ҳ������һ�����飬�����һ�����������ʽΪ{a1:'b1',a2:'b2',...}�������һ���������飬���ʽΪ[{a1:'b1',a2:'b2',...},{c1:'d1',c2:'d2',...}]
 *						ע�⣺���ﴫ����json���ݣ���������治����������ס
 * @param callbackMethod ��������ִ����󷵻�ʱ����ûص�������ִ��ʱ���Զ�����������˷��ص��ַ�����Ϊ����
 * @param onErrorMethod ���÷������˷�������ʱ�Ĵ�����
 */
function invokeServerAction(serverClassName,datas,callbackMethod,onErrorMethod)
{
	if(serverClassName==null||serverClassName=='')
	{
		wx_alert('serverClassName��������Ϊ�գ�����ָ��Ҫ���õķ����������ȫ�޶�����');
		return;
	}
	invokeServerActionImpl(serverClassName,datas,callbackMethod,onErrorMethod);
}

/**************************************************
 * ��ʾ/�������ڼ���ҳ����ʾ�ӿڷ���
 *************************************************/
/**
 * ��ʾ���ڼ�����ʾ��Ϣ
 */
function displayLoadingMessage()
{
	window.status='loading...';
   var imgobj=document.getElementById('LOADING_IMG_ID');
	if(imgobj!=null)
	{
		imgobj.style.display='block';
		imgobj.style.top = document.body.clientHeight+document.body.scrollTop-imgobj.clientHeight;// + "px";
   	imgobj.style.left = document.body.clientWidth+document.body.scrollLeft-imgobj.clientWidth;// + "px";
	}
} 

/**
 * �������ڼ�����ʾ��Ϣ
 */
function hideLoadingMessage()
{
	window.status='';
	var imgobj=document.getElementById('LOADING_IMG_ID');
   if(imgobj!=null) imgobj.style.display='none';
}
 
/**************************************************
 * ��Ϣ��ʾ�ӿڷ���
 *************************************************/

/**
 * ͨ��alert��ʽ��ʾ��Ϣ������Ĳ�����message�ⶼ��Ĭ��ֵ�����Բ���
 * @param message ��ʾ����
 * @param title ��ʾ���ڵı���
 *	@param width ��ʾ���ڵĿ��
 * @param height ��ʾ���ڵĸ߶�
 * @param handler ��ʾʱ�ص����� Ĭ��ֵΪnull
 */
function wx_alert(message,title,width,height,handler)
{
	if(typeof ymPrompt=='undefined')
	{
		alert(message);
		return;
	}
	if(title==null) title='��ʾ';
	ymPrompt.alert({message:message,title:title,width:width,height:height,handler:handler});
}

/**
 * ��ʾ������Ϣ
 * ����������wx_alert��ͬ
 */
function wx_error(message,title,width,height,handler)
{
	if(typeof ymPrompt=='undefined')
	{
		alert(message);
		return;
	}
	if(title==null) title='����';
	ymPrompt.errorInfo({message:message,title:title,width:width,height:height,handler:handler});
}

/**
 * ��ʾ�ɹ���Ϣ
 * ����������wx_alert��ͬ
 */
function wx_success(message,title,width,height,handler)
{
	if(typeof ymPrompt=='undefined')
	{
		alert(message);
		return;
	}
	if(title==null) title='�ɹ�';
	ymPrompt.succeedInfo({message:message,title:title,width:width,height:height,handler:handler});
}

/**
 * ��ʾȷ����Ϣ����handler�����⣬��������������wx_alert��ͬ
 * �����������handler�����У���������һ����������������Ϊinput�����û������ʾ���ڵġ�ȷ������ťʱ���ᴫ��'ok'�����û������ȡ������ťʱ���ᴫ��'cancel'
 * �����û��Ϳ�����handler�������и����û�����İ�ť������Ҫ�����ִ�����
 */
function wx_confirm(message,title,width,height,handler)
{
	if(typeof ymPrompt=='undefined') return;
	if(title==null) title='ȷ��';
	ymPrompt.confirmInfo({message:message,title:title,width:width,height:height,handler:handler});
}

/**
 * �Դ��ڵ���ʽ��ʾ��ʾ��Ϣ
 * @param maxbtn true����ʾ������󻯰�ť��false������ʾ������󻯰�ť
 *	@param minbtn true����ʾ������С����ť��false������ʾ������С����ť
 */
function wx_win(message,title,width,height,maxbtn,minbtn,handler)
{
	if(typeof ymPrompt=='undefined') return;
	ymPrompt.win({message:message,width:width,height:height,title:title,handler:handler,maxBtn:maxbtn,minBtn:minbtn,iframe:false});
}

/**
 * �Դ�����ʽ��ʾ��ҳ��
 * @param url��Ҫ��ʾ����ҳ��URL
 */
function wx_winpage(url,title,width,height,maxbtn,minbtn,handler)
{
	if(typeof ymPrompt=='undefined') return;
	ymPrompt.win({message:url,width:width,height:height,title:title,handler:handler,maxBtn:maxbtn,minBtn:minbtn,iframe:true});
}

/*********************************************
 * ���ù��ܺ���
 ********************************************/
 
var WX_API_LOADED=true;//���ڱ�ʶ��js�ļ������� 