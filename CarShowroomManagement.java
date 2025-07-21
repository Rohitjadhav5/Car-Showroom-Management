import java.io.FileWriter; 
import java.io.IOException; 
import java.util.LinkedList; 
import java.util.List; 
import java.util.Scanner; 
abstract class Vehicle { 
protected String brand; 
protected String model; 
protected int year; 
protected double price; 
public Vehicle(String brand, String model, int year, double price) { 
this.brand = brand; 
this.model = model; 
this.year = year; 
this.price = price; 
} 
public abstract String getInfo(); 
} 
class Car extends Vehicle { 
private int numDoors; 
public Car(String brand, String model, int year, double price, int numDoors) { 
super(brand, model, year, price); 
this.numDoors = numDoors; 
} 
public String getInfo() { 
return year + " " + brand + " " + model + " - Rs " + price + " - " + numDoors + " doors"; 
} 
} 
class Motorcycle extends Vehicle { 
private String type; 
public Motorcycle(String brand, String model, int year, double price, String type) { 
super(brand, model, year, price); 
this.type = type; 
} 
public String getInfo() { 
return year + " " + brand + " " + model + " - Rs " + price + " - " + type; 
} 
} 
class Booking { 
private String customerName; 
private Vehicle vehicle; 
public Booking(String customerName, Vehicle vehicle) { 
this.customerName = customerName; 
this.vehicle = vehicle; 
} 
public String getCustomerName() { 
return customerName; 
} 
public Vehicle getVehicle() { 
return vehicle; 
} 
} 
class CarShowroom { 
private List<Vehicle> vehicles; 
private List<Booking> bookings; 
public CarShowroom() { 
vehicles = new LinkedList<>(); 
bookings = new LinkedList<>(); 
} 
public void addVehicle(Vehicle vehicle) { 
vehicles.add(vehicle); 
} 
public void deleteVehicle(int index) throws IndexOutOfBoundsException { 
if (index < 0 || index >= vehicles.size()) { 
throw new IndexOutOfBoundsException("Invalid index!"); 
} 
vehicles.remove(index); 
System.out.println("Vehicle deleted successfully."); 
} 
public void updateVehicle(int index, Vehicle updatedVehicle) throws  
IndexOutOfBoundsException { 
if (index < 0 || index >= vehicles.size()) { 
throw new IndexOutOfBoundsException("Invalid index!"); 
} 
vehicles.set(index, updatedVehicle); 
System.out.println("Vehicle updated successfully."); 
} 
public void bookVehicle(String customerName, int index) throws  
IndexOutOfBoundsException { 
if (index < 0 || index >= vehicles.size()) { 
throw new IndexOutOfBoundsException("Invalid index!"); 
} 
Vehicle vehicle = vehicles.get(index); 
bookings.add(new Booking(customerName, vehicle)); 
System.out.println("Booking made successfully."); 
} 
public void viewBookings() { 
System.out.println("\nBookings:"); 
for (Booking booking : bookings) { 
System.out.println("Customer: " + booking.getCustomerName() + ", Vehicle: " +  
booking.getVehicle().getInfo()); 
} 
} 
public void saveBookingsToFile(String filename) { 
try (FileWriter writer = new FileWriter(filename)) { 
for (Booking booking : bookings) { 
writer.write(booking.getCustomerName() + "," + booking.getVehicle().getInfo() +  
"\n"); 
} 
System.out.println("Bookings saved to file successfully."); 
} catch (IOException e) { 
System.out.println("Error occurred while saving bookings to file: " + e.getMessage()); 
} 
} 
public void viewVehicles() { 
System.out.println("\nAvailable Vehicles:"); 
for (int i = 0; i < vehicles.size(); i++) { 
System.out.println((i + 1) + ". " + vehicles.get(i).getInfo()); 
} 
} 
public Vehicle getVehicle(int index) throws IndexOutOfBoundsException { 
if (index < 0 || index >= vehicles.size()) { 
throw new IndexOutOfBoundsException("Invalid index!"); 
} 
return vehicles.get(index); 
} 
} 
public class CarShowroomManagement{ 
public static void main(String[] args) { 
CarShowroom showroom = new CarShowroom(); 
Scanner scanner = new Scanner(System.in); 
System.out.println("Welcome to the Car Showroom!"); 
while (true) { 
try { 
System.out.println("\nMenu:"); 
System.out.println("1. View available vehicles"); 
System.out.println("2. Inquire about a vehicle"); 
System.out.println("3. Add a vehicle"); 
System.out.println("4. Delete a vehicle"); 
System.out.println("5. Update a vehicle"); 
System.out.println("6. Book a vehicle"); 
System.out.println("7. View bookings"); 
System.out.println("8. Save bookings to file"); 
System.out.println("9. Exit"); 
System.out.print("Enter your choice: "); 
int choice = scanner.nextInt(); 
switch (choice) { 
case 1: 
showroom.viewVehicles(); 
break; 
case 2: 
System.out.print("\nEnter the index of the vehicle you want to inquire about: "); 
int index = scanner.nextInt(); 
Vehicle vehicle = showroom.getVehicle(index - 1); 
if (vehicle != null) { 
System.out.println("\nInquiry:"); 
System.out.println(vehicle.getInfo()); 
} 
break; 
case 3: 
System.out.println("\nAdding a new vehicle:"); 
System.out.print("Enter Brand: "); 
String brand = scanner.next(); 
System.out.print("Enter model: "); 
String model = scanner.next(); 
System.out.print("Enter year: "); 
int year = scanner.nextInt(); 
System.out.print("Enter price: "); 
double price = scanner.nextDouble(); 
System.out.println("Is it a car or motorcycle? Enter C for Car, M for Motorcycle: "); 
char vehicleType = scanner.next().charAt(0); 
if (vehicleType == 'C') { 
System.out.print("Enter number of doors: "); 
int numDoors = scanner.nextInt(); 
showroom.addVehicle(new Car(brand, model, year, price, numDoors)); 
} else if (vehicleType == 'M') { 
System.out.print("Enter type: "); 
String type = scanner.next(); 
showroom.addVehicle(new Motorcycle(brand, model, year, price, type)); 
} else { 
System.out.println("Invalid choice!"); 
} 
break; 
case 4: 
System.out.print("\nEnter the index of the vehicle you want to delete: "); 
int deleteIndex = scanner.nextInt(); 
showroom.deleteVehicle(deleteIndex - 1); 
break; 
case 5: 
System.out.print("\nEnter the index of the vehicle you want to update: "); 
int updateIndex = scanner.nextInt(); 
Vehicle oldVehicle = showroom.getVehicle(updateIndex - 1); 
if (oldVehicle != null) { 
System.out.println("\nUpdating Vehicle:"); 
System.out.print("Enter brand: "); 
String newBrand = scanner.next(); 
System.out.print("Enter model: "); 
String newModel = scanner.next(); 
System.out.print("Enter year: "); 
int newYear = scanner.nextInt(); 
System.out.print("Enter price: "); 
double newPrice = scanner.nextDouble(); 
System.out.println("Is it a car or motorcycle? Enter C for Car, M for Motorcycle: "); 
char newVehicleType = scanner.next().charAt(0); 
if (newVehicleType == 'C') { 
System.out.print("Enter number of doors: "); 
int newNumDoors = scanner.nextInt(); 
showroom.updateVehicle(updateIndex - 1, new Car(newBrand, newModel,newYear, 
newPrice, newNumDoors)); 
} else if (newVehicleType == 'M') { 
System.out.print("Enter type: "); 
String newType = scanner.next(); 
showroom.updateVehicle(updateIndex - 1, new Motorcycle(newBrand, newModel, 
newYear, newPrice, newType)); 
} else { 
System.out.println("Invalid choice!"); 
} 
} 
break; 
case 6: 
System.out.println("\nBooking a vehicle:"); 
System.out.print("Enter your name: "); 
scanner.nextLine(); // Consume newline character 
String customerName = scanner.nextLine(); 
showroom.viewVehicles(); 
System.out.print("Enter the index of the vehicle you want to book: "); 
int bookIndex = scanner.nextInt(); 
showroom.bookVehicle(customerName, bookIndex - 1); 
break; 
case 7: 
showroom.viewBookings(); 
break; 
case 8: 
System.out.print("\nEnter filename to save bookings: "); 
scanner.nextLine();  
String filename = scanner.nextLine(); 
showroom.saveBookingsToFile(filename); 
break; 
case 9: 
System.out.println("Thank you for visiting the Car Showroom!"); 
System.exit(0); 
default: 
System.out.println("Invalid choice!"); 
} 
} catch (Exception e) { 
System.out.println("Error: " + e.getMessage()); 
scanner.nextLine(); 
} 
} 
} 
} 