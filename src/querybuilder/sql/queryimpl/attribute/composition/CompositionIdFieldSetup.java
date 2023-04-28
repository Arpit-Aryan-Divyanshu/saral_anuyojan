
package org.ehrbase.aql.sql.queryimpl.attribute.composition;

/**
 * maintain the condition of a query containing the composition id
 */
public class CompositionIdFieldSetup {

    private boolean compositionIdField = false;

    public boolean isCompositionIdField() {
        return compositionIdField;
    }

    public void setCompositionIdField(boolean compositionIdField) {
        this.compositionIdField = compositionIdField;
    }
}
