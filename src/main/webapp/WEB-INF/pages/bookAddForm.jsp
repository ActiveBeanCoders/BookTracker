<%@ include file="include.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Book ${action} Form</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="resources/js/validbook.js"></script>

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">

</head>
<body>
	<%@ include file="leftpanel.jsp"%>

	<div id="container" style="float: center;">

		<h2 align="center">${action} Book</h2>
		<c:if test="${not empty message}">
			<div class="message green">${message}</div>
		</c:if>

		<form:form modelAttribute="book" onsubmit="return isEmpty(this)">

			<label for="title">Title: </label>
			<form:input path="title" id="titleInput"/>
			<br />

			<label for="series">Series: </label>
			<form:input path="series" id="seriesInput" />
			<br />

			<label for="pseudonym">Pseudonym: </label>
			<form:input path="pseudonym" id="pseudonymInput" />
			<br />

			<label for="year">Year: </label>
			<form:input path="year" id="yearInput" />
			<br />

			<label for="read">Read: </label>
			<form:radiobutton path="readStatus" value="true" />Yes 
		    <form:radiobutton path="readStatus" value="false" />No
			<br>
			<label for="own">Own: </label>
			<form:radiobutton path="ownStatus" value="true" />Yes 
		    <form:radiobutton path="ownStatus" value="false" />No

			<br />
			<input type="submit" value="${action}" />
		</form:form>

		<a href="book?authorId=${authorId}&name=${name}">Back to Books</a>

	</div>

</body>
</html>
