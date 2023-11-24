package br.ada.visitService.exception;

import lombok.Data;

@Data
public class DefaultExceptionResponse {
	
	private Integer statusResponse;
    private String message;
}
