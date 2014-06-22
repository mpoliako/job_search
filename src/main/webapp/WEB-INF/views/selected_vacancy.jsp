<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<s:url var="editUrl" value="/vacancy/edit/${vacancy.id}" />
<s:url var="deleteUrl" value="/vacancy/delete/${vacancy.id}" />
<security:authorize access="hasRole('ROLE_AUTHENTIFICATED')">
	<c:set var="userName">
		<security:authentication property="principal.username"
			htmlEscape="false" />
	</c:set>
</security:authorize>

<div class="bottom_table"></div> 
	<table class="content_table">
	<caption class="content_table">${vacancy.area}
		- <a href="<s:url value="/vacancy/${vacancy.id}"/>">${vacancy.name}</a>
		${vacancy.salary}
		<c:if test="${vacancy.owner.email eq userName }">
			<a href="${editUrl}"><s:message code="edit" /></a>
			<a href="${deleteUrl}"><s:message code="delete" /></a>
		</c:if>
	</caption>

	<tbody>
		<tr>
			<td><s:message code="email" />:</td>
			<td>${vacancy.owner.email}</td>
		</tr>
		<tr>
			<td><s:message code="contact_phone" />:</td>
			<td>${vacancy.owner.contactNumber}</td>
		</tr>
		<tr>
			<td><s:message code="required_skills" />:</td>
			<td><c:forEach var="skill" items="${vacancy.skills}">${skill.name}<br>
				</c:forEach></td>
		</tr>
		<tr>
			<td><s:message code="required_experience" />:</td>
			<td><c:forEach var="exper" items="${vacancy.expierence}">${exper.description} ${exper.period} <s:message
						code="time_months" />
					<br>
				</c:forEach></td>
		</tr>
		<tr>
			<td><s:message code="description" />:</td>
			<td>${vacancy.description}</td>
		</tr>
	</tbody>
</table>