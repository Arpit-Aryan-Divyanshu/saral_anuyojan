
package org.ehrbase.aql.definition;

import org.ehrbase.aql.sql.queryimpl.IQueryImpl;
import org.jooq.*;

public class LateralJoinDefinition {

    private final Table<?> table;
    private final JoinType joinType;
    private final Condition condition;
    private IQueryImpl.Clause clause;
    private final String lateralVariable;
    private final String sqlExpression;

    public LateralJoinDefinition(
            String sqlExpression,
            Table<?> table,
            String lateralVariable,
            JoinType joinType,
            Condition condition,
            IQueryImpl.Clause clause) {
        this.sqlExpression = sqlExpression;
        this.table = table;
        this.lateralVariable = lateralVariable;
        this.joinType = joinType;
        this.condition = condition;
        this.clause = clause;
    }

    public Table<?> getTable() {
        return table;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public Condition getCondition() {
        return condition;
    }

    public IQueryImpl.Clause getClause() {
        return clause;
    }

    public String getLateralVariable() {
        return lateralVariable;
    }

    public void setClause(IQueryImpl.Clause clause) {
        this.clause = clause;
    }

    public String getSqlExpression() {
        return sqlExpression;
    }
}
