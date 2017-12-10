package ru.enovikow.otus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import ru.enovikow.otus.annotations.After;
import ru.enovikow.otus.annotations.Before;
import ru.enovikow.otus.annotations.Test;

class MainTest {

    static void start(Class clazz) throws Exception {
        initTests(ReflectionHelper.instantiate(clazz));
    }

    private static void initTests(Object instance) throws Exception {
        Method[] methods = instance.getClass().getDeclaredMethods();
        Method methodWithBefore = null, methodWithAfter = null;
        List<Method> methodTestList = new ArrayList();

        int beforeAnnotationCounter = 0;
        int afterAnnotationCounter = 0;
        int testAnnotationCounter = 0;

        for (Method method : methods) {

            if (ReflectionHelper.getAnnotation(method).annotationType().equals(Before.class)) {
                methodWithBefore = method;
                beforeAnnotationCounter++;
            }
            if (ReflectionHelper.getAnnotation(method).annotationType().equals(After.class)) {
                methodWithAfter = method;
                afterAnnotationCounter++;
            }
            if (ReflectionHelper.getAnnotation(method).annotationType().equals(Test.class)) {
                methodTestList.add(method);
                testAnnotationCounter++;
            }
        }

        if (beforeAnnotationCounter > 1 || afterAnnotationCounter > 1 || testAnnotationCounter == 0) {
            throw new Exception("IllegalAnnotationsState");
        }

        for (Method method : methodTestList) {

            if (methodWithBefore != null) {
                methodWithBefore.invoke(instance);
            }

            method.invoke(instance);

            if (methodWithAfter != null) {
                methodWithAfter.invoke(instance);
            }
        }
    }

    public static void start(String packageName) throws Exception {
        Set<String> classNamesSet = getClassNamesFromPackage(packageName);
        Set<Class> classList = new HashSet<>();

        for (String className : classNamesSet) {
            classList.add(Class.forName(className));
        }

        int classesWithTestAnnotationCounter = 0;

        for (Class clazz : classList) {
            if (classHaveMethodWithTestAnnotation(clazz)) {
                start(clazz);
                classesWithTestAnnotationCounter++;
            }
        }

        if (classesWithTestAnnotationCounter == 0) {
            throw new Exception("No methods at this package have an annotation @Test");
        }
    }

    private static Set<String> getClassNamesFromPackage(String packageName) throws Exception {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ClassPath currentClassPath = ClassPath.from(loader);
        ImmutableSet<ClassPath.ClassInfo> classInfoSet = currentClassPath.getTopLevelClasses(packageName);
        Set<String> setOfClassNames = new HashSet<>();
        for (ClassPath.ClassInfo classInfo : classInfoSet) {
            setOfClassNames.add(classInfo.getName());
        }
        return setOfClassNames;
    }

    private static boolean classHaveMethodWithTestAnnotation(Class className) {
        Method[] methods = className.getDeclaredMethods();
        for (Method m : methods) {
            if (ReflectionHelper.haveBeforeOrTestOrAfterAnnotation(m, Test.class)) {
                return true;
            }
        }
        return false;
    }
}
