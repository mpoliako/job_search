	$(document).ready(
			function() {

				var expNum = 0;
				var skillNum = 0;
				var courseNum = 0;
				
				while (true) {
					if ($('#experience_' + expNum).length > 0) {
						expNum++;
					} else {
						expNum--;
						break;
					}
				}

				while (true) {
					if ($('#skill_' + skillNum).length > 0) {
						skillNum++;
					} else {
						skillNum--;
						break;
					}
				}
				
				while (true) {
					if ($('#course_' + courseNum).length > 0) {
						courseNum++;
					} else {
						courseNum--;
						break;
					}
				}
				
				$("#add_course").click(						
						function() {
							var clone = $('#course_0').clone(true).attr("id",
									"course_" + (courseNum + 1));
							clone.find(".course_name").attr("name",
									"courses[" + (courseNum + 1) + "].name").attr(
									"value", "");							
							clone.find(".course_period").attr("name",
									"courses[" + (courseNum + 1) + "].period").attr(
									"value", "");
							
							clone.insertBefore('#add_course_tr');
							courseNum++;
						});
				
				$(".delete_course").click(
						function() {

							if (courseNum == 0)
								return;

							var id = $(this).parent().parent().attr("id");
							var ind = +(id.split("_")[1]);

							$(this).parent().parent().remove();
							for (var i = ind + 1; i <= courseNum; i++) {
								$('#course_' + i).find(".course_name").attr(
										"name",
										"courses[" + (i - 1)
												+ "].name");	
								$('#course_' + i).find(".course_period").attr(
										"name",
										"courses[" + (i - 1)
												+ "].period");
								$('#course_' + i).attr("id",
										"course_" + (i - 1));
							}
							courseNum--;
						});

				$("#add_skill").click(
						function() {
							var clone = $('#skill_0').clone(true).attr("id",
									"skill_" + (skillNum + 1));
							clone.find(".sk_name").attr("name",
									"skills[" + (skillNum + 1) + "].name").attr(
									"value", "");
							clone.insertBefore('#add_skill_tr');
							skillNum++;
						});
				
				$(".delete_skill").click(
						function() {

							if (skillNum == 0)
								return;

							var id = $(this).parent().parent().attr("id");
							var ind = +(id.split("_")[1]);

							$(this).parent().parent().remove();
							for (var i = ind + 1; i <= skillNum; i++) {
								$('#skill_' + i).find(".sk_name").attr(
										"name",
										"skills[" + (i - 1)
												+ "].name");								
								$('#skill_' + i).attr("id",
										"skill_" + (i - 1));
							}
							skillNum--;
						});

				$("#add_experience").click(
						function() {
							var clone = $('#experience_0').clone(true).attr(
									"id", "experience_" + (expNum + 1));
							clone.find(".descr").attr(
									"name",
									"expierence[" + (expNum + 1)
											+ "].description")
									.attr("value", "");
							clone.find(".period").attr("name",
									"expierence[" + (expNum + 1) + "].period")
									.attr("value", "");
							clone.insertBefore('#add_experience_tr');
							expNum++;
						});

				$(".delete_experience").click(
						function() {

							if (expNum == 0)
								return;

							var id = $(this).parent().parent().attr("id");
							var ind = +(id.split("_")[1]);

							$(this).parent().parent().remove();
							for (var i = ind + 1; i <= expNum; i++) {
								$('#experience_' + i).find(".descr").attr(
										"name",
										"expierence[" + (i - 1)
												+ "].description");
								$('#experience_' + i).find(".period").attr(
										"name",
										"expierence[" + (i - 1) + "].period");
								$('#experience_' + i).attr("id",
										"experience_" + (i - 1));
							}
							expNum--;
						});
			});