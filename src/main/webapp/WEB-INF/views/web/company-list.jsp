<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách công ty</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

</head>
<body>
    <main>
        <!-- Navigation -->
        <%@ include file="/common/element/header.jsp"%>

        <div class="container">
        <!-- Hero Area Start-->
		<div class="slider-area ">
			<div
				class="single-slider section-overly slider-height2 d-flex align-items-center"
				data-background='<c:url value="/template/web/img/hero/h1_hero.png"/>'>
				<div class="container">
					<div class="row">
						<div class="col-xl-12">
							<div class="hero-cap text-center">
								<h2>Danh sách công ty</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Hero Area End -->
            

            <c:if test="${empty companies}">
                <div class="alert alert-info">Không có công ty nào được tìm thấy.</div>
            </c:if>

            <div class="row">
                <c:forEach var="company" items="${companies}">
                    <div class="col-md-4">
                        <div class="card company-card mt-5">
                            <img src="/template/web/img/banner/dog.png" class="card-img-top" alt="Company Logo" >
                            <div class="card-body">
                                <h5 class="card-title" style="width: 261px; height: 40px;">${company.companyName}</h5>
                                <p class="card-text">${company.companyAddress}</p>
                                <p class="card-text">${fn:substring(company.companyIntroduce, 0, 100)}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- footer -->
        <%@ include file="/common/element/footer.jsp"%>
    </main>

    <!-- Bootstrap JS (optional) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
