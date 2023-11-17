<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Applicant Manage</title>
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


	  <div class="row">
        <div class="col-12">
          <div class="card mb-4">
            <div class="card-header pb-0">
              <h6>Have ${countUser} Appliciant</h6>
            </div>
            <div class="card-body px-0 pt-0 pb-2">
              <div class="table-responsive p-0">
<table class="table align-items-center justify-content-center mb-0">
  <thead>
    <tr>
      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">User Name</th>
      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Email</th>
      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Phone Number</th>
      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Address</th>
      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Status</th>
      <th class="text-uppercase text-secondary text-xxs font-weight-bolder text-center opacity-7 ps-2">Action</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${users}" var="user">
      <tr>
        <td>
          <div class="d-flex px-2">
            <div>
              <!-- Thay đổi đường dẫn ảnh và alt theo cấu trúc của bạn -->
                 <img src="https://bootdey.com/img/Content/avatar/avatar7.png" class="avatar avatar-sm rounded-circle me-2" alt="avatar">
            </div>
            <div class="my-auto">
              <h6 class="mb-0 text-sm">${user.firstName} ${user.lastName}</h6>
            </div>
          </div>
        </td>
        <td>
          <p class="text-sm font-weight-bold">${user.email}</p>
        </td>
        <td>
          <p class="text-sm font-weight-bold">${user.phone}</p>
        </td>
        <td>
          <span class="text-sm font-weight-bold">${user.address}</span>
        </td>
        <c:choose>
  <c:when test="${user.status == 0}">
    <td>
      <span class="text-sm font-weight-bold text-danger">Blocked access</span>
    </td>
  </c:when>
  
  <c:otherwise>  
    <td>
      <span class="text-sm font-weight-bold text-success">Access</span>
    </td>
  </c:otherwise>
</c:choose>
        <td class="align-middle text-center">
         <a href="<c:url value='/quan-tri/nguoi-tim-viec/${user.id}' />">Xem chi tiết</a>
        </td>
        <td class="align-middle">
          <button class="btn btn-link text-secondary mb-0">
            <i class="fa fa-ellipsis-v text-xs"></i>
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
    </main>
</body>
</html>