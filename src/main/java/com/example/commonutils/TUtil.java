package com.example.commonutils;

import java.lang.reflect.ParameterizedType;

/**
 * 类转换初始化
 *
 * 基于反射+泛型的类转换类，拿到最外层的<>里的参数数组然后根据索引获取实例的工具类
 */
public class TUtil {
  public static <T> T getT(Object o, int i) {
    try {
      return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

}