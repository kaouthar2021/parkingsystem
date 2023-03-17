package com.parkit.parkingsystem.constants;

public class DBConstants {

    public static final String GET_NEXT_PARKING_SPOT = "select min(PARKING_NUMBER) from parking where AVAILABLE = 1 and TYPE = ?";
    // chercher un parking en utilisant le nombre de parking pour vérifier s'il est disponible ou pas.
    public static final String  GET_PARKING_SPOT = "select p.PARKING_NUMBER, p.TYPE , p.AVAILABLE from parking p where PARKING_NUMBER = ?";
    public static final String UPDATE_PARKING_SPOT = "update parking set available = ? where PARKING_NUMBER = ?";

    public static final String SAVE_TICKET = "insert into ticket(PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME) values(?,?,?,?,?)";
    public static final String UPDATE_TICKET = "update ticket set PRICE=?, OUT_TIME=? where ID=?";
    public static final String GET_TICKET = "select t.PARKING_NUMBER, t.ID, t.PRICE, t.IN_TIME, t.OUT_TIME, p.TYPE from ticket t,parking p where p.parking_number = t.parking_number and t.VEHICLE_REG_NUMBER=? order by t.IN_TIME  limit 1";
      // compter le nombre d'occurences pour un utilisateur pour vérifier si c'est in utilisateur reccurent ou pas
    public static final String RETURNING_CUSTOMER = "select count(ID) from ticket where VEHICLE_REG_NUMBER = ?";

}
