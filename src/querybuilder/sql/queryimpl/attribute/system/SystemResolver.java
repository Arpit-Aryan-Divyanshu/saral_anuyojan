
package org.ehrbase.aql.sql.queryimpl.attribute.system;

import static org.ehrbase.jooq.pg.Tables.EHR_;
import static org.ehrbase.jooq.pg.tables.System.SYSTEM;

import org.ehrbase.aql.sql.binding.JoinBinder;
import org.ehrbase.aql.sql.queryimpl.attribute.AttributeResolver;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.value_field.GenericJsonField;
import org.jooq.Field;

@SuppressWarnings({"java:S3740", "java:S1452"})
public class SystemResolver extends AttributeResolver {

    public SystemResolver(FieldResolutionContext fieldResolutionContext, JoinSetup joinSetup) {
        super(fieldResolutionContext, joinSetup);
        joinSetup.setJoinSystem(true);
        joinSetup.setJoinEhr(true);
    }

    public Field<?> sqlField(String path) {

        if (path.isEmpty()) {
            return new GenericJsonField(fieldResolutionContext, joinSetup)
                    .hierObjectId(JoinBinder.ehrRecordTable.field(EHR_.SYSTEM_ID));
        }

        switch (path) {
            case "value":
                return new GenericJsonField(fieldResolutionContext, joinSetup)
                        .forJsonPath("value")
                        .hierObjectId(JoinBinder.ehrRecordTable.field(EHR_.SYSTEM_ID));
            case "description":
                return new SystemAttribute(fieldResolutionContext, joinSetup)
                        .forTableField(SYSTEM.DESCRIPTION)
                        .sqlField();
            default:
                throw new IllegalArgumentException("Unresolved system attribute path:" + path);
        }
    }
}
