package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrders is a Querydsl query type for Orders
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrders extends EntityPathBase<Orders> {

    private static final long serialVersionUID = 1479200533L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrders orders = new QOrders("orders");

    public final StringPath applyNum = createString("applyNum");

    public final StringPath bankName = createString("bankName");

    public final StringPath buyerAddr = createString("buyerAddr");

    public final StringPath buyerEmail = createString("buyerEmail");

    public final StringPath buyerName = createString("buyerName");

    public final StringPath buyerPostcode = createString("buyerPostcode");

    public final StringPath buyerTel = createString("buyerTel");

    public final StringPath cardName = createString("cardName");

    public final StringPath cardNumber = createString("cardNumber");

    public final NumberPath<Integer> cardQuota = createNumber("cardQuota", Integer.class);

    public final StringPath currency = createString("currency");

    public final StringPath customData = createString("customData");

    public final StringPath impUid = createString("impUid");

    public final StringPath merchantUid = createString("merchantUid");

    public final StringPath orderCode = createString("orderCode");

    public final DateTimePath<java.time.LocalDateTime> orderDate = createDateTime("orderDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public final ListPath<OrderItems, QOrderItems> orderItems = this.<OrderItems, QOrderItems>createList("orderItems", OrderItems.class, QOrderItems.class, PathInits.DIRECT2);

    public final NumberPath<Double> paidAmount = createNumber("paidAmount", Double.class);

    public final NumberPath<Long> paidAt = createNumber("paidAt", Long.class);

    public final StringPath payMethod = createString("payMethod");

    public final StringPath pgProvider = createString("pgProvider");

    public final StringPath pgTid = createString("pgTid");

    public final StringPath pgType = createString("pgType");

    public final StringPath productName = createString("productName");

    public final StringPath receiptUrl = createString("receiptUrl");

    public final QSellerProduct sellerProduct;

    public final StringPath status = createString("status");

    public final BooleanPath success = createBoolean("success");

    public final com.kream.root.Login.model.QUserListDTO user;

    public QOrders(String variable) {
        this(Orders.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QOrders(Path<? extends Orders> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrders(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrders(PathMetadata metadata, PathInits inits) {
        this(Orders.class, metadata, inits);
    }

    public QOrders(Class<? extends Orders> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sellerProduct = inits.isInitialized("sellerProduct") ? new QSellerProduct(forProperty("sellerProduct"), inits.get("sellerProduct")) : null;
        this.user = inits.isInitialized("user") ? new com.kream.root.Login.model.QUserListDTO(forProperty("user"), inits.get("user")) : null;
    }

}

