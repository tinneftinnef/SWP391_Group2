/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Config.Config;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author win
 */
@WebServlet(name = "VNPayController", urlPatterns = {"/online-pay"})
public class VNPayController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User u = (User) session.getAttribute("user");
//        if (u == null) {
//            resp.sendRedirect("home");
//        }

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        
        String xAmount = req.getParameter("amount");
        System.out.println(xAmount);
        long amount = 0;
        
        String des = req.getParameter("description");
        //long amount = 10000 * 100;
        //String bankCode = req.getParameter("deposit_method");
        String vnp_TxnRef = Config.getRandomNumber(8); //tạo một cái tham chiếu giao dịch ngẫu nhiên
        String vnp_IpAddr = Config.getIpAddress(req); //lấy địa chỉ IP của người dùng

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>(); //chứa tất cả các tham số
        vnp_Params.put("vnp_Version", vnp_Version); //Phiên bản api mà merchant kết nối.
        vnp_Params.put("vnp_Command", vnp_Command); //Mã API sử dụng, mã cho giao dịch thanh toán là: pay
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode); //Mã website của merchant trên hệ thống của VNPAY
        vnp_Params.put("vnp_Amount", String.valueOf(Math.round(Double.parseDouble(xAmount)*100000))); //Số tiền thanh toán, chuyển đổi sang vnd
        vnp_Params.put("vnp_CurrCode", "VND"); //Đơn vị tiền tệ sử dụng thanh toán
//        vnp_Params.put("vnp_BankCode", "VNPAYQR");

//        if (bankCode != null && !bankCode.isEmpty()) {
//            vnp_Params.put("vnp_BankCode", bankCode);
//        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);    //Mã tham chiếu của giao dịch tại hệ thống của merchant. 
                                                            //Mã này là duy nhất dùng để phân biệt các đơn hàng gửi sang VNPAY. 
                                                            // Không được trùng lặp trong ngày
//        session.setAttribute("code", vnp_TxnRef);
//        if(des != null){
//        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
//        }
//        else {
        vnp_Params.put("vnp_OrderInfo", des); //Thông tin mô tả nội dung thanh toán
        if (des.equals("")) {
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang: " + vnp_TxnRef); // tiếng việt k dấu và k có ký tư đặc biệt
        }
//          }
        vnp_Params.put("vnp_OrderType", orderType);//Mã danh mục hàng hóa.
        vnp_Params.put("vnp_Locale", "vn"); //Ngôn ngữ giao diện hiển thị.
        //String locate = req.getParameter("language");
//        if (locate != null && !locate.isEmpty()) {
//            vnp_Params.put("vnp_Locale", locate);
//        } else {
//            vnp_Params.put("vnp_Locale", "vn");
//        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);//URL thông báo kết quả giao dịch khi Khách hàng kết thúc thanh toán
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);//Địa chỉ IP của khách hàng thực hiện giao dịch

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        
        String vnp_CreateDate = formatter.format(cld.getTime());  // định dạng ngày
        
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);//Là thời gian giao dịch thêm vào map

        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String vnp_time = formatter2.format(cld.getTime());// định dạng ngày giờ
//        Timestamp createdTimestamp = Timestamp.valueOf(vnp_time);

        cld.add(Calendar.MINUTE, 15); // thời gian hết hạn của giao dịch 
        
        String vnp_ExpireDate = formatter.format(cld.getTime()); //định dạng ngày và giờ sau khi thêm 15 phút thcujw hiện giao dịch
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate); // thêm vào map -> xác thực xem giao dịch có được hoàn thành trong thời gian quy định hay không

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
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

        resp.sendRedirect(paymentUrl);

//        com.google.gson.JsonObject job = new JsonObject();
//        job.addProperty("code", "00");
//        job.addProperty("message", "success");
//        job.addProperty("data", paymentUrl);
//        Gson gson = new Gson();
//        resp.getWriter().write(gson.toJson(job));
//        HttpSession session = req.getSession();
//        session.setAttribute("job", job);
//        resp.sendRedirect(Config.vnp_PayUrl);
    }

    public static String formatAmountToVND(double amount) {
        Locale vietnamLocale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vietnamLocale);
        return currencyFormatter.format(amount);
    }
}
