package br.com.finco.finco_api.user.dto;

public record UserOutputDTO(
    Long id,
    String email,
    ProfileOuputDTO profile
) {
}
