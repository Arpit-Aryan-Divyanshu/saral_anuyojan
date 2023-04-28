
package org.ehrbase.aql.sql.queryimpl.attribute.ehr.ehrstatus;

import static org.ehrbase.jooq.pg.Tables.STATUS;

import org.ehrbase.aql.sql.binding.JoinBinder;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.IRMObjectAttribute;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.jooq.Field;
import org.jooq.TableField;
import org.jooq.impl.DSL;

@SuppressWarnings({"java:S3740", "java:S1452"})
public class SimpleEhrStatusAttribute extends EhrStatusAttribute {

    protected Field tableField;

    public SimpleEhrStatusAttribute(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
    }

    @Override
    public Field<?> sqlField() {
        return as(DSL.field(tableField));
    }

    @Override
    public IRMObjectAttribute forTableField(TableField tableField) {
        this.tableField = tableField;
        if (tableField.getTable().equals(STATUS)) {
            joinSetup.setJoinEhrStatus(true);
            this.tableField = JoinBinder.statusRecordTable.field(tableField.getName());
        }
        return this;
    }
}
