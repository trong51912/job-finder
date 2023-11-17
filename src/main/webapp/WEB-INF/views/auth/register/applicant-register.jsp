<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký Người Tìm Việc</title>

</head>
<body>
	<!-- slider Area Start-->
	<div class="slider-area ">
		<!-- Mobile Menu -->
		<div class="slider-active">
			<div class="single-slider slider-height d-flex align-items-center"
				data-background="./template/web/img/hero/h1_hero.png">
				<div class="container">
					<div class="section-top-border">
						<div class="row">
							<div class="col-lg-8 col-md-8">
								<form:form action="dang-ky-nguoi-tim-viec" method="POST"
									modelAttribute="applicantDTO">
									<h3 class="mb-30">Tài khoản</h3>
									<c:if test="${param.incorrectAccount != null}">
										<div class="alert alert-danger">Username or password
											incorrect</div>
									</c:if>
									<c:if test="${param.accessDenied != null}">
										<div class="alert alert-danger">you Not authorize</div>
									</c:if>
									<c:if test="${not empty message}">
										<div class="alert alert-${alert}">${message}</div>
									</c:if>
									<div class="mt-10">
										<div class="mt-10">
											<label>Tên đăng nhập (*)</label>
											<form:input type="text" class="single-input" path="userName" value="đaada"/>
											<span><form:errors cssClass="error" path="userName" /></span>
										</div>
										<div class="mt-10">
											<label>Email (*)</label>
											<form:input type="email" class="single-input" path="email"
												placeholder="Email" />
											<span><form:errors cssClass="error" path="email" /></span>
										</div>
										<div class="mt-10">
											<label>Mật khẩu (*)</label>
											<form:input type="password" class="single-input"
												path="password" />
											<span><form:errors cssClass="error" path="password" /></span>
										</div>
										<div class="mt-10">
											<label>Xác nhận mật khẩu (*)</label>
											<form:input type="password" class="single-input"
												path="confirmPassword" />
											<span><form:errors cssClass="error"
													path="confirmPassword" /></span>
										</div>
										<input type="hidden" name="status" value="1"> <input
											type="hidden" name="roleId" value="3">
									</div>
									<hr />
									<h3 class="mb-30">Thông tin ứng viên</h3>
									<div class="row">
										<div class="col-sm-6">
											<label>Họ (*)</label>
											<form:input type="text" class="single-input" path="lastName" />
											<span><form:errors cssClass="error" path="lastName" /></span>
										</div>
										<div class="col-sm-6">
											<label>Tên (*)</label>
											<form:input type="text" class="single-input" path="firstName" />
											<span><form:errors cssClass="error" path="firstName" /></span>
										</div>
									</div>
									<div class="mt-10">
										<label>Kinh nghiệm (*)</label>
										<form:textarea path="experience" rows="7"
											class="single-textarea" placeholder="Experience" />
										<span><form:errors cssClass="error" path="experience" /></span>
									</div>
									<div class="mt-10">
										<label>Học vấn (*)</label>
										<form:textarea path="education" rows="7"
											class="single-textarea" placeholder="Education" />
										<span><form:errors cssClass="error" path="education" /></span>
									</div>
									<div class="mt-10">
										<label>Kĩ năng (*)</label><br /> <select id="categorySelect"
											style="padding: 5px;">
											<c:forEach items="${categories}" var="category">
												<option value="${category.id}">${category.name}</option>
											</c:forEach>
										</select><br />
										<br /> <select id="skills" style="padding: 5px;">
										</select> <select hidden="true" id="skillVal">
											<c:forEach items="${skills}" var="skill">
												<option value="${skill.id}" id="${skill.id}"
													class="${skill.category_id}">${skill.name}</option>
											</c:forEach>
										</select><br />
										<div id="container-skill">
											<input type="hidden" id="default-skill" name="skills" />
										</div>
										<span><form:errors cssClass="error" path="skills" /></span>
									</div>
									<div class="single-element-widget mt-30">
										<div class="switch-wrap d-flex">
											<div class="primary-checkbox">
												<input type="checkbox" id="default-checkbox"> <label
													for="default-checkbox"></label>
											</div>
											<p>
												Tôi đã đọc và đồng ý với <a href="#" style="color: #fb246a">Điều
													khoản dịch vụ</a> và <a href="#" style="color: #fb246a">Chính
													sách bảo mật</a> của JobFinder
											</p>
										</div>
									</div>
									<div class="mt-10">
										<input style="color: #fff" id="submitButton" type="submit"
											class="genric-btn circle single-input-primary"
											value="Đăng ký">
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>