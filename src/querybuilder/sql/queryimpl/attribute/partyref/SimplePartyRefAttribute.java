
package org.ehrbase.aql.sql.queryimpl.attribute.partyref;

import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.IRMObjectAttribute;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.jooq.Field;
import org.jooq.TableField;
import org.jooq.impl.DSL;

@SuppressWarnings({"java:S3740", "java:S1452"})
public class SimplePartyRefAttribute extends PartyRefAttribute {

    protected Field tableField;

    public SimplePartyRefAttribute(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
    }

    @Override
    public Field<?> sqlField() {
        return as(DSL.field(tableField));
    }

    @Override
    public IRMObjectAttribute forTableField(TableField tableField) {
        this.tableField = joinSetup.getPartyJoinRef().field(tableField);
        return this;
    }
}
