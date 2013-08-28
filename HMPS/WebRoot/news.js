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
        	count +=10;
       		showNews(xhr);
        
        },
        error : function (){
            alert('error');
        }
    });
   
}

function showNews(data){
    var html = "";
    
   /* for (var i = 0; i < data.length; i++) {*/
    	 for (var i = 0; i < count; i++) {
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



//分页代码
(function( $ ){
	 
		 
 $.fn.scrollPagination = function(options) {
  	
		var opts = $.extend($.fn.scrollPagination.defaults, options);  
		var target = opts.scrollTarget;
		if (target == null){
			target = obj; 
	 	}
		opts.scrollTarget = target;
	 
		return this.each(function() {
		  $.fn.scrollPagination.init($(this), opts);
		});

  };
  
  $.fn.stopScrollPagination = function(){
	  return this.each(function() {
	 	$(this).attr('scrollPagination', 'disabled');
	  });
	  
  };
  
  $.fn.scrollPagination.loadContent = function(obj, opts){
	 var target = opts.scrollTarget;
	 var mayLoadContent = $(target).scrollTop()+opts.heightOffset >= $(document).height() - $(target).height();
	 if (mayLoadContent){
		 if (opts.beforeLoad != null){
			opts.beforeLoad(); 
		 }
		 $(obj).children().attr('rel', 'loaded');
		 $.ajax({
			  type: 'POST',
			  url: opts.contentPage,
			  data: opts.contentData,
			  success: function(data){
				$(obj).append(data); 
				var objectsRendered = $(obj).children('[rel!=loaded]');
				
				if (opts.afterLoad != null){
					opts.afterLoad(objectsRendered);	
				}
			  },
			  dataType: 'json'
		 });
	 }
	 
  };
  
  $.fn.scrollPagination.init = function(obj, opts){
	 var target = opts.scrollTarget;
	 $(obj).attr('scrollPagination', 'enabled');
	
	 $(target).scroll(function(event){
		if ($(obj).attr('scrollPagination') == 'enabled'){
	 		$.fn.scrollPagination.loadContent(obj, opts);		
		}
		else {
			event.stopPropagation();	
		}
	 });
	 
	 $.fn.scrollPagination.loadContent(obj, opts);
	 
 };
	
 $.fn.scrollPagination.defaults = {
      	 'contentPage' : null,
     	 'contentData' : {},
		 'beforeLoad': null,
		 'afterLoad': null	,
		 'scrollTarget': null,
		 'heightOffset': 0		  
 };	
})( jQuery );