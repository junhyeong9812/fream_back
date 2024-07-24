package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserProductInteraction is a Querydsl query type for UserProductInteraction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserProductInteraction extends EntityPathBase<UserProductInteraction> {

    private static final long serialVersionUID = -1972960002L;

    public static final QUserProductInteraction userProductInteraction = new QUserProductInteraction("userProductInteraction");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> interactionTime = createDateTime("interactionTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath userId = createString("userId");

    public QUserProductInteraction(String variable) {
        super(UserProductInteraction.class, forVariable(variable));
    }

    public QUserProductInteraction(Path<? extends UserProductInteraction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserProductInteraction(PathMetadata metadata) {
        super(UserProductInteraction.class, metadata);
    }

}

