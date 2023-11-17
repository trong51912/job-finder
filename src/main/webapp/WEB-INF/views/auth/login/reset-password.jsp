<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đặt lại mật khẩu</title>
</head>
<style>
	.error{
		color: red;
	}
</style>
<body>
	<main>
		<!-- slider Area Start-->
		<div class="slider-area ">
			<!-- Mobile Menu -->
			<div class="slider-active">
				<div class="single-slider slider-height d-flex align-items-center"
					data-background="/template/web/img/hero/h1_hero.png">
					<div class="container">
						<div class="section-top-border">
							<div class="row">
								<div class="col-lg-6 col-md-6">
									<blockquote class="generic-blockquote">
										<h3 class="mb-30">Đặt lại mật khẩu</h3>
										<c:if test="${not empty message}">
											<div class="alert alert-${alert}">${message}</div>
										</c:if>
										<form:form action="/quen-mat-khau/doi-mat-khau" modelAttribute="userDTO" method="post">
											<input type="hidden" name="id" value="${user.id}"/>
											<input type="hidden" name="email" value="${user.email}"/>
											<div class="mt-10">
												<form:input type="password" class="single-input" placeholder="Nhập mật khẩu mới"
												path="password" />
												<span><form:errors cssClass="error" path="password" /></span>
											</div><br/>
											<div class="mt-10">
												<form:input type="password" path="confirmPassword" placeholder="Nhập lại mật khẩu" 
												class="single-input"/>
												<span><form:errors cssClass="error" path="confirmPassword" /></span>
											</div>
											<br/>
											<div class="mt-10">
												<button type="submit" class="btn btn-primary">Xác nhận</button>
											</div>
										</form:form>
									</blockquote>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- slider Area End-->
	</main>
</body>
</html>
