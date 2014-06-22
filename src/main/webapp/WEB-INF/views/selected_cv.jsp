<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<s:url var="editUrl" value="/cv/edit/${cv.id}" />
<s:url var="deleteUrl" value="/cv/delete/${cv.id}" />
<security:authorize access="hasRole('ROLE_AUTHENTIFICATED')">
	<c:set var="userName">
		<security:authentication property="principal.username"
			htmlEscape="false" />
	</c:set>
</security:authorize>


<div class="bottom_table"></div> 
	<table class="content_table">
	<caption class="content_table">${cv.area}
		- ${cv.name} ${cv.salary} <c:if test="${cv.owner.email eq userName }"> <a href="${editUrl}"><s:message
				code="edit" /></a> <a href="${deleteUrl}"><s:message code="delete" /></a> </c:if>
	</caption>
	<tbody>
		<tr>
			<td><s:message code="email" />:</td>
			<td>${cv.owner.email}</td>
		</tr>
		<tr>
			<td><s:message code="contact_phone" />:</td>
			<td>${cv.owner.contactNumber}</td>
		</tr>
		<tr>
			<td><s:message code="experience" />:</td>
			<td><c:forEach var="exper" items="${cv.expierence}">${exper.description} ${exper.period} <s:message
						code="time_months" />
					<br>
				</c:forEach></td>
		</tr>
		<tr>
			<td><s:message code="skills" />:</td>
			<td><c:forEach var="skill" items="${cv.skills}">${skill.name}<br>
				</c:forEach></td>
		</tr>
		<tr>
			<td><s:message code="courses" />:</td>
			<td><c:forEach var="course" items="${cv.courses}">${course.name} ${course.period} <s:message
						code="time_months" />
					<br>
				</c:forEach></td>
		</tr>
		<tr>
			<td><s:message code="about_myself" />:</td>
			<td>${cv.merit}</td>
		</tr>
	</tbody>
</table>