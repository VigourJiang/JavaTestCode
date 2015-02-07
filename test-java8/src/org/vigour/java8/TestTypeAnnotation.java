package org.vigour.java8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * User: Jiang Fuqiang
 * Date: 2015-2-7
 */
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface TestTypeAnnotation {
}