package com.yxt.coolweather;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类，用于调用对象的指定方法
 */
public class ReflectionUtil {

    private static final String TAG = "ReflectionUtil"; // 日志标签

    /**
     * 调用对象的指定方法
     *
     * @param owner      方法所属的对象实例
     * @param methodName 方法名
     * @param b          方法参数，boolean类型
     * @return 方法的返回值，如果调用失败则返回null
     */
    public static Object invokeMethod(Object owner, String methodName, boolean b) {
        if (owner == null) {
            Log.e(TAG, methodName + " not invoked, owner is null"); // 记录错误日志：对象为空无法调用方法
            return null;
        }
        try {
            Class<?> ownerClass = owner.getClass(); // 获取对象的类
            Method method = ownerClass.getDeclaredMethod(methodName, boolean.class); // 获取指定方法
            method.setAccessible(true); // 设置方法为可访问
            return method.invoke(owner, b); // 调用方法
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            Log.e(TAG, methodName +
                    " not invoked, InvocationTargetException or NoSuchFieldException or IllegalAccessException: " +
                    e.getMessage()); // 记录错误日志：方法调用失败
        }
        return null;
    }
}
