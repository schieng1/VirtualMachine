public class Customer {
    // Private instance variables to control the data 
    private String ID;
    private String name;
    private String phoneNum;
    private String email;
    private String VMType;
    private String discount;
    private webServer webServer = null;
    private fileServer fileServer = null;
    private bitcoinMiner bitcoinMiner = null;
    
    //Default constructor of Customer
    public Customer() {
    }
    
    //Specific constructor of webServer
    public Customer(String ID, String name, String phoneNum, String email, String VMType, String discount, webServer webServer, fileServer fileServer, bitcoinMiner bitcoinMiner) {
      if (ID == null || ID.equals("") || ID.equals(" ")) {
            throw new IllegalArgumentException("ID must be provided");
      }
      if (name == null || name.equals("") || name.equals(" ")) {
            throw new IllegalArgumentException("name must be provided");
      }
      if (phoneNum == null || phoneNum.equals("") || phoneNum.equals(" ") || phoneNum.charAt(3) != ('.') || phoneNum.charAt(7) != ('.') || phoneNum.length() != 12) {
            throw new IllegalArgumentException("phone number must be provided");
      }
      if (email == null || email.equals("") || email.equals(" ")) {
            throw new IllegalArgumentException("email must be provided");
      }
      if (VMType == null || VMType.equals("") || VMType.equals(" ")) {
            throw new IllegalArgumentException("the virtual machine type must be provided");
      } 
      if (discount == null || discount.equals("") || discount.equals(" ")) {
            throw new IllegalArgumentException("please enter yes or no");
      } 
        this.ID = ID;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.VMType = VMType;
        this.discount = discount;
        this.webServer = webServer;
        this.fileServer = fileServer;
        this.bitcoinMiner = bitcoinMiner;
    }
    // Accessor methods
    public String getID() {
        return this.ID = ID;
    }
    
    public String getName() {
        return this.name = name;
    }
    
    public String getPhoneNum() {
        return this.phoneNum = phoneNum; 
    }
    
    public String getEmail() {
        return this.email = email;
    }
    
    public String getVMType() {
        return this.VMType = VMType;
    }
    public String getDiscount() {
        return this.discount = discount;
    }
    
    public webServer getWebServer() {
        return this.webServer;
    }
    
    public fileServer getFileServer() {
        return this.fileServer;
    }
    
    public bitcoinMiner getBitcoinMiner() {
        return this.bitcoinMiner;
    }
    
    // Mutator method to validate with an exception
    public void setID(String ID) {
        if (ID == null || ID.equals("") || ID.equals(" ")) {
            throw new IllegalArgumentException("ID must be provided");
        }
        this.ID = ID;
    }
    
    public void setName(String name) {
        if (name == null || name.equals("") || name.equals(" ")) {
            throw new IllegalArgumentException("name must be provided");
        }
        this.name = name;
    }
    
    public void setPhoneNum(String phoneNum) {
        if (phoneNum == null || phoneNum.equals("") || phoneNum.equals(" ") || phoneNum.charAt(3) != ('.') || phoneNum.charAt(7) != ('.') || phoneNum.length() != 12) {
            throw new IllegalArgumentException("phone number must be provided");
        }
        this.phoneNum = phoneNum; 
    }
    
    public void setEmail(String email) {
        if (email == null || email.equals("") || email.equals(" ")) {
            throw new IllegalArgumentException("email must be provided");
        }
        this.email = email;
    }
    
    public void setVMType(String VMType) {
        if (VMType == null || VMType.equals("") || VMType.equals(" ")) {
            throw new IllegalArgumentException("the virtual machine type must be provided");
        } 
        this.VMType = VMType;
    }
    
    public void setWebServer(webServer webServer) {
        this.webServer = webServer;
    }
    
    public void setFileServer(fileServer fileServer) {
        this.fileServer = fileServer;
    }
    
    public void setBitcoinMiner(bitcoinMiner bitcoinMiner) {
        this.bitcoinMiner = bitcoinMiner;
    }
    
    public void setDiscount(String discount) {
        if (discount == null || discount.equals("") || discount.equals(" ")) {
            throw new IllegalArgumentException("please enter yes or no");
        } 
        this.discount = discount;
    }
    // Equals method to compare two objects
    public boolean equals(Object o) {
      if (o == null) {
         return false;
      }
      if (getClass() != o.getClass()) {
         return false;
      }
      Customer Customer = (Customer)o;
      return this.getName().equals(Customer.getName()) && this.getPhoneNum() == (Customer.getPhoneNum());
    }
    
    // String representation of the instance variables
    public String toString() {
        return "\nID: " + this.getID() + "\nname: " + this.getName() + "\nPhone Number: " + this.getPhoneNum() + "\nEmail: " + this.getEmail() + "\nDiscount: " + this.getDiscount() + "\nVirtual Machine Type: " + this.getVMType();
    }
    
}