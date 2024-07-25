package com.kream.root.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.entity.*;
import com.kream.root.order.DTO.OrderDTO;
import com.kream.root.order.PaymentInfo;
import com.kream.root.order.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private UserListRepository userListRepository;

    @Autowired
    private CancellationRepository cancellationRepository;

    @Autowired
    private  RefundAccountRepository refundAccountRepository;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RestTemplate restTemplate;

    @Value("4555333718886873")
    private String apiKey;

    @Value("bAJaWBq6CWDYZn0HOXCi5cvDhTZLZrkVJUwWFBmHuvxGC5ZiFUqQz5qy2sOEd9yAOCIZ2vCwWxcRQCTC")
    private String apiSecret;

    @Autowired
    private ProductRepository productRepository;
//    public Orders createOrder(PaymentInfo paymentInfo, UserListDTO user, AddressBook addressBook) {
//        // OrderItems 목록 생성
//        List<OrderItems> orderItemsList = new ArrayList<>();
//        for (int i = 0; i < paymentInfo.getProductIds().size(); i++) {
//            Long productId = paymentInfo.getProductIds().get(i);
//            int quantity = paymentInfo.getQuantities().get(i);
//
//            Product product = productRepository.findById(productId)
//                    .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
//
//            OrderItems orderItem = OrderItems.builder()
//                    .product(product)
//                    .quantity(quantity)
//                    .price(product.getPrice())  // Assume product has a getPrice() method
//                    .build();
//
//            orderItemsList.add(orderItem);
//        }
//
//        Orders order = Orders.builder()
//                .orderCode(generateOrderCode())
//                .orderDate(LocalDateTime.now())
//                .user(user)
//                .sellerProduct(new SellerProduct())  // Assign seller product based on payment info if needed
//                .orderItems(orderItemsList)
//                .applyNum(paymentInfo.getApplyNum())
//                .bankName(paymentInfo.getBankName())
//                .buyerAddr(paymentInfo.getBuyerAddr())
//                .buyerEmail(paymentInfo.getBuyerEmail())
//                .buyerName(paymentInfo.getBuyerName())
//                .buyerPostcode(paymentInfo.getBuyerPostcode())
//                .buyerTel(paymentInfo.getBuyerTel())
//                .cardName(paymentInfo.getCardName())
//                .cardNumber(paymentInfo.getCardNumber())
//                .cardQuota(paymentInfo.getCardQuota())
//                .currency(paymentInfo.getCurrency())
//                .customData(paymentInfo.getCustomData())
//                .impUid(paymentInfo.getImpUid())
//                .merchantUid(paymentInfo.getMerchantUid())
//                .productName(paymentInfo.getProductName())
//                .paidAmount(paymentInfo.getPaidAmount())
//                .paidAt(paymentInfo.getPaidAt())
//                .payMethod(paymentInfo.getPayMethod())
//                .pgProvider(paymentInfo.getPgProvider())
//                .pgTid(paymentInfo.getPgTid())
//                .pgType(paymentInfo.getPgType())
//                .receiptUrl(paymentInfo.getReceiptUrl())
//                .status(paymentInfo.getStatus())
//                .success(paymentInfo.isSuccess())
//                .build();
//
//        // Save order to obtain the orderId
//        Orders savedOrder = ordersRepository.save(order);
//
//        // Update orderItems with the saved order
//        for (OrderItems item : orderItemsList) {
//            item.setOrder(savedOrder);
//            orderItemsRepository.save(item);
//        }
//
//        // Create delivery
//        Delivery delivery = Delivery.builder()
//                .deliveryStatus("주문된 상태")
//                .deliveryAddress(addressBook.getCity() + " " + addressBook.getStreet())
//                .order(savedOrder)
//                .build();
//        deliveryRepository.save(delivery);
//
//        System.out.println("delivery = " + delivery);
//        System.out.println("savedOrder = " + savedOrder);
//        System.out.println("delivery = " + delivery);
//        return savedOrder;
//    }
//
//    public void handlePayment(PaymentInfo paymentInfo) {
//        if ("paid".equals(paymentInfo.getStatus()) && paymentInfo.isSuccess()) {
//            UserListDTO user = getUserById(paymentInfo.getUserId());
//            List<AddressBook> sortedAddressBooks = getSortedAddressBooksByUserId(user.getUlid());
//            AddressBook addressBook = sortedAddressBooks.get(0);
//
//            createOrder(paymentInfo, user, addressBook);
//        }
//    }
public Orders createOrder(PaymentInfo paymentInfo, UserListDTO user) {
    // OrderItems 목록 생성
    List<OrderItems> orderItemsList = new ArrayList<>();
    for (int i = 0; i < paymentInfo.getProductIds().size(); i++) {
        Long productId = paymentInfo.getProductIds().get(i);
        int quantity = paymentInfo.getQuantities().get(i);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        OrderItems orderItem = OrderItems.builder()
                .product(product)
                .quantity(quantity)
                .price(product.getPrice())  // Assume product has a getPrice() method
                .build();

        orderItemsList.add(orderItem);
    }

    Orders order = Orders.builder()
            .orderCode(generateOrderCode())
            .orderDate(LocalDateTime.now())
            .user(user)
            .sellerProduct(null)  // 필요하지 않은 경우 null로 설정
            .orderItems(orderItemsList)
            .applyNum(paymentInfo.getApplyNum())
            .bankName(paymentInfo.getBankName())
            .buyerAddr(paymentInfo.getBuyerAddr())
            .buyerEmail(paymentInfo.getBuyerEmail())
            .buyerName(paymentInfo.getBuyerName())
            .buyerPostcode(paymentInfo.getBuyerPostcode())
            .buyerTel(paymentInfo.getBuyerTel())
            .cardName(paymentInfo.getCardName())
            .cardNumber(paymentInfo.getCardNumber())
            .cardQuota(paymentInfo.getCardQuota())
            .currency(paymentInfo.getCurrency())
            .customData(paymentInfo.getCustomData())
            .impUid(paymentInfo.getImpUid())
            .merchantUid(paymentInfo.getMerchantUid())
            .productName(paymentInfo.getProductName())
            .paidAmount(paymentInfo.getPaidAmount())
            .paidAt(paymentInfo.getPaidAt())
            .payMethod(paymentInfo.getPayMethod())
            .pgProvider(paymentInfo.getPgProvider())
            .pgTid(paymentInfo.getPgTid())
            .pgType(paymentInfo.getPgType())
            .receiptUrl(paymentInfo.getReceiptUrl())
            .status(paymentInfo.getStatus())
            .success(paymentInfo.isSuccess())
            .build();



    // Update orderItems with the saved order
    // OrderItems에 order 설정
    for (OrderItems item : orderItemsList) {
        item.setOrder(order);
    }
    order.setOrderItems(orderItemsList);
    // Save order to obtain the orderId
    Orders savedOrder = ordersRepository.save(order);

    // Create delivery
    Delivery delivery = Delivery.builder()
            .deliveryStatus("주문된 상태")
            .deliveryAddress(paymentInfo.getBuyerAddr())  // PaymentInfo의 buyerAddr을 사용
            .order(savedOrder)
            .build();
    deliveryRepository.save(delivery);

    System.out.println("delivery = " + delivery);
    System.out.println("savedOrder = " + savedOrder);
    System.out.println("delivery = " + delivery);
    return savedOrder;
}

    public void handlePayment(PaymentInfo paymentInfo) {
        if ("paid".equals(paymentInfo.getStatus()) && paymentInfo.isSuccess()) {
            UserListDTO user = getUserById(paymentInfo.getUserId());
            createOrder(paymentInfo, user);
        }
    }

    public UserListDTO getUserById(String userId) {
        return userListRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
    }

    public List<AddressBook> getSortedAddressBooksByUserId(int userId) {
        List<AddressBook> addressBooks = addressBookRepository.findByUserUlid(userId);
        return sortAddressBooks(addressBooks);
    }

    private List<AddressBook> sortAddressBooks(List<AddressBook> addressBooks) {
        List<AddressBook> sortedList = new ArrayList<>();
        AddressBook defaultAddress = null;

        for (AddressBook address : addressBooks) {
            if (address.getIsDefault() == '1') {
                defaultAddress = address;
            } else {
                sortedList.add(address);
            }
        }

        if (defaultAddress != null) {
            sortedList.add(0, defaultAddress);
        }

        return sortedList;
    }

    private String generateOrderCode() {
        // Generate a unique order code
        return "ORD" + System.currentTimeMillis();
    }
    public String getToken() throws Exception {
        String url = "https://api.iamport.kr/users/getToken"; // API 엔드포인트 URL
        Map<String, String> body = new HashMap<>(); // 요청 바디를 담을 Map 생성
        body.put("imp_key", apiKey); // imp_key에 API 키 설정
        body.put("imp_secret", apiSecret); // imp_secret에 API 시크릿 설정
        System.out.println("body = " + body); // 요청 바디 출력 (디버깅용)

        String requestBody = objectMapper.writeValueAsString(body); // Map을 JSON 문자열로 변환
        HttpHeaders headers = new HttpHeaders(); // HTTP 헤더 객체 생성
        headers.set("Content-Type", "application/json"); // Content-Type 헤더 설정
//                headers.set("Accept", "application/json");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); // Accept 헤더 설정

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers); // HTTP 요청 엔티티 생성 (JSON 문자열과 헤더 포함)
        System.out.println("Sending request to: " + url); // 요청 URL 출력 (디버깅용)
        System.out.println("Request headers: " + headers); // 요청 헤더 출력 (디버깅용)
        System.out.println("Request body: " + requestBody); // 요청 바디 출력 (디버깅용)

        // POST 요청을 보내고 응답을 받아옴
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        System.out.println("response = " + response); // 응답 전체 출력 (디버깅용)
        System.out.println("Response status code: " + response.getStatusCode()); // 응답 상태 코드 출력 (디버깅용)
        System.out.println("Response body: " + response.getBody()); // 응답 바디 출력 (디버깅용)

        //        if (response.getStatusCodeValue() == 200) {
//            Map<String, Object> responseBody = response.getBody();
//            Map<String, String> responseMap = (Map<String, String>) responseBody.get("response");
//            System.out.println("responseMap = " + responseMap);
//            return responseMap.get("access_token");
//        } else {
//            throw new Exception("Failed to get token");
//        }
        // 응답 상태 코드가 200 OK 인지 확인
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody(); // 응답 바디를 Map으로 가져옴
            if (responseBody != null && responseBody.get("response") instanceof Map) { // 응답 바디에 "response" 키가 있는지 확인
                Map<String, String> responseMap = (Map<String, String>) responseBody.get("response"); // "response" 키의 값을 Map으로 변환
                return responseMap.get("access_token"); // access_token 반환
            } else {
                throw new Exception("Invalid response structure"); // 응답 구조가 잘못된 경우 예외 발생
            }
        } else {
            throw new Exception("Failed to get token, status code: " + response.getStatusCode()); // 상태 코드가 200이 아닌 경우 예외 발생
        }
    }

    @Transactional
    public void cancelPayment(Long orderId) throws Exception {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));

        UserListDTO user = order.getUser();
//        RefundAccount refundAccount = refundAccountRepository.findByUserUlid(user.getUlid());

        RefundAccount refundAccount = new RefundAccount(user, "110235801026", "088", "김준형", "010-2236-6908");
        if (refundAccount == null) {
            throw new IllegalArgumentException("Refund account not found for user ID: " + user.getUlid());
        }

        String token = getToken();
        System.out.println("token = " + token);
        String url = "https://api.iamport.kr/payments/cancel";

        Map<String, Object> body = new HashMap<>();
        body.put("imp_uid", order.getImpUid());
        body.put("merchant_uid", order.getMerchantUid().toString());
        body.put("amount", order.getPaidAmount());
        body.put("tax_free", 0);
        body.put("vat_amount", 0);
        body.put("checksum", order.getPaidAmount());
        body.put("reason", "테스트 결제");
        body.put("refund_holder", refundAccount.getAccountHolder());
        body.put("refund_bank", refundAccount.getBankCode());
        body.put("refund_account", refundAccount.getAccountNumber());
        body.put("refund_tel", refundAccount.getContactNumber());

        String requestBody = objectMapper.writeValueAsString(body);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");
        headers.set("Authorization", token);

//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        System.out.println("Cancelresponse = " + response);

        if (response.getStatusCodeValue() == 200) {
            Cancellation cancellation = Cancellation.builder()
                    .order(order)
                    .user(user)
                    .impUid(order.getImpUid())
                    .merchantUid(order.getMerchantUid())
                    .amount(order.getPaidAmount())
                    .reason("테스트 결제")
                    .cancelledAt(LocalDateTime.now())
                    .refundHolder(refundAccount.getAccountHolder())
                    .refundBank(refundAccount.getBankCode())
                    .refundAccount(refundAccount.getAccountNumber())
                    .refundTel(refundAccount.getContactNumber())
                    .build();

            cancellationRepository.save(cancellation);
            // 배송 상태 업데이트
            Delivery delivery = deliveryRepository.findByOrderOrderId(orderId)
                    .orElseThrow(() -> new IllegalArgumentException("Delivery not found for order ID: " + orderId));
            delivery.setDeliveryStatus("취소됨");
            deliveryRepository.save(delivery);
        } else {
            throw new Exception("Failed to cancel payment");
        }
    }
//    public List<Orders> getAllOrders() {
//        return ordersRepository.findAllWithItemsAndProducts();
//    }
//public List<Orders> getOrdersByUserId(String userId) {
//    return ordersRepository.findByUser_UserId(userId);
//}
public List<OrderDTO> getOrdersByUserId(String userId) {
    List<Orders> ordersList = ordersRepository.findByUser_UserId(userId);

    return ordersList.stream().filter(order -> deliveryRepository.findByOrder_OrderId(order.getOrderId()) != null)
            .map(order -> {
        System.out.println("order.getOrderId() = " + order.getOrderId());
        Delivery delivery = deliveryRepository.findByOrder_OrderId(order.getOrderId());
        OrderItems orderItem = order.getOrderItems().get(0); // Assuming there's at least one order item per order
        Product product = orderItem.getProduct();


        // Process image URL
        String rawImgName = product.getProductImgs().get(0).getImgName(); // Assuming there's at least one image
        String cleanedImgName = rawImgName;
        if (rawImgName.startsWith("['") && rawImgName.endsWith("']")) {
            cleanedImgName = rawImgName.substring(2, rawImgName.length() - 2);
        }
        String[] imgNameArray = cleanedImgName.split("', '");
        String mainImageUrl = "http://192.168.42.142:3001/admin/products/files/" + imgNameArray[0];

        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .orderCode(order.getOrderCode())
                .user(order.getUser().getUserId())
                .orderDate(order.getOrderDate())
                .price(orderItem.getPrice())
                .deliveryStatus(delivery.getDeliveryStatus())
                .deliveryDate(delivery.getDeliveryDate())
                // Product-related fields
                .productId(product.getPrid())
                .productNameKor(product.getNameKor())
                .productNameEng(product.getNameEng())
                .productCategory(product.getCategory())
                .productBrand(product.getBrand())
                .productColor(product.getColor())
                .productGender(product.getGender())
                .productPrice(product.getPrice())
                .productMainImageUrl(mainImageUrl)
                .build();
    }).collect(Collectors.toList());
}

}
