
package org.ehrbase.aql.sql.queryimpl;

import java.util.Set;
import java.util.stream.Collectors;
import org.ehrbase.aql.sql.PathResolver;
import org.ehrbase.dao.access.interfaces.I_DomainAccess;
import org.jooq.DSLContext;

/**
 * Created by christian on 5/6/2016.
 */
public abstract class ObjectQuery {

    protected I_DomainAccess domainAccess;
    protected PathResolver pathResolver;

    protected static int serial = 0; // used to alias fields for now.

    protected ObjectQuery(I_DomainAccess domainAccess, PathResolver pathResolver) {
        this.domainAccess = domainAccess;

        this.pathResolver = pathResolver;
    }

    public static void reset() {
        serial = 0;
    }

    public static void inc() {
        serial++;
    }

    public static int getSerial() {
        return serial;
    }

    public String variableTemplatePath(String templateId, String identifier) {
        Set<String> pathSet = pathResolver.pathOf(templateId, identifier);

        if (pathSet == null || pathSet.isEmpty()) return null;

        return pathResolver.pathOf(templateId, identifier).stream().collect(Collectors.joining());
    }

    public DSLContext getContext() {
        return domainAccess.getContext();
    }
}
