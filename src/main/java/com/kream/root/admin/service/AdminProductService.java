package com.kream.root.admin.service;

import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.domain.ProductImg;
import com.kream.root.admin.repository.AdminProductRepository;
import com.kream.root.admin.repository.SellerProductRepository;
import com.kream.root.entity.SellerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminProductService {

    private final AdminProductRepository adminProductRepository;

    private final SellerProductRepository sellerProductRepository;

    private final String uploadDir = "C:/jsp_file/";

    public AdminProductService(AdminProductRepository adminProductRepository, SellerProductRepository sellerProductRepository) {
        this.adminProductRepository = adminProductRepository;
        this.sellerProductRepository = sellerProductRepository;
    }

    public Product saveProduct(Product product, MultipartFile mainImage, List<MultipartFile> subImages) throws IOException {
        product = adminProductRepository.saveProduct(product);

        if (mainImage != null && !mainImage.isEmpty()) {
            String mainImageName = saveFile(mainImage, product.getPrid() + "_1");
            ProductImg mainProductImg = ProductImg.builder()
                    .product(product)
                    .imgName(mainImageName)
                    .reg_date(LocalDateTime.now())
                    .build();
            product.getProductImgs().add(mainProductImg);
        }
        System.out.println("product.getProductImgs() = " + product.getProductImgs());
        for (int i = 0; i < subImages.size(); i++) {
            MultipartFile image = subImages.get(i);
            if (image != null && !image.isEmpty()) {
                String subImageName = saveFile(image, product.getPrid() + "_" + i+2);
                ProductImg subProductImg = ProductImg.builder()
                        .product(product)
                        .imgName(subImageName)
                        .reg_date(LocalDateTime.now())
                        .build();
                product.getProductImgs().add(subProductImg);
            }
        }

        adminProductRepository.saveProductImages(product.getProductImgs());
        return product;
    }
    public List<Product> findAllProducts() {
        return adminProductRepository.findAllProducts();
    }
//    public Product findProductById(Long id) {
//        return adminProductRepository.findProductById(id);
//    }

    public Product findProductById(Long id) {
        Product product = adminProductRepository.findProductById(id);
        if (product != null) {
            // 이미지 이름 가공 메서드 호출
            cleanImageNames(product);
        }
        return product;
    }

    public void cleanImageNames(Product product) {
        Pattern pattern = Pattern.compile("[\\[\\]']");
        for (ProductImg productImg : product.getProductImgs()) {
            if (productImg.getImgName() != null) {
                Matcher matcher = pattern.matcher(productImg.getImgName());
                String cleanedImageName = matcher.replaceAll("");
                if (cleanedImageName.contains(",")) {
                    cleanedImageName = cleanedImageName.split(",")[0].trim();
                }
                productImg.setImgName(cleanedImageName);
            }
        }
    }
    private String saveFile(MultipartFile file, String prefix) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = prefix + extension;
        Path filePath = Paths.get(uploadDir + fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
        return fileName;
    }
    public void deleteProducts(List<Long> ids) {
        for (Long id : ids) {
            adminProductRepository.deleteProductById(id);
            deleteProductImages(id);
        }
    }
    // 주어진 제품 ID로 시작하는 파일들을 업로드 디렉토리에서 삭제하는 메서드
    private void deleteProductImages(Long id) {
        File dir = new File(uploadDir); // 업로드 디렉토리 객체 생성
        if (dir.exists() && dir.isDirectory()) {
            // 디렉토리 내에서 파일 이름이 주어진 ID로 시작하는 파일들을 찾는다
            File[] files = dir.listFiles((d, name) -> name.startsWith(id.toString() + "_"));

            // 'd'는 파일의 부모 디렉토리(File 객체), 'name'은 파일 이름(String)
            // name.startsWith(id.toString() + "_")는 파일 이름이 "ID_"로 시작하는지 확인

            if (files != null) {
                // 찾은 파일 배열에 있는 각 파일을 순회하면서 삭제
                for (File file : files) {
                    try {
                        Files.delete(file.toPath()); // 파일 삭제
                    } catch (IOException e) {
                        e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
                        // 실제 애플리케이션에서는 로깅 또는 사용자에게 에러 메시지를 전달하는 등의 추가 처리 필요
                    }
                }
            }
        }
    }
    public List<SellerProduct> getUnsoldProducts() {
        return sellerProductRepository.findByIsSold('N');
    }

    public List<SellerProduct> getAllProducts() {
        return sellerProductRepository.findAll();
    }

}
