/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuogouqing.exercise.container.ref
 * @file MyWeakRef.java
 * @author ZuoGuoqing
 * @date 2017年11月24日 下午4:40:29
 * @version 1.0
 */
package name.zuogouqing.exercise.container.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @时间 2017年11月24日 下午4:40:29
 */
public class MyWeakRef extends WeakReference<VeryBig> {
    private Object key;
    
    public MyWeakRef(Object key, VeryBig referent, ReferenceQueue<VeryBig> q) {
        super(referent, q);
        this.setKey(key);
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

}
