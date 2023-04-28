
package org.ehrbase.aql.definition;

import java.util.Iterator;
import org.ehrbase.aql.sql.binding.VariableDefinitions;


public class Variables {

    private VariableDefinitions variableDefinitions;

    public Variables(VariableDefinitions variableDefinitions) {
        this.variableDefinitions = variableDefinitions;
    }

    public boolean hasDefinedDistinct() {
        Iterator<I_VariableDefinition> iterator = variableDefinitions.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().isDistinct()) return true;
        }
        return false;
    }

    public boolean hasDefinedFunction() {
        Iterator<I_VariableDefinition> iterator = variableDefinitions.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().isFunction()) return true;
        }
        return false;
    }
}
