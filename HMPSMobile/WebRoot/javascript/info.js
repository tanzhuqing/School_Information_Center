$(document).bind("mobileinit", function () {
    $("#impoPage").live("pageshow", function () {
        loadInfo("重要通知");
    });

    $("#meetPage").live("pageshow", function () {
        loadInfo("会议讲座");
    });

    $("#zonghePage").live("pageshow", function () {
        loadInfo("综合信息");
    });

});

function loadInfo(infoType) {
    var url = 'info/infoAction_listInfoJSON.action';
    var data = {"infoType":infoType};
    $.ajax({
        type:'post',
        url:url,
        dataType:'json',
        data:data,
        success:function (xhr) {
            showInfo(infoType, xhr);
        },
        error : function (){
            alert('error');
        }
    });
}

function showInfo(infoType, data){
    var html = "";
    for (var i = 0; i < data.length; i++) {
        html += "<li><a href='javascript:showDetails(" + data[i].id + ")'>" + data[i].title + "</a></li>";
//        html += "<li><a href='surroundingDetail.html?id=1' rel='external' data-transition='slidedown'>" + data[i].name + "</a></li>";
    }

    if (infoType == "重要通知"){
        $("#impoList").html("");
        $("#impoList").html(html);
        $("#impoList").listview();
        $('#impoList').listview("refresh");
    }
    if (infoType == "会议讲座"){
        $("#meetList").html("");
        $("#meetList").html(html);
        $("#meetList").listview();
        $('#meetList').listview("refresh");
    }
    if (infoType == "综合信息"){
        $("#zongheList").html("");
        $("#zongheList").html(html);
        $("#zongheList").listview();
        $('#zongheList').listview("refresh");
    }

}

function showDetails(id){
    var url = 'info/infoAction_listInfoDetailJSON.action';
    var data = {"id":id};
    $.ajax({
        type:'post',
        url:url,
        dataType:'json',
        data:data,
        success:function (xhr) {
        	$('#title').html("");
            $('#title').html(xhr.title);
            $('#content').html("");
            $('#content').html(xhr.content);
        },
        error : function (){
            alert('error');
        }
    });
    $.mobile.changePage($("#detail"));
}