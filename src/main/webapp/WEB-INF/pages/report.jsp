<%@ include file="include.jsp"%>

<html>
<head>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<script src="resources/js/validuser.js"></script>

</head>

<body>

	<%@ include file="leftpanel.jsp"%>

	<div id="container" style="float: center;">

		<h2>Reports</h2>
		<c:if test="${not empty message}">
			<div class="message green">${message}</div>
		</c:if>

		<form:form action="report" method="POST"
			onsubmit="return isEmpty(this)">
			<label>Unread Books for </label>
			<select name="user">
				<c:forEach items="${userEmail}" var="user">
					<option value="${user.key}">${user.key}</option>
				</c:forEach>
			</select>

			<input type="submit" value="submit" />
		</form:form>

	</div>
</body>

</html>
