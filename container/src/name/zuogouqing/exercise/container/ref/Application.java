/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuogouqing.exercise.container.ref
 * @file Application.java
 * @author ZuoGuoqing
 * @date 2017年11月24日 下午4:43:02
 * @version 1.0
 */
package name.zuogouqing.exercise.container.ref;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2017年11月24日 下午4:43:02
 */
public class Application {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        int size = 1000;
        ReferenceQueue<VeryBig> queue = new ReferenceQueue<>();
        Map<Object, MyWeakRef> map = new HashMap<>();

        Thread thread = new Thread(new ListenRefTask(map, queue));
        thread.setDaemon(true);
        thread.start();
        
        for (int i = 0; i < size; i++) {
            VeryBig tmp = new VeryBig("MyWeakRef: " + i);
            String key = "MyWeakRef-" + i;
            map.put(key, new MyWeakRef(key, tmp, queue));
        }
        System.out.println("map.size: " + map.size());
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    
    static class ListenRefTask implements Runnable {
        Map<Object, MyWeakRef> map;
        ReferenceQueue<VeryBig> queue;
        
        public ListenRefTask(Map<Object, MyWeakRef> map, ReferenceQueue<VeryBig> queue) {
            super();
            this.map = map;
            this.queue = queue;
        }

        @Override
        public void run() {
            MyWeakRef ref;
            int count = 0;
            try {
                while ((ref = (MyWeakRef) queue.remove()) != null) {
                    System.out.println(++count + ": 回收了 ：" + ref.getKey());
                    Object key = ref.getKey();
                    map.remove(key);
                }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }

}
