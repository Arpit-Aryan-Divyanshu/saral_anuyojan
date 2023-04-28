
package org.ehrbase.aql.sql.queryimpl;

import java.util.List;
import org.ehrbase.service.IntrospectService;
import org.jooq.DataType;

public class DataTypeFromTemplate {

    private final IntrospectService introspectCache;
    private String itemType;
    private String itemCategory;
    private DataType identifiedType = null;
    private final boolean ignoreUnresolvedIntrospect;
    private final IQueryImpl.Clause clause;

    public DataTypeFromTemplate(
            IntrospectService introspectCache, boolean ignoreUnresolvedIntrospect, IQueryImpl.Clause clause) {
        this.introspectCache = introspectCache;
        this.ignoreUnresolvedIntrospect = ignoreUnresolvedIntrospect;
        this.clause = clause;
    }

    public void evaluate(String templateId, List<String> referenceItemPathArray) {
        // type casting from introspected data value type
        try {
            if (introspectCache == null) throw new IllegalArgumentException("MetaDataCache is not initialized");
            String reducedItemPathArray = new SegmentedPath(referenceItemPathArray).reduce();
            if (reducedItemPathArray != null && !reducedItemPathArray.isEmpty()) {
                ItemInfo info = introspectCache.getInfo(templateId, reducedItemPathArray);

                itemType = info.getItemType();
                itemCategory = info.getItemCategory();
                if (itemType != null) {
                    identifiedType = new PGType(referenceItemPathArray, clause).forRmType(itemType);
                } else {
                    identifiedType = new PGType(referenceItemPathArray, clause).forRmType("UNKNOWN");
                }
            }
        } catch (Exception e) {
            if (!ignoreUnresolvedIntrospect)
                throw new IllegalArgumentException(
                        "Unresolved type, missing template?(" + templateId + "), reason:" + e);
        }
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public DataType getIdentifiedType() {
        return identifiedType;
    }
}
