package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductQna is a Querydsl query type for ProductQna
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductQna extends EntityPathBase<ProductQna> {

    private static final long serialVersionUID = -740922715L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductQna productQna = new QProductQna("productQna");

    public final StringPath answer = createString("answer");

    public final DateTimePath<java.time.LocalDateTime> answeredDate = createDateTime("answeredDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final ComparablePath<Character> isAnswered = createComparable("isAnswered", Character.class);

    public final com.kream.root.MainAndShop.domain.QProduct product;

    public final NumberPath<Long> qnaId = createNumber("qnaId", Long.class);

    public final StringPath question = createString("question");

    public final com.kream.root.Login.model.QUserListDTO user;

    public QProductQna(String variable) {
        this(ProductQna.class, forVariable(variable), INITS);
    }

    public QProductQna(Path<? extends ProductQna> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductQna(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductQna(PathMetadata metadata, PathInits inits) {
        this(ProductQna.class, metadata, inits);
    }

    public QProductQna(Class<? extends ProductQna> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.kream.root.MainAndShop.domain.QProduct(forProperty("product")) : null;
        this.user = inits.isInitialized("user") ? new com.kream.root.Login.model.QUserListDTO(forProperty("user"), inits.get("user")) : null;
    }

}

