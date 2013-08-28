function getP_ByJstyp(v_name){
	var thisURL = document.URL; 
	//var thisHREF = document.location.href; 
	//var thisSLoc = self.location.href; 
	//var thisDLoc = document.location;  

	var indJs = thisURL.indexOf("loginType=js");
	if(indJs>0){
		//var vpstr="&"+v_name+"=";
		var vpstr=v_name+"=";
		var vp = thisURL.indexOf(vpstr);
		return thisURL.substring(vp+vpstr.length,indJs-1);
	}
	return "";
}

function ICrypto_De(vh_,v_){
	var t = getP_ByJstyp("sPwd");
	if(t!=''){
		v_=t;
	}
	v_=encodeURIComponent(v_);
	jQuery.ajax({            
		url:vh_+"/com-portlet/ws/crypto",
		type: "GET",
		dataType: 'jsonp',
   		jsonp:'callback',
		cache:false, 
		async:false,
		beforeSend:function(){}, 
		data:({type:'de',source:v_}),
		success:function(json){
			sungard_login(json.source);
		},
		error:function(t){
			alert("error:"+t);
		}
	});
};
function ICrypto_En(vh_,v_){
	var t = getP_ByJstyp("sPwd");
	if(t!=''){
		v_=t;
	}
	v_=encodeURIComponent(v_);
	jQuery.ajax({            
		url:vh_+"/com-portlet/ws/crypto",
		type: "GET",
		dataType: 'jsonp',
   		jsonp:'callback',
		cache:false, 
		async:false,
		beforeSend:function(){}, 
		data:({type:'en',source:v_}),
		success:function(json){
			sungard_login(json.source);
		},
		error:function(t){
			alert("error:"+t);
		}
	});
};



