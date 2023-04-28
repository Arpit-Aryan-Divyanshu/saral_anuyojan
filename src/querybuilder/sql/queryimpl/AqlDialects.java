
package org.ehrbase.aql.sql.queryimpl;

import org.jooq.Configuration;

public class AqlDialects {

    protected AqlDialects() {}

    /**
     * Provisional to switch encoding depending on the supported dialect respectively for JSON
     */
    public static void isSupported(Configuration configuration) {
        // for future extensions
    }
}
