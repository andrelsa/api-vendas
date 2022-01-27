package github.com.andrelsa.rest;

import lombok.Getter;
import java.util.Collections;
import java.util.List;

public class ApiErrors {
	
	@Getter
	private final List<String> errors;
	
	public ApiErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public ApiErrors(String mensagemErro) {
		this.errors = Collections.singletonList(mensagemErro);
	}
	
}
