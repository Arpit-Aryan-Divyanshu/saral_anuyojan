
package org.ehrbase.aql.sql.queryimpl.attribute;

import org.jooq.Field;
import org.jooq.TableField;

@SuppressWarnings({"java:S1452", "java:S3740"})
public interface IRMObjectAttribute {
    Field<?> sqlField();

    IRMObjectAttribute forTableField(TableField tableField);
}
