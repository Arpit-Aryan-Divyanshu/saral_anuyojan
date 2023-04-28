
package org.ehrbase.aql.sql.queryimpl.attribute.composition;

import static org.ehrbase.jooq.pg.Tables.ENTRY;

import org.ehrbase.aql.sql.queryimpl.IQueryImpl;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.IRMObjectAttribute;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.jooq.Field;
import org.jooq.TableField;
import org.jooq.impl.DSL;

public class CompositionName extends CompositionAttribute {

    public CompositionName(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
    }

    @Override
    public Field<?> sqlField() {
        // extract the composition name from the jsonb root key
        String trimName = "trim(LEADING '''' FROM (trim(TRAILING ''']' FROM\n"
                + " (regexp_split_to_array((select root_json_key from jsonb_object_keys("
                + ENTRY.ENTRY_ + ") root_json_key where root_json_key like '/composition%'),"
                + " 'and name/value=')) [2])))";
        // postgresql equivalent expression
        if (fieldContext.isWithAlias()) {
            return aliased(DSL.field(trimName));
        } else {
            if (fieldContext.getClause().equals(IQueryImpl.Clause.WHERE)) {
                trimName = "(SELECT " + trimName + ")";
            }
            return defaultAliased(DSL.field(trimName));
        }
    }

    @Override
    public IRMObjectAttribute forTableField(TableField tableField) {
        return this;
    }
}
