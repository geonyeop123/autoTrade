package autoTrade.domain.premium;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPremium is a Querydsl query type for Premium
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPremium extends EntityPathBase<Premium> {

    private static final long serialVersionUID = 2086049167L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPremium premium = new QPremium("premium");

    public final autoTrade.domain.QBaseEntity _super = new autoTrade.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDatetime = _super.createdDatetime;

    public final autoTrade.domain.exchangeRate.QExchangeRate exchangeRate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final autoTrade.domain.ticker.QTicker krBtcTicker;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDatetime = _super.lastModifiedDatetime;

    public final NumberPath<Double> premiumRate = createNumber("premiumRate", Double.class);

    public final autoTrade.domain.ticker.QTicker usBtcTicker;

    public final autoTrade.domain.ticker.QTicker usdtTicker;

    public QPremium(String variable) {
        this(Premium.class, forVariable(variable), INITS);
    }

    public QPremium(Path<? extends Premium> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPremium(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPremium(PathMetadata metadata, PathInits inits) {
        this(Premium.class, metadata, inits);
    }

    public QPremium(Class<? extends Premium> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exchangeRate = inits.isInitialized("exchangeRate") ? new autoTrade.domain.exchangeRate.QExchangeRate(forProperty("exchangeRate")) : null;
        this.krBtcTicker = inits.isInitialized("krBtcTicker") ? new autoTrade.domain.ticker.QTicker(forProperty("krBtcTicker")) : null;
        this.usBtcTicker = inits.isInitialized("usBtcTicker") ? new autoTrade.domain.ticker.QTicker(forProperty("usBtcTicker")) : null;
        this.usdtTicker = inits.isInitialized("usdtTicker") ? new autoTrade.domain.ticker.QTicker(forProperty("usdtTicker")) : null;
    }

}

