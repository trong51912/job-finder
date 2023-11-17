<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</head>
<body class="g-sidenav-show  bg-gray-100">
	<!-- Left SideBar -->

	<main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
		<!-- Navbar -->

		<div class="container-fluid py-4">
			<div class="demo">
    <div class="container">
        <div class="row">
           <c:forEach items="${services}" var="service" varStatus="loop">
            <c:set var="cardClass" value="${loop.index == 0 ? '' : (loop.index == 1 ? 'pink' : 'blue')}" />
           <div class="col-md-4 col-sm-6">
                <div class="pricingTable ${cardClass}">
                    <h3 class="title">${service.name}</h3>
                    <div class="price-value">
                        <span class="currency"></span>
                       
                       <div class="amount" data-price="365000">
                        <c:set var="price" value="${service.price}" />
                        <fmt:formatNumber type="currency" value="${price}" currencyCode="VND" pattern="#,##0" />
                       </div>
                        <span class="month">/tháng</span>
                    </div>
                    <ul class="pricing-content">
                        <li><b>Nhà tuyển dụng</b>  </li>
                        <li>Thoải mái tiếp cân ứng viên mới với</li>
                        <li><b>${service.jobPostNumber}</b> tin / tháng</li>
                     
                    </ul>
                   <a href="${pageContext.request.contextPath}/nha-tuyen-dung/dang-ky?amount=${service.price}&id=${service.id}" class="pricingTable-signup">Thanh Toán</a>
                </div>
            </div>
           </c:forEach>
        </div>
    </div>
</div>
	</div>
	<style>
	.demo {
    background: #FAD2C8;
    padding: 30px 0
}

a {
    text-decoration: none;
}

.pricingTable {
    padding: 25px 10px 70px;
    margin: 0 15px;
    text-align: center;
    z-index: 1;
    position: relative
}

.pricingTable:after,
.pricingTable:before {
    content: "";
    position: absolute;
    left: 0
}

.pricingTable .price-value .amount {
    display: inline-block;
    font-size: 50px;
    font-weight: 700
}

.pricingTable .price-value .month {
    display: block;
    font-size: 20px;
    font-weight: 500;
    line-height: 10px;
    text-transform: uppercase
}

.pricingTable:before {
    width: 100%;
    height: 100%;
    background: #fff;
    top: 0;
    z-index: -1;
    -webkit-clip-path: polygon(100% 0, 100% 85%, 50% 100%, 0 85%, 0 0);
    clip-path: polygon(100% 0, 100% 85%, 50% 100%, 0 85%, 0 0)
}

.pricingTable:after {
    width: 70px;
    height: 30px;
    background: #1daa72;
    margin: 0 auto;
    top: 70px;
    right: 0;
    -webkit-clip-path: polygon(50% 100%, 0 0, 100% 0);
    clip-path: polygon(50% 100%, 0 0, 100% 0)
}

.pricingTable .title {
    padding: 15px 0;
    margin: 0 -25px 30px;
    background: #1daa72;
    font-size: 25px;
    font-weight: 600;
    color: #fff;
    text-transform: uppercase;
    position: relative
}

.pricingTable .title:after,
.pricingTable .title:before {
    border-top: 15px solid #51836d;
    border-bottom: 15px solid transparent;
    position: absolute;
    bottom: -30px;
    content: ""
}

.pricingTable .title:before {
    border-left: 15px solid transparent;
    left: 0
}

.pricingTable .title:after {
    border-right: 15px solid transparent;
    right: 0
}

.pricingTable .price-value {
    margin-bottom: 25px;
    color: #1daa72
}

.pricingTable .currency {
    display: inline-block;
    font-size: 30px;
    vertical-align: top;
    margin-top: 8px
}

.price-value .amount {
    display: inline-block;
    font-size: 50px;
    font-weight: 700
}

.price-value .month {
    display: block;
    font-size: 20px;
    font-weight: 500;
    line-height: 10px;
    text-transform: uppercase
}

.pricingTable .pricing-content {
    padding: 0;
    margin: 0 0 25px;
    list-style: none;
    border-top: 1px solid #8f8f8f;
    border-bottom: 1px solid #8f8f8f
}

.pricingTable .pricing-content li {
    font-size: 17px;
    color: #8f8f8f;
    line-height: 55px
}

.pricingTable .pricingTable-signup {
    display: inline-block;
    padding: 10px 30px;
    background: #1daa72;
    font-size: 18px;
    font-weight: 600;
    color: #fff;
    overflow: hidden;
    position: relative;
    transition: all .7s ease 0s
}

.pricingTable .pricingTable-signup:before {
    content: "";
    display: inline-block;
    width: 100%;
    height: 100%;
    background: linear-gradient(to bottom, rgba(255, 255, 255, 0) 0, rgba(255, 255, 255, 1) 50%, rgba(255, 255, 255, 0) 100%);
    position: absolute;
    top: 0;
    left: 0;
    opacity: 0;
    transform: translate(0, 100%);
    transition: all .6s ease-in-out 0s
}

.pricingTable .pricingTable-signup:hover:before {
    opacity: 1;
    transform: translate(0, -100%)
}

.pricingTable.blue .pricingTable-signup,
.pricingTable.blue .title,
.pricingTable.blue:after {
    background: #49b0ca
}

.pricingTable.blue .title:after,
.pricingTable.blue .title:before {
    border-top: 15px solid #407a88
}

.pricingTable.blue .price-value {
    color: #49b0ca
}

.pricingTable.pink .pricingTable-signup,
.pricingTable.pink .title,
.pricingTable.pink:after {
    background: #f06ace
}

.pricingTable.pink .price-value {
    color: #f06ace
}

.pricingTable.pink .title:after,
.pricingTable.pink .title:before {
    border-top: 15px solid #773667
}

@media only screen and (max-width:990px) {
    .pricingTable {
        margin-bottom: 30px
    }
}
	</style>
    </main>
</body>

</html>