package cl.duoc.spa.spa_sales_service.service;

import cl.duoc.spa.spa_sales_service.dto.*;
import cl.duoc.spa.spa_sales_service.model.DetalleVentaModel;
import cl.duoc.spa.spa_sales_service.model.VentaModel;
import cl.duoc.spa.spa_sales_service.repository.IVentaModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaModelService {

    private final IVentaModelRepository ventaRepository;

    @Transactional
    public VentaResponse crearVenta(CrearVentaRequest request) {
        VentaModel venta = new VentaModel();

        // Código de orden: si no viene, lo generamos
        String orderCode = request.orderCode();
        if (orderCode == null || orderCode.isBlank()) {
            String t = Long.toString(System.currentTimeMillis(), 36).toUpperCase();
            orderCode = "ORD-" + t.substring(Math.max(0, t.length() - 6));
        }
        venta.setOrderCode(orderCode);

        // Datos de cliente
        venta.setUsuarioId(request.usuarioId());
        venta.setNombreCliente(request.nombreCliente());
        venta.setEmailCliente(request.emailCliente());
        venta.setTelefonoCliente(request.telefonoCliente());

        // Preferencias
        venta.setNumeroPersonas(request.numeroPersonas());
        venta.setFechaPreferida(request.fechaPreferida());
        venta.setHorarioPreferido(request.horarioPreferido());
        venta.setComentarios(request.comentarios());

        // Monto y estado
        venta.setTotal(request.total());
        venta.setEstado(VentaModel.EstadoVenta.PAGADA);
        venta.setMedioPago(VentaModel.MedioPago.SIMULADO);

        // Detalles
        List<DetalleVentaModel> detalles = request.items().stream()
                .map(it -> {
                    DetalleVentaModel d = new DetalleVentaModel();
                    d.setVenta(venta);
                    d.setSku(it.sku());
                    d.setNombreServicio(it.nombreServicio());
                    d.setPrecioUnitario(it.precioUnitario());
                    d.setCantidad(it.cantidad());
                    d.setSubtotal(it.precioUnitario() * it.cantidad());
                    return d;
                })
                .toList();

        venta.setDetalles(detalles);

        VentaModel saved = ventaRepository.save(venta);
        return toResponse(saved);
    }

    public List<VentaResponse> listarTodas() {
        return ventaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public VentaResponse buscarPorId(Long id) {
        VentaModel venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return toResponse(venta);
    }

    public VentaResponse buscarPorOrderCode(String orderCode) {
        VentaModel venta = ventaRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return toResponse(venta);
    }

    public List<VentaResponse> listarPorUsuario(Long usuarioId) {
        return ventaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // Resumen para AdminReporte
    public ResumenVentasResponse obtenerResumen() {
        List<VentaModel> ventas = ventaRepository.findAll();

        long cantidad = ventas.size();
        long totalIngresos = ventas.stream()
                .mapToLong(v -> Optional.ofNullable(v.getTotal()).orElse(0))
                .sum();

        Map<LocalDate, Long> ventasPorDia = ventas.stream()
                .filter(v -> v.getCreatedAt() != null)
                .collect(Collectors.groupingBy(
                        v -> v.getCreatedAt().toLocalDate(),
                        Collectors.counting()
                ));

        return new ResumenVentasResponse(cantidad, totalIngresos, ventasPorDia);
    }

    private VentaResponse toResponse(VentaModel v) {
        List<ItemVentaResponse> items = Optional.ofNullable(v.getDetalles())
                .orElse(List.of())
                .stream()
                .map(d -> new ItemVentaResponse(
                        d.getId(),
                        d.getSku(),
                        d.getNombreServicio(),
                        d.getPrecioUnitario(),
                        d.getCantidad(),
                        d.getSubtotal()
                ))
                .toList();

        return new VentaResponse(
                v.getId(),
                v.getOrderCode(),
                v.getUsuarioId(),
                v.getNombreCliente(),
                v.getEmailCliente(),
                v.getTelefonoCliente(),
                v.getNumeroPersonas(),
                v.getFechaPreferida(),
                v.getHorarioPreferido(),
                v.getComentarios(),
                v.getTotal(),
                v.getEstado(),
                v.getMedioPago(),
                v.getCreatedAt(),
                items
        );
    }
}

