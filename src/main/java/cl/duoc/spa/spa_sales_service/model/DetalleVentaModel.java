package cl.duoc.spa.spa_sales_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "detalle_venta")
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con la venta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    private VentaModel venta;

    // Snapshot del servicio
    @Column(nullable = false, length = 40)
    private String sku;

    @Column(nullable = false, length = 150)
    private String nombreServicio;

    @Column(nullable = false)
    private Integer precioUnitario;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Integer subtotal;
}
