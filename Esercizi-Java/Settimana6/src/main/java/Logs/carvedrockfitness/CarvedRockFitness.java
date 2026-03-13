package Logs.carvedrockfitness;

import Logs.carvedrockfitness.order.Order;
import Logs.carvedrockfitness.order.OrderController;
import Logs.carvedrockfitness.product.ProductRepository;
import Logs.carvedrockfitness.user.User;
import Logs.carvedrockfitness.user.UserController;
import Logs.carvedrockfitness.user.UserStatus;
import Logs.carvedrockfitness.util.LoggingUtil;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CarvedRockFitness {

    public static void main(String[] args) throws IOException {
        LoggingUtil.initLogManager();
        //some code that pretends to be a user and places an order

        // add user
        User user = new User(4, "Maaike", "maaike@maaike.nl", LocalDateTime.now(), UserStatus.PENDING);
        UserController userController = new UserController();
        userController.addUser(user);

        // add order
        Order order = new Order(10, user, List.of(ProductRepository.getDummyDataList().get(0)), LocalDateTime.of(2021,11, 3, 0, 0));
        OrderController orderController = new OrderController();
        orderController.addOrder(order);

    }


}
