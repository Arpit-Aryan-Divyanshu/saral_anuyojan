
package org.ehrbase.aql.sql.queryimpl;

@FunctionalInterface
public interface Function3<T, U, V, R> {

    R apply(T t, U u, V v);
}
