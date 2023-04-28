package org.ehrbase.aql.compiler;

/**
 * Representation of the TOP parameter
 */
public class TopAttributes {
    Integer window;
    TopDirection direction;

    public TopAttributes(Integer window, TopDirection direction) {
        this.window = window;
        this.direction = direction;
    }

    public Integer getWindow() {
        return window;
    }

    public TopDirection getDirection() {
        return direction;
    }

    public enum TopDirection {
        BACKWARD,
        FORWARD
    }
}
