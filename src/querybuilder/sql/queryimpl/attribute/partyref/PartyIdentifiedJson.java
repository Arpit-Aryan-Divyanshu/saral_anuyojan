
package org.ehrbase.aql.sql.queryimpl.attribute.partyref;

import static org.ehrbase.jooq.pg.Tables.PARTY_IDENTIFIED;

import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.IRMObjectAttribute;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.value_field.GenericJsonField;
import org.jooq.Field;
import org.jooq.TableField;

public class PartyIdentifiedJson extends GenericJsonField {

    PartyIdentifiedJson(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
    }

    @Override
    public Field<?> sqlField() {
        return super.canonicalPartyIdendified(joinSetup.getPartyJoinRef().field(PARTY_IDENTIFIED.ID));
    }

    @Override
    public IRMObjectAttribute forTableField(TableField tableField) {
        return this;
    }
}
