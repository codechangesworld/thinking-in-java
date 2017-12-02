/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuogouqing.exercise.container.ref
 * @file References.java
 * @author zuoguoqing
 * @date 2017年11月24日
 * @version 
 */
package name.zuogouqing.exercise.container.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * 测试持有引用库：java.lang.ref.*
 * 
 * @author zuoguoqing
 *
 */
public class References {
    
    private static ReferenceQueue<VeryBig> queue = new ReferenceQueue<>();
    
    public static void checkQueue() {
        Reference<? extends VeryBig> reference = queue.poll();
        if (reference != null) {
            System.out.println("in queue: " + reference.get());
        }
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        int size = 10;
        
        LinkedList<SoftReference<VeryBig>> srList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            srList.add(new SoftReference<VeryBig>(new VeryBig("Soft: " + i), queue));
            System.out.println("just create: " + srList.getLast());
            checkQueue();
        }
        
        LinkedList<WeakReference<VeryBig>> wrList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            wrList.add(new WeakReference<VeryBig>(new VeryBig("Weak: " + i), queue));
            System.out.println("just create: " + wrList.getLast());
            checkQueue();
        }
        
        @SuppressWarnings("unused")
        SoftReference<VeryBig> sf = new SoftReference<VeryBig>(new VeryBig("Soft Single"));
        @SuppressWarnings("unused")
        WeakReference<VeryBig> wr = new WeakReference<VeryBig>(new VeryBig("Weak Single"));
        
        System.gc();
        
        LinkedList<PhantomReference<VeryBig>> prList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            prList.add(new PhantomReference<VeryBig>(new VeryBig("Phantom: " + i), queue));
            System.out.println("just create: " + prList.getLast());
            checkQueue();
        }
    }
    
    

}

class VeryBig {
    private static final int SIZE = 1_000_000;
    @SuppressWarnings("unused")
    private int[] la = new int[SIZE];
    private String ident;
    
    public VeryBig(String id) {
        ident = id;
    }
    
    public String toString() {
        return ident;
    }
    
    protected void finalize() {
//        System.out.println("finalizing " + ident);
    }
}
