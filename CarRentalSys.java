import java.util.ArrayList;
import java.util.Scanner;

// Car Class
class Car {
  private String model;
  private String carId;

  public Car(String model, String carId) {
    this.model = model;
    this.carId = carId;
  }

  public String getModel() {
    return model;
  }

  public String getCarId() {
    return carId;
  }

  @Override
  public String toString() {
    return "[Model: " + model + ", Car ID: " + carId + "]";
  }
}

// Customer Class
class Customer {
  private String name;
  private String phone;
  private Car rentedCar;

  public Customer(String name, String phone, Car rentedCar) {
    this.name = name;
    this.phone = phone;
    this.rentedCar = rentedCar;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public Car getRentedCar() {
    return rentedCar;
  }

  @Override
  public String toString() {
    return "[Customer Name: " + name + ", Phone: " + phone + ", Rented Car: " + rentedCar + "]";
  }
}

// Rental System Class
class CarRentalSystem {
  private ArrayList<Car> carList;
  private ArrayList<Customer> customerList;

  public CarRentalSystem() {
    carList = new ArrayList<>();
    customerList = new ArrayList<>();
  }

  public void addCar(Car car) {
    carList.add(car);
    System.out.println("Car added successfully.");
  }

  public void rentCar(String name, String phone, String carId) {
    for (Car car : carList) {
      if (car.getCarId().equalsIgnoreCase(carId)) {
        customerList.add(new Customer(name, phone, car));
        carList.remove(car);
        System.out.println("Car rented successfully by: " + name);
        return;
      }
    }
    System.out.println("Car not available or does not exist.");
  }

  public void returnCar(String carId) {
    Customer customerToRemove = null;
    for (Customer customer : customerList) {
      if (customer.getRentedCar().getCarId().equalsIgnoreCase(carId)) {
        carList.add(customer.getRentedCar());
        customerToRemove = customer;
        break;
      }
    }
    if (customerToRemove != null) {
      customerList.remove(customerToRemove);
      System.out.println("Car returned successfully.");
    } else {
      System.out.println("Invalid return attempt. Car was not rented.");
    }
  }

  public void displayCars() {
    if (carList.isEmpty()) {
      System.out.println("No cars available.");
    } else {
      for (Car car : carList) {
        System.out.println(car);
      }
    }
  }
}

// Main Class
public class CarRentalSys {
  public static void main(String[] args) {
    CarRentalSystem CRS = new CarRentalSystem();
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("\n======= Car Rental System =======");
      System.out.println("\t1. Add Car");
      System.out.println("\t2. Display All Cars");
      System.out.println("\t3. Rent Car");
      System.out.println("\t4. Return Car");
      System.out.println("\t5. Exit");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      sc.nextLine(); // consume newline

      switch (choice) {
        case 1:
          System.out.print("Enter Car Model: ");
          String model = sc.nextLine();
          System.out.print("Enter Car ID: ");
          String carId = sc.nextLine();
          CRS.addCar(new Car(model, carId));
          break;

        case 2:
          System.out.println("\nAvailable Cars:");
          CRS.displayCars();
          break;

        case 3:
          System.out.print("Enter Your Name: ");
          String name = sc.nextLine();
          System.out.print("Enter Your Phone: ");
          String phone = sc.nextLine();
          System.out.print("Enter Car ID to Rent: ");
          String rentCarId = sc.nextLine();
          CRS.rentCar(name, phone, rentCarId);
          break;

        case 4:
          System.out.print("Enter Car ID to Return: ");
          String returnCarId = sc.nextLine();
          CRS.returnCar(returnCarId);
          break;

        case 5:
          System.out.println("Thank You for using Car Rental App ...");
          sc.close();
          return;

        default:
          System.out.println("Invalid choice! Please try again.");
      }
    }
  }
}
