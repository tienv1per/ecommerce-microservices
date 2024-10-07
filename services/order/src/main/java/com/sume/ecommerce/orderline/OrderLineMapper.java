package com.sume.ecommerce.orderline;

import com.sume.ecommerce.order.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .productId(request.productId())
                .order(Order
                        .builder()
                        .id(request.orderId())
                        .build())
                .build();
    }
}
