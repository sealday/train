package me.theegg.train.repository;

import me.theegg.train.model.Tessera;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by seal on 9/17/15.
 */
public interface OrderRepository extends JpaRepository<Tessera, Long> {
}
