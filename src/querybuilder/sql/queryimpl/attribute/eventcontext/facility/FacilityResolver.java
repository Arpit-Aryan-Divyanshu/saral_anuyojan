
package org.ehrbase.aql.sql.queryimpl.attribute.eventcontext.facility;

import static org.ehrbase.aql.sql.binding.JoinBinder.facilityRef;

import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.attribute.partyref.PartyResolver;
import org.jooq.Field;

public class FacilityResolver extends PartyResolver {

    public FacilityResolver(FieldResolutionContext fieldResolutionContext, JoinSetup joinSetup) {
        super(fieldResolutionContext, joinSetup);
    }

    @Override
    public Field<?> sqlField(String path) {

        joinSetup.setJoinContextFacility(true);
        joinSetup.setPartyJoinRef(facilityRef);

        return super.sqlField(path);
    }
}
