package manager;

import domain.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {

    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);

    Ticket ticket1 = new Ticket(40, 200, "BAY", "EKV", 1500);
    Ticket ticket2 = new Ticket(60, 350, "ABT", "ABX", 12000);
    Ticket ticket3 = new Ticket(10, 250, "BAY", "EKV", 2500);
    Ticket ticket4 = new Ticket(50, 450, "AXG", "GVX", 5000);
    Ticket ticket5 = new Ticket(20, 300, "AXD", "AZP", 3000);
    Ticket ticket6 = new Ticket(30, 400, "BAY", "GVX", 6000);

    @BeforeEach
    public void setUp() {
        repository.save(ticket1);
        repository.save(ticket2);
        repository.save(ticket3);
        repository.save(ticket4);
        repository.save(ticket5);
        repository.save(ticket6);
    }

    @Test
    public void shouldSortByPrice() {
        Ticket[] expected = {ticket1, ticket3, ticket5, ticket2, ticket6, ticket4};
        Ticket[] actual = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};

        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFromTo() {
        Ticket[] expected = {ticket1, ticket3};
        Ticket[] actual = manager.searchTicket("BAY", "EKV");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNoTicket() {
        Ticket[] expected = {};
        Ticket[] actual = manager.searchTicket("HTF", "BAY");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchLine() {
        Ticket[] expected = {ticket4};
        Ticket[] actual = manager.searchTicket("AXG", "GVX");

        assertArrayEquals(expected, actual);
    }

}
