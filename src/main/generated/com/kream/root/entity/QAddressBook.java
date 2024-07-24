package com.kream.root.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAddressBook is a Querydsl query type for AddressBook
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddressBook extends EntityPathBase<AddressBook> {

    private static final long serialVersionUID = -1379867955L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddressBook addressBook1 = new QAddressBook("addressBook1");

    public final QAddressBook addressBook;

    public final NumberPath<Long> addressId = createNumber("addressId", Long.class);

    public final StringPath city = createString("city");

    public final ComparablePath<Character> isDefault = createComparable("isDefault", Character.class);

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath postalCode = createString("postalCode");

    public final StringPath street = createString("street");

    public final com.kream.root.Login.model.QUserListDTO user;

    public QAddressBook(String variable) {
        this(AddressBook.class, forVariable(variable), INITS);
    }

    public QAddressBook(Path<? extends AddressBook> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAddressBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAddressBook(PathMetadata metadata, PathInits inits) {
        this(AddressBook.class, metadata, inits);
    }

    public QAddressBook(Class<? extends AddressBook> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addressBook = inits.isInitialized("addressBook") ? new QAddressBook(forProperty("addressBook"), inits.get("addressBook")) : null;
        this.user = inits.isInitialized("user") ? new com.kream.root.Login.model.QUserListDTO(forProperty("user"), inits.get("user")) : null;
    }

}

