package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWish is a Querydsl query type for Wish
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWish extends EntityPathBase<Wish> {

    private static final long serialVersionUID = 399534295L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWish wish = new QWish("wish");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.kream.root.MainAndShop.domain.QProduct product;

    public final StringPath size = createString("size");

    public final com.kream.root.Login.model.QUserListDTO user;

    public QWish(String variable) {
        this(Wish.class, forVariable(variable), INITS);
    }

    public QWish(Path<? extends Wish> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWish(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWish(PathMetadata metadata, PathInits inits) {
        this(Wish.class, metadata, inits);
    }

    public QWish(Class<? extends Wish> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.kream.root.MainAndShop.domain.QProduct(forProperty("product")) : null;
        this.user = inits.isInitialized("user") ? new com.kream.root.Login.model.QUserListDTO(forProperty("user"), inits.get("user")) : null;
    }

}

