package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.ItemResponse;

import java.util.Collection;

public interface ItemService {
    ItemResponse save(ItemResponse itemResponse);

    ItemResponse update(int itemId, ItemResponse itemResponse);

    Collection<ItemResponse> search(String name);

    ItemResponse remove(int itemId);
}
