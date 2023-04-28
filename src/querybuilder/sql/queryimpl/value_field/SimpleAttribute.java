
package org.ehrbase.aql.sql.queryimpl.value_field;

import java.util.Optional;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.IRMObjectAttribute;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.attribute.composition.CompositionAttribute;
import org.jooq.Field;
import org.jooq.TableField;
import org.jooq.impl.DSL;

@SuppressWarnings({"java:S3740", "java:S1452"})
public class SimpleAttribute extends CompositionAttribute {

    protected Field tableField;
    Optional<String> type = Optional.empty();

    public SimpleAttribute(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
    }

    @Override
    public Field<?> sqlField() {
        Field actualField;

        if (type.isPresent()) actualField = DSL.field(tableField + "::" + type.get());
        else actualField = DSL.field(tableField);

        return as(actualField);
    }

    @Override
    public IRMObjectAttribute forTableField(TableField tableField) {
        return forTableField(tableField);
    }

    public SimpleAttribute forTableField(String pgtype, Field tableField) {
        if (pgtype != null) type = Optional.of(pgtype);

        this.tableField = tableField;
        return this;
    }
}
