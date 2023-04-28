
package org.ehrbase.aql.sql.queryimpl.attribute.partyref;

import static org.ehrbase.jooq.pg.Tables.PARTY_IDENTIFIED;

import org.ehrbase.aql.sql.queryimpl.attribute.AttributeResolver;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.jooq.Field;

@SuppressWarnings("java:S1452")
public class PartyResolver extends AttributeResolver {

    public PartyResolver(FieldResolutionContext fieldResolutionContext, JoinSetup joinSetup) {
        super(fieldResolutionContext, joinSetup);
    }

    public Field<?> sqlField(String path) {

        switch (path) {
            case "name/value":
            case "name":
                return new SimplePartyRefAttribute(fieldResolutionContext, joinSetup)
                        .forTableField(PARTY_IDENTIFIED.NAME)
                        .sqlField();
            case "external_ref/type":
                return new SimplePartyRefAttribute(fieldResolutionContext, joinSetup)
                        .forTableField(PARTY_IDENTIFIED.PARTY_REF_TYPE)
                        .sqlField();
            case "external_ref/namespace":
                return new SimplePartyRefAttribute(fieldResolutionContext, joinSetup)
                        .forTableField(PARTY_IDENTIFIED.PARTY_REF_NAMESPACE)
                        .sqlField();
            case "external_ref/scheme":
                return new SimplePartyRefAttribute(fieldResolutionContext, joinSetup)
                        .forTableField(PARTY_IDENTIFIED.PARTY_REF_SCHEME)
                        .sqlField();
            case "external_ref/id":
                return new PartyRefJson(fieldResolutionContext, joinSetup)
                        .forJsonPath("id")
                        .sqlField();
            case "external_ref/id/value":
                return new PartyRefJson(fieldResolutionContext, joinSetup)
                        .forJsonPath("id/value")
                        .sqlField();
            case "external_ref":
                return new PartyRefJson(fieldResolutionContext, joinSetup).sqlField();
            case "":
                return new PartyIdentifiedJson(fieldResolutionContext, joinSetup).sqlField();
            case "identifiers":
                return new PartyIdentifiedJson(fieldResolutionContext, joinSetup)
                        .forJsonPath(path)
                        .sqlField();
            default:
                throw new IllegalArgumentException("Unresolved party_identified external_ref attribute path:" + path);
        }
    }
}
