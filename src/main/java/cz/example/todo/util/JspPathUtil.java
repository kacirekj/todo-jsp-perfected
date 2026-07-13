package cz.example.todo.util;

import cz.example.todo.model.TodoPageSubmit;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.function.Function;

public class JspPathUtil {

    public static <T0, T1> String toPath(SerializableFunction<T0, ?> getter0, int index, SerializableFunction<T1, ?> getter1) {
        return nameOf(getter0) + array(index) + dot() + nameOf(getter1);
    }

    public interface SerializableFunction<I, O> extends Function<I, O>, Serializable {
        // Combined interface for Function and Serializable
    }

    private static <T> String nameOf(SerializableFunction<T, ?> getter) {
        var findMethod = ReflectionUtils.findMethod(getter.getClass(), "writeReplace");
        findMethod.setAccessible(true);

        var invokeMethod = (SerializedLambda) ReflectionUtils.invokeMethod(findMethod, getter);

        var methodName = invokeMethod.getImplMethodName();
        var attributeName = methodName.replaceFirst("get", "");
        var firstChar = attributeName.substring(0, 1);

        return attributeName.replaceFirst(firstChar, firstChar.toLowerCase());
    }

    private static String dot() {
        return ".";
    }

    private static String array(int index) {
        return "[" + index + "]";
    }
}
