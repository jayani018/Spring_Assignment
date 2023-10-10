package lk.ijse.service;

import lk.ijse.dto.OrderDetailsDTO;
import lk.ijse.dto.OrdersDTO;

import java.util.List;

public interface PlaceOrderService {
    void placeOrder(OrdersDTO ordersDTO, OrderDetailsDTO orderDetailsDTO);
     List<OrdersDTO> getAllOrders();
}
