package cl.duoc.spa.spa_sales_service.controller;

import cl.duoc.spa.spa_sales_service.dto.CrearVentaRequest;
import cl.duoc.spa.spa_sales_service.dto.ResumenVentasResponse;
import cl.duoc.spa.spa_sales_service.dto.VentaResponse;
import cl.duoc.spa.spa_sales_service.service.VentaModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales")
public class VentaModelController {

    private final VentaModelService ventaService;

    // Crear nueva venta (utilizado por Checkout)
    @PostMapping("/ordenes")
    public ResponseEntity<VentaResponse> crear(@RequestBody CrearVentaRequest request) {
        return ResponseEntity.ok(ventaService.crearVenta(request));
    }

    // Listar todas (para AdminReporte)
    @GetMapping("/ordenes")
    public ResponseEntity<List<VentaResponse>> listarTodas() {
        return ResponseEntity.ok(ventaService.listarTodas());
    }

    // Obtener una por ID
    @GetMapping("/ordenes/{id}")
    public ResponseEntity<VentaResponse> porId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.buscarPorId(id));
    }

    // Obtener por código de orden (ej: ORD-XXXXXX)
    @GetMapping("/ordenes/code/{orderCode}")
    public ResponseEntity<VentaResponse> porCodigo(@PathVariable String orderCode) {
        return ResponseEntity.ok(ventaService.buscarPorOrderCode(orderCode));
    }

    // Historial de un usuario
    @GetMapping("/ordenes/usuario/{usuarioId}")
    public ResponseEntity<List<VentaResponse>> porUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ventaService.listarPorUsuario(usuarioId));
    }

    // Resumen simple para AdminReporte
    @GetMapping("/resumen")
    public ResponseEntity<ResumenVentasResponse> resumen() {
        return ResponseEntity.ok(ventaService.obtenerResumen());
    }
}
