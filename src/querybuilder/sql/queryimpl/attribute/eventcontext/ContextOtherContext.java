
package org.ehrbase.aql.sql.queryimpl.attribute.eventcontext;

import static org.ehrbase.jooq.pg.Tables.EVENT_CONTEXT;

import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.IRMObjectAttribute;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.jooq.Field;
import org.jooq.TableField;

public class ContextOtherContext extends EventContextAttribute {

    public ContextOtherContext(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
    }

    @Override
    public Field<?> sqlField() {
        String path =
                new OtherContextPredicate(fieldContext.getVariableDefinition().getPath()).adjustForQuery();

        String variablePath = path.substring("context/other_context".length());

        if (variablePath.startsWith("/")) variablePath = variablePath.substring(1);
        return new EventContextJson(fieldContext, joinSetup)
                .forJsonPath("other_context/" + variablePath)
                .forTableField(EVENT_CONTEXT.OTHER_CONTEXT)
                .sqlField();
    }

    @Override
    public IRMObjectAttribute forTableField(TableField tableField) {
        return this;
    }
}
