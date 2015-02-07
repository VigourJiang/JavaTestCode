package org.vigour.java8;

import com.sun.istack.internal.NotNull;
import org.apache.commons.mail.Email;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Jiang Fuqiang<br>
 * Date: 2015-2-7<br>
 * Type Annotations are annotations that can be placed anywhere you
 * use a type. This includes the new operator, type casts, implements
 * clauses and throws clauses. Type Annotations allow improved
 * analysis of Java code and can ensure even stronger type checking.
 *
 */
public class TypeAnnotationUseCase {
    private @NotNull String str = "DSD";

    public void foo(){
        List list = new @Readonly @NonEmpty ArrayList<String>();
    }

}
