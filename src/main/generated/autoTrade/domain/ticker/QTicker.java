package autoTrade.domain.ticker;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTicker is a Querydsl query type for Ticker
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTicker extends EntityPathBase<Ticker> {

    private static final long serialVersionUID = -961627587L;

    public static final QTicker ticker = new QTicker("ticker");

    public final autoTrade.domain.QBaseEntity _super = new autoTrade.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDatetime = _super.createdDatetime;

    public final EnumPath<autoTrade.domain.ExchangeType> exchangeType = createEnum("exchangeType", autoTrade.domain.ExchangeType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDatetime = _super.lastModifiedDatetime;

    public final StringPath price = createString("price");

    public final StringPath symbol = createString("symbol");

    public QTicker(String variable) {
        super(Ticker.class, forVariable(variable));
    }

    public QTicker(Path<? extends Ticker> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTicker(PathMetadata metadata) {
        super(Ticker.class, metadata);
    }

}

