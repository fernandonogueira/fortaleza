package fortaleza.typeevaluator;

import fortaleza.FOrganization;
import fortaleza.PermissionAction;

import java.util.List;

public interface TypePermissionEvaluator<Q> {

    boolean hasPermission(TypeEvaluatorHandler handler, List<FOrganization> userOrgs, List<PermissionAction> permissionActions, Q object);

}
