package com.jobfinder.controller.admin;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobfinder.config.PaymentConfig;
import com.jobfinder.dto.EmployerDTO;
import com.jobfinder.dto.ServiceDTO;
import com.jobfinder.entity.PaymentMessage;
import com.jobfinder.service.IEmployerService;
import com.jobfinder.service.IPackageService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


@Controller

public class PaymentIntentController{
	@Autowired
	private IPackageService packageService;
	
	@Autowired
	private IEmployerService employerSevice;
	@Autowired
	private JavaMailSender mailSender;
	 @Autowired
	 private Configuration viewResolver;
	int moutValue = 0;
	Long  packageId = null ;
	
	
	@GetMapping("/nha-tuyen-dung/dang-ky")
	public String createIntent(@RequestParam("amount") int amount ,@RequestParam("id") Long id , Model model) {
		packageId = id ;
		moutValue = amount;
	    int amountpay = amount;
//	    System.out.println(amount);
	    int amountpayment = amountpay * 100;
	    model.addAttribute("amountpayment", amountpayment);
	    return "redirect:/nha-tuyen-dung/pay";
	}
	@GetMapping("/nha-tuyen-dung/thanh-cong")
	public String PaymentSuccess (Model model,HttpServletRequest request) throws MessagingException, IOException, TemplateException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Long userId = employerSevice.getUserIdByUsername(username);
		EmployerDTO user = employerSevice.findById(userId);
		String vnp_Amount = request.getParameter("vnp_Amount");
		String vnp_BankCode = request.getParameter("vnp_BankCode");
		String vnp_BankTranNo = request.getParameter("vnp_BankTranNo");
		String vnp_PayDate = request.getParameter("vnp_PayDate");
		ServiceDTO service = packageService.findById(packageId);
		if(service != null) {
			System.out.println(user.getEmail());
		}

		PaymentMessage message = new PaymentMessage(
		        user.getFirstName() + " " + user.getLastName(),
		        service.getName(),
		        service.getPrice(),
		        vnp_PayDate,
		        service.getJobPostNumber()
		    );

		String subject = "Sending Email for Employer";
		employerSevice.updatePackageService(userId, packageId);
		model.addAttribute("package", service);
		model.addAttribute("vnp_BankTranNo" , vnp_BankTranNo);
		model.addAttribute("vnp_PayDate" , vnp_PayDate);
		
		sendPaymentEmail(user.getEmail(), subject , message);
		return "admin/checkout";
	}
	@GetMapping("/nha-tuyen-dung/pay")
	public String getPay( @ModelAttribute("amountpayment") int amountpay, RedirectAttributes attributes) throws UnsupportedEncodingException{
	    String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        int amount = amountpay;
        String bankCode = "NCB";
        
        String vnp_TxnRef = PaymentConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = PaymentConfig.vnp_TmnCode;
        
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        
        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", PaymentConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = PaymentConfig.vnp_PayUrl + "?" + queryUrl;
//		System.out.println(paymentUrl);
		   // Add the paymentUrl as a flash attribute
        attributes.addFlashAttribute("paymentUrl", paymentUrl);
        // Redirect to the paymentUrl
        return "redirect:" + paymentUrl;
	}
	
	public void sendPaymentEmail(String emailTo, String subject, PaymentMessage paymentMessage) throws MessagingException, IOException, TemplateException {
	    mailSender.send(new MimeMessagePreparator() {
	        @Override
	        public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	            messageHelper.setTo(emailTo);
	            messageHelper.setSubject(subject);

	            // Use a method to generate email content from JSP template
	            String emailContent = generateEmailContent(paymentMessage);

	            messageHelper.setText(emailContent, true);
	         
	        }
	    });
	}
	private String generateEmailContent(PaymentMessage paymentMessage) throws TemplateException, IOException {
	    Template template = viewResolver.getTemplate("sendPaymentEmail.jsp");
	    Map<String, Object> model = new HashMap<>();
	    model.put("name", paymentMessage.getName());
	    model.put("packageName", paymentMessage.getPackageName());
	    model.put("paymentDate", paymentMessage.getPaymentDate());
	    model.put("amount", paymentMessage.getAmount());
	    model.put("postNumber", paymentMessage.getPostNumber());

	    return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
	}
}