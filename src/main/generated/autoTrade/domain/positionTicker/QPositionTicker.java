package autoTrade.domain.positionTicker;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPositionTicker is a Querydsl query type for PositionTicker
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPositionTicker extends EntityPathBase<PositionTicker> {

    private static final long serialVersionUID = -1200278243L;

    public static final QPositionTicker positionTicker = new QPositionTicker("positionTicker");

    public final EnumPath<autoTrade.domain.ExchangeType> exchangeType = createEnum("exchangeType", autoTrade.domain.ExchangeType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath price = createString("price");

    public final NumberPath<Double> quantity = createNumber("quantity", Double.class);

    public final StringPath symbol = createString("symbol");

    public QPositionTicker(String variable) {
        super(PositionTicker.class, forVariable(variable));
    }

    public QPositionTicker(Path<? extends PositionTicker> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPositionTicker(PathMetadata metadata) {
        super(PositionTicker.class, metadata);
    }

}

