package br.com.finco.finco_api.common.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PaginationResponseDTO<T> {

    private List<T> content;

    CustomPageable pageable;

    public PaginationResponseDTO(Page<T> page) {
        this.content = page.getContent();
        this.pageable = new CustomPageable(page.getNumber(), page.getSize(), page.getTotalElements());
    }

    @Data
    class CustomPageable {
        int pageNumber;
        int pageSize;
        long totalElements;

        public CustomPageable(int pageNumber, int pageSize, long totalElements) {

            this.pageNumber = pageNumber + 1;
            this.pageSize = pageSize;
            this.totalElements = totalElements;
        }
    }

}
