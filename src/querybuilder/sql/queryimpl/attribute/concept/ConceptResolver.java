
package org.ehrbase.aql.sql.queryimpl.attribute.concept;

import java.util.Arrays;
import org.ehrbase.aql.sql.queryimpl.QueryImplConstants;
import org.ehrbase.aql.sql.queryimpl.attribute.AttributeResolver;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.jooq.Field;
import org.jooq.TableField;

@SuppressWarnings({"java:S3776", "java:S3740", "java:S1452"})
public class ConceptResolver extends AttributeResolver {

    public static final String MAPPINGS = "mappings";
    TableField tableField;

    public ConceptResolver(FieldResolutionContext fieldResolutionContext, JoinSetup joinSetup) {
        super(fieldResolutionContext, joinSetup);
    }

    public Field<?> sqlField(String path) {

        if (path.isEmpty())
            return new ConceptJson(fieldResolutionContext, joinSetup)
                    .forTableField(tableField)
                    .sqlField();

        if (!path.equals(MAPPINGS) && path.startsWith(MAPPINGS)) {
            path = path.substring(path.indexOf(MAPPINGS) + MAPPINGS.length() + 1);
            // we insert a tag to indicate that the path operates on a json array
            fieldResolutionContext.setUsingSetReturningFunction(true); // to generate lateral join
            return new ConceptJson(fieldResolutionContext, joinSetup)
                    .forJsonPath("mappings/" + QueryImplConstants.AQL_NODE_ITERATIVE_MARKER + "/" + path)
                    .forTableField(tableField)
                    .sqlField();
        } else if (Arrays.asList(
                        "value",
                        MAPPINGS,
                        "defining_code",
                        "defining_code/terminology_id",
                        "defining_code/terminology_id/value",
                        "defining_code/code_string")
                .contains(path)) {
            Field sqlField = new ConceptJson(fieldResolutionContext, joinSetup)
                    .forJsonPath(path)
                    .forTableField(tableField)
                    .sqlField();
            if (path.equals("defining_code/terminology_id/value") || path.equals("value"))
                fieldResolutionContext.setJsonDatablock(false);
            return sqlField;
        } else throw new IllegalArgumentException("Unresolved concept attribute path:" + path);
    }

    public ConceptResolver forTableField(TableField tableField) {
        this.tableField = tableField;
        return this;
    }
}
