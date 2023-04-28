
package org.ehrbase.aql.sql.queryimpl.attribute.ehr;

import static org.ehrbase.jooq.pg.Tables.EHR_;

import org.ehrbase.aql.sql.binding.JoinBinder;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.IRMObjectAttribute;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.jooq.Field;
import org.jooq.TableField;
import org.jooq.impl.DSL;

public class EhrIdValue extends EhrAttribute {

    public EhrIdValue(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
    }

    @Override
    public Field<?> sqlField() {
        ehrSetup.setContainsEhrId(true);
        ehrSetup.setEhrIdAlias(effectiveAlias());
        if (fieldContext.getPathResolver().hasPathExpression()) {
            joinSetup.setJoinEhr(true);
            if (fieldContext.isWithAlias()) {
                return aliased(DSL.field("{0}", JoinBinder.ehrRecordTable.field(EHR_.ID.getName())));
            } else
                return defaultAliased(
                        DSL.field(JoinBinder.ehrRecordTable.field(JoinBinder.ehrRecordTable.field(EHR_.ID.getName()))));
        } else if (!joinSetup.isContainsEhrStatus()) {
            joinSetup.setJoinEhr(true);
            if (fieldContext.isWithAlias()) {
                return aliased(DSL.field("{0}", JoinBinder.ehrRecordTable.field(EHR_.ID.getName())));
            } else return defaultAliased(DSL.field(JoinBinder.ehrRecordTable.field(EHR_.ID.getName())));
        } else {
            if (fieldContext.isWithAlias()) {
                return aliased(DSL.field("{0}", JoinBinder.ehrRecordTable.field(EHR_.ID.getName())));
            } else return defaultAliased(DSL.field(JoinBinder.ehrRecordTable.field(EHR_.ID.getName())));
        }
    }

    @Override
    public IRMObjectAttribute forTableField(TableField tableField) {
        return this;
    }
}
