
package org.ehrbase.aql.sql.queryimpl.attribute.ehr;

import org.ehrbase.aql.sql.queryimpl.IQueryImpl;
import org.ehrbase.aql.sql.queryimpl.attribute.FieldResolutionContext;
import org.ehrbase.aql.sql.queryimpl.attribute.JoinSetup;
import org.ehrbase.aql.sql.queryimpl.attribute.RMObjectAttribute;

public abstract class EhrAttribute extends RMObjectAttribute {

    protected EhrAttribute(FieldResolutionContext fieldContext, JoinSetup joinSetup) {
        super(fieldContext, joinSetup);
        joinSetup.setJoinEhr(true);
        if (fieldContext.getClause().equals(IQueryImpl.Clause.FROM)) filterSetup.setEhrIdFiltered(true);
    }
}
