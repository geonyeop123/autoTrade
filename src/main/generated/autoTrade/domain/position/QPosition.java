package autoTrade.domain.position;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPosition is a Querydsl query type for Position
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPosition extends EntityPathBase<Position> {

    private static final long serialVersionUID = 1015671965L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPosition position = new QPosition("position1");

    public final autoTrade.domain.QBaseEntity _super = new autoTrade.domain.QBaseEntity(this);

    public final BooleanPath alertYn = createBoolean("alertYn");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDatetime = _super.createdDatetime;

    public final autoTrade.domain.exchangeRate.QExchangeRate exchangeRate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final autoTrade.domain.positionTicker.QPositionTicker krTicker;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDatetime = _super.lastModifiedDatetime;

    public final autoTrade.domain.member.QMember member;

    public final BooleanPath sellYn = createBoolean("sellYn");

    public final autoTrade.domain.positionTicker.QPositionTicker usTicker;

    public QPosition(String variable) {
        this(Position.class, forVariable(variable), INITS);
    }

    public QPosition(Path<? extends Position> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPosition(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPosition(PathMetadata metadata, PathInits inits) {
        this(Position.class, metadata, inits);
    }

    public QPosition(Class<? extends Position> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exchangeRate = inits.isInitialized("exchangeRate") ? new autoTrade.domain.exchangeRate.QExchangeRate(forProperty("exchangeRate")) : null;
        this.krTicker = inits.isInitialized("krTicker") ? new autoTrade.domain.positionTicker.QPositionTicker(forProperty("krTicker")) : null;
        this.member = inits.isInitialized("member") ? new autoTrade.domain.member.QMember(forProperty("member")) : null;
        this.usTicker = inits.isInitialized("usTicker") ? new autoTrade.domain.positionTicker.QPositionTicker(forProperty("usTicker")) : null;
    }

}

