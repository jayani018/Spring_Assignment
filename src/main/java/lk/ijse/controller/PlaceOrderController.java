package lk.ijse.controller;

import lk.ijse.dto.OrderDetailsDTO;
import lk.ijse.dto.OrdersDTO;
import lk.ijse.service.PlaceOrderService;
import lk.ijse.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/placeOrder")
@CrossOrigin
public class PlaceOrderController {
    PlaceOrderService service;

    @PostMapping
    public ResponseUtil addCustomer(OrdersDTO dto, OrderDetailsDTO orderDetailsDTO){
        service.placeOrder(dto, orderDetailsDTO);
        return new ResponseUtil("Ok","Successfully Added",dto);
    }

    @GetMapping
    public ResponseUtil getAllCustomer(){
        return new ResponseUtil("Ok","Successfully Loaded",service.getAllOrders());
    }
}
