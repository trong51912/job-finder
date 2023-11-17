<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
<style>
	.row-content {
	    display: flex;
	    align-items: center;
	}
	
	.image-container {
	    flex: 1;
	    width: 2rem;
	    text-align: center; /* Đặt ảnh ở giữa dòng */
	}
	
	.text-container {
	    flex: 2;
	    padding: 10px; /* Tạo khoảng cách giữa h5 và p */
	    justify-content: left;
	    text-align: left; /* Đặt ảnh ở giữa dòng */
	}
	.feature-padding{
		padding: 2rem 0;
	}
	.our-services{
		padding: 2rem 0;
	}
</style>
	<!-- Navigation -->
	<%@ include file="/common/web/home-header.jsp" %>
	<main>
		<!-- slider Area Start-->
		<div class="slider-area ">
			<!-- Mobile Menu -->
			<div class="slider-active">
				<div class="single-slider slider-height d-flex align-items-center"
					data-background="./template/web/img/hero/h1_hero.png">
					<div class="container">
						<div class="row">
							<div class="col-xl-6 col-lg-9 col-md-10">
								<div class="hero__caption">
									<h1>Tìm công việc phù hợp nhất với bạn</h1>
								</div>
							</div>
						</div>
						<!-- Search Box -->
						<div class="row">
							<div class="col-xl-8">
								<!-- form -->
								<form action="/tim-kiem" class="search-box" method="GET">
									<div class="input-form">
										<input type="text" name="keyword" placeholder="Nhập từ khóa">
										<input type="hidden" value="1" class="page" name="page"/>
										<input type="hidden" value="10" class="limit" name="limit"/>
									</div>
									<button type="submit" class="btn btn-primary search-form"> Tìm kiếm</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- slider Area End-->
		
		<!-- Featured_job_start -->
		<section class="featured-job-area feature-padding">
			<div class="container">
				<!-- Section Tittle -->
				<div class="row">
					<div class="col-lg-12">
						<div class="section-tittle text-center">
							<span>Tin tuyển dụng</span>
							<h2>Tin nổi bật</h2>
						</div>
					</div>
				</div>
				<c:forEach var="job" items="${jobs}">
					<div class="row-content justify-content-center">
						<div class="col-xl-11">
							<!-- single-job-content -->
								<div class="single-job-items mb-30">
									<div class="job-items">
										<div class="company-img">
											<a href="job_details.html"><img src="./template/web/img/icon/job-list1.png" alt=""></a>
										</div>
										<div class="job-tittle">
											<a href="/viec-lam/chi-tiet-viec-lam/${job.id}">
												<h5>${job.title}</h5>
											</a>
											<ul>
												<c:forEach var="employer" items="${employers}">
													<c:if test="${job.employer_id==employer.id}">
														<p>${employer.companyName}</p> 
													</c:if>
												</c:forEach>
												<li><i class="fas fa-map-marker-alt"></i>${job.type}</li>
												<li><i class="fas fa-map-marker-alt"></i>${job.location}</li>
												<c:if test="${job.salary==0}">
													<li>Thỏa thuận</li>
												</c:if>
												<c:if test="${job.salary>0&&job.salary<10}">
													<li>Dưới 10 triệu</li>
												</c:if>
												<c:if test="${job.salary>50}">
													<li>Trên 50 triệu</li>
												</c:if>
												<c:if test="${job.salary>=10&&job.salary<=50}">
													<li>${job.salary} - ${job.salary+5} triệu</li>
												</c:if>
												<li>
												<i class="fa fa-regular fa-clock"></i>${job.createAt}</li>
											</ul>
										</div>
									</div>
									<div class="items-link f-right">
										<a href="/viec-lam/chi-tiet-viec-lam/${job.id}">Xem chi tiết</a>
									</div>
								</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
		<!-- Featured_job_end -->
		<hr/>
		<!-- Our Services Start -->
		<div class="our-services section-pad-t30">
			<div class="container">
				<!-- Section Tittle -->
				<div class="row">
					<div class="col-lg-12">
						<div class="section-tittle text-center">
							<span>Danh mục</span>
							<h2>Danh mục hàng đầu</h2>
						</div>
					</div>
				</div>
				<a href="">
					<div class="row d-flex justify-contnet-center">
						<c:forEach var="category" items="${categories}">
							<div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
								<div class="single-services text-center mb-30">
									<div class="services-ion">
										<span class="flaticon-tour"></span>
									</div>
									<div class="services-cap">
										<a href="/viec-lam/${category.id}"><h5>${category.name}</h5></a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</a>
				<!-- More Btn -->
			</div>
		</div>
		<!-- Our Services End -->
		<hr/>
		<!-- Online CV Area Start -->
		<div class="online-cv cv-bg section-overly pt-90 pb-120"
			data-background="/template/web/img/gallery/cv_bg.jpg">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-xl-10">
						<div class="cv-caption text-center">
							<p class="pera1">CÁC DU LỊCH NỔI BẬT Các gói</p>
							<p class="pera2">Tạo sự khác biệt với sơ yếu lý lịch của bạn!</p>
							<a href="#" class="border-btn2 border-btn4">Tải CV ngay</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Online CV Area End-->
		
		<!-- How  Apply Process Start-->
		<div class="apply-process-area apply-bg pt-150 pb-150"
			data-background="/template/web/img/gallery/how-applybg.png">
			<div class="container">
				<!-- Section Tittle -->
				<div class="row">
					<div class="col-lg-12">
						<div class="section-tittle white-text text-center">
							<span>QUY TRÌNH ỨNG TUYỂN</span>
							<h2>Cách tìm công việc ưng ý</h2>
						</div>
					</div>
				</div>
				<!-- Apply Process Caption -->
				<div class="row">
					<div class="col-lg-4 col-md-6">
						<div class="single-process text-center mb-30">
							<div class="process-ion">
								<span class="flaticon-search"></span>
							</div>
							<div class="process-cap">
								<h5>1. Tìm kiếm tin tuyển dụng</h5>
								<p>Tìm kiếm cơ hội việc làm phù hợp với mục tiêu và kỹ năng của bạn là bước đầu tiên trong việc tìm công việc ưng ý.</p>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-md-6">
						<div class="single-process text-center mb-30">
							<div class="process-ion">
								<span class="flaticon-curriculum-vitae"></span>
							</div>
							<div class="process-cap">
								<h5>2. Ứng tuyển vào vị trí</h5>
								<p>Sau khi tìm thấy vị trí phù hợp, hãy ứng tuyển và chuẩn bị hồ sơ xin việc một cách cẩn thận để nổi bật trước nhà tuyển dụng.</p>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-md-6">
						<div class="single-process text-center mb-30">
							<div class="process-ion">
								<span class="flaticon-tour"></span>
							</div>
							<div class="process-cap">
								<h5>3. Nhận việc</h5>
								<p>Khi nhận cơ hội làm việc, hãy tận dụng nó và bắt đầu hành trình sự nghiệp của bạn với nhiệm vụ và đam mê.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- How  Apply Process End-->
	</main>
	<!-- Footer -->
	<%@ include file="/common/web/home-footer.jsp" %>
</body>
</html>