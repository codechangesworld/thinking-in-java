/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.io
 * @file SerializeObject.java
 * @author ZuoGuoqing
 * @date 2017年11月27日 下午11:39:17
 * @version 1.0
 */
package name.zuoguoqing.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import name.zuoguoqing.io.model.UserA;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2017年11月27日 下午11:39:17
 */
public class SerializeObject {

    /**
     * @param args
     * @throws IOException 
     * @throws FileNotFoundException 
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
//        serialize();
        
        unserialize();
    }
    
    public static void serialize() throws FileNotFoundException, IOException {
        ObjectOutput output = new ObjectOutputStream(new FileOutputStream("x.file"));
        output.writeObject(new UserA());
        output.close();
    }
    
    public static void unserialize() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInput input = new ObjectInputStream(new FileInputStream("x.file"));
        Object object = input.readObject();
        input.close();
        System.out.println(object.getClass());
    }

}
