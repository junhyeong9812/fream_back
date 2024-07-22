package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSellerProduct is a Querydsl query type for SellerProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSellerProduct extends EntityPathBase<SellerProduct> {

    private static final long serialVersionUID = -1443826112L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSellerProduct sellerProduct = new QSellerProduct("sellerProduct");

    public final ComparablePath<Character> isSold = createComparable("isSold", Character.class);

    public final com.kream.root.MainAndShop.domain.QProduct product;

    public final StringPath proSize = createString("proSize");

    public final NumberPath<Long> sellerProductId = createNumber("sellerProductId", Long.class);

    public final com.kream.root.Login.model.QUserListDTO user;

    public QSellerProduct(String variable) {
        this(SellerProduct.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QSellerProduct(Path<? extends SellerProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSellerProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSellerProduct(PathMetadata metadata, PathInits inits) {
        this(SellerProduct.class, metadata, inits);
    }

    public QSellerProduct(Class<? extends SellerProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.kream.root.MainAndShop.domain.QProduct(forProperty("product")) : null;
        this.user = inits.isInitialized("user") ? new com.kream.root.Login.model.QUserListDTO(forProperty("user"), inits.get("user")) : null;
    }

}

