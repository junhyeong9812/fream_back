package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStyleReply is a Querydsl query type for StyleReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStyleReply extends EntityPathBase<StyleReply> {

    private static final long serialVersionUID = 1928708073L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStyleReply styleReply = new QStyleReply("styleReply");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStyle style;

    public final com.kream.root.Login.model.QUserListDTO user;

    public QStyleReply(String variable) {
        this(StyleReply.class, forVariable(variable), INITS);
    }

    public QStyleReply(Path<? extends StyleReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStyleReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStyleReply(PathMetadata metadata, PathInits inits) {
        this(StyleReply.class, metadata, inits);
    }

    public QStyleReply(Class<? extends StyleReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.style = inits.isInitialized("style") ? new QStyle(forProperty("style"), inits.get("style")) : null;
        this.user = inits.isInitialized("user") ? new com.kream.root.Login.model.QUserListDTO(forProperty("user"), inits.get("user")) : null;
    }

}

