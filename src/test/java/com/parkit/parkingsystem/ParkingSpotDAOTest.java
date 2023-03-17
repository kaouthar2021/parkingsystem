package com.parkit.parkingsystem;


import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.parkit.parkingsystem.constants.ParkingType.CAR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class ParkingSpotDAOTest {
    private static DataBaseTestConfig  dataBaseTestConfig = new DataBaseTestConfig();

    private static ParkingSpotDAO parkingSpotDAO;



    @BeforeEach
    private void setUp() throws Exception   {
         parkingSpotDAO= new ParkingSpotDAO();
        parkingSpotDAO.dataBaseConfig=dataBaseTestConfig;

    }
    @AfterAll
    private static void tearDown(){

    }

    @Test
    public void getNextAvailableSlotTest() throws SQLException {

        final int result = parkingSpotDAO.getNextAvailableSlot(CAR);

        assertEquals(2, result);


    }
   @Test
    public void updateParkingTest() throws SQLException{
       ParkingSpot parkingSpotTest = new ParkingSpot(1, CAR, false);
       final boolean  result = parkingSpotDAO.updateParking(parkingSpotTest);

       assertTrue(result);

   }
   @Test
   public void findParkingByIdTest(){
        final int result = parkingSpotDAO.findParkingById(2).getId();

       assertEquals(result,2);

   }
}
