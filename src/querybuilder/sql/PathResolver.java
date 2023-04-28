
package org.ehrbase.aql.sql;

import static org.ehrbase.aql.sql.QueryProcessor.NIL_TEMPLATE;

import java.util.Set;
import java.util.TreeSet;
import org.ehrbase.aql.containment.Containment;
import org.ehrbase.aql.containment.IdentifierMapper;
import org.ehrbase.aql.containment.Templates;
import org.ehrbase.service.KnowledgeCacheService;

/**
 * Resolve the path corresponding to a symbol in a given context
 * NB. Path are resolved using WebTemplates
 */
public class PathResolver {

    private final IdentifierMapper mapper;
    private final KnowledgeCacheService knowledgeCache;

    public PathResolver(KnowledgeCacheService knowledgeCache, IdentifierMapper mapper) {
        this.knowledgeCache = knowledgeCache;
        this.mapper = mapper;
    }

    public Set<String> pathOf(String templateId, String identifier) {
        Set<String> result;

        if (!getMapper().hasPathExpression()
                && getMapper().getClassName(identifier).equals("COMPOSITION")) {
            // assemble a fake path for composition
            StringBuilder stringBuilder = new StringBuilder();
            Containment containment = (Containment) getMapper().getContainer(identifier);
            stringBuilder.append("/composition[");
            stringBuilder.append(containment.getArchetypeId());
            stringBuilder.append("]");
            result = new TreeSet<>();
            result.add(stringBuilder.toString());
        } else result = getMapper().getPath(templateId, identifier);

        return result;
    }

    public String entryRoot(String templateId) {
        Containment root = getMapper().getRootContainment();
        String result = null;
        if (!templateId.equals(NIL_TEMPLATE) && root != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("/composition[");
            if (root.getArchetypeId().isEmpty()) {
                // resolve the archetype node id according to the template
                stringBuilder.append(new Templates(knowledgeCache).rootArchetypeNodeId(templateId));
            } else stringBuilder.append(root.getArchetypeId());
            stringBuilder.append("]");
            result = stringBuilder.toString();
        }
        return result;
    }

    public boolean hasPathExpression() {
        return getMapper().hasPathExpression();
    }

    public String rootOf(String identifier) {
        return getMapper().getArchetypeId(identifier);
    }

    public String classNameOf(String identifier) {
        return getMapper().getClassName(identifier);
    }

    public IdentifierMapper getMapper() {
        return mapper;
    }
}
