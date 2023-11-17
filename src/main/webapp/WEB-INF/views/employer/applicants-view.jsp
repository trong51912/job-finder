<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ứng viên đăng ký</title>
</head>
<body class="g-sidenav-show bg-gray-100">
	<!-- Left SideBar -->
	<%@ include file="/common/employer/header.jsp"%>
	<main
		class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
	<!-- Navbar --> <%@ include file="/common/employer/navbar.jsp"%>

	<div class="container-fluid py-4">
		<div class="row">
			<div class="col-12">
				<div class="card mb-4">
					<div class="card-header pb-0">
						<h4>Danh sách ứng viên đăng ký</h4>
					</div>
					<div class="card-body px-0 pt-0 pb-2">
						<div class="table-responsive p-0">
							<table class="table align-items-center mb-0">
								<thead>
									<tr>
										<th scope="col">First Name</th>
										<th scope="col">Last Name</th>
										<th scope="col">Email</th>
										<th scope="col">Phone</th>
										<th scope="col">Status</th>
										<!-- Các cột khác cần hiển thị -->
									</tr>
								</thead>
								<tbody>
									<c:forEach var="applicant" items="${applicants}">
								    <tr>
								        <td>${applicant.user.firstName}</td>
								        <td>${applicant.user.lastName}</td>
								        <td>${applicant.user.email}</td>
								        <td>${applicant.user.phone}</td>
								        <td>
								            <%-- Hiển thị nút với sự kiện onClick --%>
								            <button class="btn btn-outline-primary btn-sm mb-0 me-3" type="button" onclick="changeStatus(${applicant.id})">
								                ${applicant.status == 1 ? 'Đã xem' : 'Chưa xem'}
								            </button>
								        </td>
								    </tr>
								</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>
</body>
</html>
