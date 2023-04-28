
package org.ehrbase.aql.sql.queryimpl.attribute.partyref;

import static org.ehrbase.jooq.pg.Tables.PARTY_IDENTIFIED;

import java.util.Optional;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.IRMObjectAttribute;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.value_field.GenericJsonField;
import org.jooq.Field;
import org.jooq.TableField;

public class PartyRefJson extends PartyRefAttribute {

    protected Optional<String> jsonPath = Optional.empty();

    public PartyRefJson(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
    }

    @Override
    public Field<?> sqlField() {
        // query the json representation of EVENT_CONTEXT and cast the result as TEXT
        if (jsonPath.isPresent()) {
            return new GenericJsonField(fieldContext, joinSetup)
                    .forJsonPath(jsonPath.get())
                    .partyRef(
                            joinSetup.getPartyJoinRef().field(PARTY_IDENTIFIED.PARTY_REF_VALUE),
                            joinSetup.getPartyJoinRef().field(PARTY_IDENTIFIED.PARTY_REF_SCHEME),
                            joinSetup.getPartyJoinRef().field(PARTY_IDENTIFIED.PARTY_REF_NAMESPACE),
                            joinSetup.getPartyJoinRef().field(PARTY_IDENTIFIED.PARTY_REF_TYPE));
        } else
            return new GenericJsonField(fieldContext, joinSetup)
                    .partyRef(
                            joinSetup.getPartyJoinRef().field(PARTY_IDENTIFIED.PARTY_REF_VALUE),
                            joinSetup.getPartyJoinRef().field(PARTY_IDENTIFIED.PARTY_REF_SCHEME),
                            joinSetup.getPartyJoinRef().field(PARTY_IDENTIFIED.PARTY_REF_NAMESPACE),
                            joinSetup.getPartyJoinRef().field(PARTY_IDENTIFIED.PARTY_REF_TYPE));
    }

    @Override
    public IRMObjectAttribute forTableField(TableField tableField) {
        return this;
    }

    public PartyRefJson forJsonPath(String jsonPath) {
        if (jsonPath == null || jsonPath.isEmpty()) {
            this.jsonPath = Optional.empty();
            return this;
        }
        this.jsonPath = Optional.of(jsonPath);
        return this;
    }
}
