<%@ include file="include.jsp"%>

<html>
<head>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>

<body>
	<h2 align="center">Authors</h2>


	<div class="container"> 
		<%@ include file="leftpanel.jsp"%>

		<c:if test="${not empty message}">
			<div class="message green">${message}</div>
		</c:if>

		<div class="right">

			<form:form modelAttribute="author">
				<table>

					<tr class="titletext">
						<td><c:out value="Name" /></td>
						<td><c:out value="Rating" /></td>
					</tr>
					<c:forEach items="${authorList}" var="author">
						<tr>
							<td><c:out value="${author.firstName}" /> <c:out
									value="${author.lastName}" /></td>
							<td><c:choose>
									<c:when test="${author.rating == 0}">
										<c:out value="Great" />
									</c:when>
									<c:when test="${author.rating == 1}">
										<c:out value="Good" />
									</c:when>
									<c:when test="${author.rating == 2}">
										<c:out value="OK" />
									</c:when>
									<c:when test="${author.rating == 3}">
										<c:out value="Bad" />
									</c:when>
								</c:choose></td>
							<td width="20%"><a
								href="book?authorId=${author.authorId}&name=${author.firstName} ${author.lastName}"><button>Books</button></a>
								<a href="authorEdit?authorId=${author.authorId}"><button>Edit</button></a>
								<a href="authorDelete?authorId=${author.authorId}"><button>Delete</button></a></td>
						</tr>
						<br>
					</c:forEach>
				</table>

			</form:form>

			<br> <a href="authorAdd">Add Author</a> <br>
		</div>
	</div>
</body>
</html>
