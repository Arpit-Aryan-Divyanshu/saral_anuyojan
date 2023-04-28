
package org.ehrbase.aql.sql.queryimpl.attribute.ehr;

/**
 * maintain context for EHR attribute and querying
 */
public class EhrSetup {

    private boolean containsEhrId = false;
    private String ehrIdAlias;

    public boolean isContainsEhrId() {
        return containsEhrId;
    }

    public void setContainsEhrId(boolean containsEhrId) {
        this.containsEhrId = containsEhrId;
    }

    public String getEhrIdAlias() {
        return ehrIdAlias;
    }

    public void setEhrIdAlias(String ehrIdAlias) {
        this.ehrIdAlias = ehrIdAlias;
    }
}
