package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecommend is a Querydsl query type for Recommend
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecommend extends EntityPathBase<Recommend> {

    private static final long serialVersionUID = 1878587020L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecommend recommend = new QRecommend("recommend");

    public final NumberPath<Integer> click = createNumber("click", Integer.class);

    public final com.kream.root.MainAndShop.domain.QProduct product;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final DatePath<java.time.LocalDate> recommend_date = createDate("recommend_date", java.time.LocalDate.class);

    public final NumberPath<Long> recommend_id = createNumber("recommend_id", Long.class);

    public final NumberPath<Integer> style = createNumber("style", Integer.class);

    public final com.kream.root.Login.model.QUserListDTO user;

    public QRecommend(String variable) {
        this(Recommend.class, forVariable(variable), INITS);
    }

    public QRecommend(Path<? extends Recommend> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecommend(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecommend(PathMetadata metadata, PathInits inits) {
        this(Recommend.class, metadata, inits);
    }

    public QRecommend(Class<? extends Recommend> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.kream.root.MainAndShop.domain.QProduct(forProperty("product")) : null;
        this.user = inits.isInitialized("user") ? new com.kream.root.Login.model.QUserListDTO(forProperty("user"), inits.get("user")) : null;
    }

}

