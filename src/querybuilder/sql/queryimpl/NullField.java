
package org.ehrbase.aql.sql.queryimpl;

import static org.ehrbase.aql.sql.queryimpl.JsonbEntryQuery.MAGNITUDE;

import org.ehrbase.aql.definition.I_VariableDefinition;
import org.jooq.Field;
import org.jooq.impl.DSL;

public class NullField {

    private final I_VariableDefinition variableDefinition;
    private final String alias;

    public NullField(I_VariableDefinition variableDefinition, String alias) {
        this.variableDefinition = variableDefinition;
        this.alias = alias;
    }

    public Field<?> instance() {
        // return a null field
        String cast = "";
        // force explicit type cast for DvQuantity
        if (variableDefinition != null
                && variableDefinition.getPath() != null
                && variableDefinition.getPath().endsWith(MAGNITUDE)) cast = "::numeric";

        if (variableDefinition != null && alias != null)
            return DSL.field(DSL.val((String) null) + cast).as(alias);
        else return DSL.field(DSL.val((String) null) + cast);
    }
}
