package uk.ac.brighton.uni.ch629.catshop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.ac.brighton.uni.ch629.catshop.data.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
//    Order findOrderByOrderID(int id);
}
