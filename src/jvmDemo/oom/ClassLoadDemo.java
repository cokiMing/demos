package jvmDemo.oom;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wuyiming on 2017/8/21.
 */
public class ClassLoadDemo {
    static class Parent{
        public static int A = 1;
        static {
            A = 2;
        }
    }

    static class Sub extends Parent{
        public static int B = A;
    }

    public static void main(String[] args){
        ClassLoader Myloader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try{
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null){
                        return super.loadClass(name);
                    }
                    byte[] buff = new byte[inputStream.available()];
                    inputStream.read(buff);
                    return defineClass(name,buff,0,buff.length);
                }catch (IOException ioe){
                    throw new ClassNotFoundException(name);
                }
            }
        };

        try{
            Object o;
//            o = Myloader.loadClass("jvmDemo.oom.ClassLoadDemo").newInstance();
            o = ClassLoadDemo.class.getClassLoader().loadClass("jvmDemo.oom.ClassLoadDemo").newInstance();

            System.out.println(o.getClass());
            System.out.println(o instanceof ClassLoadDemo);
        }catch (Exception e){

        }

    }
}
