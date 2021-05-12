package com.zp.demo.basic.serializable;


import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class User implements Externalizable {
    private String user;

    public String getUser() {
        return user;
    }

    public int getAge() {
        return age;
    }

    private int age;

    public User() {
        System.out.println("user constructor.");
    }

    public User(String user, int age) {
        System.out.println("user constructor two.");
        this.user = user;
        this.age = age;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("read external.");
        user = (String) in.readObject();
        age = in.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("write external.");
        out.writeObject(user);
        out.writeInt(age);
    }
}