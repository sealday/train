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

import java.util.*;

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
    public void buyTicket(@RequestBody HashMap<String, String> map, User user) {
        Long ticketId = Long.parseLong(map.get("ticket"));
        Tessera.SeatType seatType = Tessera.SeatType.valueOf(map.get("seatType"));
        Ticket ticket = ticketRepository.findOne(ticketId);
        Tessera tessera = new Tessera();
        tessera.setSeatType(seatType);
        tessera.setTicket(ticket);
        tessera.setDatetime(new Date());
        tessera.setUser(user);
        tessera = tesseraRepository.save(tessera);
        user.getTesseras().add(tessera);
        userRepository.save(user);
    }

    /**
     * 获得用户购买的票
     * @param user
     * @return
     */
    @RequestMapping(value = "/tesseras", method = RequestMethod.GET)
    public List<Tessera> getTesseras(User user) {
        return tesseraRepository.findByUser(user);
    }

    /**
     *
     * 取消订单
     */
    @RequestMapping(value = "/tesseras/{id}", method = RequestMethod.DELETE)
    public void returnTessera(@PathVariable("id") Tessera tessera) {
        tessera.setTesseraStatus(Tessera.TesseraStatus.RETURNED);
        tesseraRepository.save(tessera);
    }
}
