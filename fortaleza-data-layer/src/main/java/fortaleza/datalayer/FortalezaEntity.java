package fortaleza.datalayer;

import java.io.Serializable;

public interface FortalezaEntity<ID extends Serializable> {

    ID getId();

}
