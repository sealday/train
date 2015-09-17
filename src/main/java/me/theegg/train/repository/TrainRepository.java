package me.theegg.train.repository;

import me.theegg.train.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by seal on 9/16/15.
 */
public interface TrainRepository extends JpaRepository<Train, String>{

    List<Train> findByStartAndEnd(String start, String end);
}
