package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReplyLikesId is a Querydsl query type for ReplyLikesId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QReplyLikesId extends BeanPath<ReplyLikesId> {

    private static final long serialVersionUID = 462692317L;

    public static final QReplyLikesId replyLikesId = new QReplyLikesId("replyLikesId");

    public final NumberPath<Long> replyId = createNumber("replyId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QReplyLikesId(String variable) {
        super(ReplyLikesId.class, forVariable(variable));
    }

    public QReplyLikesId(Path<? extends ReplyLikesId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReplyLikesId(PathMetadata metadata) {
        super(ReplyLikesId.class, metadata);
    }

}

