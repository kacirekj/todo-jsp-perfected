package cz.example.todo.util;

import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.function.Function;

public class JspPathUtil {

    // Method contract variants

    public static <T0> String toPath(SerializableFunction<T0, ?> getter0) {
        return attributeOf(getter0);
    }

    public static <T0> String toPath(SerializableFunction<T0, ?> getter0, int index) {
        return attributeOf(getter0) + array(index);
    }

    public static <T0, T1> String toPath(SerializableFunction<T0, ?> getter0, int index, SerializableFunction<T1, ?> getter1) {
        return attributeOf(getter0) + array(index) + dot() + attributeOf(getter1);
    }

    // Utils

    public interface SerializableFunction<I, O> extends Function<I, O>, Serializable {}

    private static <T> String attributeOf(SerializableFunction<T, ?> getter) {
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
