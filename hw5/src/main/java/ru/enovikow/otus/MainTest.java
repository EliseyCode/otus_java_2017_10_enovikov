package ru.enovikow.otus;

import ru.enovikow.otus.annotations.After;
import ru.enovikow.otus.annotations.Before;
import ru.enovikow.otus.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class MainTest {

    private static Map<Method, Object> methodBefore;
    private static Map<Method, Object> methodTest;
    private static Map<Method, Object> methodAfter;

    private static final String OK = "OK";
    private static final String FAILED = "FAILED";

    static void start(Class clazz) {
        methodBefore = new HashMap<>();
        methodTest = new HashMap<>();
        methodAfter = new HashMap<>();

        Set<Method> currentMethods = ReflectionHelper.getMethods(clazz);

        for (Method method : currentMethods) {
            setInstance(method);
        }

        currentTestRun(methodBefore);
        currentTestRun(methodTest);
        currentTestRun(methodAfter);
    }

    private static void setInstance(Method method) {
        Annotation[] annotations = ReflectionHelper.getAnnotations(method);

        for (Annotation annotation : annotations) {
            if (annotation instanceof Before) {
                methodBefore.put(method, ((Before) annotation).testResult());
            } else if (annotation instanceof Test) {
                methodTest.put(method, ((Test) annotation).testResult());
            } else if (annotation instanceof After) {
                methodAfter.put(method, ((After) annotation).testResult());
            }
        }
    }

    private static void currentTestRun(Map<Method, Object> methods) {
        Object object = ReflectionHelper.instantiate(TestClass.class);

        for (Method methodName : methods.keySet()) {
            if (isEqual(ReflectionHelper.callMethod(object, methodName.getName()), methods.get(methodName))) {
                printResult(methodName, OK);
            } else {
                printResult(methodName, FAILED);
            }
        }
    }

    private static boolean isEqual(Object src, Object refl) {
        return src != null && src.equals(refl);
    }

    private static void printResult(Method method, String status) {
        System.out.format("Test %s in %s in method %s\n", status, method.getDeclaringClass(), method.getName());
    }
}
