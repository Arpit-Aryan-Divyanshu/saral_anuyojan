
package org.ehrbase.aql.sql.queryimpl;

/**
 * environment variable definitions. Generally not used as provided in application YAML profiles
 */
public class IterativeNodeConstants {

    private IterativeNodeConstants() {}

    // set the list of prefixes of nodes that are ignored when building the json_array expression (default is /content
    // and /events)
    public static final String ENV_AQL_ARRAY_IGNORE_NODE = "aql.ignoreIterativeNodeList";
    // set the depth of embedded arrays in json_array expression (default is 1)
    public static final String ENV_AQL_ARRAY_DEPTH = "aql.iterationScanDepth";
    // True force to use jsQuery
    public static final String ENV_AQL_USE_JSQUERY = "aql.useJsQuery";
}
