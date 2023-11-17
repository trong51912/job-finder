<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ page import="com.jobfinder.util.SecurityUtils"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo việc làm</title>
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
						<h4>Tạo việc làm</h4>
					</div>
					<div class="card-body px-0 pt-0 pb-2">
						<div class="container" style="margin-top: 20px">
							<section class="panel panel-default">
								<div class="panel-body">
									<form:form cssClass="form-horizontal"
										action="/nha-tuyen-dung/tao-viec-lam" modelAttribute="jobDTO"
										method="POST">
										<input type="hidden" name="employer_id" value="${employer.id}" />
										<!-- title // -->
										<div class="form-group">
											<label for=title class="col-sm-3 control-label"
												style="font-weight: bold">Tiêu đề</label>
											<div class="col-sm-9">
												<form:textarea rows="2" path="title" cssClass="form-control"
													placeholder="Nhập tiêu đề tin tuyển dụng" />
											</div>
											<span><form:errors cssClass="error" path="title" /></span>
										</div>

										<!-- description // -->
										<div class="form-group">
											<label for=description class="col-sm-3 control-label"
												style="font-weight: bold">Mô tả tuyển dụng</label>
											<div class="col-sm-9">
												<form:textarea rows="6" path="description"
													cssClass="form-control"
													placeholder="Nhập mô tả cho tin tuyển dụng" />
											</div>
											<span><form:errors cssClass="error" path="description" /></span>
										</div>

										<!-- requirements // -->
										<div class="form-group">
											<label for="requirements" class="col-sm-3 control-label"
												style="font-weight: bold">Yêu cầu tuyển dụng</label>
											<div class="col-sm-9">
												<form:textarea rows="6" path="requirements"
													cssClass="form-control"
													placeholder="Nhập yêu cầu tuyển dụng" />
											</div>
											<span><form:errors cssClass="error"
													path="requirements" /></span>
										</div>

										<!-- benefit // -->
										<div class="form-group">
											<label for="benefit" class="col-sm-3 control-label"
												style="font-weight: bold">Quyền lợi</label>
											<div class="col-sm-9">
												<form:textarea rows="6" path="benefit"
													cssClass="form-control"
													placeholder="Nhập quyền lợi cho ứng viên" />
											</div>
											<span><form:errors cssClass="error" path="benefit" /></span>
										</div>

										<!-- deadline // -->
										<div class="form-group">
											<label for="deadline" class="col-sm-3 control-label"
												style="font-weight: bold">Hạn ứng tuyển</label>
											<div class="col-sm-9">
												<input type="date" name="deadline"
													pattern="\d{2}-\d{2}-\d{4}" placeholder="dd-mm-yyyy"
													class="form-control" />
											</div>
											<span><form:errors cssClass="error" path="deadline" /></span>
										</div>

										<!-- location -->
										<div class="form-group">
											<label for="location" class="col-sm-3 control-label"
												style="font-weight: bold">Địa điểm làm việc</label>
											<div class="col-sm-9">
												<select style="padding: 5px" name="location" id="location">
													<option selected>Chọn tỉnh thành</option>
												</select>
											</div>
											<span><form:errors cssClass="error" path="location" /></span>
										</div>

										<!-- salary & type -->
										<div class="form-group">
											<div class="row">
												<div class="col-sm-5">
													<label for="salary" class="col-sm-2 control-label"
														style="font-weight: bold">Mức lương</label>
													<div class="col-sm-2">
														<select onclick="handleSelectClick()" id="options" style="padding: 5px" name="salary">
															<option value="9">Dưới 10 triệu</option>
															<% for (int i = 10; i <= 45; i += 5) { %>
																<option value="<%=i%>"><%=i%> - <%=i + 5%> triệu</option>
															<% } %>
															<option value="50">Trên 50 triệu</option>
															<option value="0">Thỏa thuận</option>
														</select>
													</div>
												</div>
												<div class="col-sm-6">
													<label for="type" class="col-sm-3 control-label"
														style="font-weight: bold">Phương thức làm việc</label>
													<div class="col-sm-8">
														<select style="padding: 5px" name="type">
															<c:forEach items="${types}" var="type">
																<option value="${type}">${type}</option>
															</c:forEach>
														</select>
													</div>
													<span><form:errors cssClass="error" path="type" /></span>
												</div>
											</div>
										</div>

										<!-- category -->
										<div class="form-group">
											<label for="category_id" class="col-sm-1 control-label"
												style="font-weight: bold">Danh mục:</label>
											<div class="col-sm-9">
												<select id="category_id" name="category_id"
													style="padding: 5px;">
													<c:forEach items="${categories}" var="category">
														<option value="${category.id}">${category.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- position & skill -->
										<div class="form-group">
											<div class="row">
												<div class="col-sm-5">
													<label for="position" class="col-sm-2 control-label"
														style="font-weight: bold">Vị trí</label>
													<div class="col-sm-2">
														<select id="position" name="position"
															style="padding: 5px;"></select>
													</div>
													<span><form:errors cssClass="error" path="position" /></span>
												</div>
												<div class="col-sm-6">
													<label for="skills" class="col-sm-2 control-label"
														style="font-weight: bold">Kỹ năng</label>
													<div class="col-sm-8">
														<select id="skills" style="padding: 5px;"></select>
													</div>
													<span><form:errors cssClass="error" path="skills" /></span>
												</div>
											</div>
										</div>

										<!-- skills selected // -->
										<div class="form-group">
											<div id="container-skill">
												<input type="hidden" id="default-skill" name="skills" />
											</div>
										</div>
										<hr>
										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">
												<button type="submit" class="btn btn-primary">Tạo tin</button>
											</div>
										</div>

										<!-- value -->
										<select hidden="true" id="positionVal">
											<c:forEach items="${positions}" var="position">
												<option value="${position.category_id}"
													class="${position.category_id}">${position.name}</option>
											</c:forEach>
										</select>
										<select hidden="true" id="skillVal">
											<c:forEach items="${skills}" var="skill">
												<option value="${skill.id}" id="${skill.id}"
													class="${skill.category_id}">${skill.name}</option>
											</c:forEach>
										</select>
									</form:form>
								</div>
							</section>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
		referrerpolicy="no-referrer"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
	<script>
    	const host = "https://provinces.open-api.vn/api/";
    	// Sử dụng fetch API để lấy dữ liệu từ API
    	fetch(host).then(response => response.json())
    	    .then(data => {
    	        // Xử lý dữ liệu JSON ở đây
    	        fillSelect(data);
    	    });
    	function fillSelect(data) {
    	    const selectElement = document.querySelector('#location');
    	    selectElement.innerHTML = '<option value="">Chọn tỉnh/thành phố</option>';

    	    data.forEach(city => {
    	        const option = document.createElement('option');
    	        option.value = city.name;
    	        option.text = city.name;
    	        selectElement.appendChild(option);
    	    });
    	}
	</script>
</body>
</html>