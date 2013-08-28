$(document).bind("mobileinit", function(){
	$("#campusview").live("pageshow", function(){
		loadphoto();
	});
});

function loadphoto(){
	var url = 'introduction/introductionAction_campusViewJson.action';
	$.ajax({
		type: 'post',
		url: url,
		data:"",
		dataType: 'json',
		success: function(xhr){
			showphoto(xhr);
		},
		error: function(){
			alert('error');
		}
	});
}

function showphoto(data){
		var html = "";
		for(var i = 0; i < data.length; i++){
			//html += "<li><img src="+ data[i].imageName +" alt = "+ data[i].content +"/></li>\n";
			html += "<li><a href = '."+ data[i].imageName +"' rel='external' ><img src='."+ data[i].imageName +"' alt = "+ data[i].content +"/></a></li>\n";
		}
		//$('#photo').html("");
		$("#photo").html(html);
}