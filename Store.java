//Parent class called Store
public class Store {
   // Private instance variables to control the data  
   private Customer[] customer;
   private double total; 
   private int maxCustomer = 1000;
   public static int size = 0;
   
   //Constructor for store pertaining to the customer array
   public Store() {
      this.customer = new Customer[maxCustomer];
   }
   
   // Accessor methods
   public Customer getCustomer(int position) {
      if (position < 0 || position > this.size) {
         throw new IllegalArgumentException("Customer does not exist");
      }
      return customer[position];
   } 
   
   public void setACustomer(Customer customer) {
      if (this.size >= maxCustomer) {
         throw new IllegalArgumentException("No more customer can be added");
      }
      this.customer[this.size] = customer;
   } 
   
   public double getTotal() {
      return this.total = total;
   }
   
   // Mutator method to validate with an exception
   public void setTotal(double total) {
      if (total < 0) {
         throw new NumberFormatException();
      }
      this.total = total;
   }
   
   public String toString() {
      String output = "";
      for (int i = 0; i < size; i++) {
         output += customer[i].toString() + "\n";
      }
      return output;
   }
}