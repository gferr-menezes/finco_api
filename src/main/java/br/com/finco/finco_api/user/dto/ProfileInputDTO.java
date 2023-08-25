package br.com.finco.finco_api.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProfileInputDTO {
    
    @NotEmpty(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    private String avatarUrl;

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
