
package org.ehrbase.aql.sql.queryimpl;

import org.ehrbase.service.IntrospectService;

/**
 * Created by christian on 5/9/2018.
 */
public abstract class TemplateMetaData {

    protected IntrospectService introspectCache;

    protected TemplateMetaData(IntrospectService introspectCache) {
        this.introspectCache = introspectCache;
    }
}
