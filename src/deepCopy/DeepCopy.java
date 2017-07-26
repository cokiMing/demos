package deepCopy;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;

/**
 * Created by wuyiming on 2017/7/25.
 */
public class DeepCopy {

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
                        try{
                            String paramName = name.substring(3);
                            Object value = method.invoke(t);
                            if (value != null){
                                value = deepCopy(value);
                            }
                            paramMap.put(paramName,value);
                        }catch (Exception e){
                            throw new Exception("get method invalid!");
                        }
                    }
                }

                T result = (T)paramType.newInstance();
                for (Method method : methods){
                    String name = method.getName();
                    if (name.startsWith("set")){
                        try{
                            String paramName = name.substring(3);
                            Object value = paramMap.get(paramName);
                            method.invoke(result,value);
                        }catch (Exception e){
                            throw new  Exception("set method invalid!");
                        }
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

    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        GpsInfo gpsInfo = new GpsInfo();
        gpsInfo.setAltitude(100.2);
        gpsInfo.setLat(31.1);
        gpsInfo.setLng(121.3);
        gpsInfo.setPeoples(list);

        Address address1 = new Address();
        address1.setAddressName("hangzhou");
        address1.setId(12345678);
        address1.setGpsInfo(gpsInfo);
        Address address2 = new Address();
        address2.setAddressName("shanghai");
        address2.setId(98765432);
        address2.setGpsInfo(gpsInfo);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);

        String[] num = {"1","2","3"};
        int[] intNums = {1,2,3};
        Entity entity = new Entity();
        entity.setName("xiaoming");
        entity.setAge(18);
        entity.setAddressList(addressList);
        entity.setNums(num);
        entity.setIntNums(intNums);
        try{
            long millis = System.currentTimeMillis();
            Entity another = null;
            for (int i=0;i<100;i++){
                another = deepCopy(entity);
            }
            System.out.println("深拷贝耗时："+(System.currentTimeMillis() - millis));
            System.out.println(entity);
            System.out.println(another);
            System.out.println();
        }catch (Exception e){
            e.printStackTrace();
        }
//
//        try{
//            List<String> strings = solveType(list);
//            System.out.println(list);
//            System.out.println(strings);
//            System.out.println();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
