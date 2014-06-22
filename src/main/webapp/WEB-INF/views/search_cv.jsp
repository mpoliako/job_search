<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="content_table">


	<c:forEach var="cv" items="${allCv}">
		<s:url var="cvUrl" value="/cv/${cv.id}" />		
		<div class="bottom_table"></div> <br/>
		<table class="content_table">
			<caption class="content_table">${cv.area}
				- <a href="${cvUrl}">${cv.name}</a> ${cv.salary}
			</caption>
			<tbody>
				<tr>
					<td><s:message code="skills" />:</td>
					<td><c:forEach var="skill" items="${cv.skills}">${skill.name},  
						</c:forEach></td>
				</tr>
				<tr>
					<td><s:message code="experience" />:</td>
					<td><c:forEach var="exper" items="${cv.expierence}">${exper.description} ${exper.period} <s:message
								code="time_months" />
							<br>
						</c:forEach></td>
				</tr>
			</tbody>
		</table>
	</c:forEach>

	<c:forEach begin="1" end="${cvCount/resPerPage + 1}" var="i">		
		<s:url var="cvUrl" value="/cv/search/page/${i}" />	
		<td><a href="${cvUrl}">${i}</a></td>		
	</c:forEach>

</div>


