package cl.duoc.spa.spa_sales_service.service;

import cl.duoc.spa.spa_sales_service.model.SalesOrderItemModel;
import cl.duoc.spa.spa_sales_service.repository.ISalesOrderItemModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalesOrderItemModelService {
    private final ISalesOrderItemModelRepository repo;

    public SalesOrderItemModel save(SalesOrderItemModel item) {
        return repo.save(item);
    }

    public List<SalesOrderItemModel> findByOrderId(UUID orderId) {
        return repo.findByOrderId(orderId);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
