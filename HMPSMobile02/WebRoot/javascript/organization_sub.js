$(document).bind("mobileinit", function () {
    $("#organization").live("pageshow", function () {
        loadOrg("机关部门");
    });
    $("#organization").live("pageshow", function () {
        loadOrg("直属机构");
    });
    $("#organization").live("pageshow", function () {
        loadOrg("院系设置");
    });
	/*$("#organization").live("pageshow", function () {
        loadOrg();
    });*/
});

function loadOrg(tid) {
    var url = 'introduction/introductionAction_organizationSubJson.action';
    var data = {"tid":tid};
    $.ajax({
        type:'post',
        url:url,
        dataType:'json',
        data:data,
        success:function (xhr) {
            showOrg(tid,xhr);
        },
        error : function (){
            alert('error');
        }
    });
}

function showOrg(tid,data){
    var html = "";
    for (var i = 0; i < data.length; i++) {
        html += "<li><a href='javascript:showDetails(" + data[i].id + ")'>" + data[i].child + "</a></li>";
    }
    if (tid == "机关部门"){
        $("#jiguan").html("");
        $("#jiguan").html(html);
        $("#jiguan").listview();
        $('#jiguan').listview("refresh");
    }
    if (tid == "直属机构"){
        $("#zhishu").html("");
        $("#zhishu").html(html);
        $("#zhishu").listview();
        $('#zhishu').listview("refresh");
    }
    if (tid == "院系设置"){
        $("#yuanxi").html("");
        $("#yuanxi").html(html);
        $("#yuanxi").listview();
        $('#yuanxi').listview("refresh");
    }
    
}

function showDetails(id){
    var url = 'introduction/introductionAction_listOrganizationSubDetailJSON.action';
    var data = {"id":id};
    $.ajax({
        type:'post',
        url:url,
        dataType:'json',
        data:data,
        success:function (xhr) {
        	$('#child').html("");
            $('#child').html(xhr.child);
            $('#content').html("");
            $('#content').html(xhr.text);
        },
        error : function (){
            alert('error');
        }
    });
    $.mobile.changePage($("#detail"));
}