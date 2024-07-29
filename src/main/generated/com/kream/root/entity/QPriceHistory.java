package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPriceHistory is a Querydsl query type for PriceHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPriceHistory extends EntityPathBase<PriceHistory> {

    private static final long serialVersionUID = -961872069L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPriceHistory priceHistory = new QPriceHistory("priceHistory");

    public final DatePath<java.time.LocalDate> historyDate = createDate("historyDate", java.time.LocalDate.class);

    public final NumberPath<Long> historyId = createNumber("historyId", Long.class);

    public final NumberPath<Double> newPrice = createNumber("newPrice", Double.class);

    public final NumberPath<Double> priceChange = createNumber("priceChange", Double.class);

    public final com.kream.root.MainAndShop.domain.QProduct product;

    public QPriceHistory(String variable) {
        this(PriceHistory.class, forVariable(variable), INITS);
    }

    public QPriceHistory(Path<? extends PriceHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPriceHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPriceHistory(PathMetadata metadata, PathInits inits) {
        this(PriceHistory.class, metadata, inits);
    }

    public QPriceHistory(Class<? extends PriceHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.kream.root.MainAndShop.domain.QProduct(forProperty("product")) : null;
    }

}

