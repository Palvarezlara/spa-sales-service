package cl.duoc.spa.spa_sales_service.dto;

public record ItemVentaRequest(
        String sku,
        String nombreServicio,
        Integer precioUnitario,
        Integer cantidad
) {}
