package cl.duoc.azuread.ejemplo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class DefaultController {
	private final RestTemplate restTemplate = new RestTemplate();

	@Value("${services.tienditaventas.url}")
	private String tienditaventasUrl;

	@Value("${services.tienditaventas.productos.listar}")
	private String productosListarEndpoint;

	@Value("${services.tienditaventas.productos.comprar}")
	private String productosComprarEndpoint;

	@Value("${services.tienditapromos.url}")
	private String tienditapromosUrl;

	@Value("${services.tienditapromos.promo.generar}")
	private String promoGenerarEndpoint;

	@Value("${services.tienditaventas.ventas.usuario}")
	private String ventasUsuarioEndpoint;

	@Value("${services.tienditaventas.promo.listar}")
	private String promoListarEndpoint;

	@PostMapping("/api/status")
	public String mensaje() {

		System.out.println("Backend llamado");
		return "{\"mensaje\": \"Integración OK al backend\"}";
	}

	@PostMapping(value = "/api/productos", produces = MediaType.APPLICATION_JSON_VALUE)
	public String obtenerProductos() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestBody = "{}";
		HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
		String url = tienditaventasUrl + "/" + productosListarEndpoint;
		System.out.println("Llamando al backend: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Respuesta del backend: " + response);
		return response;
	}
	
	@PostMapping(value = "/api/productos/venta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String compraCarrito(@RequestBody String requestBody) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
		String url = tienditaventasUrl + "/" + productosComprarEndpoint;
		System.out.println("Llamando al backend: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Respuesta del backend: " + response);
		return response;
	}


	@PostMapping(value = "/api/promos/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String crearPromo(@RequestBody String requestBody) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
		String url = tienditapromosUrl + "/" + promoGenerarEndpoint;
		System.out.println("Llamando al backend: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Respuesta del backend: " + response);
		return response;
	}
	
	@PostMapping(value = "/api/ventas/usuario/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerVentasPorUsuario(@PathVariable String usuarioId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = tienditaventasUrl +"/"+ ventasUsuarioEndpoint +"/" +usuarioId;
		System.out.println("Llamando al backend: " + url);
		String response = restTemplate.getForObject(url, String.class);
		System.out.println("Respuesta del backend: " + response);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/api/promos/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public String listarPromociones() {
		String url = tienditaventasUrl + "/" + promoListarEndpoint;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);
		System.out.println("Llamando al backend: " + url);
		String response = restTemplate.getForObject(url, String.class);
		System.out.println("Respuesta del backend: " + response);
		return response;
	}
}
