package cl.duoc.spa.spa_sales_service.dto;

import cl.duoc.spa.spa_sales_service.model.VentaModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record VentaResponse(
        Long id,
        String orderCode,
        Long usuarioId,
        String nombreCliente,
        String emailCliente,
        String telefonoCliente,
        Integer numeroPersonas,
        LocalDate fechaPreferida,
        String horarioPreferido,
        String comentarios,
        Integer total,
        VentaModel.EstadoVenta estado,
        VentaModel.MedioPago medioPago,
        LocalDateTime createdAt,
        List<ItemVentaResponse> items
) {}
