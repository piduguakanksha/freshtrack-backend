
package FreshTrack.repository;

import FreshTrack.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemRepository
        extends JpaRepository<GroceryItem, Long> {
}
