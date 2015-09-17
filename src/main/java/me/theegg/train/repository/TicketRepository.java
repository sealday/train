package me.theegg.train.repository;

import me.theegg.train.model.Ticket;
import me.theegg.train.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;


/**
 * Created by seal on 9/16/15.
 */
public interface TicketRepository extends JpaRepository<Ticket, Long>{

    List<Ticket> findByTrainAndDepartDate(Train train, @Temporal(TemporalType.DATE) Date departDate);

    List<Ticket> findByTrain(Train train);

    List<Ticket> findByTrain(String number);
}
