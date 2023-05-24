package miu.edu.onlineRetailSystem.events.listeners;

import miu.edu.onlineRetailSystem.domain.Order;
import miu.edu.onlineRetailSystem.domain.OrderStatus;
import miu.edu.onlineRetailSystem.events.ProcessEvent;
import miu.edu.onlineRetailSystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ProcessOrderServiceImpl implements ProcessOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    @EventListener
    public void processOrder(ProcessEvent processEvent) {
        Order order = orderRepository.findByIdAndCustomer(processEvent.getCustomerId(),
                processEvent.getOrderId());
        order.setStatus(OrderStatus.PROCESSED);
        orderRepository.save(order);
    }
}
