package com.kream.root.Login.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserListDTO is a Querydsl query type for UserListDTO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserListDTO extends EntityPathBase<UserListDTO> {

    private static final long serialVersionUID = 33062065L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserListDTO userListDTO = new QUserListDTO("userListDTO");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath blockedProfiles = createString("blockedProfiles");

    public final ListPath<com.kream.root.entity.Board, com.kream.root.entity.QBoard> boards = this.<com.kream.root.entity.Board, com.kream.root.entity.QBoard>createList("boards", com.kream.root.entity.Board.class, com.kream.root.entity.QBoard.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final StringPath favoriteProducts = createString("favoriteProducts");

    public final StringPath gender = createString("gender");

    public final DatePath<java.time.LocalDate> joinDate = createDate("joinDate", java.time.LocalDate.class);

    public final StringPath kakaoUser = createString("kakaoUser");

    public final DateTimePath<java.time.LocalDateTime> lastLoginTime = createDateTime("lastLoginTime", java.time.LocalDateTime.class);

    public final StringPath phone = createString("phone");

    public final StringPath profileName = createString("profileName");

    public final StringPath profileUrl = createString("profileUrl");

    public final StringPath receiveEmail = createString("receiveEmail");

    public final StringPath receiveMessage = createString("receiveMessage");

    public final com.kream.root.entity.QRefundAccount refundAccount;

    public final ListPath<com.kream.root.entity.Reply, com.kream.root.entity.QReply> replies = this.<com.kream.root.entity.Reply, com.kream.root.entity.QReply>createList("replies", com.kream.root.entity.Reply.class, com.kream.root.entity.QReply.class, PathInits.DIRECT2);

    public final ListPath<String, StringPath> roles = this.<String, StringPath>createList("roles", String.class, StringPath.class, PathInits.DIRECT2);

    public final SetPath<com.kream.root.entity.StyleLike, com.kream.root.entity.QStyleLike> styleLikes = this.<com.kream.root.entity.StyleLike, com.kream.root.entity.QStyleLike>createSet("styleLikes", com.kream.root.entity.StyleLike.class, com.kream.root.entity.QStyleLike.class, PathInits.DIRECT2);

    public final SetPath<com.kream.root.entity.StyleReply, com.kream.root.entity.QStyleReply> styleReplies = this.<com.kream.root.entity.StyleReply, com.kream.root.entity.QStyleReply>createSet("styleReplies", com.kream.root.entity.StyleReply.class, com.kream.root.entity.QStyleReply.class, PathInits.DIRECT2);

    public final SetPath<com.kream.root.entity.Style, com.kream.root.entity.QStyle> styles = this.<com.kream.root.entity.Style, com.kream.root.entity.QStyle>createSet("styles", com.kream.root.entity.Style.class, com.kream.root.entity.QStyle.class, PathInits.DIRECT2);

    public final NumberPath<Integer> ulid = createNumber("ulid", Integer.class);

    public final StringPath userBio = createString("userBio");

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final StringPath userPw = createString("userPw");

    public final StringPath userSize = createString("userSize");

    public final ListPath<com.kream.root.entity.Wish, com.kream.root.entity.QWish> wishes = this.<com.kream.root.entity.Wish, com.kream.root.entity.QWish>createList("wishes", com.kream.root.entity.Wish.class, com.kream.root.entity.QWish.class, PathInits.DIRECT2);

    public QUserListDTO(String variable) {
        this(UserListDTO.class, forVariable(variable), INITS);
    }

    public QUserListDTO(Path<? extends UserListDTO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserListDTO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserListDTO(PathMetadata metadata, PathInits inits) {
        this(UserListDTO.class, metadata, inits);
    }

    public QUserListDTO(Class<? extends UserListDTO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.refundAccount = inits.isInitialized("refundAccount") ? new com.kream.root.entity.QRefundAccount(forProperty("refundAccount"), inits.get("refundAccount")) : null;
    }

}

