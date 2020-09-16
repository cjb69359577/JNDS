package com.jiuqi.dna.jnjs.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
 
public class PropertyUtil {
 
	
    private static final String SET_PREFIX = "set";
   // private static final String IS_PREFIX = "is";
    private static final String GET_PREFIX = "get";
    
    public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName) {//�������󣬶��� �Լ���get��set����
        Method setMethod = null;
        Method getMethod = null;
        PropertyDescriptor pd = null;
        try {
            Field field = clazz.getDeclaredField(propertyName);// �����ֶ�������ȡ�ֶ�
            if (field != null) {
                // ���������ĺ�׺
                String methodEnd = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                setMethod = clazz.getDeclaredMethod(SET_PREFIX + methodEnd, new Class[] { field.getType() });
                // ����get ����
                getMethod = clazz.getDeclaredMethod(GET_PREFIX + methodEnd, new Class[] {});
                // ����һ������������ �Ѷ�Ӧ���� propertyName �� get �� set �������浽������������
                pd = new PropertyDescriptor(propertyName, getMethod, setMethod);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
 
        return pd;
    }
    
    public static PropertyDescriptor getPropertyDescriptor2(Class<?> clazz, String propertyName) {//ʹ�� PropertyDescriptor �ṩ�� get��set����
        try {
            return new PropertyDescriptor(propertyName, clazz);
        } catch (IntrospectionException e) {
        }
        return null;
    }
 
    public static void setProperty(Object obj, String propertyName, Object value) {
        Class<?> clazz = obj.getClass();// ��ȡ���������
        propertyName = formatProperty(propertyName);//�ֶ��е��»��ߴ���
        PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);// ��ȡ clazz�����е�propertyName������������
        Method setMethod = pd.getWriteMethod();// �������������л�ȡ set ����
        try {
            setMethod.invoke(obj, new Object[] { value });// ���� set �����������valueֵ����������ȥ
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
 
    public static Object getProperty(Object obj, String propertyName) {
        Class<?> clazz = obj.getClass();// ��ȡ���������
        propertyName = formatProperty(propertyName);//�ֶ��е��»��ߴ���
        PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);// ��ȡclazz�����е�propertyName������������
        Method getMethod = pd.getReadMethod();// �������������л�ȡ get ����
        Object value = null;
        try {
            value = getMethod.invoke(obj, new Object[] {});// ���÷�����ȡ�����ķ���ֵ
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return value;// ����ֵ
    }
    
    public static String formatProperty(String propertyName) {
    	if (propertyName.contains("_")) {
    		String[] tmp = propertyName.split("_");
    		StringBuilder sb = new StringBuilder();
    		sb.append(tmp[0]);
    		for (int i = 1; i < tmp.length; i++) {
    			sb.append(tmp[i].substring(0, 1).toUpperCase()).append(tmp[i].substring(1));
			}
    		return sb.toString();
		}else {
			return propertyName;
		}
    }
    
    
}
