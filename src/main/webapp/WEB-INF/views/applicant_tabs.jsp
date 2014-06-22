<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<s:url var="searchUrl" value="/vacancy/search" />
<s:url var="myCvUrl" value="/cv/my" />
<s:url var="searchCvUrl" value="/cv/search" />
<s:url var="addCvUrl" value="/cv/add" />
<c:set var="currentUrl"
	value="${requestScope['javax.servlet.forward.request_uri']}"></c:set>

<ul class="semiopaquemenu">
	<c:choose>
		<c:when test="${ fn:contains(currentUrl, searchUrl)}">
			<li><a href="${searchUrl}" id="tab-1" class="selected"><s:message
						code="search_cv_tab" /></a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${searchUrl}" id="tab-1"><s:message
						code="search_cv_tab" /></a></li>
		</c:otherwise>
	</c:choose>
	<security:authorize access="hasRole('ROLE_AUTHENTIFICATED')">
		<c:choose>
			<c:when test="${ fn:contains(currentUrl, myCvUrl)}">
				<li><a href="${myCvUrl}" id="tab-2" class="selected"><s:message
							code="my_cv_tab" /></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${myCvUrl}" id="tab-2"><s:message
							code="my_cv_tab" /></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ fn:contains(currentUrl, addCvUrl)}">
				<li><a href="${addCvUrl}" id="tab-3" class="selected"><s:message
							code="add_cv" /></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${addCvUrl}" id="tab-3"><s:message
							code="add_cv" /></a></li>
			</c:otherwise>
		</c:choose>
	</security:authorize>
	<li><a href="${searchCvUrl}" id="tab-4"><s:message
				code="employer_tab" /></a></li>
</ul>
<div class="bottombar"></div>
