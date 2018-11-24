package fortaleza.datalayer.model;

import java.io.Serializable;

public interface FortalezaEntity<ID extends Serializable> {

    ID getId();

}
