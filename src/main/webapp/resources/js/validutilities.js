function isEmpty() {
	if (document.getElementById("authorName").value == "") {
		alert("Author cannot be empty");
		return false;
	}

	if (document.getElementById("user").value == "") {
		alert("User cannot be empty");
		return false;
	}

}