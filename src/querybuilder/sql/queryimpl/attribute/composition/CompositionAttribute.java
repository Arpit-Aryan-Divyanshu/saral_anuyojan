
package org.ehrbase.aql.sql.queryimpl.attribute.composition;

import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.attribute.RMObjectAttribute;

public abstract class CompositionAttribute extends RMObjectAttribute {

    protected CompositionAttribute(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
        joinSetup.setJoinComposition(true);
        compositionIdFieldSetup.setCompositionIdField(false);
    }
}
