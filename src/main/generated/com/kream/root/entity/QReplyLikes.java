package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReplyLikes is a Querydsl query type for ReplyLikes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReplyLikes extends EntityPathBase<ReplyLikes> {

    private static final long serialVersionUID = 1233999650L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReplyLikes replyLikes = new QReplyLikes("replyLikes");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ComparablePath<Character> likeDislike = createComparable("likeDislike", Character.class);

    public final QReply reply;

    public final com.kream.root.Login.model.QUserListDTO user;

    public QReplyLikes(String variable) {
        this(ReplyLikes.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QReplyLikes(Path<? extends ReplyLikes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReplyLikes(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReplyLikes(PathMetadata metadata, PathInits inits) {
        this(ReplyLikes.class, metadata, inits);
    }

    public QReplyLikes(Class<? extends ReplyLikes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reply = inits.isInitialized("reply") ? new QReply(forProperty("reply"), inits.get("reply")) : null;
        this.user = inits.isInitialized("user") ? new com.kream.root.Login.model.QUserListDTO(forProperty("user"), inits.get("user")) : null;
    }

}

