
package org.ehrbase.aql.sql.queryimpl;

public class QueryImplConstants {

    public static final String AQL_NODE_NAME_PREDICATE_MARKER = "$AQL_NODE_NAME_PREDICATE$";
    public static final String AQL_NODE_ITERATIVE_MARKER = "$AQL_NODE_ITERATIVE$";
    public static final String AQL_NODE_NAME_PREDICATE_FUNCTION = "ehr.aql_node_name_predicate";
    // we use an extended jsonb array elements function that returns a null jsonb object instead of an empty resultset
    // see  https://www.postgresql.org/docs/current/static/functions-json.html for more on usage
    public static final String AQL_NODE_ITERATIVE_FUNCTION = "ehr.xjsonb_array_elements";

    private QueryImplConstants() {}
}
