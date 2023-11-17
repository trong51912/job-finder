<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<main>
		<!-- slider Area Start-->
		<div class="slider-area ">
			<!-- Mobile Menu -->
			<div class="slider-active">
				<div class="single-slider slider-height d-flex align-items-center"
					data-background="./template/web/img/hero/h1_hero.png">
					<div class="container">
						<div class="section-top-border">
							<div class="row">
								<div class="col-lg-6 col-md-6">
									<blockquote class="generic-blockquote">
										<h3 class="mb-30">Đăng nhập</h3>
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
										<form action="j_spring_security_check" id="formLogin" method="post">
											<div class="mt-10">
												<input type="text" id="userName" name="j_username" placeholder="UserName"
													onfocus="this.placeholder = ''"
													onblur="this.placeholder = 'Email'" required
													class="single-input">
											</div>
											<div class="mt-10">
												<input type="password" id="password" name="j_password" placeholder="Password"
													onfocus="this.placeholder = ''"
													onblur="this.placeholder = 'Password'" required
													class="single-input">
											</div>
											<div class="mt-3">
												<div class="col-lg-4 col-md-4 mt-sm-30">
													<div class="single-element-widget">
														<span><a style="color: #000" href="/quen-mat-khau">Quên mật khẩu</a></span>
													</div>
												</div>
											</div>
											<div class="mt-10">
												<button type="submit" class="btn btn-primary" >Đăng nhập</button>
											</div>
										</form>
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
