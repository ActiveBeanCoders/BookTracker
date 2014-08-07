<%@ include file="include.jsp"%>

<html>
<head>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>

<body>
	<h2 = align="center">
		Books :
		<c:out value="${name}" />
	</h2>

	<div class="container"> 
		<%@ include file="leftpanel.jsp"%>

		<c:if test="${not empty message}">
			<div class="message green">${message}</div>
		</c:if>

		<div class="right">
			<!-- 			style="float: right; width: 85%; vertical-align: top; display: inline-block; margin:9px 0 0 9px;"> -->
			<form>
				<table>
					<tr class="titletext">
						<td><c:out value="Title" /></td>
						<td><c:out value="Series" /></td>
						<td><c:out value="Pseudonym" /></td>
						<td><c:out value="Year" /></td>
						<td><c:out value="Read" /></td>
						<td><c:out value="Own" /></td>
					</tr>
					<c:forEach items="${bookList}" var="book">

						<tr>
							<td><c:out value="${book.title}" /></td>
							<td><c:out value="${book.series}" /></td>
							<td><c:out value="${book.pseudonym}" /></td>
							<td><c:out value="${book.year}" /></td>
							<td><c:choose>
									<c:when test="${book.readStatus == false}">
										<img class="status" src="resources/images/no.png" />
									</c:when>
									<c:otherwise>
										<img class="status" src="resources/images/yes.png" />
									</c:otherwise>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${book.ownStatus == false}">
										<img class="status" src="resources/images/no.png" />
									</c:when>
									<c:otherwise>
										<img class="status" src="resources/images/yes.png" />
									</c:otherwise>
								</c:choose></td>
							<td width="15%"><a
								href="bookEdit?bookId=${book.bookId}&authorId=${authorId}&name=${name}"><button>Edit</button></a>
								<a href="bookDelete?bookId=${book.bookId}&name=${name}"><button>Delete</button></a></td>
						</tr>

						<br>
					</c:forEach>
				</table>

			</form>
			<br> <a href="bookAdd?authorId=${authorId}&name=${name}">Add
				Book</a>
		</div>
	</div>
</body>
</html>
