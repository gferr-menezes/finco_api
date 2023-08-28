package br.com.finco.finco_api.common.dto;

import org.springframework.data.domain.Sort.Direction;

public class PaginationDTO {
    
    private int page = 0;
    private int perPage = 50;
    private String sortBy = "id";
    private Direction sortDirection = Direction.ASC;

    public int getPage() {
        return page == 1 ? 0 : page - 1;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public Direction getSortDirection() {
        return sortDirection;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortDirection(Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public String toString() {
        return "PaginationDTO [page=" + page + ", perPage=" + perPage + " sortBy=" + sortBy + ", sortDirection=" + sortDirection + "]";
    }

}
