package com.kream.root.MainAndShop.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1886256634L;

    public static final QProduct product = new QProduct("product");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath brand = createString("brand");

    public final StringPath category = createString("category");

    public final StringPath color = createString("color");

    public final StringPath gender = createString("gender");

    public final StringPath info = createString("info");

    public final StringPath nameEng = createString("nameEng");

    public final StringPath nameKor = createString("nameKor");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Long> prid = createNumber("prid", Long.class);

    public final ListPath<ProductImg, QProductImg> productImgs = this.<ProductImg, QProductImg>createList("productImgs", ProductImg.class, QProductImg.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final SetPath<com.kream.root.entity.Style, com.kream.root.entity.QStyle> styles = this.<com.kream.root.entity.Style, com.kream.root.entity.QStyle>createSet("styles", com.kream.root.entity.Style.class, com.kream.root.entity.QStyle.class, PathInits.DIRECT2);

    public final ListPath<com.kream.root.entity.Wish, com.kream.root.entity.QWish> wishes = this.<com.kream.root.entity.Wish, com.kream.root.entity.QWish>createList("wishes", com.kream.root.entity.Wish.class, com.kream.root.entity.QWish.class, PathInits.DIRECT2);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

