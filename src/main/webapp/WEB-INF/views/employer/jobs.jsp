<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Việc làm</title>
</head>
<body class="g-sidenav-show  bg-gray-100">
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
						<h4>Danh sách việc làm đã đăng</h4>
					</div>
					<div class="card-body px-0 pt-0 pb-2">
						<div class="table-responsive p-0">
							<table class="table align-items-center mb-0">
								<thead>
									<tr>
										<th
											class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Ngành/Vị
											trí</th>
										<th
											class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Tên công ty
											</th>
										<th
											class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Địa
											chỉ</th>
										<th
											class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Mức
											lương</th>
										<th
											class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Ngày
											đăng</th>
										<th
											class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Trạng thái</th>
										<th class="text-secondary opacity-7"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="job" items="${jobs}">
										<c:if test="${job.employer_id==employer.id}">
											<tr style="background: #d4efee ;color: #000"; font-weight: bold;>
												<td colspan="7"><span>${job.title}</span></td>
											</tr>
											<tr style="margin-bottom: 5px">
											<td>
												<div class="d-flex px-2 py-1">
													<div>
														<img src="/template/web/img/icon/job-list1.png" class="avatar avatar-sm me-3" alt="user1">
													</div>
													<div class="d-flex flex-column justify-content-center">
														<c:forEach var="category" items="${categories}">
															<c:if test="${category.id==job.category_id}">
																<h6 class="mb-0 text-sm">${category.name}</h6>
															</c:if>
														</c:forEach>
														<p class="text-xs text-secondary mb-0">${job.position}</p>
													</div>
												</div>
											</td>
											<td>
												<p class="text-xs font-weight-bold mb-0">${employer.companyName}</p>
											</td>
											<td>
												<p class="text-xs font-weight-bold mb-0">${job.location}</p>
											</td>
											<td class="align-middle text-center">
												<c:if test="${job.salary==0}">
													<span class="text-secondary text-xs font-weight-bold">Thỏa thuận</span>
												</c:if>
												<c:if test="${job.salary>0&&job.salary<10}">
													<span class="text-secondary text-xs font-weight-bold">Dưới 10 triệu</span>
												</c:if>
												<c:if test="${job.salary>50}">
													<span class="text-secondary text-xs font-weight-bold">Trên 50 triệu</span>
												</c:if>
												<c:if test="${job.salary>=10&&job.salary<=50}">
													<span class="text-secondary text-xs font-weight-bold">${job.salary} - ${job.salary+5} triệu</span>
												</c:if>
											</td>
											<td class="align-middle text-center"><span
												class="text-secondary text-xs font-weight-bold">${job.createAt}</span>
											</td>
											
											<!-- Trong phần cột "Trạng thái" -->
											<td class="align-middle text-center text-sm">
											    <a href="/nha-tuyen-dung/showApplicants?jobId=${job.id}" 
											       class="badge badge-sm bg-gradient-success">Online</a>
											</td>
											
											<!--  <td class="align-middle text-center text-sm"><span
												class="badge badge-sm bg-gradient-success">Online</span>
											</td>
											-->
											<td class="align-middle"><a href="javascript:;"
												class="text-secondary font-weight-bold text-xs"
												data-toggle="tooltip" data-original-title="Edit user">
													Edit </a></td>
										</tr>
										</c:if>
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