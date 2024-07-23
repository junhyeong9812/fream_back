package com.kream.root.admin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserAccessLog is a Querydsl query type for UserAccessLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAccessLog extends EntityPathBase<UserAccessLog> {

    private static final long serialVersionUID = -648888923L;

    public static final QUserAccessLog userAccessLog = new QUserAccessLog("userAccessLog");

    public final DateTimePath<java.time.LocalDateTime> accessTime = createDateTime("accessTime", java.time.LocalDateTime.class);

    public final StringPath browser = createString("browser");

    public final StringPath deviceType = createString("deviceType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath os = createString("os");

    public final StringPath refererUrl = createString("refererUrl");

    public final StringPath userAgent = createString("userAgent");

    public QUserAccessLog(String variable) {
        super(UserAccessLog.class, forVariable(variable));
    }

    public QUserAccessLog(Path<? extends UserAccessLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserAccessLog(PathMetadata metadata) {
        super(UserAccessLog.class, metadata);
    }

}

