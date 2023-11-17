<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companies Manage</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
</head>
<body class="g-sidenav-show  bg-gray-100">
	<!-- Left SideBar -->
	<%@ include file="/common/admin/header.jsp" %>
	<main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
		<!-- Navbar -->
		<%@ include file="/common/admin/navbar.jsp" %>
		<div class="container-fluid py-4">
		
<section class="bg-light">
   <div class="container mt-5">

  <div class="row">

    <div class="col-md-4">
    
      <a href="<c:url value='/quan-tri/nguoi-tim-viec'/>" class="btn btn-primary">
        <i class="fas fa-arrow-left"></i> Quay lại
        
      </a>
      
          <div class="card">
        <img src="https://bootdey.com/img/Content/avatar/avatar7.png" class="card-img-top">
        
        <div class="card-body">
          <h5 class="card-title name-card">
            ${applicant.firstName} ${applicant.lastName}  
          </h5>
          
          <span class="badge badge-primary">Ứng viên</span>
          
          <ul class="list-group list-group-flush">
            <li class="list-group-item">Email: ${applicant.email}</li>
            <li class="list-group-item">Số điện thoại: ${applicant.phone}</li>
          </ul>
          
          <div class="d-flex justify-content-between">
            <a href="#"><i class="fab fa-facebook-f"></i></a>
            <a href="#"><i class="fab fa-twitter"></i></a>  
            <a href="#"><i class="fab fa-instagram"></i></a>
          </div>
          
        </div>
        
        
      </div>
      
    </div>

<div class="col-md-8 right-box">

  <div class="experience">
    <h5>Kinh nghiệm</h5>
    
    <p>${applicant.experience}</p>
  </div>

  <h5>Kỹ năng</h5>

  <div class="progress">
    <div class="progress-bar">
    <ul>
        <c:forEach var="skill" items="${skills}">
		<c:forEach var="jobSkill" items="${applicant.skills}">
			<c:if test="${skill.id==jobSkill}">
				<li><p>${skill.name}</p></li>
			</c:if>
		</c:forEach>
	</c:forEach>
	</ul>
    </div>
  </div>
	
  <div class="education">
    <h5>Học vấn</h5>
    
    <p>${applicant.education}</p>
  </div>
  <div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title">More Actions</h5>
            <button type="button" class="close" data-dismiss="modal">
                <span>&times;</span>
            </button>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-md-6">
                    <form>
                        <div class="form-group">
                            <label for="actionSelect">Chọn tác vụ</label>
                            <select class="form-control" id="actionSelect">
                                <option value="none">Chọn tác vụ</option>
                                <option value="block">Chặn người dùng</option>
                                <option value="unblock">Mở khóa người dùng</option>
                                <option value="role">Thay đổi quyền người dùng</option>
                            </select>
                        </div>
                    </form>
                </div>
                <% String baseUrl = request.getContextPath(); %>
                <div class="col-md-6">
                    <!-- Form chặn/mở người dùng -->
                    <div id="blockForm" style="display: none;">
                        <form action="<%= baseUrl %>/quan-tri/nguoi-tim-viec/blockUser/${applicant.user_id}"  method="get" >
                            <div class="form-group">
                                <label for="reasonBlock">Lý do chặn</label>
                                <input type="text" class="form-control" id="reasonBlock">
                            </div>
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="confirmBlock">
                                <label class="form-check-label" for="confirmBlock">Tôi xác nhận chặn người dùng này</label>
                            </div>
                            <button type="submit" class="btn btn-danger">Chặn/Mở người dùng</button>
                        </form>
                    </div>
                    
                    <!-- Form mở khóa người dùng -->
					<div id="unlockForm" style="display: none;">
					    <form  action="<%= baseUrl %>/quan-tri/nguoi-tim-viec/unblockUser/${applicant.user_id}" method="get">
					        <div class="form-group">
					            <label for="reasonUnlock">Lý do mở khóa</label>
					            <input type="text" class="form-control" id="reasonUnlock">
					        </div>
					         <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="confirmBlock">
                                <label class="form-check-label" for="confirmBlock">Tôi xác nhận mở người dùng này</label>
                            </div>
					        <button type="submit" class="btn btn-success">Mở khóa người dùng</button>
					    </form>
					</div>

                    <!-- Form thay đổi quyền -->
                    <div id="roleForm" style="display: none;">
                <form action="<%=baseUrl%>/quan-tri/nguoi-tim-viec/role/${applicant.user_id}/{roleId}" method="get">
						    <div class="form-group">
						        <label for="newRole">Chọn quyền mới</label>
						        <select class="form-control" id="roleId" name="roleId">
						            <option value="1">Admin</option>
						            <option value="2">Seeker</option>
						            <option value="3">Employer</option>
						        </select>
						    </div>
						    <button type="submit" class="btn btn-primary">Cập nhật quyền</button>
						</form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    </div>
  </div>
</div>


<style>
.name-card{
  margin-left: 80px;
}
.right-box{
 margin-top:50px;
}
/* Kinh nghiệm */
.experience {
  background-color: #f7f7f7; 
  padding: 20px;
  margin-bottom: 20px;
}

/* Kỹ năng */
.progress {
  height: 90px; 
  background-color: #f7f7f7;
    margin-bottom: 20px;

}

.progress-bar {
  font-size: 15px;
  height: 30px;
      margin-left: 10px;
        margin-top: 10px;
}

/* Học vấn */ 
.education {
  background-color: #f7f7f7;
  padding: 20px; 
}
</style>

  


</section>

</div>

</main>
<script type="text/javascript">
//Lấy element dropdown 
var dropdown = document.getElementById('actionSelect');

// Xử lý sự kiện thay đổi giá trị
dropdown.addEventListener('change', function() {

  // Lấy giá trị được chọn
  var action = this.value; 
  
  // Ẩn các form đi
  document.getElementById('blockForm').style.display = 'none';
  document.getElementById('roleForm').style.display = 'none';
  document.getElementById('unlockForm').style.display = 'none';
  

  // Hiển thị form dựa vào giá trị được chọn
  if (action == 'block') {
    document.getElementById('blockForm').style.display = 'block';
  } else if (action == 'role') {
    document.getElementById('roleForm').style.display = 'block';
  } else if (action == 'unblock'){
	  document.getElementById('unlockForm').style.display = 'block';
  }

});
</script>

</body>
<style>

.card-style1 {
    box-shadow: 0px 0px 10px 0px rgb(89 75 128 / 9%);
}
.border-0 {
    border: 0!important;
}
.card {
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 1px solid rgba(0,0,0,.125);
    border-radius: 0.25rem;
}

section {
    padding: 120px 0;
    overflow: hidden;
    background: #fff;
}
.mb-2-3, .my-2-3 {
    margin-bottom: 2.3rem;
}

.section-title {
    font-weight: 600;
    letter-spacing: 2px;
    text-transform: uppercase;
    margin-bottom: 10px;
    position: relative;
    display: inline-block;
}
.text-primary {
    color: #ceaa4d !important;
}
.text-secondary {
    color: #15395A !important;
}
.font-weight-600 {
    font-weight: 600;
}
.display-26 {
    font-size: 1.3rem;
}

@media screen and (min-width: 992px){
    .p-lg-7 {
        padding: 4rem;
    }
}
@media screen and (min-width: 768px){
    .p-md-6 {
        padding: 3.5rem;
    }
}
@media screen and (min-width: 576px){
    .p-sm-2-3 {
        padding: 2.3rem;
    }
}
.p-1-9 {
    padding: 1.9rem;
}

.bg-secondary {
    background: #15395A !important;
}
@media screen and (min-width: 576px){
    .pe-sm-6, .px-sm-6 {
        padding-right: 3.5rem;
    }
}
@media screen and (min-width: 576px){
    .ps-sm-6, .px-sm-6 {
        padding-left: 3.5rem;
    }
}
.pe-1-9, .px-1-9 {
    padding-right: 1.9rem;
}
.ps-1-9, .px-1-9 {
    padding-left: 1.9rem;
}
.pb-1-9, .py-1-9 {
    padding-bottom: 1.9rem;
}
.pt-1-9, .py-1-9 {
    padding-top: 1.9rem;
}
.mb-1-9, .my-1-9 {
    margin-bottom: 1.9rem;
}
@media (min-width: 992px){
    .d-lg-inline-block {
        display: inline-block!important;
    }
}
.rounded {
    border-radius: 0.25rem!important;
}
</style>

</html>
