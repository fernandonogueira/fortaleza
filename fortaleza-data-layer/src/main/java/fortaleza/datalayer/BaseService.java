package fortaleza.datalayer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;
import java.util.Optional;

public class BaseService<T, ID extends Serializable> {

    private final BaseRepository<T, ID> repository;

    private final Class<T> classType;

    public BaseService(Class<T> classType, BaseRepository<T, ID> repository) {
        this.repository = repository;
        this.classType = classType;
    }

    public T save(T payload) {
        validatePermission(payload);
        T payload2 = preSave(payload);
        T persisted = repository.save(payload2);
        return postSave(persisted);
    }

    public void validatePermission(T payload) {

    }

    public T preSave(T payload) {
        return payload;
    }

    public T postSave(T payload) {
        return payload;
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public Class<T> getClassType() {
        return classType;
    }

    public void delete(T payload) {
        repository.delete(payload);
    }

    public Page<T> findAll(Integer page, Integer pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }
}
