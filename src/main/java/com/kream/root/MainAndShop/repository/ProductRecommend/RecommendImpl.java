package com.kream.root.MainAndShop.repository.ProductRecommend;


import com.kream.root.MainAndShop.domain.QProduct;
import com.kream.root.MainAndShop.domain.QProductImg;
import com.kream.root.MainAndShop.dto.brandDTO;
import com.kream.root.MainAndShop.domain.Product;

import com.kream.root.MainAndShop.dto.OneProductDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class RecommendImpl extends QuerydslRepositorySupport implements Recommend{

    public RecommendImpl(){super(Product.class);}

    @Override
    public List<brandDTO> getBrandCnt() {

        QProduct product = QProduct.product;

        JPQLQuery<brandDTO> query = from(product).orderBy(product.brand.count().desc())
                .groupBy(product.brand)
                .select(Projections.bean(brandDTO.class,
                product.brand, product.brand.count().as("brandCnt")));

            List<brandDTO> dtoList = query.fetch();

        dtoList.forEach(dto -> log.info(dto));

        return dtoList;
    }

    @Override
    public List<OneProductDTO> getOneProduct(List<Long> pridList) {
        // 메인 추천 prid 리스트 구성 시 사용
        QProduct product = QProduct.product;
        QProductImg productImg = QProductImg.productImg;

        JPQLQuery<OneProductDTO> query = from(product).innerJoin(productImg)
                .on(product.prid.eq(productImg.prid)).select(Projections.bean(OneProductDTO.class,
                        product.prid, product.nameKor, product.color, product.gender ,product.brand, product.price, productImg.imgName));

        //or 사용할 때는 BooleanBuilder
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        pridList.forEach(prid -> booleanBuilder.or(product.prid.eq(prid)));
        query.where(booleanBuilder);

        List<OneProductDTO> dtoList = query.fetch();

        dtoList.forEach(dto -> log.info(dto));

        return dtoList;
    }
}
