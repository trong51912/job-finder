<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
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
										<h3 class="mb-30">Quên mật khẩu</h3>
										<c:if test="${not empty message}">
											<div class="alert alert-${alert}">${message}</div>
										</c:if>
										<form action="/quen-mat-khau" id="formLogin" method="post">
											<div class="mt-10">
												<input type="text" id="email" name="email" placeholder="Nhập email"
													onfocus="this.placeholder = ''"
													onblur="this.placeholder = 'Email'" required
													class="single-input">
											</div>
											<br/>
											<div class="mt-10">
												<button type="submit" class="btn btn-primary" >Xác nhận</button>
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
