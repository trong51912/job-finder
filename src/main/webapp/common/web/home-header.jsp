<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.jobfinder.util.SecurityUtils"%>
<%@include file="/common/taglib.jsp"%>

<!-- Preloader Start -->
<div id="preloader-active">
	<div class="preloader d-flex align-items-center justify-content-center">
		<div class="preloader-inner position-relative">
			<div class="preloader-circle"></div>
			<div class="preloader-img pere-text">
				<img src="./template/web/img/logo/logo.png" alt="">
			</div>
		</div>
	</div>
</div>
<!-- Preloader Start -->
<header>
	<!-- Header Start -->
	<div class="header-area header-bg header-transparrent">
		<div class="headder-top header-sticky">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-lg-3 col-md-2">
						<!-- Logo -->
						<div class="logo">
							<a href="/"><img src="./template/web/img/logo/logo.png"
								alt=""></a>
						</div>
					</div>
					<div class="col-lg-9 col-md-9">
						<div class="menu-wrapper">
							<!-- Main-menu -->
							<div class="main-menu">
								<nav class="d-none d-lg-block">
									<ul id="navigation">
										<li><a href="/">Trang chủ</a></li>
										<li><a href="/viec-lam/danh-sach?page=1&limit=10">Tìm việc</a></li>
										<li><a href="#">Công ty</a>
											<ul class="submenu">
												<li><a href="/cong-ty">Danh sách công ty</a></li>
												<li><a href="#">Top công ty</a></li>
											</ul></li>
										<li><a href="#">Liên hệ</a></li>
										<li><a href="#">Trợ giúp</a></li>
									</ul>
								</nav>
							</div>
							<!-- Header-btn -->
							<div class="header-btn d-none f-right d-lg-block">
								<security:authorize access="isAnonymous()">
									<a href="dang-ky" class="btn head-btn1">Đăng ký</a>
									<a href="dang-nhap" class="btn head-btn2">Đăng nhập</a>
								</security:authorize>
								<security:authorize access="isAuthenticated()">
									<c:forEach items="${users}" var="user">
										<c:if test="${user.userName==SecurityUtils.getPrincipal().getUsername()}">
											<c:if test="${role==3}">
												<a style="color: #000" href="<c:url value='/thong-tin-ca-nhan?id=${user.id}'/>">
													<span><img alt="" src="./template/web/img/user.png"></span> <%=SecurityUtils.getPrincipal().getUsername()%>
												</a>
											</c:if>
											<c:if test="${role==2}">
												<a style="color: #000" href="<c:url value='nha-tuyen-dung/thong-tin-ca-nhan?id=${user.id}'/>">
													<span><img alt="" src="./template/web/img/user.png"></span> <%=SecurityUtils.getPrincipal().getUsername()%>
												</a>
											</c:if>
											<c:if test="${role==1}">
												<a style="color: #000" href="<c:url value='quan-tri/edit/${user.userName}'/>">
													<span><img alt="" src="./template/web/img/user.png"></span> <%=SecurityUtils.getPrincipal().getUsername()%>
												</a>
											</c:if>
										</c:if>
									</c:forEach>
									<a href="thoat" class="btn head-btn2">Thoát</a>
								</security:authorize>
							</div>
						</div>
					</div>
					<!-- Mobile Menu -->
					<div class="col-12">
						<div class="mobile_menu d-block d-lg-none"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Header End -->
</header>