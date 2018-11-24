package fortaleza.datalayer.repository;

import fortaleza.datalayer.model.OrganizationalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseOrganizationalRepository<T extends OrganizationalEntity, ID extends Serializable> extends BaseRepository<T, ID> {

    @Query("SELECT e FROM #{entityName} e JOIN e.organization o WHERE o.id in :orgIds")
    List<T> findByOrganization(@Param("orgIds") List<? extends Serializable> orgIds);

}
