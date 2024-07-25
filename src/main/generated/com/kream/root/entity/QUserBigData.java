package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserBigData is a Querydsl query type for UserBigData
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserBigData extends EntityPathBase<UserBigData> {

    private static final long serialVersionUID = -419564465L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserBigData userBigData = new QUserBigData("userBigData");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final com.kream.root.MainAndShop.domain.QProduct product;

    public final NumberPath<Integer> ub_clickCount = createNumber("ub_clickCount", Integer.class);

    public final NumberPath<Long> ub_id = createNumber("ub_id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> ubDate = _super.ubDate;

    public final com.kream.root.Login.model.QUserListDTO userListDTO;

    public QUserBigData(String variable) {
        this(UserBigData.class, forVariable(variable), INITS);
    }

    public QUserBigData(Path<? extends UserBigData> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserBigData(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserBigData(PathMetadata metadata, PathInits inits) {
        this(UserBigData.class, metadata, inits);
    }

    public QUserBigData(Class<? extends UserBigData> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.kream.root.MainAndShop.domain.QProduct(forProperty("product")) : null;
        this.userListDTO = inits.isInitialized("userListDTO") ? new com.kream.root.Login.model.QUserListDTO(forProperty("userListDTO"), inits.get("userListDTO")) : null;
    }

}

