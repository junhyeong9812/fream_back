package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStyle is a Querydsl query type for Style
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStyle extends EntityPathBase<Style> {

    private static final long serialVersionUID = -502699135L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStyle style = new QStyle("style");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<StyleLike, QStyleLike> likes = this.<StyleLike, QStyleLike>createSet("likes", StyleLike.class, QStyleLike.class, PathInits.DIRECT2);

    public final com.kream.root.MainAndShop.domain.QProduct product;

    public final SetPath<StyleReply, QStyleReply> replies = this.<StyleReply, QStyleReply>createSet("replies", StyleReply.class, QStyleReply.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> styleDate = createDateTime("styleDate", java.time.LocalDateTime.class);

    public final StringPath StyleImgName = createString("StyleImgName");

    public final com.kream.root.Login.model.QUserListDTO user;

    public QStyle(String variable) {
        this(Style.class, forVariable(variable), INITS);
    }

    public QStyle(Path<? extends Style> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStyle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStyle(PathMetadata metadata, PathInits inits) {
        this(Style.class, metadata, inits);
    }

    public QStyle(Class<? extends Style> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.kream.root.MainAndShop.domain.QProduct(forProperty("product")) : null;
        this.user = inits.isInitialized("user") ? new com.kream.root.Login.model.QUserListDTO(forProperty("user"), inits.get("user")) : null;
    }

}

