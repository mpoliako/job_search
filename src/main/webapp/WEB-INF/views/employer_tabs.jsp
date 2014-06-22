<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<s:url var="searchUrl" value="/cv/search" />
<s:url var="myVacancyUrl" value="/vacancy/my" />
<s:url var="searchVacancyUrl" value="/vacancy/search" />
<s:url var="addVacancy" value="/vacancy/add" />
<c:set var="currentUrl"
	value="${requestScope['javax.servlet.forward.request_uri']}"></c:set>

<ul class="semiopaquemenu">

	<c:choose>
		<c:when test="${ fn:contains(currentUrl, searchUrl)}">
			<li><a href="${searchUrl}" class="selected"><s:message code="search_cv" /></a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${searchUrl}" ><s:message
						code="search_cv" /></a></li>
		</c:otherwise>
	</c:choose>

	<security:authorize access="hasRole('ROLE_AUTHENTIFICATED')">
		<c:choose>
			<c:when test="${ fn:contains(currentUrl, myVacancyUrl)}">
				<li><a href="${myVacancyUrl}" class="selected"><s:message
					code="my_vacancies" /></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${myVacancyUrl}" ><s:message
					code="my_vacancies" /></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ fn:contains(currentUrl, addVacancy)}">
				<li><a href="${addVacancy}" class="selected"><s:message code="add_vacancy" /></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${addVacancy}"><s:message code="add_vacancy" /></a></li>
			</c:otherwise>
		</c:choose>
	</security:authorize>
	
	<li><a href="${searchVacancyUrl}"><s:message
				code="applicant_tab" /></a></li>
</ul>

<div class="bottombar"></div>