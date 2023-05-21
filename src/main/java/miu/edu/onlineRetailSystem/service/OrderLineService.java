package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.OrderLineResponse;

public interface OrderLineService {
    void save(int itemId, OrderLineResponse orderLineResponse);

    void update(int orderLineId, OrderLineResponse orderLineResponse);

    void remove(int orderLineId);
}
