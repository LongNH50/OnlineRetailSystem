package miu.edu.onlineRetailSystem.controller;

import miu.edu.onlineRetailSystem.contract.IndividualItemResponse;
import miu.edu.onlineRetailSystem.contract.ItemResponse;
import miu.edu.onlineRetailSystem.repository.ItemRepository;
import miu.edu.onlineRetailSystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @PostMapping
    public ResponseEntity<?> createIndividualItem(@RequestBody ItemResponse itemResponse) {
        itemService.save(itemResponse);
        return ResponseEntity.ok(itemResponse);
    }

    @PostMapping("/{id}")
    public void addSubItem(@PathVariable Integer id, @RequestBody ItemResponse itemResponse)  {
        itemService.addSubItem(id, itemResponse);
    }

    @GetMapping
    public Page<ItemResponse> findAllItems(Pageable pageable) {
        return itemService.findAll(pageable);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchItemByName(@RequestParam String name) {
        return ResponseEntity.ok(itemService.search(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findItemByID(@PathVariable int id) {
        return ResponseEntity.ok(itemService.findByID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIndividualItem(@PathVariable Integer id, @RequestBody IndividualItemResponse individualItemResponse) {
        return ResponseEntity.ok(itemService.update(id, individualItemResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemByID(@PathVariable Integer id) {
        return ResponseEntity.ok(itemService.remove(id));
    }


}
