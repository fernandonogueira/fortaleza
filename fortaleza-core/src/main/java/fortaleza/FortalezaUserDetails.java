package fortaleza;

import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

public interface FortalezaUserDetails<T extends Serializable> extends UserDetails {

    void setOrganizations(List<FOrganization<T>> organizations);

    List<FOrganization<T>> getOrganizations();

}
