package cl.duoc.spa.spa_sales_service.dto;

import java.time.LocalDate;
import java.util.List;

public record CrearVentaRequest(
        Long usuarioId,
        String nombreCliente,
        String emailCliente,
        String telefonoCliente,
        Integer numeroPersonas,
        LocalDate fechaPreferida,
        String horarioPreferido,
        String comentarios,
        Integer total,
        String orderCode,        // opcional: lo puede mandar el front
        List<ItemVentaRequest> items
) {}
