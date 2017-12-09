package ru.enovikow.otus;

//import com.sun.org.apache.bcel.internal.util.ClassPath;

import ru.enovikow.otus.annotations.After;
import ru.enovikow.otus.annotations.Before;
import ru.enovikow.otus.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

class MainTest {

    static void start(Class clazz) throws Exception {
        if (haveMultiplyBeforeAfterIllegalAnnotations(clazz)) {
            throw new Exception("IllegalAnnotationsState");
        } else {
            initTests(clazz);
        }
    }

    private static void initTests(Class clazz) throws Exception {

        Object instance = ReflectionHelper.instantiate(clazz);
        Method[] methods = clazz.getDeclaredMethods();
        Method methodWithAfter = null, methodWithBefore = null;
        List<Method> methodTestList = new ArrayList();

        for (Method method : methods) {
            if (haveBeforeAnnotation(method)) {
                methodWithBefore = method;
            }
            if (haveAfterAnnotation(method)) {
                methodWithAfter = method;
            }
            if (haveTestAnnotation(method)) {
                methodTestList.add(method);
            }
        }

        for (Method m : methodTestList) {
            if (methodWithBefore != null) {
                methodWithBefore.invoke(instance);
            }

            m.invoke(instance);

            if (methodWithAfter != null) {
                methodWithAfter.invoke(instance);
            }
        }
    }

    private static boolean haveMultiplyBeforeAfterIllegalAnnotations(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        int beforeAnnotationCount = 0;
        int afterAnnotationCount = 0;
        int testAnnotation = 0;

        for (Method method : methods) {
            if (haveBeforeAnnotation(method)) {
                beforeAnnotationCount++;
            }
            if (haveAfterAnnotation(method)) {
                afterAnnotationCount++;
            }
            if (haveTestAnnotation(method)) {
                testAnnotation++;
            }
        }

        return beforeAnnotationCount > 1 || afterAnnotationCount > 1 || testAnnotation == 0;
    }

    private static boolean haveBeforeAnnotation(Method method) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Before.class)) {
                return true;
            }
        }
        return false;
    }

    private static boolean haveAfterAnnotation(Method method) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(After.class)) {
                return true;
            }
        }
        return false;
    }

    private static boolean haveTestAnnotation(Method method) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Test.class)) {
                return true;
            }
        }
        return false;
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
            if (haveTestAnnotation(m)) {
                return true;
            }
        }
        return false;
    }
}
