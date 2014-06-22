<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div>
	<h2><s:message code="register_user" /></h2>
	<s:url var="registerUrl" value="/add_user/" />
	<sf:form method="POST" id="contactform" modelAttribute="user" action="${registerUrl}" commandName="user">
		<fieldset>
			<table>
				<tr>
					<th><label for="user_email"><s:message code="email_address" />:</label></th>
					<td><sf:input path="email" size="30" id="user_email" /> <sf:errors
							path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<th><label for="user_screen_name"><s:message code="username" />:</label></th>
					<td><sf:input path="login" size="15" maxlength="15"
							id="user_screen_name" /> <sf:errors path="login"
							cssClass="error" /></td>
				</tr>
				<tr>
					<th><label for="user_password"><s:message code="password" />:</label></th>
					<td><sf:password path="password" size="30" showPassword="true"
							id="user_password" /> <sf:errors path="password"
							cssClass="error" /></td>
				</tr>
				<tr>
					<th><label for="user_contact_phone"><s:message code="contact_phone" />:</label></th>
					<td><sf:input path="contactNumber" size="30"
							id="user_contact_phone" /> <sf:errors path="contactNumber"
							cssClass="error" /></td>
				</tr>
			</table>
			
				<input name="commit" id="simple_link" type="submit" value="<s:message code="accept_create_account" />" />
			
		</fieldset>
	</sf:form>
</div>

