function isEmpty() {
	if (document.getElementById("titleInput").value == "") {
		alert("Cannot add book without a title");
		return false;
	}

	if (document.getElementById("yearInput").value < "1800" ||document.getElementById("yearInput").value > "2050") {
		alert("Yeat must be valid CCYY format");
		return false;
	}

}