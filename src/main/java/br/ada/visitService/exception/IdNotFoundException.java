package br.ada.visitService.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdNotFoundException extends Exception{
	
	private String message;

}
