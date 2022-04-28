package uz.pdp.online.newappwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.online.newappwarehouse.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
