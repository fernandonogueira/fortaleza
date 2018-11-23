package fortaleza;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface PermissionProvider {

    List<PermissionAction> getPermissionsByAuthorities(Collection<? extends GrantedAuthority> authorities);

}
