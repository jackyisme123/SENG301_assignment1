package entity;

public class Vehicle {
    private String ownerId;
    private String type;
    private String plateNumber;
    private String make;
    private String model;
    private String fuel;
    private String manufactureDate;

    public Vehicle() {
        this.ownerId = null;
        this.type = null;
        this.plateNumber = null;
        this.make = null;
        this.model = null;
        this.fuel = null;
        this.manufactureDate = null;
    }

    public Vehicle(String ownerId, String type, String plateNumber, String make, String model, String fuel, String manufactureDate) {
        this.ownerId = ownerId;
        this.type = type;
        this.plateNumber = plateNumber;
        this.make = make;
        this.model = model;
        this.fuel = fuel;
        this.manufactureDate = manufactureDate;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }
}
