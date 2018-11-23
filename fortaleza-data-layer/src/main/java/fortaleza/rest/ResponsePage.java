package fortaleza.rest;

import org.springframework.data.domain.Page;

public class ResponsePage {
    private Integer number;

    private Long totalElements;

    private Integer totalPages;

    private Boolean isLast;

    public <T> ResponsePage(Page<T> page) {
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.number = page.getNumber();
        this.isLast = page.isLast();
    }

    public ResponsePage() {
    }

    public static ResponsePage empty() {
        ResponsePage page = new ResponsePage();
        page.isLast = true;
        page.number = 0;
        page.totalElements = 0L;
        page.totalPages = 0;
        return page;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }
}
