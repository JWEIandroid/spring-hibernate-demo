package com.example.springhibernatedemo.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.*;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class BeanMapperUtil {

    private static Logger logger = LogManager.getLogger(BeanMapperUtil.class);

    /**
     * 复制简单对象
     *
     * @param sourceObject    需要复制数据的源对象
     * @param destObjectclazz 复制数据到的目标对象类
     * @return
     */
    public static <T> T map(Object sourceObject, Class<T> destObjectclazz) {
        if (sourceObject == null) {
            return null;
        }

        T destObject = BeanUtils.instantiateClass(destObjectclazz);
        copyProperties(sourceObject, destObject, null, (String[]) null);
        return destObject;
    }

    /**
     * 深度对象克隆
     * <p>
     * 该方法将返回一个深度克隆后的对象结果。此对象与克隆源对象不为同一个内存地址
     *
     * @param sourceObject    克隆源
     * @param destObjectclazz 克隆对象类型
     * @param <T>
     * @return
     */
    public static <T> T clone(Object sourceObject, Class<T> destObjectclazz) {
        if (sourceObject == null) {
            return null;
        }
        if (!destObjectclazz.getName().equals(sourceObject.getClass().getName())) {
//            throw new InvalidClassException("源对象与目标对象的类名不一致");
            return null;
        }
        T destObject = BeanUtils.instantiateClass(destObjectclazz);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(sourceObject);
            //将流序列化成对象
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            destObject = (T) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.warn(e.getMessage(), e);
        }
        return destObject;
    }

    /**
     * 复制列表对象
     *
     * @param sourceList      需要复制数据的源对象列表
     * @param destObjectclazz 复制数据到的目标对象
     * @return
     */
    public static <T, S> List<T> mapList(Collection<S> sourceList, Class<T> destObjectclazz) {
        if (sourceList == null) {
            return null;
        }

        List<T> destList = new ArrayList<T>();
        for (Iterator<S> it = sourceList.iterator(); it.hasNext(); ) {
            destList.add(map(it.next(), destObjectclazz));
        }

        return destList;
    }

    /**
     * Copy the property values of the given source bean into the given target bean.
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     *
     * @param source           the source bean
     * @param target           the target bean
     * @param editable         the class (or interface) to restrict property setting to
     * @param ignoreProperties array of property names to ignore
     * @throws BeansException if the copying failed
     * @see BeanWrapper
     */
    private static void copyProperties(Object source, Object target, @Nullable Class<?> editable,
                                       @Nullable String... ignoreProperties) throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
                        "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null) {
                        boolean isSameObject = ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType());
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            if (isSameObject) {
                                writeMethod.invoke(target, value);
                            } else {
                                Object targetValue = null;
                                Class<?> targetClass = writeMethod.getParameterTypes()[0];
                                if (targetClass.isPrimitive()) {
                                    //  当为原始对象时
                                    if (targetClass.equals(byte.class)) {
                                        targetValue = 0;
                                    } else if (targetClass.equals(short.class)) {
                                        targetValue = 0;
                                    } else if (targetClass.equals(int.class)) {
                                        targetValue = 0;
                                    } else if (targetClass.equals(long.class)) {
                                        targetValue = 0;
                                    } else if (targetClass.equals(float.class)) {
                                        targetValue = 0.0f;
                                    } else if (targetClass.equals(double.class)) {
                                        targetValue = 0.0d;
                                    } else if (targetClass.equals(char.class)) {
                                        targetValue = '\u0000';
                                    } else if (targetClass.equals(boolean.class)) {
                                        targetValue = false;
                                    }
                                } else {
                                    Constructor<?>[] constructors = targetClass.getDeclaredConstructors();
                                    boolean existDefaultConstructor = false;
                                    boolean existConstructor = constructors != null && constructors.length > 0;
                                    if (existConstructor) {
                                        for (Constructor constructor : constructors) {
                                            if (constructor.getParameterCount() == 0) {
                                                existDefaultConstructor = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (existConstructor) {
                                        if (existDefaultConstructor) {
                                            targetValue = map(value, targetClass);
                                        } else {
                                            logger.trace("map object {}: targetClass [{}] has no default constructor. so use NULL value instead.", target, targetClass.getName());
                                        }
                                    } else {
                                        logger.trace("map object {}: targetClass [{}] not contain a constructor. so use break!", target, targetClass.getName());
                                        break;
                                    }
                                }
                                writeMethod.invoke(target, targetValue);
                            }
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 拷贝属性值到指定的对象，忽略null值的对象拷贝
     *
     * @param src    源对象
     * @param target 目标对象
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * 拷贝属性值到指定的对象
     *
     * @param src    源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target);
    }
}

