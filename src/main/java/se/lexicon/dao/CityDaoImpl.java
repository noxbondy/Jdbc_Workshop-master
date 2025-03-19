package se.lexicon.dao;

import se.lexicon.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the implementation of CityDao for interacting with the 'city' table in the database.
 */
public class CityDaoImpl implements CityDao{
    // TODO: Needs completion

    private static final String URL = "jdbc:mysql://localhost:3306/world";
    private static final String USER = "root";
    private static final String PASSWORD = "Nou123456@";

    @Override
    public Optional<City> findById(int id) {
        String sql = "SELECT * FROM city WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<City> findByCode(String code) {
        return findCities("SELECT * FROM city WHERE countryCode = ?", code);
    }

    @Override
    public List<City> findByName(String name) {
        return findCities("SELECT * FROM city WHERE name LIKE ?", "%" + name + "%");
    }

    @Override
    public List<City> findAll() {
        return findCities("SELECT * FROM city");
    }

    @Override
    public City save(City city) {
        String sql = "INSERT INTO city (name, countryCode, district, population) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, city.getName());
            stmt.setString(2, city.getCountryCode());
            stmt.setString(3, city.getDistrict());
            stmt.setDouble(4, city.getPopulation());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                city.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public void update(City city) {
        String sql = "UPDATE city SET name = ?, countryCode = ?, district = ?, population = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, city.getName());
            stmt.setString(2, city.getCountryCode());
            stmt.setString(3, city.getDistrict());
            stmt.setDouble(4, city.getPopulation());
            stmt.setInt(5, city.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM city WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<City> findCities(String sql, String... params) {
        List<City> cities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setString(i + 1, params[i]);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cities.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    private City mapRow(ResultSet rs) throws SQLException {
        return new City(rs.getInt("id"), rs.getString("name"), rs.getString("countryCode"), rs.getString("district"), rs.getDouble("population"));
    }
}

