package me.theegg.train.repository;

import me.theegg.train.model.Tessera;
import me.theegg.train.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by seal on 9/18/15.
 */
public interface TesseraRepository extends JpaRepository<Tessera, Long>{

    List<Tessera> findByUser(User user);
    List<Tessera> findByUserAndDatetimeBetween(User user, Date startDate, Date endDate);
}
