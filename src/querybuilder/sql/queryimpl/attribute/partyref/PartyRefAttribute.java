
package org.ehrbase.aql.sql.queryimpl.attribute.partyref;

import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.attribute.RMObjectAttribute;

public abstract class PartyRefAttribute extends RMObjectAttribute {

    protected PartyRefAttribute(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
    }
}
