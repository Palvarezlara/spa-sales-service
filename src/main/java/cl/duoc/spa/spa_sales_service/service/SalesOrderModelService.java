package cl.duoc.spa.spa_sales_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cl.duoc.spa.spa_sales_service.model.SalesOrderModel;
import cl.duoc.spa.spa_sales_service.repository.ISalesOrderModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalesOrderModelService {

    private final ISalesOrderModelRepository repo;

    public SalesOrderModel save(SalesOrderModel order) {
        return repo.save(order);
    }

    public SalesOrderModel getById(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public List<SalesOrderModel> getAll() {
        return repo.findAll();
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }

    public List<SalesOrderModel> getByUser(UUID userId) {
        return repo.findByUserId(userId);
    }
}
