
package org.ehrbase.aql.sql.queryimpl.attribute.ehr.ehrstatus;

import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.IRMObjectAttribute;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.jooq.Field;
import org.jooq.TableField;

public class EhrStatusOtherDetails extends EhrStatusAttribute {

    public EhrStatusOtherDetails(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
        joinSetup.setContainsEhrStatus(true);
    }

    @Override
    public Field<?> sqlField() {
        String variablePath =
                fieldContext.getVariableDefinition().getPath().substring("ehr_status/other_details".length());
        if (variablePath.startsWith("/")) variablePath = variablePath.substring(1);

        return new EhrStatusJson(fieldContext, joinSetup)
                .forJsonPath("other_details/" + variablePath)
                .sqlField();
    }

    @Override
    public IRMObjectAttribute forTableField(TableField tableField) {
        return this;
    }
}
