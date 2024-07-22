package com.kream.root.MainAndShop.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductPreData is a Querydsl query type for ProductPreData
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductPreData extends EntityPathBase<ProductPreData> {

    private static final long serialVersionUID = -161752685L;

    public static final QProductPreData productPreData = new QProductPreData("productPreData");

    public final StringPath age = createString("age");

    public final StringPath brand = createString("brand");

    public final StringPath cat = createString("cat");

    public final NumberPath<Long> click = createNumber("click", Long.class);

    public final StringPath color = createString("color");

    public final NumberPath<Double> cr = createNumber("cr", Double.class);

    public final StringPath gender = createString("gender");

    public final StringPath name_eng = createString("name_eng");

    public final StringPath name_kor = createString("name_kor");

    public final NumberPath<Long> prid = createNumber("prid", Long.class);

    public final NumberPath<Long> ranking = createNumber("ranking", Long.class);

    public final StringPath reg_date = createString("reg_date");

    public final NumberPath<Long> rwid = createNumber("rwid", Long.class);

    public final NumberPath<Long> total_price = createNumber("total_price", Long.class);

    public final NumberPath<Long> trading_volume = createNumber("trading_volume", Long.class);

    public QProductPreData(String variable) {
        super(ProductPreData.class, PathMetadataFactory.forVariable(variable));
    }

    public QProductPreData(Path<? extends ProductPreData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductPreData(PathMetadata metadata) {
        super(ProductPreData.class, metadata);
    }

}

