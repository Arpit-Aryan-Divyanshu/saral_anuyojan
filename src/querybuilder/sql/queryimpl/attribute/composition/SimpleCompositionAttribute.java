
package org.ehrbase.aql.sql.queryimpl.attribute.composition;

import static org.ehrbase.jooq.pg.Tables.COMPOSITION;

import org.ehrbase.aql.sql.binding.JoinBinder;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.IRMObjectAttribute;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.jooq.Field;
import org.jooq.TableField;
import org.jooq.impl.DSL;

@SuppressWarnings({"java:S3740", "java:S1452"})
public class SimpleCompositionAttribute extends CompositionAttribute {

    protected TableField tableField;

    public SimpleCompositionAttribute(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
    }

    @Override
    public Field<?> sqlField() {
        Field actualField = DSL.field(tableField);

        if (tableField.getTable().equals(COMPOSITION)) {
            actualField = DSL.field(JoinBinder.compositionRecordTable.getName() + "." + tableField.getName());
        }

        return as(actualField);
    }

    @Override
    public IRMObjectAttribute forTableField(TableField tableField) {
        this.tableField = tableField;
        return this;
    }
}
