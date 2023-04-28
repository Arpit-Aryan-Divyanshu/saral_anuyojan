
package org.ehrbase.aql.sql.queryimpl;

import org.ehrbase.aql.sql.binding.Iso8601Duration;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.postgresql.util.PGInterval;

public class DurationFormatter {

    public static void toISO8601(Result<Record> result) {
        if (result.isEmpty()) return;

        for (Record record : result) {

            for (Field field : record.fields()) {
                if (record.getValue(field) instanceof PGInterval) {
                    record.setValue(field, new Iso8601Duration((PGInterval) record.getValue(field)).toIsoString());
                }
            }
        }
    }
}
