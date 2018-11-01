var nextElementNumber = 2;
var elementCounter = 1;

function setNextElementNumber(elemNumber) {
	nextElementNumber = elemNumber+1;
	elementCounter = elemNumber;
}

function checkedChange(check) {
	var groupId = check.id.substring('check'.length);
	var textId = groupId;
	var textElement = document.getElementById(textId);

	if(check.checked) {
		textElement.style.textDecoration = "line-through";
	} else {
		textElement.style.textDecoration = "none";
	}
	// Add element value to check
	check.value = textElement.value;
}

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
		for(var element=1; element<nextElementNumber; element++) {
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

function deleteElementModification(button) {
	var textId = button.id.substring('delete_button'.length);
	var checkedId = "check" + textId;
	var checkedElement = document.getElementById(checkedId);
	checkedElement.parentNode.removeChild(checkedElement);
	button.parentNode.removeChild(button);
	var textElement = document.getElementById(textId);
	textElement.parentNode.removeChild(textElement);
	var elemLabelId = "elem_label" + textId.toString();
	var elemLabelElement = document.getElementById(elemLabelId);
	elemLabelElement.parentNode.removeChild(elemLabelElement);
	var elementBrId = "br" + textId.toString();
	var elementBr = document.getElementById(elementBrId);
	elementBr.parentNode.removeChild(elementBr);
	elementCounter--;
	if(elementCounter === 1) {
		for(var element=1; element<nextElementNumber; element++) {
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
		for(var element=1; element<nextElementNumber; element++) {
			elementId = element.toString();
			var pendingElement = document.getElementById(elementId);
			if(pendingElement != null) {
				delivery.insertAdjacentHTML("beforeend", "<button type=\"button\" id=\"delete_button" + element + "\" onclick=\"deleteElement(this);\">Borrar Elemento</button>");
			}
		}
	}
    delivery.insertAdjacentHTML("beforeend", "<span id=\"elem_label" + nextElementNumber + "\"><br>Elemento: </span><input type=\"text\" onkeyup=\"buttonDisabledOrEnabled();\" name=\"elements\" id=\"" +
		nextElementNumber + "\" /> <button type=\"button\" id=\"delete_button" + nextElementNumber +"\" onclick=\"deleteElement(this);\">Borrar Elemento</button>");
	document.getElementById('submit_button').disabled = true;
    nextElementNumber++;
    elementCounter++;
}

function addElementModification()
{
	var delivery = document.getElementById("element_div");
	if(elementCounter === 1) {
		for(var element=1; element<nextElementNumber; element++) {
			elementId = element.toString();
			var pendingElement = document.getElementById(elementId);
			if(pendingElement != null) {
				delivery.insertAdjacentHTML("beforeend", " <button type=\"button\" id=\"delete_button" + element + "\" onclick=\"deleteElementModification(this);\">Borrar Elemento</button>");
			}
		}
	}

    delivery.insertAdjacentHTML("beforeend", "<br id=\"br" + nextElementNumber + "\">");
    delivery.insertAdjacentHTML("beforeend", " <input onchange=\"checkedChange(this);\" type=\"checkbox\" id=\"check" + nextElementNumber + "\" name=\"checkbox\">");
    delivery.insertAdjacentHTML("beforeend", " <span id=\"elem_label" + nextElementNumber + "\"></span>");
    delivery.insertAdjacentHTML("beforeend", " <input type=\"text\" onkeyup=\"buttonDisabledOrEnabled();\" name=\"elements\" id=\"" +
		nextElementNumber + "\" />");
    delivery.insertAdjacentHTML("beforeend", " <button type=\"button\" id=\"delete_button" + nextElementNumber +"\" onclick=\"deleteElementModification(this);\">Borrar Elemento</button>");
	document.getElementById('submit_button').disabled = true;
    nextElementNumber++;
    elementCounter++;
}

function buttonDisabledOrEnabled() {
	for(var element=1; element<nextElementNumber; element++) {
		var elemText = document.getElementById(element.toString());
		// Check if checkbox must be updated
		var checkElementId = "check" + element.toString();
		var checkElement = document.getElementById(checkElementId);
		if(checkElement != null) {
			// Update check value as text changes
			checkElement.value = elemText.value;
		}

		if(elemText != null && elemText.value === "") {
			document.getElementById('submit_button').disabled = true;
			return false;
		}
	}
	if(document.getElementById("delivery_name").value === "") {
		document.getElementById('submit_button').disabled = true;
		return false;
	}
	document.getElementById('submit_button').disabled = false;
	return true;
}