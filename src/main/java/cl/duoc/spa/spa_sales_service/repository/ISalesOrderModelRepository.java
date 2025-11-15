package cl.duoc.spa.spa_sales_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.spa.spa_sales_service.model.SalesOrderModel;

import java.util.List;
import java.util.UUID;
public interface ISalesOrderModelRepository  extends JpaRepository<SalesOrderModel, UUID> {
    List<SalesOrderModel> findByUserId(UUID userId);
}
