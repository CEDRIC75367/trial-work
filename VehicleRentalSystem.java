import java.util.ArrayList;


abstract class Vehicle {
    protected String vehicleId;
    protected String brand;
    protected String model;
    protected boolean isAvailable;

    public Vehicle(String vehicleId, String brand, String model) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.isAvailable = true; // Default to available
    }

    public String getVehicleId() { return vehicleId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public boolean isAvailable() { return isAvailable; }

    public void rentVehicle() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Vehicle " + vehicleId + " rented successfully.");
        } else {
            throw new IllegalStateException("Vehicle " + vehicleId + " is already rented.");
        }
    }

    public void returnVehicle() {
        isAvailable = true;
        System.out.println("Vehicle " + vehicleId + " returned successfully.");
    }

    public abstract double calculateRentalCost(int days);
}


class Car extends Vehicle {
    private int seatingCapacity;

    public Car(String vehicleId, String brand, String model, int seatingCapacity) {
        super(vehicleId, brand, model);
        this.seatingCapacity = seatingCapacity;
    }

    public int getSeatingCapacity() { return seatingCapacity; }

    @Override
    public double calculateRentalCost(int days) {
        return 1000 * days + seatingCapacity * 50;
    }
}


class Bike extends Vehicle {
    private int engineCapacity;

    public Bike(String vehicleId, String brand, String model, int engineCapacity) {
        super(vehicleId, brand, model);
        this.engineCapacity = engineCapacity;
    }

    public int getEngineCapacity() { return engineCapacity; }

    @Override
    public double calculateRentalCost(int days) {
        return 500 * days + engineCapacity / 10.0;
    }
}


class RentalManager {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void displayAvailableVehicles() {
        System.out.println("\nAvailable Vehicles:");
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                System.out.println(v.getVehicleId() + " - " + v.getBrand() + " " + v.getModel());
            }
        }
    }

    public Vehicle getVehicleById(String vehicleId) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equals(vehicleId)) {
                return v;
            }
        }
        return null;
    }

    public void rentVehicle(String vehicleId, int days) {
        Vehicle vehicle = getVehicleById(vehicleId);
        if (vehicle != null && vehicle.isAvailable()) {
            vehicle.rentVehicle();
            System.out.println("Total Rental Cost: GHS " + vehicle.calculateRentalCost(days));
        } else {
            System.out.println("Vehicle not available for rent.");
        }
    }

    public void returnVehicle(String vehicleId) {
        Vehicle vehicle = getVehicleById(vehicleId);
        if (vehicle != null && !vehicle.isAvailable()) {
            vehicle.returnVehicle();
        } else {
            System.out.println("Vehicle not found or already returned.");
        }
    }
}


public class VehicleRentalSystem {
    public static void main(String[] args) {
        RentalManager rentalManager = new RentalManager();


        rentalManager.addVehicle(new Car("C001", "Toyota", "Corolla", 5));
        rentalManager.addVehicle(new Car("C002", "Honda", "Civic", 4));
        rentalManager.addVehicle(new Bike("B001", "Yamaha", "R15", 150));
        rentalManager.addVehicle(new Bike("B002", "Suzuki", "Gixxer", 250));


        rentalManager.displayAvailableVehicles();


        System.out.println("\nRenting a Toyota Corolla for 3 days...");
        rentalManager.rentVehicle("C001", 3);


        System.out.println("\nRenting a Yamaha R15 for 2 days...");
        rentalManager.rentVehicle("B001", 2);


        rentalManager.displayAvailableVehicles();


        System.out.println("\nReturning the Toyota Corolla...");
        rentalManager.returnVehicle("C001");


        rentalManager.displayAvailableVehicles();
    }
}