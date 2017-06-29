package AnnotationDemo;

import java.lang.reflect.Method;

/**
 * Created by wuyiming on 2017/6/23.
 */
public class Main {
    public static void main(String args[]){
        try{
            Class<Test> testClass = Test.class;
            ObjectAnno objectAnno = testClass.getAnnotation(ObjectAnno.class);
            String id = objectAnno.id();
            String description = objectAnno.description();
            System.out.println(id);
            System.out.println(description);

            Method declaredMethod = testClass.getDeclaredMethod("test",null);
            MethodAnno methodAnno = declaredMethod.getAnnotation(MethodAnno.class);
            String key = methodAnno.key();
            String value = methodAnno.value();
            System.out.println(key);
            System.out.println(value);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

@ObjectAnno(id="2017062201",description = "description")
class Test{

    @MethodAnno(key = "key",value = "value")
    public void test(){
        System.out.println("MethodAnnotation test!");
    }
}
