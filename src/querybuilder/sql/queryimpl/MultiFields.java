
package org.ehrbase.aql.sql.queryimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.ehrbase.aql.definition.I_VariableDefinition;
import org.jooq.Field;

public class MultiFields {

    private final List<QualifiedAqlField> fields = new ArrayList<>();
    private boolean useEntryTable = false;
    private String rootJsonKey;
    private final String templateId;
    private I_VariableDefinition variableDefinition; // the variable def for this list of qualified fields

    public MultiFields(I_VariableDefinition variableDefinition, List<QualifiedAqlField> fields, String templateId) {
        this.fields.addAll(fields);
        this.variableDefinition = variableDefinition;
        this.templateId = templateId;
    }

    public MultiFields(I_VariableDefinition variableDefinition, Field<?> field, String templateId) {
        this(variableDefinition, new QualifiedAqlField(field), templateId);
    }

    public static MultiFields asNull(
            I_VariableDefinition variableDefinition, String templateId, IQueryImpl.Clause clause) {
        String alias = variableDefinition.getAlias();

        if (clause.equals(IQueryImpl.Clause.WHERE)) alias = null;
        else {
            if (alias == null) alias = DefaultColumnId.value(variableDefinition);
        }

        Field<?> nullField = new NullField(variableDefinition, alias).instance();
        return new MultiFields(variableDefinition, nullField, templateId);
    }

    public MultiFields(I_VariableDefinition variableDefinition, QualifiedAqlField field, String templateId) {
        fields.add(field);
        this.variableDefinition = variableDefinition;
        this.templateId = templateId;
    }

    public void setUseEntryTable(boolean useEntryTable) {
        this.useEntryTable = useEntryTable;
    }

    public boolean isUseEntryTable() {
        return useEntryTable;
    }

    public int fieldsSize() {
        return fields.size();
    }

    private QualifiedAqlField getQualifiedField(int index) {
        return fields.get(index);
    }

    public Iterator<QualifiedAqlField> iterator() {
        return fields.iterator();
    }

    public QualifiedAqlField getLastQualifiedField() {
        if (fieldsSize() > 0) return fields.get(fieldsSize() - 1);
        else
            return new QualifiedAqlField(
                    new NullField(variableDefinition, DefaultColumnId.value(variableDefinition)).instance());
    }

    public QualifiedAqlField getQualifiedFieldOrLast(int index) {
        if (index >= fieldsSize()) return getLastQualifiedField();
        else return getQualifiedField(index);
    }

    public int size() {
        return fields.size();
    }

    public boolean isEmpty() {
        return fields.isEmpty();
    }

    public String getRootJsonKey() {
        return rootJsonKey;
    }

    public void setRootJsonKey(String rootJsonKey) {
        this.rootJsonKey = rootJsonKey;
    }

    public I_VariableDefinition getVariableDefinition() {
        return variableDefinition;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void replaceField(QualifiedAqlField originalField, Field newField) {
        int index = fields.indexOf(originalField);
        QualifiedAqlField originalAqlField = fields.get(index);
        QualifiedAqlField clonedQualifiedField = originalAqlField.duplicate();
        clonedQualifiedField.setField(newField);
        fields.set(fields.indexOf(originalField), clonedQualifiedField);
    }
}
