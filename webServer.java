public class webServer {
   // Private instance variables to control the data
   private int numGigs;
   private double costPerGig = 10.0;
   private double total = 0;
   
   //Default constructor of webServer
   public webServer() {
   }
   
   //Specific constructor of webServer
   public webServer(int numGigs) {
      if (numGigs < 0 || numGigs > 8) {
         throw new NumberFormatException("number of GB must be provided");
      }
      this.numGigs = numGigs;
   }
   
   public webServer(webServer webServer) {
      this.numGigs = webServer.getNumGigs();
   }
   
   // Accessor methods
   public int getNumGigs() {
      return this.numGigs = numGigs;
   }
   
   public double getTotal() {
      return this.total = total;
   }
   
   // Mutator method to validate with an exception
   public void setNumGigs(int numGigs) {
      if (numGigs < 0 || numGigs > 120) {
         throw new NumberFormatException("number of GB must be provided");
      }
      this.numGigs = numGigs;
   }
   
   public double setTotal() {
      int num = 0;
      if (numGigs == 8) {
         num = 1;
      }
      else if (numGigs == 16) {
         num = 2;
      }
      else if (numGigs == 24) {
         num = 3;
      }
      else if (numGigs == 36) {
         num = 4;
      }
      else if (numGigs == 48) {
         num = 5;
      }
      else if (numGigs == 56) {
         num = 6;
      }
      else if (numGigs == 64) {
         num = 7;
      }
      else if (numGigs == 72) {
         num = 8;
      }
      else if (numGigs == 80) {
         num = 9;
      }
      else if (numGigs == 88) {
         num = 10;
      }
      else if (numGigs == 96) {
         num = 11;
      }
      else if (numGigs == 104) {
         num = 12;
      }
      else if (numGigs == 112) {
         num = 13;
      }
      else if (numGigs == 120) {
         num = 14;
      }
      else if (numGigs == 128) {
         num = 15;
      }
         
      return total = num * costPerGig; 
   }
   
   // Equals method to compare two objects
   public boolean equals(Object o) {
      if (o == null) {
         return false;
      }
      if (getClass() != o.getClass()) {
         return false;
      }
      webServer webServer = (webServer)o;
      return this.getNumGigs() == (webServer.getNumGigs());
   }

   
   // String representation of the instance variables
   public String toString() {
      return "Number of GBs: " + this.getNumGigs() + "\nMonthly Cost: " + String.format("%.2f", this.setTotal() + "\n"); 
   }
}