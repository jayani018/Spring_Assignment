package lk.ijse.service.impl;

import lk.ijse.dto.OrderDetailsDTO;
import lk.ijse.dto.OrdersDTO;
import lk.ijse.entity.Item;
import lk.ijse.entity.OrderDetails;
import lk.ijse.entity.Orders;
import lk.ijse.repo.CustomerRepo;
import lk.ijse.repo.ItemRepo;
import lk.ijse.repo.OrderDetailRepo;
import lk.ijse.repo.OrdersRepo;
import lk.ijse.service.PlaceOrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    @Autowired
    OrdersRepo ordersRepo;
    @Autowired
    OrderDetailRepo orderDetailRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ModelMapper mapper;

    @Override
    public void placeOrder(OrdersDTO ordersDTO, OrderDetailsDTO orderDetailsDTO) {
        if (ordersRepo.existsById(ordersDTO.getOid())) {
            throw new RuntimeException(ordersDTO.getOid()+" is already available, please insert a new ID");
        }
        if (!customerRepo.existsById(ordersDTO.getCusID())) {
            throw new RuntimeException(ordersDTO.getOid()+" is not available, please enter valid customer ID");
        }
        if (!itemRepo.existsById(orderDetailsDTO.getItemCode())) {
            throw new RuntimeException(ordersDTO.getOid()+" is not available, please enter valid item code");
        }
        Orders map = mapper.map(ordersDTO, Orders.class);
        OrderDetails map1 = mapper.map(orderDetailsDTO, OrderDetails.class);

        Item item = itemRepo.findById(orderDetailsDTO.getItemCode()).get();
        item.setQtyOnHand(item.getQtyOnHand()-orderDetailsDTO.getQty());
        //first param = source
        //Type you want to convert
        ordersRepo.save(map);
        orderDetailRepo.save(map1);
        itemRepo.save(item);
    }
    public List<OrdersDTO> getAllOrders(){
        List<Orders> all = ordersRepo.findAll();
        return mapper.map(all, new TypeToken<List<OrdersDTO>>() {
        }.getType());
    }
}
