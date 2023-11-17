<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Thành công</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            color: #78ab46;
        }
        p {
            font-size: 16px;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #78ab46;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #4c742f;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Thanh toán thành công!</h2>
        <p>Mã hóa đơn ${vnp_BankTranNo}</p>
        <p>Quý khách vui lòng <a href="https://accounts.google.com/signin" target="_blank" style="color: #0077B5; font-weight: bold; text-decoration: underline;">kiểm tra email</a> để xem lại thông tin gói sản phẩm.</p>
        <a href="${pageContext.request.contextPath}/nha-tuyen-dung/trang-chu">Quay lại trang chủ</a>
    </div>
</body>
</html>