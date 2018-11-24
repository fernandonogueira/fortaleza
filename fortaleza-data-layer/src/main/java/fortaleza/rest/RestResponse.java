package fortaleza.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fortaleza.model.SecuredContent;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class RestResponse<T> implements SecuredContent {

    @JsonIgnore
    private Class<T> type;

    private Collection<T> content;

    private ResponsePage page;

    private ResponseError error;

    public RestResponse(Class<T> type, Page<T> page) {
        this.type = type;
        if (page.hasContent()) {
            this.content = page.getContent();
            this.page = new ResponsePage(page);
        } else {
            this.content = new ArrayList<>();
            this.page = null;
        }
    }

    public RestResponse(Class<T> type, T object) {
        this.type = type;
        if (object != null) {
            this.content = Collections.singletonList(object);
            this.page = null;
        } else {
            this.content = new ArrayList<>();
            this.page = null;
        }
    }

    public static <T> RestResponse<T> of(Class<T> type, Optional<T> opt) {
        if (opt.isPresent()) {
            return new RestResponse<T>(type, opt.get());
        } else {
            return RestResponse.empty();
        }
    }

    public RestResponse() {
    }

    private static <T> RestResponse<T> empty() {
        RestResponse<T> response = new RestResponse<>();
        response.content = new ArrayList<>();
        response.setError(null);
        response.setPage(null);
        response.setType(null);
        return response;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public Collection<T> getContent() {
        return content;
    }

    public void setContent(Collection<T> content) {
        this.content = content;
    }

    public ResponsePage getPage() {
        return page;
    }

    public void setPage(ResponsePage page) {
        this.page = page;
    }

    public ResponseError getError() {
        return error;
    }

    public void setError(ResponseError error) {
        this.error = error;
    }

    @JsonIgnore
    @Override
    public Class getClassType() {
        return type;
    }
}
