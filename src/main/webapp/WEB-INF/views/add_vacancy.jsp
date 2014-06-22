<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script
	src="<s:url value="/resources/js/jquery.min.js"/>"
	type="text/javascript"></script>
	
<div>
	<s:url var="addVacancyUrl" value="/vacancy/add" />
	<sf:form method="POST" modelAttribute="vacancy"
		action="${addVacancyUrl}" id="contactform" commandName="vacancy">
		<sf:hidden path="id"/>
		<fieldset>
			<table>
				<tbody id="vacancyContainer">
					<tr>
						<th><label for="vacancy_name"><s:message code="vacancy_name" />:</label></th>
						<td><sf:input path="name" size="30" id="vacancy_name" /> <sf:errors
								path="name" cssClass="error" /></td>
						<th><label for="vacancy_salary"><s:message code="salary" />:</label></th>	
						<td><sf:input path="salary" size="30" id="vacancy_salary" /> <sf:errors
								path="salary" cssClass="error" /></td>	
					</tr>
					<tr>
						<th><label for="vacancy_area"><s:message code="area" />:</label></th>
						<td><sf:input path="area" size="30" id="vacancy_area" /> <sf:errors
								path="area" cssClass="error" /></td>
					</tr>					
					<tr>
						<th><label for="user_experience"><s:message code="experience" />:</label></th>
					</tr>
					<c:forEach var="experienceLine"
						items="${vacancy.expierence}" varStatus="status">
						<tr class="experience_tr" id="experience_${status.index}">
							<td><s:message code="description" /></td>
							<td><input class="descr" type="text" class="exp_line"
								name="expierence[${status.index}].description"
								value="${experienceLine.description}" /></td>
							<td><s:message code="period" />, <s:message code="time_months" /></td>
							<td><input class="period" type="text" class="exp_line"
								name="expierence[${status.index}].period"
								value="${experienceLine.period}" /></td>
							<td><a class="delete_experience" href="#"><s:message code="delete" /></a></td>
						</tr>
					</c:forEach>
					<c:if test="${vacancy.expierence.size()  == 0}">
						<tr class="experience_tr" id="experience_0">
							<td><s:message code="description" /></td>
							<td><input class="descr" type="text" class="exp_line"
								name="expierence[0].description" value="" /></td>
							<td><s:message code="period" />, <s:message code="time_months" /></td>
							<td><input class="period" type="text" class="exp_line"
								name="expierence[0].period" value="" /></td>
							<td><a class="delete_experience" href="#"><s:message code="delete" /></a></td>
						</tr>
					</c:if>
					<tr id="add_experience_tr" class="spaceUnder">
						<td><a id="add_experience" class="simple_link" href="#"><s:message code="add_experience" /></a></td>
					</tr>
					<tr>
						<th><label for="user_skills"><s:message code="skills" />:</label></th>
					</tr>
					<c:forEach var="skillLine" items="${vacancy.skills}"
						varStatus="status">
						<tr class="skill_tr" id="skill_${status.index}">
							<td><s:message code="name" />:</td>
							<td><input class="sk_name" type="text" class="skill_line"
								name="skills[${status.index}].name" value="${skillLine.name}" /></td>								
							<td><a class="delete_skill" href="#"><s:message code="delete" /></a></td>
						</tr>
					</c:forEach>
					<c:if test="${vacancy.skills.size()  == 0}">
						<tr class="skill_tr" id="skill_0">
							<td><s:message code="name" />:</td>
							<td><input class="sk_name" type="text" class="skill_line"
								name="skills[0].name" value="" /></td>
							<td><a class="delete_skill" href="#"><s:message code="delete" /></a></td>
						</tr>
					</c:if>
					<tr id="add_skill_tr" class="spaceUnder">
						<td><a id="add_skill" class="simple_link" href="#"><s:message code="add_skills" /></a></td>
					</tr>				
					
					<tr>
						<th><label for="vacancy_description"><s:message code="description" />:</label></th>
						<td><sf:textarea path="description" size="30" id="vacancy_description" /> <sf:errors
								path="description" cssClass="error" /></td>
					</tr>				
					
				</tbody>
			</table>
			<div class="buttons">
				<input name="commit" class="button" type="submit" value="<s:message code="accept" />" />
			</div>
		</fieldset>
	</sf:form>

</div>
