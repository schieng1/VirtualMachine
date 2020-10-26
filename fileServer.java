public class fileServer {
   // Private instance variables to control the data
   private String storage;
   private String media;
   private int tera;
   private double total;
   
   //Default constructor of fileServer
   public fileServer() {
   }
   
   //Specific constructor of fileServer
   public fileServer(String storage, String media) {
      if (storage == null || storage.equals("") || storage.equals(" ")) {
         throw new IllegalArgumentException("storage must be provided");
      }
      if (media == null || media.equals("") || media.equals(" ")) {
         throw new IllegalArgumentException("media must be provided");
      }
      if (tera < 0 || tera > 1024) {
         throw new NumberFormatException("Must be between 0 - 1024");
      }

      this.storage = storage;
      this.media = media;
      this.tera = tera;
   }
   
   public fileServer(fileServer fileServer) {
      this.storage = fileServer.getStorage();
      this.media = fileServer.getMedia();
      this.tera = fileServer.getNumTera();
   }
   // Accessor methods
   public String getStorage() {
      return this.storage;
   }
   
   public int getNumTera() {
      return this.tera;
   }
   
   public String getMedia() {
      return this.media;
   }
   
   public double getTotal() {
      return this.total = total;
   }
   
   // Mutator method to validate with an exception
   public void setStorage(String storage) {
      if (storage == null || storage.equals("") || storage.equals(" ")) {
         throw new IllegalArgumentException("storage must be provided");
      }
      this.storage = storage;
   }
   
   public void setNumTera(int tera) {
      if (tera < 0 || tera > 1024) {
         throw new NumberFormatException("Must be between 0 - 1024");
      }
      this.tera = tera;
   }

   
   public void setMedia(String media) {
      if (media == null || media.equals("") || media.equals(" ")) {
         throw new IllegalArgumentException("media must be provided");
      }
      this.media = media;
   }
   
   public double setTotal(String media) {
      if (media.equalsIgnoreCase("SSD")) {
         total = 5.0 * tera;
      }
      if (media.equalsIgnoreCase("Magnetic")) {
         total = 2.0 * tera;
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
      fileServer fileServer = (fileServer)o;
      return this.getStorage().equals(fileServer.getStorage()) && this.getMedia().equals(fileServer.getMedia()) && this.getNumTera() == (fileServer.getNumTera());
   }

   // String representation of the instance variables
   public String toString() {
      return "Storage media: " + this.getMedia() + "\nStorage: " + this.getStorage() + "\nNumber of terabytes: " + this.getNumTera() + "\nMonthly Cost: " + String.format("%.2f", this.setTotal(media) + "\n");
   }
}