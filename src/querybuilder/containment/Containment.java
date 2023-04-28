package org.ehrbase.aql.containment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Container for containment definition<p>
 * This class is used to resolve a symbol in an AQL expression by associating its corresponding archetype Id,
 * class type (EHR, COMPOSITION etc.). Once resolved it holds the path for a given template.
 * </p>
 */
public class Containment implements Serializable {

    private String symbol;
    private String archetypeId;
    private String className;
    private Map<String, Set<String>> path =
            new HashMap<>(); // path is identified by a templateId and the relative aql path within

    public Containment(String className, String symbol, String archetypeId) {
        this.setSymbol(symbol);
        this.archetypeId = archetypeOnly(archetypeId);
        this.className = className;
    }

    public void setPath(String template, Set<String> path) {
        this.path.put(template, path);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getArchetypeId() {
        return archetypeId;
    }

    public Set<String> getPath(String templateId) {
        return path.get(templateId);
    }

    public String getClassName() {
        return className;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(className == null ? "" : className);
        sb.append("::");
        sb.append(getSymbol() == null ? "" : getSymbol());
        sb.append("::");
        sb.append(archetypeId == null ? "" : archetypeId);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Containment that = (Containment) o;

        if (getSymbol() != null ? !getSymbol().equals(that.getSymbol()) : that.getSymbol() != null) return false;
        if (archetypeId != null ? !archetypeId.equals(that.archetypeId) : that.archetypeId != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = getSymbol() != null ? getSymbol().hashCode() : 0;
        result = 31 * result + (archetypeId != null ? archetypeId.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    private String archetypeOnly(String archetypeId) {
        if (archetypeId != null && archetypeId.length() > 0 && archetypeId.contains("["))
            return archetypeId.substring(0, archetypeId.indexOf(']')).substring(1);
        else return archetypeId;
    }
}
