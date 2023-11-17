<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bill Thanh Toán Dịch Vụ</title>
    <style type="text/css">
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
        }
        .header {
            text-align: center;
            background-color: #78ab46;
            padding: 20px 0;
        }
        .header img {
            display: block;
            margin: 0 auto;
        }
        .content {
            padding: 20px;
        }
        .content p {
            font-size: 16px;
        }
        .content b {
            font-weight: bold;
        }
        .footer {
            background-color: #777777;
            color: #ffffff;
            text-align: center;
            padding: 20px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRijocVopJ3v9Aa56TrSybY-W5XUekR7mdzXitkKCyX9E33AywBnckOdDdYysbXwNjrqYQ&usqp=CAU" alt="Hóa Đơn Thanh Toán">
        </div>
        <div class="content">
            <p>Xin chào ${name},</p>
            <p>Đây là hóa đơn thanh toán cho dịch vụ sử dụng của bạn dành cho gói  ${packageName}.</p>
            <p>Mã hóa đơn: ${paymentDate}</p>
            <p>Tổng tiền thanh toán: <b>${amount} VND</b></p>
            <p>Số tin đăng : ${postNumber} tin / tháng:</p>
            <p>...</p>
            <p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!</p>
        </div>
        <div class="footer">
            <p>Trân trọng,</p>
            <p>Công ty JobFinder</p>
        </div>
    </div>
</body>
</html>
