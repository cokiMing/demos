package test;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuyiming on 2017/7/25.
 */
public class CopyUtil {

    /**
     * 深拷贝
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T deepCopy(T t){
        try{
            Class<?> paramType = t.getClass();
            String paramTypeName = paramType.getName();
            if (paramType.isArray()){
                Class<?> componentType = paramType.getComponentType();
                Object result = Array.newInstance(componentType, Array.getLength(t));
                if (componentType.isPrimitive()){
                    return t;
                }

                for (int i=0;i<Array.getLength(t);i++){
                    Object object = Array.get(t, i);
                    Object cast = componentType.cast(object);
                    cast = deepCopy(cast);
                    Array.set(result,i,cast);
                }
                return (T)result;
            }
            if (!paramTypeName.startsWith("java") && !paramType.isPrimitive()){
                Class<?> superclass = paramType.getSuperclass();
                Method[] superMethods = {};
                if (!superclass.getName().startsWith("java")){
                    superMethods = superclass.getDeclaredMethods();
                }
                Method[] declaredMethods = paramType.getDeclaredMethods();
                Method[] methods = new Method[superMethods.length+declaredMethods.length];
                System.arraycopy(declaredMethods,0,methods,0,declaredMethods.length);
                System.arraycopy(superMethods,0,methods,declaredMethods.length,superMethods.length);

                Map<String,Object> paramMap = new HashMap<>();

                for (Method method : methods){
                    String name = method.getName();
                    if (name.startsWith("get")){
                        String paramName = name.substring(3);
                        Object value = method.invoke(t);
                        if (value != null){
                            value = deepCopy(value);
                        }
                        paramMap.put(paramName,value);
                    }
                }

                T result = (T)paramType.newInstance();
                for (Method method : methods){
                    String name = method.getName();
                    if (name.startsWith("set")){
                        String paramName = name.substring(3);
                        Object value = paramMap.get(paramName);
                        method.invoke(result,value);
                    }
                }
                return result;
            }

            if (t instanceof Collection){
                T list = (T)paramType.newInstance();
                for (Object object: (Collection)t){
                    object = deepCopy(object);
                    ((Collection)list).add(object);
                }
                return list;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return t;
    }
}
