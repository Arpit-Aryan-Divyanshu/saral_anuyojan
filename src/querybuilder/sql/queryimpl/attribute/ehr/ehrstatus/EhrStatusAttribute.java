
package org.ehrbase.aql.sql.queryimpl.attribute.ehr.ehrstatus;

import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.attribute.ehr.EhrAttribute;

public abstract class EhrStatusAttribute extends EhrAttribute {

    protected EhrStatusAttribute(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
        joinSetup.setJoinEhrStatus(true);
    }
}
