package se.lexicon.model;

/**
 * Represents a City entity based on the 'city' table in the 'world' database.
 */
public class City {
    // TODO: Needs completion

    private int id ;
    private  String Name ;
    private String CountryCode;
    private String District;
    private double Population;

    // Constructor

    public City(int id, String name, String countryCode, String district, double population) {
        this.id = id;
        this. Name = name;
        this.CountryCode = countryCode;
        this. District = district;
        this. Population = population;
    }


    // getter


    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getDistrict() {
        return District;
    }

    public double getPopulation() {
        return Population;
    }

    // setter

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public void setPopulation(double population) {
        Population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", District='" + District + '\'' +
                ", Population=" + Population +
                '}';
    }
}
