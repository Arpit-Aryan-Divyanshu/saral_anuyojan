
package org.ehrbase.aql.sql.queryimpl;

@FunctionalInterface
public interface Function2<T, U, R> {

    R apply(T t, U u);
}
