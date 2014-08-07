<%@ include file="include.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Author ${action} Form</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="resources/js/validauthor.js"></script>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body>

	<%@ include file="leftpanel.jsp"%>

	<div id="container" style="float: center;">

		<h2>${action} Author</h2>
		<c:if test="${not empty message}">
			<div class="message green">${message}</div>
		</c:if>

		<form:form modelAttribute="author" onsubmit="return isEmpty(this)">

			<label for="firstName">First Name: </label>
			<form:input path="firstName" id="firstNameInput" />
			<br />

			<label for="lastName">Last Name: </label>
			<form:input path="lastName" id="lastNameInput" />
			<br />

			<label for="rating">Rating: </label>
			<form:radiobutton path="rating" id="ratingInput" value="0" />Great 
			<form:radiobutton path="rating" id="ratingInput" value="1" />Good
			<form:radiobutton path="rating" id="ratingInput" value="2" />OK <!-- default to OK -->
			<form:radiobutton path="rating" id="ratingInput" value="3" />Bad



			<form:hidden path="authorId" value="${authorId}" />

			<br />
			<input type="submit" value="${action}" />
		</form:form>

	</div>

</body>
</html>
