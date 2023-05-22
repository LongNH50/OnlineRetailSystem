package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.OrderLineResponse;
import miu.edu.onlineRetailSystem.domain.OrderLine;
import miu.edu.onlineRetailSystem.exception.ResourceNotFoundException;
import miu.edu.onlineRetailSystem.repository.OrderLineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Override
    public OrderLineResponse findById(int orderLineId) {
        OrderLine orderLine = orderLineRepository.findById(orderLineId).orElseThrow(
                () -> new ResourceNotFoundException("OrderLine", "Id", orderLineId)
        );

        return mapper.map(orderLine, OrderLineResponse.class);
    }

    @Override
    public OrderLineResponse save(OrderLineResponse orderLineResponse) {
        OrderLine orderLine = mapper.map(orderLineResponse, OrderLine.class);
        OrderLine savedOrderLine = orderLineRepository.save(orderLine);
        return mapper.map(savedOrderLine, OrderLineResponse.class);
    }

    @Override
    public OrderLineResponse update(int orderLineId, OrderLineResponse orderLineResponse) {

        OrderLine orderLine = orderLineRepository.findById(orderLineId).orElseThrow(
                () -> new ResourceNotFoundException("OrderLine", "Id", orderLineId)
        );

        OrderLine newOrderLine = mapper.map(orderLineResponse, OrderLine.class);

        orderLine.setDiscount(newOrderLine.getDiscount());
        orderLine.setQuantity(newOrderLine.getQuantity());
        orderLine.setItem(newOrderLine.getItem());

        OrderLine savedOrderLine = orderLineRepository.save(orderLine);
        return mapper.map(savedOrderLine, OrderLineResponse.class);
    }

    @Override
    public List<OrderLineResponse> getAllOrderLines() {
        List<OrderLine> orderLines = orderLineRepository.findAll();

        return orderLines
                .stream()
                .map((orderLine) -> mapper.map(orderLine, OrderLineResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderLineResponse remove(int orderLineId) {
        return null;
    }
}
