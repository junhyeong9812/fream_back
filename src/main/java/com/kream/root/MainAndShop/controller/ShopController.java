package com.kream.root.MainAndShop.controller;

import com.kream.root.MainAndShop.dto.OneProductDTO;
import com.kream.root.MainAndShop.service.shopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Shop", description = "ShopPage API 입니다.")
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    shopService ss;
    // 전체 데이터
    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(summary = "Shop Data", description = "상품 데이터 전송")
    @ApiResponse(responseCode = "200")
    @GetMapping("")
    public List<OneProductDTO> totalList(){

        List<OneProductDTO> productList = ss.getList();
        return productList;
    }


}
