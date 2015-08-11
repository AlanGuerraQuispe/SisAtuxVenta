package com.aw.core.domain;

/**
 * Created by IntelliJ IDEA.
 * User: Julio C. Macavilca
 * Date: 22/06/2009
 * Time: 02:37:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AWExcepcionInterpreter {
    Throwable handle(Throwable e);
}
