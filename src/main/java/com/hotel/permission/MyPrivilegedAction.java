package main.java.com.hotel.permission;

import main.java.com.hotel.login.PrincipalImpl;
import main.java.com.hotel.model.Permission;

import javax.security.auth.Subject;
import java.security.AccessControlContext;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Set;

/**
 * Created by Admin on 28/06/2016.
 */
public class MyPrivilegedAction implements PrivilegedAction {

    private String info;
    private int permission;

    public MyPrivilegedAction(String info, int permission) {
        this.info = info;
        this.permission = permission;
    }

    @Override
    public Object run() {
        // Get the passed in subject from the DoAs
        AccessControlContext context = AccessController.getContext();
        Subject subject = Subject.getSubject(context);
        if (subject == null) {
            throw new AccessControlException("Denied");
        }
        Set<Permission> permissions = ((PrincipalImpl) subject.getPrincipals().iterator().next()).getUser().getUsertype().getPermissions();


        Permission permission = permissions
                .stream()
                .filter(e -> e.getId().getInformation().equalsIgnoreCase(info))
                .findAny()
                .orElse(null);

        if (permission != null) {
            if (permission.hasRight(this.permission)) {
                return new Integer(0);
            }
        }

        throw new AccessControlException("Denied");
    }
}
