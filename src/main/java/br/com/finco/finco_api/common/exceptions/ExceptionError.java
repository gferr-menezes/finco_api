package br.com.finco.finco_api.common.exceptions;

public record ExceptionError(String message, Integer error, String errorDate) {
}
