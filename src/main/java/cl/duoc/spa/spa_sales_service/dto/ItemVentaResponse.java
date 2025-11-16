package cl.duoc.spa.spa_sales_service.dto;

public record ItemVentaResponse(
        Long id,
        String sku,
        String nombreServicio,
        Integer precioUnitario,
        Integer cantidad,
        Integer subtotal
) {}
