<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Work Search</title>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/resources/css/cvstyle.css"/>" media="screen">
<link rel="stylesheet" type="text/css"
	href="<s:url value="/resources/css/horizontal_menu.css"/>"
	media="screen">
<link rel="stylesheet" type="text/css"
	href="<s:url value="/resources/css/input_form.css"/>"
	media="screen">	
<script src="<s:url value="/resources/js/jquery.min.js"/>"
	type="text/javascript"></script>
<script src="<s:url value="/resources/js/vacancy_cv_form.js"/>"
	type="text/javascript"></script>
</head>
<body class="header">
	<div id="template">

		<table class="header">
			<tr>
				<td><security:authorize
						access="hasRole('ROLE_AUTHENTIFICATED')">
						<s:url var="logoutUrl" value="/j_spring_security_logout" />
						<table class="greet_table">
							<tr>
								<td><s:message code="greeting" /> <security:authentication
										property="principal.username" /></td>
								<td><form method="post" action="${logoutUrl}">
										<input name="commit" type="submit" class="simple_link"
											value="<s:message code="logout" />" />
									</form></td>
							</tr>
						</table>

					</security:authorize> <security:authorize access="hasRole('ROLE_ANONYMOUS')">
						<s:url var="authUrl" value="/j_spring_security_check" />
						<table>
							<tr>
								<td>
									<form method="post" action="${authUrl}">
										<table class="auth_table" cellspacing="0">
											<tr>
												<td><input id="username_or_email" class="input" name="j_username"
													type="text" value="<s:message code="email" />"
													onfocus="if(this.value=='<s:message code="email" />') this.value='';"
													onblur="if(this.value=='') this.value='<s:message code="email" />';" /></td>
												<td><input id="password" name="j_password" class="input"
													type="password" value="<s:message code="password" />"
													onfocus="if(this.value=='<s:message code="password" />') this.value='';"
													onblur="if(this.value=='') this.value='<s:message code="password" />';" /></td>
												<td><input name="commit" type="submit" class="simple_link"
													value="<s:message code="sign_in" />" /></td>
											</tr>
										</table>
									</form>
								</td>
								<s:url var="registerUrl" value="/register/" />
								<td><a href="${registerUrl}" class="simple_link"><s:message code="register" /></a>
								</td>
							</tr>
						</table>
					</security:authorize></td>
				<td><div class="locale">
						<table class=locale_table>
							<tr>
								<td><a href="?lang=en">en</a>|<a href="?lang=ru">ru</a></td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>

		<tiles:insertAttribute name="tabs" />
		<tiles:insertAttribute name="content" />
	</div>

</body>

</html>