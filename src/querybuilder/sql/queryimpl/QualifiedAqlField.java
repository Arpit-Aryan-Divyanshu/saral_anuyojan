
package org.ehrbase.aql.sql.queryimpl;

import org.jooq.Field;

public class QualifiedAqlField {

    protected String itemType = null; // the actual data type as specified in the template
    protected String itemCategory =
            null; // the category of the object as per the WebTemplate (f.e. CLUSTER, ELEMENT ...)
    private Field<?> field; // the actual field

    public QualifiedAqlField(Field<?> field) {
        this.field = field;
    }

    public QualifiedAqlField(Field<?> field, String itemType, String itemCategory) {
        this.field = field;
        this.itemType = itemType;
        this.itemCategory = itemCategory;
    }

    public Field<?> getSQLField() {
        return field;
    }

    public void setField(Field<?> field) {
        this.field = field;
    }

    public QualifiedAqlField duplicate() {
        return new QualifiedAqlField(this.getSQLField(), this.itemType, this.itemCategory);
    }

    public boolean isQualified() {
        return itemType != null && itemCategory != null;
    }
}
