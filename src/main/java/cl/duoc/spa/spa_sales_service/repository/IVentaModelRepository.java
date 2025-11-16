package cl.duoc.spa.spa_sales_service.repository;

import cl.duoc.spa.spa_sales_service.model.VentaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IVentaModelRepository extends JpaRepository<VentaModel, Long> {

    Optional<VentaModel> findByOrderCode(String orderCode);

    List<VentaModel> findByUsuarioId(Long usuarioId);
}
