package fortaleza.typeevaluator.impl;

import fortaleza.FOrganization;
import fortaleza.PermissionAction;
import fortaleza.typeevaluator.TypeEvaluatorHandler;
import fortaleza.typeevaluator.TypePermissionEvaluator;

import java.util.List;

public class FOrganizationTypeEvaluator implements TypePermissionEvaluator<FOrganization> {

    @Override
    public boolean hasPermission(TypeEvaluatorHandler handler, List<FOrganization> userOrgs, List<PermissionAction> permissionActions, FOrganization object) {
        if (userOrgs == null || userOrgs.isEmpty()) {
            return false;
        }

        for (FOrganization userOrg : userOrgs) {
            if (userOrg.getOId().equals(object.getOId())) {
                return true;
            }
        }

        return false;
    }
}
