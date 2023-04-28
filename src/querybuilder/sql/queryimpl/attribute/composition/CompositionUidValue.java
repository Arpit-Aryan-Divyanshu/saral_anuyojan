
package org.ehrbase.aql.sql.queryimpl.attribute.composition;

import static org.ehrbase.jooq.pg.Tables.COMPOSITION_HISTORY;

import java.util.UUID;
import org.ehrbase.aql.sql.binding.JoinBinder;
import org.ehrbase.aql.sql.queryimpl.IQueryImpl;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.IRMObjectAttribute;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.jooq.Field;
import org.jooq.SelectQuery;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class CompositionUidValue extends CompositionAttribute {

    public CompositionUidValue(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
    }

    @Override
    public Field<?> sqlField() {
        if (fieldContext.getClause() == IQueryImpl.Clause.WHERE) filterSetup.setCompositionIdFiltered(true);
        else compositionIdFieldSetup.setCompositionIdField(true);

        joinSetup.setJoinComposition(true);

        if (fieldContext.isWithAlias()) return uid();
        else return rawUid();
    }

    @Override
    public IRMObjectAttribute forTableField(TableField tableField) {
        return this;
    }

    private Field<?> uid() {

        // use inline SQL as it seems coalesce is not going through with POSTGRES dialect
        SelectQuery<?> subSelect = fieldContext.getContext().selectQuery();
        subSelect.addSelect(DSL.count());
        subSelect.addFrom(COMPOSITION_HISTORY);
        subSelect.addConditions(
                JoinBinder.compositionRecordTable.field("id", UUID.class).eq(COMPOSITION_HISTORY.ID));
        subSelect.addGroupBy(COMPOSITION_HISTORY.ID);

        String coalesceVersion = "1 + COALESCE(\n(" + subSelect + "), 0)";

        return aliased(DSL.field(
                JoinBinder.compositionRecordTable.field("id")
                        + "||"
                        + DSL.val("::")
                        + "||"
                        + DSL.val(fieldContext.getServerNodeId())
                        + "||"
                        + DSL.val("::")
                        + "||"
                        + DSL.field(coalesceVersion),
                SQLDataType.VARCHAR));
    }

    private Field<?> rawUid() {
        return as(DSL.field(JoinBinder.compositionRecordTable.field("id", UUID.class)));
    }
}
