package main.java.com.hotel.login;

import java.security.BasicPermission;

//
// Implement a user defined permission for access to the personnel
// code for this example
public class PersonnelPermission extends BasicPermission {

    public PersonnelPermission(String name) {
        super(name);
    }

    public PersonnelPermission(String name, String action) {
        super(name);
    }
}
