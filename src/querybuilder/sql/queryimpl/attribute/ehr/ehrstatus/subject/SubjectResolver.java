
package org.ehrbase.aql.sql.queryimpl.attribute.ehr.ehrstatus.subject;

import static org.ehrbase.aql.sql.binding.JoinBinder.subjectRef;

import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.attribute.partyref.PartyResolver;
import org.jooq.Field;

public class SubjectResolver extends PartyResolver {

    public SubjectResolver(FieldResolutionContext fieldResolutionContext, JoinSetup joinSetup) {
        super(fieldResolutionContext, joinSetup);
    }

    @Override
    public Field<?> sqlField(String path) {
        joinSetup.setPartyJoinRef(subjectRef);
        joinSetup.setJoinSubject(true);

        return super.sqlField(path);
    }
}
