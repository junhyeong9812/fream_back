package com.kream.root.admin.controller;

import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.admin.service.AdminProductService;
import com.kream.root.entity.SellerProduct;
import com.kream.root.entity.Style;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/admin/products")
public class AdminProductController {
    @Autowired
    private AdminProductService productService;

    @GetMapping("/style")
    public List<Style> getAllStyles() {
        return productService.getAllStyles();
    }

    @GetMapping("/style/{id}")
    public Style getStyleById(@PathVariable Long id) {
        return productService.getStyleById(id);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @ModelAttribute Product product,
            @RequestParam("mainImage") MultipartFile mainImage,
            @RequestParam(value = "subImages", required = false) List<MultipartFile> subImages) throws IOException {
        Product savedProduct = productService.saveProduct(product, mainImage, subImages);
        return ResponseEntity.ok(savedProduct);
    }
//@PostMapping
//public ResponseEntity<Product> createProduct(
//        @RequestParam("nameKor") String nameKor,
//        @RequestParam("nameEng") String nameEng,
//        @RequestParam("category") String category,
//        @RequestParam("brand") String brand,
//        @RequestParam("color") String color,
//        @RequestParam("gender") String gender,
//        @RequestParam("price") String priceStr,  // 문자열로 받습니다
//        @RequestParam("info") String info,
//        @RequestParam("mainImage") MultipartFile mainImage,
//        @RequestParam(value = "subImages", required = false) List<MultipartFile> subImages) throws IOException {
//
//    int price = Integer.parseInt(priceStr);  // 문자열을 int로 변환합니다
//
//    Product product = Product.builder()
//            .nameKor(nameKor)
//            .nameEng(nameEng)
//            .category(category)
//            .brand(brand)
//            .color(color)
//            .gender(gender)
//            .price(price)  // 변환된 값을 설정합니다
//            .info(info)
//            .build();
//
//    Product savedProduct = productService.saveProduct(product, mainImage, subImages);
//    return ResponseEntity.ok(savedProduct);
//    }
    @GetMapping("/list")
    public ResponseEntity<List<Product>> getProductList() {
        List<Product> products = productService.findAllProducts();
        System.out.println("Number of products retrieved Controller: " + products.size());
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @ModelAttribute Product product,
            @RequestParam("mainImage") MultipartFile mainImage,
            @RequestParam(value = "subImages", required = false) List<MultipartFile> subImages) throws IOException {
        product.setPrid(id);
        Product updatedProduct = productService.saveProduct(product,  mainImage, subImages);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("C:/jsp_file/").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/linkedfiles/{filename:.+}")
    public ResponseEntity<Resource> downloadLinkedFile(@PathVariable String filename) {
        try {
            System.out.println("filename = " + filename);
            Path filePath = Paths.get("C:/jsp_file/linked_images/").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                System.out.println("resource = " + resource);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 로그 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/delete")
    public ResponseEntity<Void> deleteProducts(@RequestBody List<Long> ids) {
        productService.deleteProducts(ids);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/unsold")
    public ResponseEntity<List<SellerProduct>> getUnsoldProducts() {
        List<SellerProduct> products = productService.getUnsoldProducts();
        return ResponseEntity.ok(products);
    }
}
