package autoTrade.domain.exchangeRate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExchangeRate is a Querydsl query type for ExchangeRate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExchangeRate extends EntityPathBase<ExchangeRate> {

    private static final long serialVersionUID = -578111139L;

    public static final QExchangeRate exchangeRate = new QExchangeRate("exchangeRate");

    public final autoTrade.domain.QBaseEntity _super = new autoTrade.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDatetime = _super.createdDatetime;

    public final EnumPath<CreateType> createType = createEnum("createType", CreateType.class);

    public final DateTimePath<java.time.LocalDateTime> datetime = createDateTime("datetime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDatetime = _super.lastModifiedDatetime;

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public QExchangeRate(String variable) {
        super(ExchangeRate.class, forVariable(variable));
    }

    public QExchangeRate(Path<? extends ExchangeRate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExchangeRate(PathMetadata metadata) {
        super(ExchangeRate.class, metadata);
    }

}

