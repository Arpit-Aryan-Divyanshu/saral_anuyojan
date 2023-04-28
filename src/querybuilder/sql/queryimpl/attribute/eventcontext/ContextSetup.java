
package org.ehrbase.aql.sql.queryimpl.attribute.eventcontext;

/**
 * maintain context for EHR attribute and querying
 */
public class ContextSetup {
    private boolean containsOtherContext = false;

    public boolean isContainsOtherContext() {
        return containsOtherContext;
    }

    public void setContainsOtherContext(boolean containsOtherContext) {
        this.containsOtherContext = containsOtherContext;
    }
}
