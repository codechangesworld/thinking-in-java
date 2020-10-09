/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.reflect
 * @file ClassInfoTester.java
 * @author ZuoGuoqing
 * @date 2020年10月1日 上午11:54:20
 * @version 1.0
 */
package name.zuoguoqing.reflect;

import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

import name.zuoguoqing.reflect.InnerClass.Inner3;
import name.zuoguoqing.reflect.InnerClass.Inner4;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月1日 上午11:54:20
 */
public class ClassInfoTester {

    private void show(String method, String result) {
        if (result.startsWith("[") && result.endsWith("]") && result.length() > 50) {
            result = result.substring(1, result.length() - 1)
                    .replaceAll(",", "\n                              |    ")
                    .replaceAll("\\w+\\.", "");
        }

        System.out.format("%-30s|     %s\n", method, result);
    }

    public void test(Class<?> cls) throws Exception {
        System.out.println(
                "*************************************************************************");
        System.out.format("%-30s|     %-20s\n", "Method Name", "Result");

        // ********* others **********
        System.out.println(
                "-------------------------------------------------------------------------------");
        show("getModifiers", Modifier.toString(cls.getModifiers()));
        show("getPackage", String.valueOf(cls.getPackage()));

        // ********* class name **********
        System.out.println(
                "-------------------------------------------------------------------------------");
        show("getName", cls.getName());
        show("getSimpleName", cls.getSimpleName());
        show("getCanonicalName", cls.getCanonicalName());
        show("getTypeName", String.valueOf(cls.getTypeName()));

        // ********* class type **********
        System.out.println(
                "-------------------------------------------------------------------------------");
        show("isAnnotation", String.valueOf(cls.isAnnotation()));
        show("isAnonymousClass", String.valueOf(cls.isAnonymousClass()));
        show("isArray", String.valueOf(cls.isArray()));
        show("isEnum", String.valueOf(cls.isEnum()));
        show("isInterface", String.valueOf(cls.isInterface()));
        show("isLocalClass", String.valueOf(cls.isLocalClass()));
        show("isMemberClass", String.valueOf(cls.isMemberClass()));
        show("isPrimitive", String.valueOf(cls.isPrimitive()));
        show("isSynthetic", String.valueOf(cls.isSynthetic()));

        // ********* annotation **********
        System.out.println(
                "-------------------------------------------------------------------------------");
        show("getAnnotatedInterfaces", Arrays.toString(cls.getAnnotatedInterfaces()));
        show("getAnnotatedSuperclass", String.valueOf(cls.getAnnotatedSuperclass()));
        show("getAnnotations", Arrays.toString(cls.getAnnotations()));

        // ********* class constructor **********
        System.out.println(
                "-------------------------------------------------------------------------------");
        show("getConstructors", Arrays.toString(cls.getConstructors()));
        show("getDeclaredConstructors", Arrays.toString(cls.getDeclaredConstructors()));
        show("getEnclosingConstructor", String.valueOf(cls.getEnclosingConstructor()));

        // ********* class field **********
        System.out.println(
                "-------------------------------------------------------------------------------");
        show("getFields", Arrays.toString(cls.getFields()));
        show("getDeclaredFields", Arrays.toString(cls.getDeclaredFields()));

        // ********* class method **********
        System.out.println(
                "-------------------------------------------------------------------------------");
        show("getMethods", Arrays.toString(cls.getMethods()));
        show("getDeclaredMethods", Arrays.toString(cls.getDeclaredMethods()));
        show("getEnclosingMethod", String.valueOf(cls.getEnclosingMethod()));

        // ********* about class **********
        System.out.println(
                "-------------------------------------------------------------------------------");
        show("getClasses", Arrays.toString(cls.getClasses()));
        show("getDeclaredClasses", Arrays.toString(cls.getDeclaredClasses()));
        show("getEnclosingClass", String.valueOf(cls.getEnclosingClass()));

        // ********* others **********
        System.out.println(
                "-------------------------------------------------------------------------------");
        show("getInterfaces", Arrays.toString(cls.getInterfaces()));
        show("getGenericInterfaces", Arrays.toString(cls.getGenericInterfaces()));
        show("getSuperclass", String.valueOf(cls.getSuperclass()));
        show("getGenericSuperclass", String.valueOf(cls.getGenericSuperclass()));
        show("getSigners", Arrays.toString(cls.getSigners()));
        show("getTypeParameters", Arrays.toString(cls.getTypeParameters()));
    }

    /**
     * some examples to run test
     * 
     * @throws Exception
     */
    public void run() throws Exception {
        test(Null.class);
        test(NormalInterface.class);
        test(ExtendInterface.class);
        test(AbstractBaseClass.class);
        test(NormalClass.class);
        test(ExtendClass.class);
        test(InnerClass.class);
        test(Inner3.class);
        test(Inner4.class);
        test(int.class);
        test(new int[] { 1, 2, 3 }.getClass());
        test(new String[] { "he", "she", "it" }.getClass());
        test(GenericClass.class);
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        PrintStream stream = System.out;
        System.setOut(new PrintStream("ClassInfoTest.txt"));
        new ClassInfoTester().run();
        System.setOut(stream);
        System.out.println("Complete!");
    }

}

interface Null {
}

interface NormalInterface {
    int id = 1001;
    String name = "Normal Interface";

    int getId();

    String getName();

    static String staticMethod() {
        return "this is a static method";
    }

    default String defaultMethod() {
        return "this is a default method";
    }
}

interface ExtendInterface extends Null, NormalInterface {
    String name = "Extend Interface";

    void extendInterfaceMethod();
}

abstract class AbstractBaseClass {
    private String baseName = "AbstractBaseClass";
    protected String rootPath = "a root path";

    public String getBaseInfo() {
        return baseName;
    }

    public abstract AbstractBaseClass get();
}

class NormalClass {
    private String firstname;
    private String lastname;
    private int age;
    private float score;

    public NormalClass() {
        this("", "", 0, 0);
    }

    public NormalClass(String firstname, String lastname) {
        this(firstname, lastname, 0, 0);
    }

    public NormalClass(String firstname, String lastname, int age, float score) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.score = score;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

}

class ExtendClass extends NormalClass implements NormalInterface {

    @SuppressWarnings("unused")
    private double money;

    public ExtendClass() {
        this(.0);
    }

    public ExtendClass(double money) {
        super();
        this.money = money;
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    public String show() {
        return composeInfo();
    }

    private String composeInfo() {

        return new StringBuilder().append(id).append(";").append(getFirstname()).append(" ")
                .append(getLastname()).append(";").append(getScore()).toString();
    }

}

class InnerClass {
    @SuppressWarnings("unused")
    private String name = "Inner class";

    private class Inner1 {
        private int i = 1;
        private int j = 2;
        
        public void donothing() {
            class LocalClass {
                int a = 1;
                
                int getA() {
                    return a;
                }
            }
            new LocalClass().getA();
        }
        
    }

    protected class Inner2 {
    }

    static class Inner3 {
        void f() {}
        int  g(int i, int g) {
          return  i + g;
        }
    }

    public class Inner4 {
        public Runnable runnable() {
            return new Runnable() {

                @Override
                public void run() {
                    new Inner1().donothing();
                }
            };
        }

        public int cal() {
            Inner1 inner1 = new Inner1();
            return inner1.i + inner1.j;
        }
    }

    public int innerCal() {
        return new Inner4().cal();
    }
}

interface Nothing<T> {
    T doNothing();
}

class GenericClass<T, E, A> extends AbstractCollection<E> implements Nothing<A>, Consumer<T>{
    private T type;
    private E ele;

    public GenericClass(T type, E ele) {
        super();
        this.type = type;
        this.ele = ele;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

    public E get() {
        return ele;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public A doNothing() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void accept(T t) {
        // TODO Auto-generated method stub
        
    }
}
