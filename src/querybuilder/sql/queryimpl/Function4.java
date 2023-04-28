
package org.ehrbase.aql.sql.queryimpl;

@FunctionalInterface
public interface Function4<T, U, V, W, R> {

    R apply(T t, U u, V v, W w);
}
