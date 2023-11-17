<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Công việc đã ứng tuyển</title>

<!-- Thêm Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<style>
    .job-card {
        border-radius: 20px; /* Adjust the radius as needed */
        transition: transform 0.3s ease; /* Add a smooth transition effect */
    }

    .job-card:hover {
        transform: scale(1.03); /* Scale up the card on hover for a subtle effect */
    }
</style>

	<main> 
	<!-- Navigation --> 
	<%@ include file="/common/element/header.jsp"%>
		<!-- Hero Area Start-->
		<div class="slider-area ">
			<div
				class="single-slider section-overly slider-height2 d-flex align-items-center"
				data-background='<c:url value="/template/web/img/hero/h1_hero.png"/>'>
				<div class="container">
					<div class="row">
						<div class="col-xl-12">
							<div class="hero-cap text-center">
								<h2>Công việc đã ứng tuyển</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Hero Area End -->
		
		<!-- Apply_job_start -->
		<section class="featured-job-area mt-5">
    <div class="container">
        <!-- Section Tittle -->

        <!-- JOBS -->
        <c:if test="${empty appliedJobs}">
            <div class="alert alert-info">Bạn chưa ứng tuyển công việc nào</div>
        </c:if>
        <c:if test="${not empty appliedJobs}">
            <ul class="list-group">
                <c:forEach var="job" items="${appliedJobs}">
                    <div class="row justify-content-center">
                        <div class="col-xl-10">
                            <!-- single-job-content -->
                            <div class="card mb-4 job-card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <div class="company-img">
                                                <a href="/viec-lam/chi-tiet-viec-lam/${job.id}">
                                                    <img src="/template/web/img/icon/job-list1.png" alt="" class="img-fluid">
                                                </a>
                                            </div>
                                        </div>
                                        <div class="col-md-10">
                                            <div class="job-tittle">
                                                <a href="/viec-lam/chi-tiet-viec-lam/${job.id}">
                                                    <h4 class="card-title">${job.title}</h4>
                                                </a> ${job.createBy}
                                                <ul class="list-unstyled">
                                                    <li><i class="fas fa-map-marker-alt"></i> ${job.location}</li>
                                                    <li>${job.salary} - ${job.salary} triệu</li>
                                                    <li><i class="far fa-clock"></i> ${job.createAt}</li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </ul>
        </c:if>
        <!-- JOBS -->
    </div>
</section>

		<!-- Apply_job_end --> 
		
		<!-- Footer --> 
		<%@ include file="/common/element/footer.jsp"%>
</body>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</html>
