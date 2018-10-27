var elementNumber = 2;
function addElement()
{
	var delivery = document.getElementById("element_div");
    delivery.insertAdjacentHTML("beforeend", "Elemento:<input type=\"text\" onkeyup=\"buttonDisable();\" name=\"elements\" id=\"" + elementNumber + "\" /><br>");
	document.getElementById('submit_button').disabled = true;
    elementNumber++;
}

function buttonDisable() {
	for(var element=1; element<elementNumber; element++) {
		if(document.getElementById(element.toString()).value ==="") {
			document.getElementById('submit_button').disabled = true;
			return false;
		}
	}
	if(document.getElementById("delivery_name").value ==="") {
		document.getElementById('submit_button').disabled = true;
		return false;
	}
	document.getElementById('submit_button').disabled = false;
	return true;
}