$(document).bind("mobileinit", function () {
    $("#page1").live("pageshow", function () {
        loadSurrounding("001");
    });

    $("#page2").live("pageshow", function () {
        loadSurrounding("002");
    });

    $("#page3").live("pageshow", function () {
        loadSurrounding("003");
    });

});

function loadSurrounding(surroundingType) {
    var url = 'surrounding/surroundingAction_listSurroundingJSON.action';
    var data = {"type":surroundingType};
    $.ajax({
        type:'post',
        url:url,
        dataType:'json',
        data:data,
        success:function (xhr) {
            showSurrounding(surroundingType, xhr);
        },
        error : function (){
            alert('error');
        }
    });
}

function showSurrounding(surroundingType, data){
    var html = "";
    for (var i = 0; i < data.length; i++) {
    	html += "<li><a href='javascript:showDetails(" + data[i].id + ")'><img src='" + data[i].imageNameURL + "'class='ui-li-icon2'></img><br>&nbsp;&nbsp;&nbsp;&nbsp;" + data[i].name + "</a></li>";
//        html += "<li><a href='javascript:showDetails(" + data[i].id + ")'>" + data[i].name + "<img src='" + data[i].imageName + "'>" + "</a></li>";
//        html += "<li><a href='surroundingDetail.html?id=1' rel='external' data-transition='slidedown'>" + data[i].name + "</a></li>";
    }

    if (surroundingType == "001"){
        $("#foodList").html("");
        $("#foodList").html(html);
        $("#foodList").listview();
        $('#foodList').listview("refresh");
    }
    if (surroundingType == "002"){
        $("#amuseList").html("");
        $("#amuseList").html(html);
        $("#amuseList").listview();
        $('#amuseList').listview("refresh");
    }
    if (surroundingType == "003"){
        $("#shopList").html("");
        $("#shopList").html(html);
        $("#shopList").listview();
        $('#shopList').listview("refresh");
    }

}

function showDetails(id){
    var url = 'surrounding/surroundingAction_listSurroundingDetailJSON.action';
    var data = {"id":id};
    $.ajax({
        type:'post',
        url:url,
        dataType:'json',
        data:data,
        success:function (xhr) {
            $('#title').html(xhr.name);
            $('#content').html(xhr.content);
            $('#address').html(xhr.address);
            $('#website').html("<a href='" + xhr.website + "' style='text-decoration:none'>相关站点</a>");
            $('#tel').html("<a href=tel:'" + xhr.tel + "' style='text-decoration:none'>拨打电话</a>");
        },
        error : function ()
        {
            alert('error');
        }
    });
    $.mobile.changePage($("#detail"));
}






