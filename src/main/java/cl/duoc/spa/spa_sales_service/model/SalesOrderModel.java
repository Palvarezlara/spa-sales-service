package cl.duoc.spa.spa_sales_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sales_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;

    private UUID userId;       // cliente (viene del user-service)

    private Double total;

    private String notes;

    private LocalDateTime createdAt = LocalDateTime.now();
}
