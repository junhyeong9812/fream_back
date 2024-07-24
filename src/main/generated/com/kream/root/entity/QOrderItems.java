package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderItems is a Querydsl query type for OrderItems
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderItems extends EntityPathBase<OrderItems> {

    private static final long serialVersionUID = -1757829822L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderItems orderItems = new QOrderItems("orderItems");

    public final QOrders order;

    public final NumberPath<Long> orderItemId = createNumber("orderItemId", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final com.kream.root.MainAndShop.domain.QProduct product;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QOrderItems(String variable) {
        this(OrderItems.class, forVariable(variable), INITS);
    }

    public QOrderItems(Path<? extends OrderItems> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderItems(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderItems(PathMetadata metadata, PathInits inits) {
        this(OrderItems.class, metadata, inits);
    }

    public QOrderItems(Class<? extends OrderItems> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrders(forProperty("order"), inits.get("order")) : null;
        this.product = inits.isInitialized("product") ? new com.kream.root.MainAndShop.domain.QProduct(forProperty("product")) : null;
    }

}

