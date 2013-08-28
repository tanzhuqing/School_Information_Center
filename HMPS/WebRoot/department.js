$(document).bind("mobileinit", function () {
    $("#jiguanPage").live("pageshow", function () {

        loadDepartment("行政机构");
    });
    $("#yuanxiPage").live("pageshow", function () {
        loadDepartment("教学院系");
    });
    $("#zhishuPage").live("pageshow", function () {
        loadDepartment("直属部门");
    });

});

function loadDepartment(departmentType) {
    var url = 'department/departmentAction_listMainDepartmentJSON.action';
    var data = {"deptTypeName":departmentType};
    $.ajax({
        type:'post',
        url:url,
        dataType:'json',
        data:data,
        success:function (xhr) {
            showDepartment(departmentType, xhr);
        },
        error:function () {
            alert('error');
        }
    });
}

function showDepartment(departmentType, data) {
    var html = "";
    for (var i = 0; i < data.length; i++) {
        html += "<li><a href='javascript:showDetail(" + data[i].id + ")'>" + data[i].name + "</a></li>\n";
    }

    if (departmentType == '行政机构') {
        $('#jiguanList').empty();
        $('#jiguanList').html("");
        $('#jiguanList').html(html);
        $('#jiguanList').listview();
        $('#jiguanList').listview("refresh");
    }
    if (departmentType == '教学院系') {
        $('#yuanxiList').html("");
        $('#yuanxiList').html(html);
        $('#yuanxiList').listview();
        $('#yuanxiList').listview("refresh");
    }

    if (departmentType == '直属部门') {
        $('#zhishuList').html("");
        $('#zhishuList').html(html);
        $('#zhishuList').listview();
        $('#zhishuList').listview("refresh");
    }

}


function showDetail(mainDeptCode) {
    var url = 'department/departmentAction_listSubDepartmentJson.action';
    var data = {"mainDeptCode":mainDeptCode};
    $.ajax({
        type:'post',
        url:url,
        dataType:'json',
        data:data,
        success:function (xhr) {
            var html = "";
            for (var i = 0; i < xhr.length; i++) {
                html += "<li><a href='javascript:showDetails(" + i + ")'>" + xhr[i].name + "</a></li>";
            }
            $('#detailList').html(html);
            $('#detailList').listview();
            $.mobile.changePage($("#detail"));
        },
        error:function () {
            alert('error');
        }
    });

}





