function jumpPage(pageNo) {
    $("#pageNo").val(pageNo);
    $("#mainForm").submit();
}
function query() {
    $("#pageNo").val(1);
    $("#mainForm").submit();
}

function deleteItem(action){
     if (confirm("您真的想删除么？")) {
        window.location.href = action ;
    }

}
function deleteItems(input1, action) {
    var objs = document.getElementsByName(input1);
    var flag = false;
    var result = "";

    for (var i = 0; i < objs.length; ++i) {
        if (objs[i].checked) {
            var v = objs[i].value;
            result += v + "@";
            flag = true;
        }
    }

    if (!flag) {
        alert("您尚未选择任何数据！");
        return;
    }
    else if (confirm("您真的想删除么？")) {
        window.location.href = action + "?ids=" + result;
    }

}

function chkall(input1, input2, input3) {
    var objForm = document.forms[input1];
    var objLen = objForm.length;
    for (var iCount = 0; iCount < objLen; iCount++) {
        if (input2.checked == true) {
            if (objForm.elements[iCount].type == "checkbox" && objForm.elements[iCount].name == input3) {
                if (objForm.elements[iCount].disabled == true) {
                    objForm.elements[iCount].checked = false;
                } else {
                    objForm.elements[iCount].checked = true;
                }
            }
        } else {
            if (objForm.elements[iCount].type == "checkbox" && objForm.elements[iCount].name == input3) {
                objForm.elements[iCount].checked = false;
            }
        }
    }
}

function change(o, a, b, c, d) {
    var t = document.getElementById(o).getElementsByTagName("tr");
    for (var i = 1; i < t.length - 1; i++) {
        t[i].style.backgroundColor = (t[i].sectionRowIndex % 2 == 0) ? a : b;
        t[i].onclick = function() {
            if (this.x != "1") {
                this.x = "1";
                this.style.backgroundColor = d;
            } else {
                this.x = "0";
                this.style.backgroundColor = (this.sectionRowIndex % 2 == 0) ? a : b;
            }
        }
        t[i].onmouseover = function() {
            if (this.x != "1")this.style.backgroundColor = c;
        }
        t[i].onmouseout = function() {
            if (this.x != "1")this.style.backgroundColor = (this.sectionRowIndex % 2 == 0) ? a : b;
        }
    }
}

function isNotEmpty(element/*element in the page*/, message/*error message*/)
{
	if(0 == element.value.length)
	{
		alert(message);
		element.focus();
		return false;
	}

	return true;
}