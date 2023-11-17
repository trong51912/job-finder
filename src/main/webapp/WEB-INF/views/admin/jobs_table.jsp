<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Jobs Manage</title>
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
            <button class="btn btn-danger" id="deleteSelected">Xóa Các Mục Đã Chọn</button>
            <div class="container-fluid py-4">
                <div class="row">
                    <div class="col-12">
                        <form method="post" action="/quan-tri/cong-viec" id="jobForm">
                            <div class="card mb-4">
                                <div class="card-header pb-0">
                                    <h6>${countJobs} Công Việc</h6>
                                </div>
                                <div class="card-body px-0 pt-0 pb-2">
                                    <div class="table-responsive p-0">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Công ty</th>
                                                    <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Nhà tuyển dụng</th>
                                                    <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Ngành</th>
                                                    <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Vị trí</th>
                                                    <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Loại hình</th>
                                                    <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Hết hạn</th>
                                                    <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Xóa</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${jobs}" var="job">
                                                    <tr>
                                                        <td>
                                                            <div class="d-flex px-2 py-1">
                                                                <div>
                                                                    <img src="../template/admin/img/small-logos/logo-webdev.svg" class="avatar avatar-sm me-3" alt="user1">
                                                                </div>
                                                                <div class="d-flex flex-column justify-content-center">
                                                                    <h6 class="mb-0 text-sm">${employerMap[job.id].companyName} </h6>
                                                                    <p class="text-xs text-secondary mb-0"></p>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <p class="text-xs font-weight-bold mb-0">${employerMap[job.id].firstName} ${employerMap[job.id].lastName}</p>
                                                            <p class="text-xs text-secondary mb-0">${employerMap[job.id].position}</p>
                                                        </td>
                                                        <td class="align-middle text-center text-sm">
                                                            <span class="badge badge-sm bg-gradient-success">${categoryMap[job.id].name}</span>
                                                        </td>
                                                        <td class="align-middle text-center">
                                                            <span class="text-secondary text-xs font-weight-bold">${job.position}</span>
                                                        </td>
                                                        <td class="align-middle text-center">
                                                            <span class="text-secondary text-xs font-weight-bold">${job.type}</span>
                                                        </td>
                                                        <td class="align-middle text-center">
                                                            <c:choose>
                                                                <c:when test="${Integer.parseInt(job.deadline) <= 3}">
                                                                    <span class="text-danger text-xs font-weight-bold">${job.deadline} Ngày</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="text-secondary text-xs font-weight-bold">${job.deadline} Ngày</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td class="align-middle text-center">
                                                            <input type="checkbox" name="jobIds" value="${job.id}">
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <script>
        document.getElementById("deleteSelected").addEventListener("click", function() {
            // Find the form by its ID
            var form = document.getElementById("jobForm");

            // Submit the form
            form.submit();
        });
    </script>
</body>

</html>
