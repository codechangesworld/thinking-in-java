/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuogouqing.exercise.container.ref
 * @file WeakRef.java
 * @author ZuoGuoqing
 * @date 2017年11月24日 下午5:40:53
 * @version 1.0
 */
package name.zuogouqing.exercise.container.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2017年11月24日 下午5:40:53
 */
public class WeakRef {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ReferenceQueue<VeryBig> queue = new ReferenceQueue<>();
        Map<String, WeakReference<VeryBig>> map = new WeakHashMap<>();

        Thread thread = new Thread(new ListenRefTask(map, queue));
        thread.setDaemon(true);
        thread.start();

        int size = 1000;
        for (int i = 0; i < size; i++) {
            VeryBig tmp = new VeryBig("WeakRef: " + i);
            String key = "WeakRef-" + i;
            map.put(key, new WeakReference<VeryBig>(tmp, queue));
        }
        System.out.println("map.size: " + map.size());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class ListenRefTask implements Runnable {
        Map<String, WeakReference<VeryBig>> map;
        ReferenceQueue<VeryBig> queue;

        public ListenRefTask(Map<String, WeakReference<VeryBig>> map,
                ReferenceQueue<VeryBig> queue) {
            super();
            this.map = map;
            this.queue = queue;
        }

        @Override
        public void run() {
            WeakReference<? extends VeryBig> ref;
            int count = 0;
            try {
                while ((ref = (WeakReference<? extends VeryBig>) queue.remove()) != null) {
                    System.out.println(++count + ": 回收了 ：" + ref);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
