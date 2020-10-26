public class bitcoinMiner {
   // Private instance variables to control the data
   private int numGPU;
   private String brand;
   private int maxGPU = 8; 
   private double GPUCost = 10.0;
   private double NviadaCost = 15.0;
   private double total;
   
   //Default constructor of bitcoinMiner
   public bitcoinMiner() {
   }
   
   //Specific constructor of bitcoinMiner
   public bitcoinMiner(int numGPU, String brand) {
      if (numGPU < 0 || numGPU > maxGPU) {
         throw new NumberFormatException("number of GPU must be between 1 - 8");
      }
      if (brand == null || brand.equals("") || brand.equals(" ")) {
         throw new NumberFormatException("brand must be provided");
      }
      this.numGPU = numGPU;
      this.brand = brand;

   }
   
   public bitcoinMiner(bitcoinMiner bitcoinMiner) {
      this.numGPU = bitcoinMiner.getNumGPU();
      this.brand = bitcoinMiner.getBrand();
   }
   // Accessor methods
   public int getNumGPU() {
      return this.numGPU = numGPU;
   }
   
   public String getBrand() {
      return this.brand = brand;
   }
   
   public double getTotal() {
      return this.total = total;
   }
  
   // Mutator method to validate with an exception
   public void setNumGPU(int numGPU) {
      if (numGPU < 0 || numGPU > maxGPU) {
         throw new NumberFormatException("number of GPU must be between 1 - 8");
      }
      this.numGPU = numGPU;
   }
   
   public void setBrand(String brand) {
      if (brand == null || brand.equals("") || brand.equals(" ")) {
       throw new NumberFormatException("brand must be provided");
      }
      this.brand = brand;
   }
   
   public double setTotal() {
      if(brand.equalsIgnoreCase("Nvidia")) {
         total = 15.0;
      }
      if(brand.equalsIgnoreCase("AMD")) {
         total = 10;
      }
      return total; 
   }
   
   // Equals method to compare two objects
   public boolean equals(Object o) {
      if (o == null) {
         return false;
      }
      if (getClass() != o.getClass()) {
         return false;
      }
      bitcoinMiner bitcoinMiner = (bitcoinMiner)o;
      return this.getBrand().equals(bitcoinMiner.getBrand()) && this.getNumGPU() == (bitcoinMiner.getNumGPU());
   }
   
   // String representation of the instance variables
   public String toString() {
      return "Number of GPU: " + this.getNumGPU() + "\nBrand: " + this.getBrand() + "\nMonthly Cost: " + String.format("%.2f", this.setTotal() + "\n");
   }
}