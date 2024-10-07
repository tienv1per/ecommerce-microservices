package com.sume.ecommerce.order;

import com.sume.ecommerce.customer.CustomerClient;
import com.sume.ecommerce.exception.BusinessException;
import com.sume.ecommerce.orderline.OrderLineRequest;
import com.sume.ecommerce.orderline.OrderLineService;
import com.sume.ecommerce.product.ProductClient;
import com.sume.ecommerce.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest request) {
        // check the customer
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exist with ID:: " + request.customerId()));
        // purchase the products --> product ms (RestTemplate)
        this.productClient.purchaseProducts(request.products());
        // persist order
        var order = this.repository.save(mapper.toOrder(request));
        // persist order lines
        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        // start payment process
        // send the order confirmation --> notification-ms (kafka)
        return null;
    }
}
