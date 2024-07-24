package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardLikesId is a Querydsl query type for BoardLikesId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBoardLikesId extends BeanPath<BoardLikesId> {

    private static final long serialVersionUID = 356323425L;

    public static final QBoardLikesId boardLikesId = new QBoardLikesId("boardLikesId");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QBoardLikesId(String variable) {
        super(BoardLikesId.class, forVariable(variable));
    }

    public QBoardLikesId(Path<? extends BoardLikesId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardLikesId(PathMetadata metadata) {
        super(BoardLikesId.class, metadata);
    }

}

