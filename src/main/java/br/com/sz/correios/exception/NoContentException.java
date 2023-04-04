package br.com.sz.correios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Service is in installation, please wait 3-5 minutes") // -> 204
public class NoContentException extends Exception{}
