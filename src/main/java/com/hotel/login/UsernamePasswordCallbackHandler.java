package main.java.com.hotel.login;

import javax.security.auth.callback.*;
import java.io.IOException;

//
// This class implements a username/password callback handler that gets
// information from the user
public class UsernamePasswordCallbackHandler implements CallbackHandler {
    //
    // The handle method does all the work and iterates through the array
    // of callbacks, examines the type, and takes the appropriate user
    // interaction action.

    private String password;
    private String username;

    public UsernamePasswordCallbackHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void handle(Callback[] callbacks) throws
            UnsupportedCallbackException, IOException {

        for (int i = 0; i < callbacks.length; i++) {
            Callback cb = callbacks[i];
            //
            // Handle username aquisition
            if (cb instanceof NameCallback) {
                NameCallback nameCallback = (NameCallback) cb;
                nameCallback.setName(username);
                username = null;
                //
                // Handle password aquisition
            } else if (cb instanceof PasswordCallback) {
                PasswordCallback passwordCallback = (PasswordCallback) cb;

                passwordCallback.setPassword(password.toCharArray());
                password = null;
                // Other callback types are not handled here
            } else {
                throw new UnsupportedCallbackException(cb, "Unsupported Callback Type");
            }
        }
    }
}
