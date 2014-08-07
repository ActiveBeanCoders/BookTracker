<%@ include file="include.jsp"%>

<html>
<head>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<script src="resources/js/validutilities.js"></script>

</head>

<body>
	<%@ include file="leftpanel.jsp"%>

	<div id="container" style="float: center;">

		<h2>Utilities</h2>
		<c:if test="${not empty message}">
			<div class="message green">${message}</div>
		</c:if>
		<br>
		<form:form action="utilities" method="POST" commandName="utilities">

			<label>Move Author</label>
			<input align="left" type="text" name="authorName" />
			to
			<select name="user">
				<c:forEach items="${userEmail}" var="user">
					<option value="${user.key}">${user.key}</option>
				</c:forEach>
			</select>

			<input type="submit" value="submit" />

		</form:form>

		<form:form action="backup" method="POST">
			<label>Backup</label>
			<input type="submit" value="submit" />
		</form:form>

	</div>
</body>
</html>
