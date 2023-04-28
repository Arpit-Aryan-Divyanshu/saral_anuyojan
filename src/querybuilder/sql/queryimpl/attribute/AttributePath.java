
package org.ehrbase.aql.sql.queryimpl.attribute;

public class AttributePath {

    String rootPath;

    public AttributePath(String rootPath) {
        this.rootPath = rootPath;
    }

    /**
     * return the path without the root part
     * @return
     */
    public String redux(String path) {

        if (path.equals(rootPath)) return ""; // empty non null path to resolve (partial path for canonical result)

        return path.substring(rootPath.length() + 1); // skip trailing '/'
    }
}
