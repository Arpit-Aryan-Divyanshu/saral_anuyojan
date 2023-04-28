
package org.ehrbase.aql.sql.queryimpl.attribute;

import org.ehrbase.aql.sql.binding.IJoinBinder;
import org.ehrbase.aql.sql.queryimpl.DefaultColumnId;
import org.ehrbase.aql.sql.queryimpl.IQueryImpl;
import org.ehrbase.aql.sql.queryimpl.attribute.composition.CompositionIdFieldSetup;
import org.ehrbase.aql.sql.queryimpl.attribute.ehr.EhrSetup;
import org.jooq.Field;

@SuppressWarnings({"java:S3740", "java:S1452"})
public abstract class RMObjectAttribute implements IRMObjectAttribute, IJoinBinder {

    protected FilterSetup filterSetup = new FilterSetup();
    protected CompositionIdFieldSetup compositionIdFieldSetup = new CompositionIdFieldSetup();
    protected EhrSetup ehrSetup = new EhrSetup();

    protected final JoinSetup joinSetup;
    protected final FieldResolutionContext fieldContext;

    protected RMObjectAttribute(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        this.fieldContext = fieldContext;
        this.joinSetup = joinSetup;
    }

    protected Field<?> as(Field field) {
        if (fieldContext.isWithAlias()) return aliased(field);
        else {
            if (!fieldContext.getClause().equals(IQueryImpl.Clause.WHERE)) return defaultAliased(field);
            else return field;
        }
    }

    protected Field<?> aliased(Field field) {
        return field.as(effectiveAlias());
    }

    protected String effectiveAlias() {
        return (fieldContext.getVariableDefinition().getAlias() == null)
                ? "/" + fieldContext.getColumnAlias()
                : fieldContext.getVariableDefinition().getAlias();
    }

    protected Field<?> defaultAliased(Field field) {
        if (fieldContext.getClause().equals(IQueryImpl.Clause.WHERE)) return field;
        else return field.as(DefaultColumnId.value(fieldContext.getVariableDefinition()));
    }
}
