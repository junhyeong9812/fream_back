package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCancellation is a Querydsl query type for Cancellation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCancellation extends EntityPathBase<Cancellation> {

    private static final long serialVersionUID = -1184493261L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCancellation cancellation = new QCancellation("cancellation");

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final NumberPath<Long> cancellationId = createNumber("cancellationId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> cancelledAt = createDateTime("cancelledAt", java.time.LocalDateTime.class);

    public final StringPath impUid = createString("impUid");

    public final StringPath merchantUid = createString("merchantUid");

    public final QOrders order;

    public final StringPath reason = createString("reason");

    public final StringPath refundAccount = createString("refundAccount");

    public final StringPath refundBank = createString("refundBank");

    public final StringPath refundHolder = createString("refundHolder");

    public final StringPath refundTel = createString("refundTel");

    public final com.kream.root.Login.model.QUserListDTO user;

    public QCancellation(String variable) {
        this(Cancellation.class, forVariable(variable), INITS);
    }

    public QCancellation(Path<? extends Cancellation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCancellation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCancellation(PathMetadata metadata, PathInits inits) {
        this(Cancellation.class, metadata, inits);
    }

    public QCancellation(Class<? extends Cancellation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrders(forProperty("order"), inits.get("order")) : null;
        this.user = inits.isInitialized("user") ? new com.kream.root.Login.model.QUserListDTO(forProperty("user"), inits.get("user")) : null;
    }

}

