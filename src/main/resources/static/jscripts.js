var elementNumber = 2;
function addElement()
{
	var delivery = document.getElementById("element_div");
    delivery.insertAdjacentHTML("beforeend", "Elemento:<input type=\"text\" name=\"elements\" id=\"" + elementNumber + "\" /><br>");
    elementNumber++;
}