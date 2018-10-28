var elementNumber = 2;
var elementCounter = 1;

function deleteElement(button) {
	var textId = button.id.substring('delete_button'.length);
	button.parentNode.removeChild(button);
	var textElement = document.getElementById(textId);
	textElement.parentNode.removeChild(textElement);
	var elemLabelId = "elem_label" + textId.toString();
	var elemLabelElement = document.getElementById(elemLabelId);
	elemLabelElement.parentNode.removeChild(elemLabelElement);
	elementCounter--;
	if(elementCounter === 1) {
		for(var element=1; element<elementNumber; element++) {
			var deleteButtonId = "delete_button"+element.toString();
			var deleteButton = document.getElementById(deleteButtonId);
			if(deleteButton != null) {
				deleteButton.parentNode.removeChild(deleteButton);
			}
		}
	}
	// After deletion, check if button must be disabled/enabled
	buttonDisabledOrEnabled();
}

function addElement()
{
	var delivery = document.getElementById("element_div");
	if(elementCounter === 1) {
		for(var element=1; element<elementNumber; element++) {
			elementId = element.toString();
			var pendingElement = document.getElementById(elementId);
			if(pendingElement != null) {
				delivery.insertAdjacentHTML("beforeend", "<button type=\"button\" id=\"delete_button1\" onclick=\"deleteElement(this);\">Borrar Elemento</button>");
			}
		}
	}

    delivery.insertAdjacentHTML("beforeend", "<span id=\"elem_label" + elementNumber + "\"><br>Elemento: </span><input type=\"text\" onkeyup=\"buttonDisabledOrEnabled();\" name=\"elements\" id=\"" +
		elementNumber + "\" /> <button type=\"button\" id=\"delete_button" + elementNumber +"\" onclick=\"deleteElement(this);\">Borrar Elemento</button>");
	document.getElementById('submit_button').disabled = true;
    elementNumber++;
    elementCounter++;
}

function buttonDisabledOrEnabled() {
	console.log("ESERGBR - Checking Submit button");
	for(var element=1; element<elementNumber; element++) {
		var elemText = document.getElementById(element.toString());
		if(elemText != null && elemText.value === "") {
			document.getElementById('submit_button').disabled = true;
			console.log("ESERGBR - Submit button disabled:" + elementText);
			return false;
		}
	}
	if(document.getElementById("delivery_name").value === "") {
		document.getElementById('submit_button').disabled = true;
		console.log("ESERGBR - Submit button disabled, empty delivery name");
		return false;
	}
	document.getElementById('submit_button').disabled = false;
	return true;
}