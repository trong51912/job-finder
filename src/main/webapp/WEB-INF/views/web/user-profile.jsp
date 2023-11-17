<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin cá nhân</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
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
</style>
	<main> 
	<!-- Navigation --> 
	<%@ include file="/common/element/header.jsp"%> 
	
	<div class="job-post-company pt-50 pb-50">
		<div class="container">
			<a href="/" class="account-link">
		        <i class="fas fa-angle-double-left"></i>Trang chủ
			</a>
			<div class="row mt-4">
				<a href="/quan-ly-tai-khoan?id=${user.id}" class="account-link">
			        <i class="fas fa-user"></i>Cài đặt tài khoản
			    </a>
			    <a href="/viec-lam/ung-tuyen-cong-viec" class="account-link">
			        <i class="fas fa-address-book"></i>Tin đã ứng tuyển
			    </a>
			</div>
			<div class="row">
				<div class="col-md-12 text-center">
					<h3>Thông tin cá nhân</h3>
					<c:if test="${not empty message}">
						<div class="alert alert-${alert}">${message}</div>
					</c:if>
				</div>
				
			</div>
			<hr/>
			<form id="contact-form" role="form" action="/thong-tin-ca-nhan?id=${user.id}" method="post">
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
								<label for="form_email">Tên tài khoản</label> 
								<input id="form_email" type="text" disabled="disabled"
									class="form-control" value="${user.email}">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="form_firstName">Họ *</label> <input
									id="form_firstName" type="text" name="firstName"
									class="form-control" value="${user.firstName}"
									required="required" data-error="Firstname is required.">

							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="form_lastName">Tên *</label> <input
									id="form_lastName" type="text" name="lastName"
									class="form-control" value="${user.lastName}"
									required="required" data-error="Lastname is required.">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="form_phone">Số điện thoại *</label> <input id="form_phone"
									type="text" name="phone" class="form-control"
									value="${user.phone}" required="required"
									data-error="phone is required.">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="form_address">Địa chỉ *</label> <input
									id="form_address" name="address" class="form-control"
									value="${user.address}" required="required"
									data-error="Please, leave us a message.">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 text-center">
							<input type="submit" class="btn" value="Cập nhật thông tin">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	</main>
</body>
</html>