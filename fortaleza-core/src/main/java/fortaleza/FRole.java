package fortaleza;

import java.util.List;

public interface FRole {

    FRole getParentRole();

    String name();

    List<PermissionAction> getPermissions();

}
