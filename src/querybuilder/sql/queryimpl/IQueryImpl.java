
package org.ehrbase.aql.sql.queryimpl;

import org.ehrbase.aql.definition.I_VariableDefinition;

/**
 * Created by christian on 5/6/2016.
 */
@SuppressWarnings("java:S1452")
public interface IQueryImpl {

    enum Clause {
        SELECT,
        WHERE,
        ORDERBY,
        FROM
    }

    MultiFields makeField(String templateId, String identifier, I_VariableDefinition variableDefinition, Clause clause);

    MultiFields whereField(String templateId, String identifier, I_VariableDefinition variableDefinition);
}
