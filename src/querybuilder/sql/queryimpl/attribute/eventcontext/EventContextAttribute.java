
package org.ehrbase.aql.sql.queryimpl.attribute.eventcontext;

import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.attribute.RMObjectAttribute;

public abstract class EventContextAttribute extends RMObjectAttribute {

    protected EventContextAttribute(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
        joinSetup.setJoinEventContext(true);
    }
}
