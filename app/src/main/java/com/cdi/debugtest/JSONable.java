package com.cdi.debugtest;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by eharoldreyes on 10/3/16.
 */

public abstract class JSONable {

    protected static final String[] RESERVED = new String[]{"serialVersionUID", "RESERVED", "CREATOR"};

    public JSONable() {

    }

    public JSONable(JSONObject obj) {
        for(Field field : this.getClass().getDeclaredFields()) {
            if(Arrays.asList(RESERVED).contains(field.getName()))
                continue;
            if(field.isAccessible()) {
                try { setFieldFromJSON(field, obj); } catch (Exception e) { }
            } else {
                field.setAccessible(true);
                try { setFieldFromJSON(field, obj); } catch (Exception e) { }
                field.setAccessible(false);
            }
        }
    }


    private JSONObject toJSONObject() throws NoSuchFieldException, IllegalAccessException, JSONException {
        JSONObject obj = new JSONObject();
        for(Field field : this.getClass().getDeclaredFields()) {
            if(Arrays.asList(RESERVED).contains(field.getName()))
                continue;
            obj.put(field.getName(), getInstanceField(field.getName()));
        }
        return obj;
    }

    private Object getInstanceField(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(fieldName);
        Object data;
        if(field.isAccessible()){
            data = field.get(this);
        } else {
            field.setAccessible(true);
            data = field.get(this);
            field.setAccessible(false);
        }
        return data;
    }

    @Override
    public String toString() {
        try {
            return toJSONObject().toString();
        } catch (Exception e) {
            return super.toString();
        }
    }

    private void setFieldFromJSON(Field field, JSONObject obj) throws JSONException, IllegalAccessException {
        String key = field.getName();
        Class<?> type = field.getType();
        Object data = field.get(this);
        Log.d("type", type.toString());
        if (data instanceof String[]){
            Log.d(key, "is instance of string[]");
        } else if(type == String.class || data instanceof String){
            field.set(this, obj.getString(key));
        } else if(type == CharSequence.class || data instanceof CharSequence){
            field.set(this, obj.getString(key));
        } else if (type == Double.class || data instanceof Double) {
            field.set(this, obj.getDouble(key));
        } else if (type == Float.class || data instanceof Float) {
            field.set(this, Float.parseFloat(obj.getDouble(key) + ""));
        } else if (type == Integer.class || data instanceof Integer) {
            field.set(this, obj.getInt(key));
        } else if (type == Boolean.class || data instanceof Boolean) {
            field.set(this, obj.getBoolean(key));
        } else if (type == Long.class || data instanceof Long) {
            field.set(this, obj.getLong(key));
        }
    }
}
