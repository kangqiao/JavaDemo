package com.zp.demo.serializable;

import java.io.*;

public class Human implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private transient String password;

    public Human(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        inputStream.defaultReadObject();
        password = (String) inputStream.readObject();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
        outputStream.writeObject(password);
    }

    @Override
    public String toString() {
        return "Human{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception{
        Human human = new Human("huhx", "liuli");
        System.out.println("before: \n" + human);

        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(buff);
        objectOutputStream.writeObject(human);

        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buff.toByteArray()));
        Human human2 = (Human) objectInputStream.readObject();
        System.out.println("after: \n" + human2);
    }
}
