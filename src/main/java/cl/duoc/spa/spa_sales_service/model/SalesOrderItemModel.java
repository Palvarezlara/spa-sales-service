package cl.duoc.spa.spa_sales_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sales_order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID itemId;

    private UUID orderId;                // FK lógica a SalesOrderModel

    private UUID serviceSku;             // viene de catalog-service

    private UUID therapistUserId;        // terapeuta (user-service)

    private String serviceName;          // denormalizado
    private String therapistName;        // denormalizado

    private Double unitPrice;

    private Integer quantity = 1;

    private LocalDateTime performedAt;   // fecha que se realizó

    private String observations;
}
