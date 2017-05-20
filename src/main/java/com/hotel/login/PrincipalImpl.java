package main.java.com.hotel.login;

import main.java.com.hotel.model.Utilisateur;

import java.io.Serializable;
import java.security.Principal;

//
// This class defines the principle object, which is just an encapsulated
// String name
public class PrincipalImpl implements Principal, Serializable {

    private Utilisateur user;

    public PrincipalImpl(Utilisateur utilisateur) {
        user = utilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrincipalImpl principal = (PrincipalImpl) o;

        return user != null ? user.equals(principal.user) : principal.user == null;
    }

    @Override
    public int hashCode() {
        return user != null ? user.hashCode() : 0;
    }

    public String getName() {
        return user.getUsername();
    }



    public String toString() {
        return getName();
    }

}
