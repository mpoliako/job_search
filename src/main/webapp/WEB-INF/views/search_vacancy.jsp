<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="content_table">

	
	<c:forEach var="vacancy" items="${vacancies}">
		<div class="bottom_table"></div> <br/>
		<table class="content_table">
		<s:url var="vacancyUrl" value="/vacancy/${vacancy.id}"></s:url>
			<caption class="content_table">${vacancy.area} - <a href='${vacancyUrl}'>${vacancy.name}</a> ${vacancy.salary}</caption>

			<tbody>
				<tr>
					<td><s:message code="company" />:</td>
					<td>${vacancy.owner.login}</td>
				</tr>				
				<tr>
					<td><s:message code="description" />:</td>
					<c:set var="fullDescr" value="${vacancy.description}"/>
					<td>${fn:substring(fullDescr, 0, 255)}...</td>
				</tr>
			</tbody>
		</table>
		<br>
	</c:forEach>
	
	<c:forEach begin="1" end="${vacancyCount/resPerPage + 1}" var="i">		
		<s:url var="vacancyUrl" value="/vacancy/search/page/${i}" />	
		<td><a href="${vacancyUrl}">${i}</a></td>		
	</c:forEach>

</div>



