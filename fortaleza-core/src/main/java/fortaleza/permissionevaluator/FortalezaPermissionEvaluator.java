package fortaleza.permissionevaluator;

import fortaleza.model.SecuredContent;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class FortalezaPermissionEvaluator implements PermissionEvaluator {

    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        if (targetDomainObject instanceof SecuredContent) {
            Class realClass = ((SecuredContent) targetDomainObject).getClassType();
            return hasPrivilege(authentication.getAuthorities(), realClass.getSimpleName(), (String) permission);
        } else {
            return hasPrivilege(authentication.getAuthorities(), targetDomainObject.getClass().getSimpleName(), (String) permission);
        }

    }

    private boolean hasPrivilege(Collection<? extends GrantedAuthority> authorities, String simpleName, String permission) {
        String privilegeName = (simpleName + "_" + permission + "_PRIVILEGE").toUpperCase();

        for (GrantedAuthority grantedAuth : authorities) {
            if (grantedAuth.getAuthority().equals(privilegeName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return hasPrivilege(authentication.getAuthorities(), targetType, (String) permission);
    }

}
