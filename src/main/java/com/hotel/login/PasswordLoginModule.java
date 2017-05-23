package main.java.com.hotel.login;


import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Utilisateur;
import main.java.com.hotel.modeldao.DAOFactory;
import main.java.com.hotel.modeldao.UtilisateurDAO;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

//
// This is a JAAS Login Module that requires both a username and a password.
// The username must equal the hardcoded "joeuser" and the password
// must match the hardcoded "joeuserpw".
public class PasswordLoginModule implements LoginModule {

    private Subject subject;
    private Principal principal;
    private CallbackHandler callbackHandler;
    private String username;
    private Utilisateur utilisateur;
    private char[] password;
    private boolean loginSuccess;
    private Map options;

    //
    // Initialize sets up the login module.  sharedState and options are
    // advanced features not used here
    public void initialize(Subject sub, CallbackHandler cbh,
                           Map sharedState, Map options) {

        subject = sub;
        callbackHandler = cbh;
        loginSuccess = false;
        username = null;
        utilisateur = new Utilisateur();
        this.options = options;

        clearPassword();
    }

    //
    // The login phase gets the userid and password from the user and
    // compares them to the hardcoded values "joeuser" and "joeuserpw".
    public boolean login() throws LoginException {
        //
        // Since we need input from a user, we need a callback handler
        if (callbackHandler == null) {
            throw new LoginException("No CallbackHandler defined");
        }
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Username");
        callbacks[1] = new PasswordCallback("Password", false);
        //
        // Call the callback handler to get the username and password
        try {

            callbackHandler.handle(callbacks);
            username = ((NameCallback) callbacks[0]).getName();
            char[] temp = ((PasswordCallback) callbacks[1]).getPassword();
            password = new char[temp.length];
            System.arraycopy(temp, 0, password, 0, temp.length);
            ((PasswordCallback) callbacks[1]).clearPassword();
        } catch (IOException ioe) {
            throw new LoginException(ioe.toString());
        } catch (UnsupportedCallbackException uce) {
            throw new LoginException(uce.toString());
        }
        //
        // If username matches, go on to check password
        UtilisateurDAO utilisateurDAO = (UtilisateurDAO) DAOFactory.getDAO(StringRessources.USER);
        if (utilisateurDAO.isExist(username)) {
            utilisateur = utilisateurDAO.isValidPassword(username, password);
            if (utilisateur != null && utilisateur.getIdUser() != 0) {
                //If userid and password match, then login is a success
                loginSuccess = true;
                clearPassword();
                return true;
            } else {
            }
        } else {
        }
        //
        // If either mismatch, then this login module fails
        loginSuccess = false;
        username = null;
        clearPassword();
        throw new FailedLoginException();
    }

    //
    // The commit phase adds the principal if both the overall authentication
    // succeeds (which is why commit was called) as well as this particular
    // login module
    public boolean commit() throws LoginException {
        //
        // Check to see if this login module succeeded
        if (loginSuccess == false) {
            return false;
        }
        // If this login module succeded too, then add the new principal
        // to the subject (if it does not already exist)

        principal = new PrincipalImpl(utilisateur);
        if (!(subject.getPrincipals().contains(principal))) {
            subject.getPrincipals().add(principal);
        }
        utilisateur = null;
        username = null;
        return true;
    }

    //
    // The abort phase is called if the overall authentication fails, so
    // we have to cleanup the internal state
    public boolean abort() throws LoginException {

        if (loginSuccess == false) {
            principal = null;
            username = null;
            utilisateur = null;
            return false;
        }
        logout();
        return true;
    }

    //
    // The logout phase cleans up the state
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(principal);
        loginSuccess = false;
        username = null;
        principal = null;
        utilisateur = null;
        return true;
    }

    //
    // Private helper function to clear the password, a good programming
    // practice
    private void clearPassword() {

        if (password == null) {
            return;
        }
        for (int i = 0; i < password.length; i++) {
            password[i] = ' ';
        }
        password = null;
    }
}
