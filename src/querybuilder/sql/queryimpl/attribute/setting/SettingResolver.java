
package org.ehrbase.aql.sql.queryimpl.attribute.setting;

import static org.ehrbase.jooq.pg.tables.EventContext.EVENT_CONTEXT;

import org.ehrbase.aql.sql.queryimpl.QueryImplConstants;
import org.ehrbase.aql.sql.queryimpl.attribute.AttributeResolver;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.attribute.eventcontext.EventContextJson;
import org.jooq.Field;

@SuppressWarnings({"java:S3740", "java:S1452"})
public class SettingResolver extends AttributeResolver {

    public static final String MAPPINGS = "mappings";

    public SettingResolver(FieldResolutionContext fieldResolutionContext, JoinSetup joinSetup) {
        super(fieldResolutionContext, joinSetup);
    }

    public Field<?> sqlField(String path) {

        Field<?> retField;

        if (path.isEmpty())
            return new EventContextJson(fieldResolutionContext, joinSetup)
                    .forJsonPath("setting")
                    .sqlField();

        if (!path.equals(MAPPINGS) && path.startsWith(MAPPINGS)) {
            path = path.substring(path.indexOf(MAPPINGS) + MAPPINGS.length() + 1);
            // we insert a tag to indicate that the path operates on a json array
            fieldResolutionContext.setUsingSetReturningFunction(true); // to generate lateral join
            retField = new EventContextJson(fieldResolutionContext, joinSetup)
                    .forJsonPath("setting/mappings/" + QueryImplConstants.AQL_NODE_ITERATIVE_MARKER + "/" + path)
                    .forTableField(EVENT_CONTEXT.SETTING)
                    .sqlField();
        } else {
            retField = new EventContextJson(fieldResolutionContext, joinSetup)
                    .forJsonPath("setting/" + path)
                    .forTableField(EVENT_CONTEXT.SETTING)
                    .sqlField();
        }

        return retField;
    }
}
