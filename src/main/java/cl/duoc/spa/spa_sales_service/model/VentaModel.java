package cl.duoc.spa.spa_sales_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "ventas")
@AllArgsConstructor
@NoArgsConstructor
public class VentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Código legible tipo ORD-XXXX (puede venir del front o lo genera el backend)
    @Column(name = "order_code", length = 20, unique = true, nullable = false)
    private String orderCode;

    // =========================
    // Datos del usuario / cliente
    // =========================
    private Long usuarioId; // puede ser null si no está logueado, pero en tu caso sí

    @Column(nullable = false, length = 120)
    private String nombreCliente;

    @Column(nullable = false, length = 120)
    private String emailCliente;

    @Column(length = 20)
    private String telefonoCliente;

    // =========================
    // Preferencias de reserva
    // =========================
    private Integer numeroPersonas;

    private LocalDate fechaPreferida;

    @Column(length = 20)
    private String horarioPreferido; // "MANANA", "TARDE", etc.

    @Column(length = 500)
    private String comentarios;

    // =========================
    // Monto y estado
    // =========================
    @Column(nullable = false)
    private Integer total; // CLP sin decimales

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoVenta estado = EstadoVenta.PAGADA;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MedioPago medioPago = MedioPago.SIMULADO;

    // =========================
    // Auditoría
    // =========================
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoVenta.PAGADA;
        }
        if (this.medioPago == null) {
            this.medioPago = MedioPago.SIMULADO;
        }
    }

    // =========================
    // Relación con detalles
    // =========================
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVentaModel> detalles;

    // Enums internos
    public enum EstadoVenta {
        PENDIENTE,
        PAGADA,
        FALLIDA
    }

    public enum MedioPago {
        SIMULADO
    }
}
