
package org.ehrbase.aql.definition;

import java.util.List;
import java.util.Set;
import org.jooq.DataType;

/**
 * Represents a AQL Select Variable

 */
public interface I_VariableDefinition extends Cloneable {
    String getPath();

    String getAlias();

    String getIdentifier();

    LateralJoinDefinition getLateralJoinDefinition(String templateId, int index);

    int getLateralJoinsSize(String templateId);

    boolean isLateralJoinsEmpty(String templateId);

    LateralJoinDefinition getLastLateralJoin(String templateId);

    void setLateralJoinTable(String templateId, LateralJoinDefinition lateralJoinDefinition);

    boolean isDistinct();

    boolean isFunction();

    boolean isExtension();

    boolean isHidden();

    List<FuncParameter> getFuncParameters();

    I_VariableDefinition duplicate();

    void setPath(String path); // used to modify the path in case of struct query (canonical json).

    void setDistinct(boolean distinct);

    void setHidden(boolean hidden);

    void setAlias(String alias);

    String toString();

    boolean isConstant();

    boolean isLateralJoin(String templateId);

    Set<LateralJoinDefinition> getLateralJoinDefinitions(String templateId);

    PredicateDefinition getPredicateDefinition();

    void setSubstituteFieldVariable(String variableAlias);

    String getSubstituteFieldVariable();

    void setSelectType(DataType castTypeAs);

    DataType getSelectType();

    boolean isVoidAlias();

    void setVoidAlias(boolean isVoidAlias);
}
