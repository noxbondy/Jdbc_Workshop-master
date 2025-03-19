package se.lexicon;

import se.lexicon.dao.CityDao;
import se.lexicon.dao.CityDaoImpl;
import se.lexicon.model.City;

import javax.naming.Name;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


/**
 * Represents the entry point of the application.
 */
public class Main {
    public static void main(String[] args) {
        // TODO: Needs completion
        CityDao cityDao = new CityDaoImpl();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "Nou123456@")) {
            System.out.println("Connected to MySQL database!");
            cityDao.findAll().forEach(city -> System.out.println(city.getName()));
            cityDao.findById(3).ifPresent(city -> System.out.println("City found: " + city.getName()));
            cityDao.findByCode("ALB").forEach(city -> System.out.println(city.getName()));
            cityDao.findByName("Alger").forEach(city -> System.out.println(city.getName()));



            City newCity = new City(1100,"Bangladesh","DHk","Dhaka",12000);
            cityDao.save(newCity);

        } catch (SQLException e) {
            e.printStackTrace();
        }
   // insert
        City newCity = new City(1012, "Bangladesh", "USA", "Dhaka", 500000);
        City savedCity = cityDao.save(newCity);
        System.out.println("Saved City: " + savedCity.getId());

        // update by id
        newCity.setName("UpdatedCity");
        cityDao.update(newCity);
        System.out.println("updatecity"+savedCity.getId());

        // delete by id
        cityDao.deleteById(33);

    }
}
