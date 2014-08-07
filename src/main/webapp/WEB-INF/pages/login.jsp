<%@ include file="include.jsp"%>
<html>
<head>
<style>
body {
	background-color: #b0c4de;
	font: helvetica;
}
#images {
	text-align: center;
}
.logo {
	display: inline-block;
	margin-left: -2;
	margin-right: -2;
	height: 100px;
	width: 100px;
}
</style>
<title>Login Page</title>

</head>
<body onload='document.f.j_username.focus();'>
	<h3>Login</h3>
 
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
 
	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
 
		<table id="noborder">
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset" />
				</td>
			</tr>
		</table>
 
	</form>
</body>
</html>