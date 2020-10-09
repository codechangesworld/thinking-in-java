/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.generics
 * @file Sets.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 上午8:08:45
 * @version 1.0
 */
package name.zuoguoqing.generics;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 上午8:08:45
 */
public class Sets {
    /**
     * 合集
     * @param set1
     * @param set2
     * @return
     */
    public static <E> Set<E> union(Set<E> set1, Set<E> set2) {
        Set<E> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }
    
    /**
     * 交集
     * @param set1
     * @param set2
     * @return
     */
    public static <E> Set<E> intersection(Set<E> set1, Set<E> set2) {
        Set<E> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }
    
    /**
     * 差异
     * @param sup
     * @param sub
     * @return
     */
    public static <E> Set<E> difference(Set<E> sup, Set<E> sub) {
        Set<E> restult = new HashSet<>(sup);
        restult.removeAll(sub);
        return restult;
    }
    
    /**
     * 合集中除去重复元素
     * @param set1
     * @param set2
     * @return
     */
    public static <E> Set<E> complement(Set<E> set1, Set<E> set2) {
        return difference(union(set1, set2), intersection(set1, set2));
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        class Bob {
            private int id;
            private String name;
            public Bob(int id, String name) {
                this.id = id;
                this.name = name;
            }
            
            @SuppressWarnings("unused")
            public void setId(int id) {
                this.id = id;
            }
            
            @SuppressWarnings("unused")
            public void setName(String name) {
                this.name = name;
            }
            
            @Override
            public String toString() {
                return "id : " + id + ", name : " + name;
            }
        }
        
        Bob b1 = new Bob(1, "bob1");
        Bob b2 = new Bob(2, "bob2");

        Set<Bob> set1 = new HashSet<>();
        set1.add(b1);
        set1.add(b2);
        Set<Bob> set2 = new HashSet<>();
        set2.add(b1);
        set2.add(new Bob(3, "bob3"));
        
        System.out.println(set1 + "\n" + set2);
        
        System.out.println(union(set1, set2));
        System.out.println(intersection(set1, set2));
        System.out.println(difference(set2, set1));
        System.out.println(complement(set1, set2));
    }

}
