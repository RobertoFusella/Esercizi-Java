package carvedrockfitness;

import carvedrockfitness.order.Order;
import carvedrockfitness.order.OrderController;
import carvedrockfitness.order.OrderService;
import carvedrockfitness.product.Product;
import carvedrockfitness.product.ProductRepository;
import carvedrockfitness.user.User;
import carvedrockfitness.user.UserController;
import carvedrockfitness.user.UserStatus;
import carvedrockfitness.util.LoggingUtil;


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
