package cl.duoc.spa.spa_sales_service.controller;

import cl.duoc.spa.spa_sales_service.model.SalesOrderModel;
import cl.duoc.spa.spa_sales_service.model.SalesOrderItemModel;
import cl.duoc.spa.spa_sales_service.service.SalesOrderModelService;
import cl.duoc.spa.spa_sales_service.service.SalesOrderItemModelService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SalesOrderModelController {
    private final SalesOrderModelService orderService;
    private final SalesOrderItemModelService itemService;

    // ---------- SALES ORDERS ----------
    @PostMapping
    public SalesOrderModel createOrder(@RequestBody SalesOrderModel order) {
        return orderService.save(order);
    }

    @GetMapping
    public List<SalesOrderModel> listOrders() {
        return orderService.getAll();
    }

    @GetMapping("/{orderId}")
    public SalesOrderModel getOrder(@PathVariable UUID orderId) {
        return orderService.getById(orderId);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable UUID orderId) {
        orderService.delete(orderId);
    }

    @GetMapping("/user/{userId}")
    public List<SalesOrderModel> getOrdersByUser(@PathVariable UUID userId) {
        return orderService.getByUser(userId);
    }

    // ---------- ORDER ITEMS ----------
    @PostMapping("/{orderId}/items")
    public SalesOrderItemModel createItem(
            @PathVariable UUID orderId,
            @RequestBody SalesOrderItemModel item
    ) {
        item.setOrderId(orderId);
        return itemService.save(item);
    }

    @GetMapping("/{orderId}/items")
    public List<SalesOrderItemModel> getItemsByOrder(@PathVariable UUID orderId) {
        return itemService.findByOrderId(orderId);
    }

}
