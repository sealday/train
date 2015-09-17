package me.theegg.train.controller;

import me.theegg.train.model.Train;
import me.theegg.train.model.User;
import me.theegg.train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by seal on 9/16/15.
 */
@RestController
@RequestMapping("/api/trains")
public class TrainController {

    @Autowired
    private TrainRepository trainRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void addTrain(@RequestBody Train train, User user) {
        trainRepository.save(train);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Train> getTrain() {
        return trainRepository.findAll();
    }

    @RequestMapping(value = "/{number}", method = RequestMethod.DELETE)
    public void removeTrain(@PathVariable("number") Train train) {
        trainRepository.delete(train);
    }

    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    public Train getTrain(@PathVariable("number") Train train) {
        return train;
    }
}
