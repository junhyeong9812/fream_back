package com.kream.root.MainAndShop.repository.ProductCategory;

import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.domain.QProduct;
import com.kream.root.MainAndShop.domain.QProductImg;
import com.kream.root.MainAndShop.dto.OneProductDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Map;

@Log4j2
public class CategoryImpl extends QuerydslRepositorySupport implements Category{

    public CategoryImpl(){ super(Product.class); }

    public List<OneProductDTO> totalList(){
        QProduct product = QProduct.product;
        QProductImg productImg = QProductImg.productImg;

        JPQLQuery<OneProductDTO> query = from(product).innerJoin(productImg)
                .on(product.prid.eq(productImg.prid)).select(Projections.bean(OneProductDTO.class,
                        product.prid, product.nameKor, product.brand, product.price,
                        product.color, product.gender, productImg.imgName));

        //카테고리 다중 필터
        //다른 카테고리 별로는 and 조건, 같은 카테고리 별로는 or 조건
//        if(categories != null){
//            BooleanBuilder booleanBuilder = new BooleanBuilder();
//            if (categories.containsKey("gender")){
//                BooleanBuilder genderBuilder = new BooleanBuilder();
//                categories.get("gender").forEach(category -> {
//                    genderBuilder.or(product.gender.eq(category));
//                });
//                booleanBuilder.and(genderBuilder);
//            }
//            if (categories.containsKey("color")){
//                BooleanBuilder colorBuilder = new BooleanBuilder();
//                categories.get("color").forEach(category -> {
//                    colorBuilder.or(product.color.eq(category));
//                });
//                booleanBuilder.and(colorBuilder);
//            }
//            if (categories.containsKey("brand")){
//                BooleanBuilder brandBuilder = new BooleanBuilder();
//                categories.get("brand").forEach(category -> {
//                    brandBuilder.or(product.brand.eq(category));
//                });
//                booleanBuilder.and(brandBuilder);
//
//            }
//
//            query.where(booleanBuilder);
//        }
        log.info(query);
        List<OneProductDTO> dtoList = query.fetch();
        log.info("리스트", dtoList);

        return dtoList;
    }
}
