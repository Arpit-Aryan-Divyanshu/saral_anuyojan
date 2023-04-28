
package org.ehrbase.aql.sql.queryimpl.attribute;

import org.jooq.TableField;

@SuppressWarnings("java:S3740")
public abstract class AttributeResolver implements RMAttributeResolver {

    protected static final TableField NULL_FIELD = null;

    protected final JoinSetup joinSetup;
    protected final FieldResolutionContext fieldResolutionContext;

    protected AttributeResolver(FieldResolutionContext fieldResolutionContext, JoinSetup joinSetup) {
        this.fieldResolutionContext = fieldResolutionContext;
        this.joinSetup = joinSetup;
    }
}
