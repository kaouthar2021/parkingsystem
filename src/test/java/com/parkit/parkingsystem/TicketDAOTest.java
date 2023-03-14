package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TicketDAOTest {
    private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private  static  TicketDAO TicketDAO;

    @BeforeEach
    private void setUp() throws Exception{
        TicketDAO = new TicketDAO();

        TicketDAO.dataBaseConfig = dataBaseTestConfig;

    }

    @AfterAll
    private static void tearDown(){

    }

    @Test
    public void saveTicketTest() throws SQLException {
        Ticket ticket = new Ticket();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        Date inTime = new Date();
        inTime.setTime(System.currentTimeMillis() - (60 * 60 * 1000));
        ticket.setInTime(inTime);
        ticket.setPrice(1.5);
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber("ABCDEF");

        TicketDAO.saveTicket(ticket);

        Assertions.assertFalse(TicketDAO.saveTicket(ticket));


    }

    @Test
    public void getTicketTest() {
        Ticket ticket = new Ticket();
        Date inTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setPrice(0);
        inTime.setTime(System.currentTimeMillis() - (60 * 60 * 1000));
        ticket.setInTime(inTime);
        TicketDAO.saveTicket(ticket);

        Ticket ticket2 = TicketDAO.getTicket("ABCDEF");

        Assertions.assertEquals(ticket.getVehicleRegNumber(), ticket2.getVehicleRegNumber());
    }

    @Test
    public void updateTicketTest() {
        Ticket ticket = new Ticket();
        Date inTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setPrice(0);
        inTime.setTime(System.currentTimeMillis() - (60 * 60 * 1000));
        ticket.setInTime(inTime);
        TicketDAO.saveTicket(ticket);

        Date outTime = new Date();
        ticket.setOutTime(outTime);
        ticket.setPrice(1.5);
        TicketDAO.updateTicket(ticket);

        Assertions.assertNotEquals(TicketDAO.updateTicket(ticket), TicketDAO.saveTicket(ticket));
    }
    @Test
    public void recurrentNumberUserTest() {
        Ticket ticket = new Ticket();
        Date inTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber("EFGH");
        ticket.setPrice(0);
        inTime.setTime(System.currentTimeMillis() - (60 * 60 * 1000));
        ticket.setInTime(inTime);
        TicketDAO.saveTicket(ticket);

        Ticket ticket2 = TicketDAO.getTicket("EFGH");
        TicketDAO.saveTicket(ticket2);

        Assertions.assertEquals(TicketDAO.recurrentNumberUser("EFGH"), 2);
    }
}
