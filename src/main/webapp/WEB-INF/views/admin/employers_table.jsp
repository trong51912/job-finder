<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employer Manage</title>
</head>
<body class="g-sidenav-show  bg-gray-100">
	<!-- Left SideBar -->
	<%@ include file="/common/admin/header.jsp" %>

	<main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
		<!-- Navbar -->
		<%@ include file="/common/admin/navbar.jsp" %>
			<div class="container-fluid py-4">
		<%-- Kiểm tra nếu có tin nhắn --%>
			<c:if test="${not empty message}">
			  <%-- Tạo phần tử div với lớp và vai trò tương ứng --%>
			  <div class="alert alert-success" role="alert">
			    <%-- Hiển thị tin nhắn --%>
			    <h6>${message}</h6>
			  </div>
			
			  <%-- Đặt thời gian tự động xóa sau 5 giây --%>
			  <script>
			    setTimeout(function() {
			      var alertDiv = document.querySelector(".alert");
			      alertDiv.parentNode.removeChild(alertDiv);
			    }, 3000);
			  </script>
			</c:if>
		<div class="container-fluid py-4">
	     <div class="row">
        <div class="col-12">
          <div class="card mb-4">
            <div class="card-header pb-0">
              <h6>${countEmployer} Employers</h6>
            </div>
            <div class="card-body px-0 pt-0 pb-2">
              <div class="table-responsive p-0">
                <table class="table align-items-center mb-0">
                  <thead>
                    <tr>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Tên</th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Công ty</th>
                      <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Dịch Vụ</th>
                      <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Địa chỉ Công ty</th>
                      <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Liên Hệ</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${employers}" var="employer">
                    <tr>
                      <td>
                        <div class="d-flex px-2 py-1">
                          <div>
                            <img src="../template/admin/img/team-2.jpg" class="avatar avatar-sm me-3" alt="user1">
                          </div>
                          <div class="d-flex flex-column justify-content-center">
                            <h6 class="mb-0 text-sm">${employer.firstName} ${employer.lastName}</h6>
                            <p class="text-xs text-secondary mb-0">${employer.email}</p>
                          </div>
                        </div>
                      </td>
                      <td>
                        <p class="text-xs font-weight-bold mb-0">${employer.companyName}</p>
                        <p class="text-xs text-secondary mb-0">${employer.position}</p>
                      </td>
                      <td class="align-middle text-center text-sm">
                        <c:choose>
					    <c:when test="${empty employer.service}">
					        <span class="badge badge-sm bg-gradient-danger">Chưa đăng ký</span>
					    </c:when>
					    <c:otherwise>
					        <span class="badge badge-sm bg-gradient-success">${employer.service}</span>
					    </c:otherwise>
					</c:choose>
                      </td>
                      <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${employer.companyAddress}</span>
                      </td>
                   <td class="align-middle text-center text-sm">
					  <a href="${pageContext.request.contextPath}/quan-tri/sendEmail/${employer.id}" class="text-secondary" data-toggle="tooltip" data-original-title="Send">
					    <i class="fas fa-paper-plane text-xs"></i>
					    <span class="text-xs">💌 Gửi Email</span>
					  </a>
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