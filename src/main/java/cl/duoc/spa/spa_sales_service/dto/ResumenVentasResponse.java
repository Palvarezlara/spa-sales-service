package cl.duoc.spa.spa_sales_service.dto;

import java.time.LocalDate;
import java.util.Map;

public record ResumenVentasResponse(
        Long cantidadVentas,
        Long totalIngresos,
        Map<LocalDate, Long> ventasPorDia
) {}
