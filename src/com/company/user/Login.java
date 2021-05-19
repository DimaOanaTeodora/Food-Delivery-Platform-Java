package com.company.user;

import java.util.*;

public class Login { //clasa singleton
    private Set <User> usersReg;
    private static Login single_instance = null;
    private User curentUser;

    private Login()
    {
        this.usersReg=new HashSet<User>();
        this.curentUser=null;
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

    public void setUsersReg(Set<User> usersReg) {
        this.usersReg = usersReg;
    }
}
