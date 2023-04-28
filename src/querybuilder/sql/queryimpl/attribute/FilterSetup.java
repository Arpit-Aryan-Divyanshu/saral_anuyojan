
package org.ehrbase.aql.sql.queryimpl.attribute;

/**
 * maintain the state of specific filter to apply in WHERE clause
 */
public class FilterSetup {
    private boolean ehrIdFiltered = false; // true if the query specifies the ehr id (in the AQL FROM clause)
    private boolean compositionIdFiltered =
            false; // true if the query contains a where clause with composition id specified

    public boolean isEhrIdFiltered() {
        return ehrIdFiltered;
    }

    public void setEhrIdFiltered(boolean ehrIdFiltered) {
        this.ehrIdFiltered = ehrIdFiltered;
    }

    public boolean isCompositionIdFiltered() {
        return compositionIdFiltered;
    }

    public void setCompositionIdFiltered(boolean compositionIdFiltered) {
        this.compositionIdFiltered = compositionIdFiltered;
    }
}
