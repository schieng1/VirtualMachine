/*
 Description:
 
 This supports companies and their method of obtaining detailed customer reports through a program that will calculate and display a report.
 This programming assignment requires to output to print a report consisted of the customer, and the virtual machines they have chosen; web server, file server,
 or bitcoin miner and all the questions pertained to that virtual machine. It will first ask the user input for the customer's name, phone number, email, discount,
 and the virtual type. Afterwards, it will be asked certain questions for web server if web server is chosen such as the number of gigabtyes which cost $10 for each 
 8 gb increase. The maximum of storage of gigabyte is 128 GB. If file server is chosen then it will prompt the user to enter the type of storage which is either
 block or object storage. Afterwards they must enter in the type of storage media; SSD which is $5 per terabyte per month or magnetic storage which is $2 per terabyte
 per month. Then they will enter the amount of terabyte they would like up to 1024 terabyte excluding the additional 20 GB of standard SSD storage. If bitcoin miner
 is entered, then they will provided the number of GPU and the brand they would like. Afterwards the user will quit or if the program reached a capacity of 999
 customers. It will then print out all of the customers detailed report pertaining all of their information, the total monthly fee, and the monthly fee if they have a
 discount. After the customer report is shown, the customer statistic report will appear that will include informatio regarding the total number of customers who have
 bitcoin miner machines, file server machines, and web server machines. It will also show the total amount of monthly fee overrall, the average monthly fee collected
 from all of the customers, and how many memory, disk space, and GPUs are used.
 
 Web Server
 Each 8 GB = $10 * num = total
 
 File Server
 5  numTerabyte or 2  numTerabyte = total
 
 Bitcoin Miner  
 numGPU * 10 or $15 = total
 
 DISCOUNT
 total * 0.8
 
 
 */
import java.io.*;
import javax.swing.JOptionPane;
public class Report {
    public static void main(String[] args) {
        // mainline method used to only call each method/display
        Store store = new Store();
        store = getInfo(store);
        JOptionPane.showMessageDialog(null, Display(store));
        JOptionPane.showMessageDialog(null, displayStats(store));
    }
    
    /*
     Calls each method to create each customer
     @param Store store
     @return type is Store
     */
    
    public static Store getInfo(Store store) {
        // Variable to end while loop
        boolean done = false;
        // Loops through until the user is done entering in the customer's information
        while(!done && store.size < 1000){
            // Creates a new object for customer
            Customer customer = new Customer();
            getName(customer);
            getPhone(customer);
            getEmail(customer);
            getDiscount(customer);
            getType(customer);
            // If web server is entered then the webserver method is called
            if(customer.getVMType().equalsIgnoreCase("web server")){
                webServerSet(customer);
            }
            // If file server is entered then the fileserver method is called
            if(customer.getVMType().equalsIgnoreCase("file server")){
                fileServerSet(customer);
            }
            // If bitcoin miner is entered then the bitcoinminer method is called
            if(customer.getVMType().equalsIgnoreCase("bitcoin miner")){
                bitcoinMinerSet(customer);
            }
            //Increment the static counter and set the customer in store
            store.setACustomer(customer);
            store.size += 1;
            String quit = JOptionPane.showInputDialog(null,"Please enter q to quit or any other key to continue: ");
            if(quit.equals("q")){
                done = true;
            }
        }
        return store;
    }
    
    /*
     Creation of ID for each customer
     @param Store store, Customer customer
     @return type is void
     */
    
    
    public static void getID(Store store, Customer customer) {
        String ID = "usr";
        int num = 0;
        // loops through each customer to set an ID
        for(int i = 0; i < store.size; i++) {
            ID += num;
            num++;
            customer.setID(ID);
        }
    }
    
    /*
     Obtains and validates the user input for name
     @param Customer customer
     @return type is void
     */
    
    public static void getName(Customer customer){
        boolean done = false;
        // Loops through until a valid name is entered
        while(!done){
            try{
                //Prompts the user for the name and sets it in customer
                String name = JOptionPane.showInputDialog(null,"Please enter a name: ");
                customer.setName(name);
                done = true;
            }
            // If a exception is caught, the program will throw a error message
            catch(IllegalArgumentException e){
                JOptionPane.showMessageDialog(null,"Please input a valid name.");
            }
        }
    }
    
    /*
     Obtains and validates the user input for phone number
     @param Customer customer
     @return type is void
     */
    
    public static void getPhone(Customer customer){
        boolean numSet = false;
        // Loops through until a valid phone number is entered
        while(!numSet){
            try{ 
                // Prompts the user for the phone number and sets it in customer after it checks if it is valid
                String cellPhone = JOptionPane.showInputDialog(null, "Enter the cellphone number in the format XXX.XXX.XXXX: ");
                if (cellPhone.charAt(3) == ('.') && cellPhone.charAt(7) == ('.') && cellPhone.length() == 12) {
                    customer.setPhoneNum(cellPhone);
                    numSet = true;
                }
                else{
                    throw new NumberFormatException();
                }
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter in the following format: XXX.XXX.XXXX");
            } 
            // If a exception is caught, the program will throw a error message
            catch(StringIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid phone number");
            }
        }
    }
    
    /*
     Obtains and validates the user input for email
     @param Customer customer
     @return type is void
     */
    
    public static void getEmail(Customer customer){
        boolean emailSet = false;
        // Loops through until a valid email is entered
        while(!emailSet){
            try{ 
                // Prompts the user for the email and sets it in customer after it checks if it is valid
                String email = JOptionPane.showInputDialog(null, "Enter the email of the participant: ");
                boolean doneEmail = true;
                int periodCount = 0;
                int atCount = 0;
                // Checks to make sure the correct email validation
                for(int i = 0; i < email.length(); i++){
                    if(email.charAt(i) == '@'){
                        atCount += 1;
                    }
                    if(email.charAt(i) == '.'){
                        periodCount += 1;
                    }
                    if( Character.isLetter(email.charAt(i)) == false && Character.isDigit(email.charAt(i)) == false && email.charAt(i) != '@' && email.charAt(i) != '.' && email.charAt(i) != '_'){
                        JOptionPane.showMessageDialog(null,"Email can only contain letters, digits, '@', '_', and '.'");
                        doneEmail = false;
                        break;
                    }
                }
                if(atCount > 1){
                    JOptionPane.showMessageDialog(null,"Email can only contain one '@' sign");
                    doneEmail = false;
                }
                if(atCount < 1 || periodCount < 1){
                    JOptionPane.showMessageDialog(null,"Email must contain both '@' and '.'");
                    doneEmail = false;
                }
                boolean foundAt = false;
                int periodCount2 = 0;
                int index = 0;
                for(int i = 0; i < email.length(); i++){
                    if(email.charAt(i) == '@'){
                        foundAt = true;
                    }
                    if(email.charAt(i) == '.'){
                        periodCount2 += 1;
                    }
                    if((periodCount2 == periodCount) && (!foundAt)){
                        JOptionPane.showMessageDialog(null,"Last '.' in email does not come after '@'");
                        doneEmail = false;
                        break;
                    }
                    if((periodCount2 == periodCount)){
                        index = i;
                        break;
                    }
                }
                if(email.length() - 1 - index < 2){
                    JOptionPane.showMessageDialog(null,"Last '.' in email does not have at least two characters after.");
                    doneEmail = false;
                }
                if(doneEmail){
                    customer.setEmail(email);
                    emailSet = true;
                }
            }            
            catch(IllegalArgumentException e){
                JOptionPane.showMessageDialog(null, "Please enter in a valid email address");
            } 
            // If a exception is caught, the program will throw a error message
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Please enter a valid email address");
            }
        }
    }
    
    /*
     Obtains and validates the user input for discount
     @param Customer customer
     @return type is void  
     */
    
    public static void getDiscount(Customer customer){
        boolean done = false;
        // Loops through until a valid answer is entered
        while(!done){
            try{
                // Prompts the user for the discount and sets it in customer after it checks if it is valid
                String discount = JOptionPane.showInputDialog(null,"Please enter 'yes' or 'no' if you have a discount: ");
                if(discount.equalsIgnoreCase("yes") || discount.equalsIgnoreCase("no")){
                    customer.setDiscount(discount);
                    done = true;
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please input yes or no.");
                }
            }
            // If a exception is caught, the program will throw a error message
            catch(IllegalArgumentException e){
                JOptionPane.showMessageDialog(null,"Please input yes or no.");
            }
        }
    }
    
    /*
     Obtains and validates the user input for type
     @param Customer customer
     @return type is void
     */
    
    public static void getType(Customer customer){
        boolean typeSet = false;
        // Loops through until a valid type is entered
        while(!typeSet){
            try{ 
                // Prompts the user for the type and sets it in customer after it checks if it is valid
                String type = JOptionPane.showInputDialog(null, "Enter the type of Virtual Machine: \nWeb Server \nFile Server \nBitcoin Miner");
                if (type.equalsIgnoreCase("bitcoin miner") || type.equalsIgnoreCase("web server") || type.equalsIgnoreCase("file server") ) {
                    customer.setVMType(type);
                    if(type.equalsIgnoreCase("bitcoin miner")){
                        customer.setBitcoinMiner(new bitcoinMiner());
                    }
                    if(type.equalsIgnoreCase("web server")){
                        customer.setWebServer(new webServer());
                    }
                    if(type.equalsIgnoreCase("file server")){
                        customer.setFileServer(new fileServer());
                    }
                    typeSet = true;
                }
                else {
                    throw new IllegalArgumentException();
                }
            }
            // If a exception is caught, the program will throw a error message
            catch(IllegalArgumentException e){
                JOptionPane.showMessageDialog(null, "Please enter bitcoin miner, web server, or file server.");
            } 
        }
    }
    
    /*
     Obtains and validates the user input for web server machine
     @param Customer customer
     @return type is void
     */
    
    public static void webServerSet(Customer customer) {
        int num = 0;
        boolean done = false;
        // Loops through until a valid number is entered
        while(!done) {
            try {
                // Prompts the user for the number of gbs and sets it in web server after it checks if it is valid
                num = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the number of gigs ($10 for each 8GB increment): "));
                if (num == 8 || num == 16 || num == 64 || num == 120) {
                    customer.getWebServer().setNumGigs(num);
                    done = true;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please enter a multiple of 8.");
                }
            }
            // If a exception is caught, the program will throw a error message
            catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Please enter a multiple of 8's to a maximum of 120");
            }
        }
    }
    
    /*
     Obtains and validates the user input for file server machine
     @param Customer customer
     @return type is void
     */
    
    public static void fileServerSet(Customer customer) {
        boolean done2 = false;
        boolean done3 = false;
        boolean done4 = false;
        String media = "";
        String storage;
        int tera = 0;
        // Loops through until a valid storage is entered
        while(!done2) {
            try {
                // Prompts the user for the media storage and sets it in file server after it checks if it is valid
                media = JOptionPane.showInputDialog(null, "Please enter one of the following: \nSSD - $5 per terabyte per month \nMagnetic - $2 per terabyte per month");
                if (media.equalsIgnoreCase("SSD") || media.equalsIgnoreCase("Magnetic")) {
                    customer.getFileServer().setMedia(media);
                    done2 = true;
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
            // If a exception is caught, the program will throw a error message
            catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Please enter SSD or Magnetic Storage");
            }
        }
        while(!done3) {
            try {
                // Prompts the user for the number of terabytes and sets it in file server after it checks if it is valid
                tera = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the amount of " + media + " terabytes: "));
                if (tera > 0 || tera < 1024) {
                    customer.getFileServer().setNumTera(tera);
                    customer.getFileServer().setTotal(media);
                    done3 = true;
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
            // If a exception is caught, the program will throw a error message
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a number from 1 - 1024");
            } 
        }
        while(!done4) {
            try {
                // Prompts the user for the storage and sets it in file server after it checks if it is valid
                storage = JOptionPane.showInputDialog(null, "Please enter one of the following: \nBlock Storage \nObject Storage ");
                if (storage.equalsIgnoreCase("block storage") || storage.equalsIgnoreCase("object Storage")) {
                    customer.getFileServer().setStorage(storage);
                    done4 = true;
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
            // If a exception is caught, the program will throw a error message
            catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Please enter block storage or object storage");
            }
        }
    }
    
    /*
     Obtains and validates the user input for bitcoin miner machine
     @param Customer customer
     @return type is void
     */
    
    public static void bitcoinMinerSet(Customer customer) {
        boolean done2 = false;
        boolean done3 = false;
        int GPU;
        String brand;
        // Loops through until a valid number is entered
        while(!done3) {
            try {
                // Prompts the user for the number of GPU and sets it in bitcoin miner after it checks if it is valid
                GPU = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter how many GPUs (max is 8 GPUs): "));
                if (GPU == 1 || GPU == 2 || GPU == 3 || GPU == 4 || GPU == 5 || GPU == 6 || GPU == 7 || GPU == 8) {
                    customer.getBitcoinMiner().setNumGPU(GPU);
                    done3 = true;
                }  
            }
            // If a exception is caught, the program will throw a error message
            catch (IllegalArgumentException e) {
                JOptionPane.showInputDialog(null, "Please enter 1, 2, 3, 4, 5, 6, 7, or 8");
            }
        }
        while(!done2) {
            try {
                // Prompts the user for the brand and sets it in bitcoin miner after it checks if it is valid
                brand = JOptionPane.showInputDialog(null, "Please enter one of the following: \nAMD \nNvidia ");
                if (brand.equalsIgnoreCase("AMD") || brand.equalsIgnoreCase("Nvidia")) {
                    customer.getBitcoinMiner().setBrand(brand);     
                    done2 = true;
                }
            }
            // If a exception is caught, the program will throw a error message
            catch (IllegalArgumentException e) {
                JOptionPane.showInputDialog(null, "Please enter AMD or Nvidia");
            }
        }
    }
    /*
     Display the information about each customer
     @param Store store
     @return type is String
     */
    
    public static String Display (Store store){
        String out = "----------------- Customer Report -----------------\n";
        double discountTotal = 0;
        // Loops through the store customers to obtain and display their information
        for(int i = 0; i < store.size; i++){
            if(store.getCustomer(i) != null){
                out += "Name: " + store.getCustomer(i).getName() + "\n";
                out += "Phone Number: " + store.getCustomer(i).getPhoneNum() + "\n";
                out += "Email: " + store.getCustomer(i).getEmail() + "\n";
                out += "Discount: " + store.getCustomer(i).getDiscount() + "\n";
                out += "Type of Virtual Machine: " + store.getCustomer(i).getVMType() + "\n";
                //If the customer bought bitcoin miner, then it will display the information regarding bitcoin miner
                if(store.getCustomer(i).getVMType() != null && store.getCustomer(i).getVMType().equalsIgnoreCase("bitcoin miner")) {
                    if(store.getCustomer(i).getDiscount().equalsIgnoreCase("no")) { 
                        out += "Brand: " + store.getCustomer(i).getBitcoinMiner().getBrand() + "\n";
                        out += "Monthly Cost: $" + String.format("%.2f", store.getCustomer(i).getBitcoinMiner().setTotal()) + "\n";
                    }
                    // If the cusomter has a discount it will multiply the discount into the total
                    if(store.getCustomer(i).getDiscount().equalsIgnoreCase("yes")) {
                        out += "Brand: " + store.getCustomer(i).getBitcoinMiner().getBrand() + "\n";
                        out += "Monthly Cost: $" + String.format("%.2f", store.getCustomer(i).getBitcoinMiner().setTotal() * 0.8) + "\n";
                    }
                }
                //If the customer bought web server, then it will display the information regarding web server
                if(store.getCustomer(i).getVMType().equalsIgnoreCase("web server") && store.getCustomer(i).getVMType() != null) {
                    if(store.getCustomer(i).getDiscount().equalsIgnoreCase("no")) {
                        out += "Number of GBs: " + store.getCustomer(i).getWebServer().getNumGigs() + "\n";
                        out += "Monthly Cost: $" + String.format("%.2f", store.getCustomer(i).getWebServer().setTotal()) + "\n";
                    }
                    // If the cusomter has a discount it will multiply the discount into the total
                    if(store.getCustomer(i).getDiscount().equalsIgnoreCase("yes")) {
                        out += "Number of GBs: " + store.getCustomer(i).getWebServer().getNumGigs() + "\n";
                        out += "Monthly Cost: $" + String.format("%.2f", store.getCustomer(i).getWebServer().setTotal() * 0.8) + "\n";
                    }
                }
                //If the customer bought file server, then it will display the information regarding file server
                if(store.getCustomer(i).getVMType().equalsIgnoreCase("file server") && store.getCustomer(i).getVMType() != null) {
                    if(store.getCustomer(i).getDiscount().equalsIgnoreCase("no")) {
                        out += "Storage media: " + store.getCustomer(i).getFileServer().getMedia() + "\n";
                        out += "Storage: " + store.getCustomer(i).getFileServer().getStorage() + "\n"; 
                        out += "Number of Terabytes: " + store.getCustomer(i).getFileServer().getNumTera() + "\n";
                        out += "Monthly Cost: $" + String.format("%.2f", store.getCustomer(i).getFileServer().getTotal()) + "\n";
                    }
                    // If the cusomter has a discount it will multiply the discount into the total
                    if(store.getCustomer(i).getDiscount().equalsIgnoreCase("yes")) {
                        out += "Storage media: " + store.getCustomer(i).getFileServer().getMedia() + "\n";
                        out += "Storage: " + store.getCustomer(i).getFileServer().getStorage() + "\n";
                        out += "Number of Terabytes: " + store.getCustomer(i).getFileServer().getNumTera() + "\n";
                        out += "Monthly Cost: $" + String.format("%.2f", store.getCustomer(i).getFileServer().getTotal() * 0.8) + "\n";
                    }             
                }
                out += "------------------------------------------------------\n";
            }
        }
        return out;
 
    }
    
    /*
  Display the statistics of all customers
  @param Store store
  @return type is String
  */
 
    public static String displayStats (Store store){
        //Variables to find the total 
        String output = "";
        double total = 0;
        int totalWeb = 0;
        int totalFile = 0;
        int totalBitcoin = 0;
        int totalMemory = 0;
        int totalStorage = 0;
        int totalGPU = 0;
        // Loops through to count the number of web servers are bought
        for(int j = 0; j < store.size; j++){
            if (store.getCustomer(j).getVMType().equalsIgnoreCase("web server")) {
            totalWeb++;
            }
        }
        // Loops through to count the number of file servers are bought
        for(int ii = 0; ii < store.size; ii++){
            if (store.getCustomer(ii).getVMType().equalsIgnoreCase("file server")) {
            totalFile++;
            }
        }
        // Loops through to count the number of bitcoin miners are bought
        for(int iii = 0; iii < store.size; iii++){
            if (store.getCustomer(iii).getVMType().equalsIgnoreCase("bitcoin miner")) {
            totalBitcoin++;
            }
        }
        // Loops through to obtain the total of all of the customers combined
        for(int ix = 0; ix < store.size; ix++) {
            if (store.getCustomer(ix).getVMType() != null && store.getCustomer(ix).getVMType().equalsIgnoreCase("web server")) {
               total += store.getCustomer(ix).getWebServer().getTotal();
            }
            if (store.getCustomer(ix).getVMType() != null && store.getCustomer(ix).getVMType().equalsIgnoreCase("file server")) {
               total += store.getCustomer(ix).getFileServer().getTotal();
            }
            if (store.getCustomer(ix).getVMType() != null && store.getCustomer(ix).getVMType().equalsIgnoreCase("bitcoin miner")) {
               total += store.getCustomer(ix).getBitcoinMiner().getTotal();
            }
        }
        // Loops through to find the total memory, storage, and GPU are used
        for(int xx = 0; xx < store.size; xx++) {
            if (store.getCustomer(xx).getVMType().equalsIgnoreCase("web server") && store.getCustomer(xx).getVMType() != null) {
               totalMemory += store.getCustomer(xx).getWebServer().getNumGigs();
            }
            if (store.getCustomer(xx).getVMType().equalsIgnoreCase("file server") && store.getCustomer(xx).getVMType() != null) {
               totalStorage += store.getCustomer(xx).getFileServer().getNumTera() + 20;
            }
            if (store.getCustomer(xx).getVMType().equalsIgnoreCase("bitcoin miner") && store.getCustomer(xx).getVMType() != null) {
               totalGPU += store.getCustomer(xx).getBitcoinMiner().getNumGPU();
            }
        }
        // Display the statistic reports
        output += "----------------- Customer Statistic -----------------\n";
        output += "Number of Web Servers Customers: " + totalWeb + "\n";
        output += "Number of File Servers Customers: " + totalFile + "\n";
        output += "Number of Bitcoin Miner Customers: " + totalBitcoin + "\n";
        output += "Total monthly fees collected: $" + String.format("%.2f", total) + "\n";
        output += "Average monthly fee: $" + String.format("%.2f", total/store.size) + "\n";
        output += "Amount of memory used: " + totalMemory + "\n";
        output += "Amount of disk space used: " + totalStorage + "\n";
        output += "Amount of GPUs used: " + totalGPU + "\n";
        output += "-----------------------------------------------\n";
       return output;
    }
    
    /*
  Creates a text file that stores information about the customers
  @param Store store
  @return type is void
  */

    
    public static void createFile(Store store) {
      try {
         // Creation of a PrintWriter object that takes in a fileOuputStream that takes in a object file class
         PrintWriter out = new PrintWriter(new FileOutputStream(new File("Customer_list.txt")));
         
         // Loops through the customer to print their name and IDs
         for(int i= 0; i < store.size; i++) {
            out.println(store.getCustomer(i).getName());
            out.println(store.getCustomer(i).getID());
         }  
            out.close();
     }
     // If a exception is caught, the program will throw a error message
     catch(FileNotFoundException e) {
         JOptionPane.showMessageDialog(null, "Couldn't find file");
     }
   }
}