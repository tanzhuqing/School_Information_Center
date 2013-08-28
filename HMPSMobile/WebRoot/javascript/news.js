var count = 0;
$(document).bind("mobileinit", function () {
    $("#newsPage").live("pageshow", function () {
        loadNews();
   });
     
});

function loadNews() {
    var url = 'news/newsAction_listNewsJson.action';
     $.ajax({
        type:'post',
        url:url,
        dataType:'json',
        data:"",
        success:function (xhr) {
        //	count +=8;
       		showNews(xhr);
        
        },
        error : function (){
            alert('error');
        }
    });
   
}

function showNews(data){
    var html = "";
    
   for (var i = 0; i < data.length; i++) {
    	// for (var i = data.length-1; i > (data.length-count-1); i--) {
    	 //html += "<li><a href='javascript:showDetails(" + data[i].id + ")'>" + data[i].smallImage + data[i].title + "</a></li>";
    	//html += "<li><a href='javascript:showDetails(" + data[i].id + ")'><img src='./HeuNewsPic/2012050511491818.jpg' class='ui-li-icon'></img>" + data[i].title + "</a></li>";
    	html += "<li><a href='javascript:showDetails(" + data[i].id + ")'><img src='./"+data[i].smallImage+"' class='ui-li-icon'></img>" + data[i].title + "</a></li>";
//        html += "<li><a href='surroundingDetail.html?id=1' rel='external' data-transition='slidedown'>" + data[i].name + "</a></li>";
           
    	 }
    	
        $("#newsList").html("");
        $("#newsList").html(html);
        $("#newsList").listview();
        $('#newsList').listview("refresh");

}

function showDetails(id){
    var url = 'news/newsAction_listNewsDetailJSON.action';
    var data = {"id":id};
    $.ajax({
        type:'post',
        url:url,
        dataType:'json',
        data:data,
        success:function (xhr) {
        	$('#title').html("");
            $('#title').html(xhr.title);
            $('#author').html("");
            $('#author').html(xhr.author);
            $('#date').html("");
            $('#date').html(xhr.publishDate);
            $('#content').html("");
            $('#content').html(xhr.content);
        },
        error : function (){
            alert('error');
        }
    });
    $.mobile.changePage($("#detail"));
}

