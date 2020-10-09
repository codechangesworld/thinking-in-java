/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.generics
 * @file CollectionMethodDifference.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 上午9:05:28
 * @version 1.0
 */
package name.zuoguoqing.generics;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 上午9:05:28
 */
public class CollectionMethodDifference {
    static Set<String> objectMethodSet = methodSet(Object.class);
    static {
        objectMethodSet.add("clone");
    }

    public static Set<String> methodSet(Class<?> cls) {
        return Arrays.stream(cls.getMethods()).map(Method::getName).collect(Collectors.toSet());
    }

    public static List<String> interfaceList(Class<?> cls) {
        return Arrays.stream(cls.getInterfaces()).map(Class::getSimpleName)
                .collect(Collectors.toList());
    }

    public static void difference(Class<?> subset, Class<?> supset) {
        System.out
                .println("interfaces of " + supset.getSimpleName() + " : " + interfaceList(supset));
        System.out.println(supset.getSimpleName() + " extends " + subset.getSimpleName()
                + " and add the following methods:");

        Set<String> temp = methodSet(supset);
        temp.removeAll(methodSet(subset));
        temp.removeAll(objectMethodSet);
        System.out.println(new TreeSet<>(temp));

        System.out.println("-------------------------------------------");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("interfaces of Collection : " + interfaceList(Collection.class));
        System.out.println("methods of Collection : " + new TreeSet<>(methodSet(Collection.class)));
        System.out.println("-------------------------------------------");

        difference(Collection.class, List.class);
        difference(List.class, ArrayList.class);
        difference(List.class, LinkedList.class);

        difference(Collection.class, Set.class);
        difference(Set.class, HashSet.class);
        difference(HashSet.class, LinkedHashSet.class);
        difference(Set.class, TreeSet.class);

        difference(Collection.class, Queue.class);
        difference(Queue.class, PriorityQueue.class);

        System.out.println("interfaces of Map : " + interfaceList(Map.class));
        System.out.println("methods of Map : " + new TreeSet<>(methodSet(Map.class)));
        System.out.println("-------------------------------------------");
        difference(Map.class, HashMap.class);
        difference(HashMap.class, LinkedHashMap.class);
        difference(Map.class, TreeMap.class);

    }

}
