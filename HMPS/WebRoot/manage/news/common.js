function changeMenu(name,a){
	var menu = document.getElementById("menu_"+name);
	for(var i=1;i<a;i++){
		var menuName = "m"+i;
		var listName = "none";
		if(menuName==name){
			if(menu.style.display=="none"){
			listName="block";
			}else{
				listName = "none";
			}
		}
	document.getElementById("menu_"+menuName).style.display = listName;
	}
}