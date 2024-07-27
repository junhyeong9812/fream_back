package com.kream.root.Detail.repository.productDetail;

import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.domain.QProduct;
import com.kream.root.MainAndShop.domain.QProductImg;

import com.kream.root.Detail.dto.OneProductDTO;
import com.kream.root.entity.QOrderItems;
import com.kream.root.entity.QOrders;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DetailImpl extends QuerydslRepositorySupport implements Detail {

        public DetailImpl(){
            super(Product.class);
        }

        @Override
        public List<OneProductDTO> getProductDetail(Long prId) {
            QProduct product = QProduct.product;
            QProductImg productImg = QProductImg.productImg;

            JPQLQuery<OneProductDTO> query = from(product)
                    .innerJoin(productImg).on(product.prid.eq(productImg.prid))
                    .select(Projections.bean(OneProductDTO.class,
                            product.prid, product.nameKor, product.nameEng,
                            product.category, product.brand, product.color, product.price,
                            product.info, product.gender, productImg.imgName, productImg.linkedImgName
                    ));
                    query.where(product.prid.eq(prId));

            List<OneProductDTO> dtoList = query.fetch();

            log.info(dtoList);

            return dtoList;
        }

    @Override
    public List<OneProductDTO> getProductsByBrand(Long prId) {
            QProduct product = QProduct.product;
            QProductImg productImg = QProductImg.productImg;

            //주어진 prId로 상품을 조회하여 브랜드 추출
            JPQLQuery<String> brandQuery = from(product)
                    .select(product.brand)
                    .where(product.prid.eq(prId))
                    .limit(1);

            String brand = brandQuery.fetchOne();

            if (brand == null) {
                return new ArrayList<>(); //브랜드를 찾을 수 없는 경우 빈 리스트 반환
            }

            //해당 브랜드의 모든 상품 데이터 (해당 prId 제외)
            JPQLQuery<OneProductDTO> query = from(product)
                    .innerJoin(productImg).on(product.prid.eq(productImg.product.prid))
                    .select(Projections.bean(OneProductDTO.class,
                            product.prid, product.nameKor, product.nameEng,
                            product.category, product.brand, product.color, product.price,
                            product.info, product.gender, productImg.imgName
                    ))
                    .where(product.brand.eq(brand)
                            .and(product.prid.ne(prId)))
                    .orderBy(product.prid.asc());

            List<OneProductDTO> dtoList = query.fetch();
            dtoList.forEach(dto -> log.info(dto));

            return dtoList;
        }

    @Override
    public List<OneProductDTO> getProductsByGender(Long prId) {
            QProduct product = QProduct.product;
            QProductImg productImg = QProductImg.productImg;

            //주어진 prId로 상품을 조회하여 성별 추출
            JPQLQuery<String> genderQuery = from(product)
                    .select(product.gender)
                    .where(product.prid.eq(prId))
                    .limit(1);

            String gender = genderQuery.fetchOne();

            if (gender == null) {
                return new ArrayList<>(); //성별을 찾을 수 없는 경우 빈 리스트 반환
            }

            //해당 성별의 모든 상품 데이터 (해당 prId 제외)
            JPQLQuery<OneProductDTO> query = from(product)
                    .innerJoin(productImg).on(product.prid.eq(productImg.product.prid))
                    .select(Projections.bean(OneProductDTO.class,
                            product.prid, product.nameKor, product.nameEng,
                            product.category, product.brand, product.color, product.price,
                            product.info, product.gender, productImg.imgName
                    ))
                    .where(product.gender.eq(gender)
                            .and(product.prid.ne(prId)))
                    .orderBy(product.prid.asc());

            List<OneProductDTO> dtoList = query.fetch();
            dtoList.forEach(dto -> log.info(dto));

            return dtoList;
        }

    @Override
    public List<OneProductDTO> productsRecentView(List<Long> prId) {
        QProduct product = QProduct.product;
        QProductImg productImg = QProductImg.productImg;

        JPQLQuery<OneProductDTO> query = from(product)
                .innerJoin(productImg).on(product.prid.eq(productImg.product.prid))
                .select(Projections.bean(OneProductDTO.class,
                        product.prid, product.nameKor, product.nameEng,
                        product.category, product.brand, product.color, product.price,
                        product.info, product.gender, productImg.imgName
                ));
                //.where(product.prid.in(prId));

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        prId.forEach(prid -> booleanBuilder.or(product.prid.eq(prid)));
        query.where(booleanBuilder);

        List<OneProductDTO> dtoList = query.fetch();

        dtoList.forEach(dto -> log.info(dto));

        return dtoList;
    }
}