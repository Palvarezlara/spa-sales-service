package cl.duoc.spa.spa_sales_service.repository;

import cl.duoc.spa.spa_sales_service.model.SalesOrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;


public interface ISalesOrderItemModelRepository extends JpaRepository<SalesOrderItemModel, UUID> {
    List<SalesOrderItemModel> findByOrderId(UUID orderId);

}
