package cl.duoc.azuread.ejemplo.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class DefaultController {
	private final RestTemplate restTemplate = new RestTemplate();

	@PostMapping("/mensaje")
	public String mensaje() {

		System.out.println("Backend llamado");
		return "{\"mensaje\": \"Integración OK al backend\"}";
	}

 	@PostMapping("/productos/listar")
	public String obtenerProductos() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestBody = "{}";
		HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
		String url = "http://localhost:8081/productos/listar";
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Respuesta del backend: " + response);
		return response;

		
	}
}
