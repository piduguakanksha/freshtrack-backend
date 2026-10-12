
package FreshTrack.controller;

import FreshTrack.entity.GroceryItem;
import FreshTrack.repository.GroceryItemRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/groceries")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000", "https://freshtrack-frontend-pi.vercel.app", "https://freshtrack-frontend-pi.vercel.app/", "https://freshtrack-rho.vercel.app"})
public class GroceryItemController {

    private final GroceryItemRepository repository;

    public GroceryItemController(GroceryItemRepository repository) {
        this.repository = repository;
    }

    // Get all grocery items
    @GetMapping
    public List<GroceryItem> getAllGroceries() {
        return repository.findAll();
    }

    // Get one grocery item by ID
    @GetMapping("/{id}")
    public GroceryItem getGroceryById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Grocery item not found: " + id));
    }

    // Add a grocery item
    @PostMapping
    public GroceryItem addGrocery(@RequestBody GroceryItem grocery) {
        return repository.save(grocery);
    }

    // Update a grocery item
    @PutMapping("/{id}")
    public GroceryItem updateGrocery(
            @PathVariable Long id,
            @RequestBody GroceryItem updatedGrocery) {

        GroceryItem existing = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Grocery item not found: " + id));

        existing.setName(updatedGrocery.getName());
        existing.setCategory(updatedGrocery.getCategory());
        existing.setQuantity(updatedGrocery.getQuantity());
        existing.setUnit(updatedGrocery.getUnit());
        existing.setPurchaseDate(updatedGrocery.getPurchaseDate());
        existing.setExpiryDate(updatedGrocery.getExpiryDate());
        existing.setStorageLocation(updatedGrocery.getStorageLocation());
        existing.setBrand(updatedGrocery.getBrand());

        return repository.save(existing);
    }

    // Delete a grocery item
    @DeleteMapping("/{id}")
    public void deleteGrocery(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
