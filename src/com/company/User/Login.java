package com.company.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Login { //clasa singleton
    private Set <User> usersReg;
    private static Login single_instance = null;
    private User curentUser;

    private Login()
    {

        this.usersReg=new HashSet<User>();

        //setare date admin
        String name="Admin";
        String email="admin@gmail.com";
        String phone="9999999999";
        String password="Admin01";
        User admin=new User(name, email, phone, password);
        this.curentUser=null;
        this.usersReg.add(admin);

        //citire useri din csv
        try (BufferedReader buffer = new BufferedReader(new
                FileReader("C:\\Users\\Lenovo\\Desktop\\Food-Delivery-Platform-Java-First-Phase\\Proiect PAO\\src\\com\\company\\Users.csv"))) {
            String line = buffer.readLine();
            while (line != null) {
                String [] array=line.split(",");
                User user=new User(array[0], array[1], array[2], array[3]);
                this.usersReg.add(user);
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Login getInstance()
    {
        if (single_instance == null)
            single_instance = new Login();

        return single_instance;
    }
    //conectare
    public boolean signIn(String email, String password){
        if(usersReg!=null) {
            for (User it : usersReg)
                if (email.equals(it.getEmail()) && password.equals(it.getPassword())) {
                    this.curentUser = it;
                    return true;
                }

        }
        return false;
    }
    //inscriere
    public boolean signUp(User u){
        if(usersReg!=null) {
            for (User it : usersReg)
                if ((u.getEmail()).equals(it.getEmail()) && (u.getPassword()).equals(it.getPassword()))
                    return false;
        }

        this.usersReg.add(u);
        return true;
    }

    public User getCurentUser() {
        return curentUser;
    }

    public void setCurentUser(User curentUser) {
        this.curentUser = curentUser;
    }
}
