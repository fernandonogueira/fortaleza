package fortaleza;

import fortaleza.exception.WrongUserDetailsType;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FortalezaPermissionEvaluator implements PermissionEvaluator {

    private final PermissionProvider permissionProvider;

    public FortalezaPermissionEvaluator(PermissionProvider permissionProvider) {
        this.permissionProvider = permissionProvider;
    }

    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        if (targetDomainObject instanceof FOrganization<?>) {
            Serializable organizationId = ((FOrganization) targetDomainObject).getOId();

            FortalezaUserDetails principal = ensureUserDetailsType(authentication.getPrincipal());
//            principal.getOrganizations()

        } else if (isPermissionEvaluatedType(targetDomainObject)) {
            return false;
        } else {
            String className = targetDomainObject.getClass().getName();
            List<PermissionAction> permissionActions = getPermissionActionsByEntityTypeAndId(authentication, className, null);
            return hasPermissionByActionList(permissionActions, className, permission);
        }

        return false;
    }

    private boolean isPermissionEvaluatedType(Object targetDomainObject) {
        return false;
    }

    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        List<PermissionAction> permissionActions = getPermissionActionsByEntityTypeAndId(authentication, targetType, targetId);
        return hasPermissionByActionList(permissionActions, targetType, permission);
    }

    private boolean hasPermissionByActionList(List<PermissionAction> permissionActions, String targetType, Object permission) {

        if (targetType == null || targetType.isEmpty()) {
            return false;
        }

        if (permissionActions == null || permissionActions.isEmpty()) {
            return false;
        }

        for (PermissionAction permissionAction : permissionActions) {
            if (permissionAction.getEntityClass().toUpperCase().equals(targetType.toUpperCase())) {
                if (permission instanceof String) {
                    if (permissionAction.getType().name().equals(((String) permission).toUpperCase())) {
                        return true;
                    }
                } else {
                    if (permissionAction.getType().name().equals(permission)) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    private List<PermissionAction> getPermissionActionsByEntityTypeAndId(Authentication authentication, String targetType, Serializable targetId) {
        Object details = authentication.getPrincipal();
        if (details == null) {
            return Collections.emptyList();
        }
        FortalezaUserDetails fDetails = ensureUserDetailsType(details);

        Collection<? extends GrantedAuthority> authorities = fDetails.getAuthorities();

        return getPermissionsByAuthorities(authorities);
    }

    private List<PermissionAction> getPermissionsByAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return permissionProvider.getPermissionsByAuthorities(authorities);
    }

    private FortalezaUserDetails ensureUserDetailsType(Object principal) {

        if (principal instanceof FortalezaUserDetails) {
            return (FortalezaUserDetails) principal;
        }
        throw new WrongUserDetailsType();
    }

}
