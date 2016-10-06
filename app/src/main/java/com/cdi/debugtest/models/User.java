package com.cdi.debugtest.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.cdi.debugtest.JSONable;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by eharoldreyes on 10/3/16.
 */

public class User extends JSONable implements Parcelable {

    private int id;
    private String email;
    private String password;
    private int age;
    private double lat;
    private double lng;
    private boolean isActive;
    private String[] roles;

    public User(){
        super();
    }

    public User(JSONObject jsonObject){
        super(jsonObject);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    protected User(Parcel in) {
        for(Field field : this.getClass().getDeclaredFields()) {
            if(Arrays.asList(RESERVED).contains(field.getName()))
                continue;

            if(field.isAccessible()){
                try { field.set(this, in.readValue(field.getType().getClassLoader())); } catch (Exception e) { }
            } else {
                field.setAccessible(true);
                try { field.set(this, in.readValue(field.getType().getClassLoader())); } catch (Exception e) { }
                field.setAccessible(false);
            }
        }
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        for(Field field : this.getClass().getDeclaredFields()) {
            if(Arrays.asList(RESERVED).contains(field.getName()))
                continue;
            if(field.isAccessible()){
                try { dest.writeValue(field.get(this));} catch (Exception e) { }
            } else {
                field.setAccessible(true);
                try { dest.writeValue(field.get(this));} catch (Exception e) { }
                field.setAccessible(false);
            }
        }
    }
}
