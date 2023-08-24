package br.com.finco.finco_api.common.dto;

public class FilterDTO {

    private String search = "";

    public FilterDTO() {
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "[search=" + search + "]";
    }
}
