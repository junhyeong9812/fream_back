package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRefundAccount is a Querydsl query type for RefundAccount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRefundAccount extends EntityPathBase<RefundAccount> {

    private static final long serialVersionUID = 688122213L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRefundAccount refundAccount = new QRefundAccount("refundAccount");

    public final StringPath accountHolder = createString("accountHolder");

    public final StringPath accountNumber = createString("accountNumber");

    public final StringPath bankCode = createString("bankCode");

    public final StringPath contactNumber = createString("contactNumber");

    public final NumberPath<Integer> raid = createNumber("raid", Integer.class);

    public final com.kream.root.Login.model.QUserListDTO user;

    public QRefundAccount(String variable) {
        this(RefundAccount.class, forVariable(variable), INITS);
    }

    public QRefundAccount(Path<? extends RefundAccount> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRefundAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRefundAccount(PathMetadata metadata, PathInits inits) {
        this(RefundAccount.class, metadata, inits);
    }

    public QRefundAccount(Class<? extends RefundAccount> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kream.root.Login.model.QUserListDTO(forProperty("user"), inits.get("user")) : null;
    }

}

