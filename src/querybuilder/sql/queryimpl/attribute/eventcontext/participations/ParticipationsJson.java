
package org.ehrbase.aql.sql.queryimpl.attribute.eventcontext.participations;

import static org.ehrbase.jooq.pg.tables.EventContext.EVENT_CONTEXT;

import java.util.Optional;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.IRMObjectAttribute;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.attribute.eventcontext.EventContextAttribute;
import org.ehrbase.aql.sql.queryimpl.value_field.GenericJsonField;
import org.jooq.Field;
import org.jooq.TableField;

public class ParticipationsJson extends EventContextAttribute {

    protected Optional<String> jsonPath = Optional.empty();

    public ParticipationsJson(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
    }

    @Override
    public Field<?> sqlField() {
        fieldContext.setJsonDatablock(true);
        if (jsonPath.isPresent())
            return new GenericJsonField(fieldContext, joinSetup)
                    .forJsonPath(jsonPath.get())
                    .participations(EVENT_CONTEXT.ID);
        else return new GenericJsonField(fieldContext, joinSetup).participations(EVENT_CONTEXT.ID);
    }

    @Override
    public IRMObjectAttribute forTableField(TableField tableField) {
        return this;
    }

    public ParticipationsJson forJsonPath(String jsonPath) {
        if (jsonPath == null || jsonPath.isEmpty()) {
            this.jsonPath = Optional.empty();
            return this;
        }
        this.jsonPath = Optional.of(jsonPath);
        return this;
    }
}
