package br.com.finco.finco_api.user.dto;

public record UserOutput(
    Long id,
    String email,
    ProfileOuput profile
) {
}
