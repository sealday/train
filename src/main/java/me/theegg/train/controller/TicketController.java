package me.theegg.train.controller;

import me.theegg.train.model.Tessera;
import me.theegg.train.model.Ticket;
import me.theegg.train.model.Train;
import me.theegg.train.model.User;
import me.theegg.train.repository.TesseraRepository;
import me.theegg.train.repository.TicketRepository;
import me.theegg.train.repository.TrainRepository;
import me.theegg.train.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by seal on 9/16/15.
 */
@RestController
@RequestMapping("/api/trains")
public class TicketController {

    @Autowired private TicketRepository ticketRepository;
    @Autowired private TrainRepository trainRepository;
    @Autowired private TesseraRepository tesseraRepository;
    @Autowired private UserRepository userRepository;

    @RequestMapping(value = "/{number}/tickets", method = RequestMethod.GET)
    public Set<Ticket> getTicket(@PathVariable("number") Train train) {
        return train.getTickets();
    }

    @RequestMapping(value = "/{number}/tickets", method = RequestMethod.POST)
    public void addTicket(@RequestBody Ticket ticket, @PathVariable("number")Train train) {
        ticket.setTrain(train);
        ticketRepository.save(ticket);

        train.getTickets().add(ticket);
        trainRepository.save(train);
    }

    @RequestMapping(value = "/tickets/search", method = RequestMethod.GET)
    public List<Ticket> searchTicket(String start, String end, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departDate) {
        List<Train> trains =  trainRepository.findByStartAndEnd(start, end);
        List<Ticket> tickets = new ArrayList<>();
        trains.forEach(train ->
                tickets.addAll(ticketRepository.findByTrainAndDepartDate(train, departDate)));
        return tickets;
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buyTicket(@RequestParam("id") Ticket ticket, @RequestParam("type") Tessera.SeatType seatType, User user) {
        System.out.println(user);
        Tessera tessera = new Tessera();
        tessera.setDatetime(new Date());
        tessera.setTicket(ticket);
        tessera.setUser(user);
        tessera.setSeatType(seatType);
        tesseraRepository.save(tessera);
        user.getTesseras().add(tessera);
        userRepository.save(user);
    }
}
