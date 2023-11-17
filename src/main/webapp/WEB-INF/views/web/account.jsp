<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý tài khoản</title>
</head>
<body>
<style>
	.account-link {
		display: inline-block;
		text-decoration: none;
		background-color: #ff246c;
		color: #fff;
		padding: 10px 20px;
		margin-right: 10px;
		font-size: 16px;
		border-radius: 5px;
		transition: background-color 0.3s ease;
	}
	.account-link i {
	    margin-right: 5px;
	}
	
	.account-link:hover {
	    background-color: #dd1958;
	}
	.error{
		color: red;
	}
</style>
	<main> 
	<!-- Navigation --> 
	<%@ include file="/common/element/header.jsp"%> 
	
	<div class="job-post-company pt-50 pb-50">
		<div class="container">
			<a href="/" class="account-link">
		        <i class="fas fa-angle-double-left"></i>Trang chủ
			</a>
			<div class="row">
				<div class="col-md-12 text-center">
					<h3>Quản lý tài khoản</h3>
					<c:if test="${not empty message}">
						<div class="alert alert-${alert}">${message}</div>
					</c:if>
				</div>
				
			</div>
			<hr/>
			<form:form modelAttribute="userDTO" action="/quan-ly-tai-khoan?id=${user.id}" method="post">
				<input name="id" value="${user.id}" type="hidden">
				<div class="controls">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="form_userName">Tên tài khoản</label> 
								<input id="form_userName" type="text" disabled="disabled"
									class="form-control" value="${user.userName}">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="form_email">Email</label> 
								<input id="form_email" disabled="disabled"
									class="form-control" value="${user.email}">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="form_passwordOld">Mật khẩu cũ *</label> 
								<input type="password" id="form_passwordOld" name="passwordOld" class="form-control">
								<c:if test="${not empty notmatch}">
									<span class="error">${notmatch}</span>
								</c:if>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="form_passwordNew">Mật khẩu mới *</label> 
								<input type="password" id="form_passwordNew" name="password" class="form-control">
								<span><form:errors cssClass="error" path="password" /></span>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="form_confirmPassword">Nhập lại khẩu mới *</label>
								<input type="password" id="form_confirmPassword" name="confirmPassword" class="form-control">
								<span><form:errors cssClass="error" path="confirmPassword" /></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 text-center">
							<input type="submit" class="btn" value="Cập nhật tài khoản">
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	</main>
</body>
</html>