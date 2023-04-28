package org.ehrbase.aql.containment;

import java.util.ArrayList;
import java.util.List;

/**
 * Container defining list of predicates with their associated operator (if any).
 */
public class Predicates {

    private ContainmentSet containmentSet;

    public static class Details {
        private String expression;
        private Containment containedIn;
        private ContainmentSet inSet;

        public Details(String expression, ContainmentSet inSet, Containment enclosing) {
            this.expression = expression;
            this.containedIn = enclosing;
            this.inSet = inSet;
        }

        public boolean isVoid() {
            return (getExpression() == null || getExpression().length() == 0);
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public Containment getContainedIn() {
            return containedIn;
        }

        public void setContainedIn(Containment containedIn) {
            this.containedIn = containedIn;
        }

        public ContainmentSet getInSet() {
            return inSet;
        }

        public void setInSet(ContainmentSet inSet) {
            this.inSet = inSet;
        }
    }

    public Predicates(ContainmentSet containmentSet) {
        this.containmentSet = containmentSet;
    }

    private final List<Details> intersectPredicates = new ArrayList<>();
    private final List<Details> exceptPredicates = new ArrayList<>();
    private final List<Details> unionPredicates = new ArrayList<>();
    private final List<Details> atomicPredicates = new ArrayList<>();

    public ContainmentSet getContainmentSet() {
        return containmentSet;
    }

    public List<Details> getIntersectPredicates() {
        return intersectPredicates;
    }

    public List<Details> getExceptPredicates() {
        return exceptPredicates;
    }

    public List<Details> getUnionPredicates() {
        return unionPredicates;
    }

    public List<Details> getAtomicPredicates() {
        return atomicPredicates;
    }
}
