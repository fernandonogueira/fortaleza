package fortaleza.datalayer;

import fortaleza.rest.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

public class BaseController<T, ID extends Serializable> {

    private final BaseService<T, ID> service;

    public BaseController(BaseService<T, ID> service) {
        this.service = service;
    }

    @PreAuthorize("hasPermission(payload, 'CREATE') && hasPermission(payload, 'UPDATE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T save(@RequestBody T payload) {
        return service.save(payload);
    }

    @PreAuthorize("hasPermission(payload, 'DELETE')")
    @DeleteMapping
    public void delete(@RequestBody T payload) {
        service.delete(payload);
    }

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    @GetMapping("/{id}")
    public RestResponse<T> findById(@PathVariable("id") ID id) {
        return RestResponse.of(service.getClassType(), service.findById(id));
    }

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    @GetMapping
    public RestResponse<T> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "50") Integer pageSize) {
        return new RestResponse<>(service.getClassType(), service.findAll(page, pageSize));
    }

}
