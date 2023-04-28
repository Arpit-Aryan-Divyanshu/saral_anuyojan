
package org.ehrbase.aql.sql.queryimpl.attribute.composer;

import static org.ehrbase.aql.sql.binding.JoinBinder.composerRef;

import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.attribute.partyref.PartyResolver;
import org.jooq.Field;

public class ComposerResolver extends PartyResolver {

    public ComposerResolver(FieldResolutionContext fieldResolutionContext, JoinSetup joinSetup) {
        super(fieldResolutionContext, joinSetup);
    }

    @Override
    public Field<?> sqlField(String path) {

        joinSetup.setJoinComposer(true);
        joinSetup.setPartyJoinRef(composerRef);

        return super.sqlField(path);
    }
}
