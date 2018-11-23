package fortaleza.permissionevaluator;

import org.springframework.security.core.Authentication;

public interface FortalezaPrivilegeEvaluator {

    boolean hasPrivilege(Authentication auth, String targetType, String permission);
}
