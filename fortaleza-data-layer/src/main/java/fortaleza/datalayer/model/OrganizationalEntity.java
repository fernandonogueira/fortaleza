package fortaleza.datalayer.model;

import fortaleza.FOrganization;

import java.io.Serializable;

public interface OrganizationalEntity<ID extends Serializable> extends FortalezaEntity<ID> {
    FOrganization getOrganization();
}
