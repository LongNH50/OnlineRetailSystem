package miu.edu.onlineRetailSystem.service;

import jakarta.persistence.EntityNotFoundException;
import miu.edu.onlineRetailSystem.contract.CompositeItemResponse;
import miu.edu.onlineRetailSystem.contract.IndividualItemResponse;
import miu.edu.onlineRetailSystem.contract.ItemResponse;
import miu.edu.onlineRetailSystem.domain.CompositeItem;
import miu.edu.onlineRetailSystem.domain.IndividualItem;
import miu.edu.onlineRetailSystem.domain.Item;
import miu.edu.onlineRetailSystem.repository.CompositeItemRepository;
import miu.edu.onlineRetailSystem.repository.IndividualItemRepository;
import miu.edu.onlineRetailSystem.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private IndividualItemRepository individualItemRepository;

    @Autowired
    private CompositeItemRepository compositeItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<ItemResponse> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable)
                .map(entity -> modelMapper.map(entity, ItemResponse.class));
    }

    @Override
    public ItemResponse save(ItemResponse itemResponse) {
        IndividualItem item = modelMapper.map(itemResponse, IndividualItem.class);
        item = individualItemRepository.save(item);
        return modelMapper.map(item, IndividualItemResponse.class);
    }


    @Override
    public ItemResponse update(int itemId, ItemResponse itemResponse) {

        Item updateItem = itemRepository.findById(itemId).orElseThrow(() -> new EntityNotFoundException("Item not found"));
        Item item = modelMapper.map(itemResponse, Item.class);
        updateItem.setName(item.getName());
        updateItem.setReviews(item.getReviews());
        updateItem.setImage(item.getImage());
        updateItem.setPrice(item.getPrice());
        updateItem.setDescription(item.getDescription());
        updateItem.setBarcodeNumber(item.getBarcodeNumber());
        updateItem.setQuantityInStock(item.getQuantityInStock());
        itemRepository.save(updateItem);

        return itemResponse;
    }

    @Override
    public Collection<ItemResponse> search(String name) {
        Collection<ItemResponse> itemResponseCollection = itemRepository.findByName(name).stream().map(entity -> modelMapper.map(entity, ItemResponse.class)).collect(Collectors.toList());
        return itemResponseCollection;
    }

    @Override
    public ItemResponse remove(int id) {
        Item updateItem = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found"));

        itemRepository.delete(updateItem);
        return modelMapper.map(updateItem, ItemResponse.class);
    }

    @Override
    public ItemResponse findByID(int id) {
        return modelMapper.map(itemRepository.findById(id), ItemResponse.class);
    }

    @Override
    public void addSubItem(int id, ItemResponse itemResponse) {

        Item itemAdded = modelMapper.map(itemResponse, Item.class);
        Item item = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found"));

        if (!(item instanceof CompositeItem)) {
            CompositeItemResponse compositeItemResponse = new CompositeItemResponse();
            ItemResponse deletedItem = remove(item.getId());
            compositeItemResponse.setId(deletedItem.getId());
            compositeItemResponse.setDescription(deletedItem.getDescription());
            compositeItemResponse.setBarcodeNumber(deletedItem.getBarcodeNumber());
            compositeItemResponse.setName(deletedItem.getName());
            compositeItemResponse.setImage(deletedItem.getImage());
            compositeItemResponse.setPrice(deletedItem.getPrice());
            compositeItemResponse.addSubItem(itemResponse);
            compositeItemRepository.save(modelMapper.map(compositeItemResponse, CompositeItem.class));
        } else {
            CompositeItem i = ((CompositeItem) item);
            i.addSubItem(itemAdded);
            compositeItemRepository.save(i);
        }
    }

}

//    @Override
//    public void removeSubItem(int itemID, int subItemID) {
//        Item item = itemRepository.findById(itemID).orElseThrow(() -> new EntityNotFoundException("Item not found"));
//
//        ItemResponse subItemResponse = itemRepository.findSubItemByID(subItemID).orElseThrow(() -> new EntityNotFoundException("Item not found"));
//        Item subItem = modelMapper.map(subItemResponse, Item.class);
//
//        itemRepository.delete(subItem);
//
//    }



