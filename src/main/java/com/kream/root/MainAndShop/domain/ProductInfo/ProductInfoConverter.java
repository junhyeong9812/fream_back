package com.kream.root.MainAndShop.domain.ProductInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProductInfoConverter implements AttributeConverter<ProductInfo, String> {
    private ObjectMapper mapper = new ObjectMapper();

    //JSON 문자열로 변환하고 그 반대로 변환하는 JPA Attribute Converter를 정의
    @Override
    public String convertToDatabaseColumn(ProductInfo attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        }catch (JsonProcessingException e){
            throw new IllegalArgumentException("Error to converting JSON", e);
        }
    }

    @Override
    public ProductInfo convertToEntityAttribute(String dbData) {
//        try{
//            return mapper.readValue(dbData, ProductInfo.class);
//
//        }catch (JsonProcessingException e){
//            throw new IllegalArgumentException("Error to converting JSON", e);
//        }
        return null;
    }
    //저장 시: Product 엔티티를 저장할 때, ProductDetailsConverter의 convertToDatabaseColumn 메서드가 호출되어
    // ProductDetails 객체를 JSON 문자열로 변환합니다.
    //조회 시: 데이터베이스에서 Product 엔티티를 조회할 때,
    // ProductDetailsConverter의 convertToEntityAttribute 메서드가 호출되어 JSON 문자열을 ProductDetails 객체로 변환합니다.
}
