<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="g-sidenav-show  bg-gray-100">

	<!-- Left SideBar -->
	<%@ include file="/common/employer/header.jsp"%>
	<main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
	<!-- Navbar --> 
	<%@ include file="/common/employer/navbar.jsp"%>

	<div class="container">
		<!-- Body form -->
		<div class="row">
			<div class="container">
				<form id="contact-form" role="form"
					action="/nha-tuyen-dung/cap-nhat-thong-tin?id=${user.id}" method="POST">
					<div class="controls">
						<div class=" text-center mt-5 ">
							<h1>Update Information</h1>
						</div>

						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label for="form_userName">UserName </label> <input
										id="form_userName" name="userName" class="form-control"
										value="${employer.userName}" required="required"
										data-error="Please, leave us a message." readonly>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_firstName">First Name *</label> <input
										id="form_firstName" type="text" name="firstName"
										class="form-control" value="${employer.firstName}"
										required="required" data-error="Firstname is required.">

								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_lastName">Last Name *</label> <input
										id="form_lastName" type="text" name="lastName"
										class="form-control" value="${employer.lastName}"
										required="required" data-error="Lastname is required.">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_email">Email *</label> <input id="form_email"
										type="email" name="email" class="form-control"
										value="${employer.email}" required="required"
										data-error="Valid email is required.">

								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_phone">Phone *</label> <input id="form_phone"
										type="text" name="phone" class="form-control"
										value="${employer.phone}" required="required"
										data-error="Valid email is required.">

								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_position">Position *</label> <input
										id="form_position" type="text" name="position"
										class="form-control" value="${employer.position}"
										required="required" data-error="Valid email is required.">

								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_companyName">Company Name *</label> <input
										id="form_companyName" type="text" name="companyName"
										class="form-control" value="${employer.companyName}"
										required="required"
										data-error="Valid companyName is required.">

								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label for="form_companyAddress">Address *</label> <input
										id="form_companyAddress" name="companyAddress"
										class="form-control" value="${employer.companyAddress}"
										required="required"
										data-error="Valid companyAddress is required.">
								</div>
							</div>
							<div class="col-md-12">
								<input type="submit"
									class="btn btn-send  pt-2 btn-block
			                            "
									value="Update" style="background-color: #E60095; color: white">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	</main>

</body>
</html>