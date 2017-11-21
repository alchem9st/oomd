/* 
oomd hotel management system
Aakash Arora
Ankit Prabhu
Anmol Patil
*/

import java.io.*;
import java.util.*;
// Hotel_Managment Class will Create & call the Necessary Methods.
class hotel_management
{
    // Main Method of Program.
    public static void main(String[] args)
    {
        try
        {
            // Creating  Object of Operation Class And Allocate Memory.
            Operation opr = new Operation();   
            opr.screenHeader();     //Calling The ScreenHeader Function.
            opr.introHotel(); //calling  The introHotel Function.
            int choice;    //Choice for the Which Feature You Want to select.
           
            Scanner user_input = new Scanner(System.in);
            opr.openFile();
            // Repeates N-number of Time to show Category Menu to user and Take input from User.
            do
            {
                opr.displayMenu();    //displayMenu function for the Taking choice for the user that which Feature user want to select.
                System.out.print("\n\nEnter Choice :- ");
                choice = user_input.nextInt();
                //Switch case for the user choice which user will select.
                switch(choice)
                {
                    case 1:   
                        opr.displayRoomFeature();    //Calling displayRoomFeature function Which will show which type of room user want.
                        break;
                    case 2:
                        opr.roomAvailability();        //display room Availability if it is Available then it will be "Yes" other wise "No"
                        break;
                    case 3:
                        opr.roomAllocation();        //The Room will be allocated to user by taking details of user.
                        break;
                    case 4:
                        opr.restaurentOrder();    //The User Want to Eat TASTY Food Of Our "FERN HOTEL"
                        break;
                    case 5:
                        opr.roomDeallocation();    //When the customer Want to check out he will Enter the name Then The Room of customer Will be de-allocated
                        break;
                    case 6:
                        opr.showCustomerData();    //When The User press 6 the details of customer will be display .
                        break;
                    case 7:
                        System.exit(0);        //When THe user want to "EXIT" From Hotel.
                    default:
                        System.out.println("Invalid Choice... ");    //When User Will not Press Between 1-7.
                }
            }while(choice!=7);   
        }
        catch(Exception e)
        {
            System.out.println("Invalid Input!");
        }
    }
}
// This Class Specifies the Operations To be Performed by this Application.
// This class contain methods for the Feature which are use.
class Operation
{
    Scanner user_input = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    Boolean avail[] = {true,true,true,true};
    String rooms[] = {"Suite","Spl.Delux","Delux","General"};
    int r_charges[] = {2500,1500,1000,750};
    int advance[] = {1200,750,500,400};
    Food f = new Food();    //creating Object of Food class for Making final bill So it is container ship.
    Customer cust[] = new Customer[5];    //Array of Object.
    BufferedWriter bw = null;    //bw is Object of the Buffer Writter to write the file.
    Operation(){}    //USE the Default constructor.
    // This Method will Open-up a File for Printing the Bill.
    void openFile() throws IOException
    {
        try
        {
            bw = new BufferedWriter(new FileWriter("Customer_Detail.txt"));//The name of file is the "customer.txt" in which the bill of customer will be print.
            bw.write("Customer_Name\t\tBill\n");    //The Title Of in the text fill will be this.
            bw.flush();
        }
        catch(Exception e)
        {
            System.out.println("Invalid Input!");
        }
    }
    // This Method will Display the Hotel Name along with a Welcome Note.
    void screenHeader()    //This Function will be display to customer one time When the User Start the Program.
    {
            System.out.println("        ****************************************");
            System.out.println("        *                       *");
            System.out.println("        *        WELCOME TO           *");
            System.out.println("        *                       *");
            System.out.println("        *        HOTEL  FERN           *");
            System.out.println("        *                         *");
            System.out.println("        ****************************************");
       
    }
    // This Method Will Display the Hotel Details.
    void introHotel() throws Exception    //the Address of Hotel will be printed and Show the Extra Feature of Hotel.
    {
            System.out.println("\n\t         Near Sola Overbridge, SG Highway, Ahmedabad City,\n\t\t\t\t Gujarat 380054, INDIA");
            System.out.println("\n\n                          Ph. No.:079-30230000");
            System.out.println("\n\n\n                          WELCOMES YOU..............");
            System.out.println("\n\n\n\tHotel FERN Inn is one of the newest Hotel in Ahmedabad. The Hotel is \t\tequipped with with all the general amenities and facilities that go \t\talong with memorable stay. Set amidst beautifully landscaped gardens, \t\tit proves to be a ideal dream destination for perceptive traveller.");
            System.out.println("\n\n\tThe Hotel have well furnished rooms along with rooms providing pleasent \tviews of the city. The hotel satisfies the needs of business as well \t\tas the leisure traveller. All the rooms at the hotel are furnished \t\t beautifully. All the rooms are fitted with amenities.");
            System.out.println("\n\n                             AMENITIES .......\n");
            System.out.println("\n\t\t\t1. 100% Power backup.\n");
            System.out.println("\t\t\t2. Automatic lift.\n");
            System.out.println("\t\t\t3. Ample parking space.\n");
            System.out.println("\t\t\t4. Round the clock security.\n");
            System.out.println("\t\t\t5. Running hot and cold water.\n");
            System.out.println("\t\t\t6. Free internet service.(WIFI Available)\n");
            System.out.println("\t\t\t7. 24*7 room service.\n");
            System.out.println("\t\t\t8. Laundary service.\n");
            System.out.println("\nPress Enter to continue:");
            br.readLine();
    }
    // Displays the Category-Menu of Hotel Fern.
    void displayMenu()    //This will Show to user To select Which type user wants.
    {
            System.out.println("\n\n\t##########################################");
            System.out.println("\t#                     #");
            System.out.println("\t#         HOTEL FERN                  #");
            System.out.println("\t#                         #");
            System.out.println("\t##########################################");
            System.out.println("\t#\t1.Features of Rooms              #");
            System.out.println("\t#\t2.Room Availability              #");
            System.out.println("\t#\t3.Room Allocation                #");
            System.out.println("\t#\t4.Restaurant                     #");
            System.out.println("\t#\t5.Room Deallocation              #");
            System.out.println("\t#\t6.Show Customer Detail           #");
            System.out.println("\t#\t7.Exit                           #");
            System.out.println("\t##########################################");
    }
    // Displays the Room Features of a Hotel.
    void displayRoomFeature() throws Exception
    {
        try
        {
            Room room_type = null;//here Room is "interface" So it is abstract so due to this We can not make object but we can use Reference of Room
            //There are 4 types of Room The USer will Decide which Type of Room Want to Stay.
            System.out.println("\nRoom Types\n\n1.For Suite\n2.For Spe. Delux\n3.For Delux\n4.For General\n");
            System.out.print("Enter Room Type :- ");
            int type = user_input.nextInt();
            switch(type)
            {
                case 1:
                    room_type = new Suite();    //The Memory of Object of Interface Allocate to Suite type.
                    room_type.displayDetail();    //calling the DisplayDetails methods which is override
                    System.out.println("\nPress Enter to continue:");
                    br.readLine();
                    break;
                case 2:
                    room_type = new Sp_Delux();    //The Memory of Object of Interface Allocate to Sp_Delux type.
                    room_type.displayDetail();        //calling the DisplayDetails methods which is override
                    System.out.println("\nPress Enter to continue:");
                    br.readLine();
                    break;
                case 3:
                    room_type = new Delux();    //The Memory of Object of Interface Allocate to delux type.
                    room_type.displayDetail();    //calling the DisplayDetails methods which is override
                    System.out.println("\nPress Enter to continue:");
                    br.readLine();
                    break;
                case 4:
                    room_type = new General();    //The Memory of Object of Interface Allocate to General type.
                    room_type.displayDetail();    //calling the DisplayDetails methods which is override
                    System.out.println("\nPress Enter to continue:");
                    br.readLine();
                    break;
               
                default:
                    System.out.println("Invalid Room Type..\n");
                    System.out.println("\nPress Enter to continue:");
                    br.readLine();
                    break;
            }
        }
        catch(Exception e)
        {
            System.out.println("Invalid Input!");
        }
    }
    // Displays The Room Availability.
    void roomAvailability() throws Exception    //If the Room will be available THen it will show "YES" otherwise "NO"
    {
        try
        {
            System.out.println("Room No.\tRoom Type\tCharges\tAvailability\n");
            for(int i=0;i<4;i++)
            {
                if(i==1)
                    System.out.println((i+1) + "\t\t" + rooms[i] + "\t" + r_charges[i] + "\t" + (avail[i] ? "YES" : "NO"));
                else
                    System.out.println((i+1) + "\t\t" + rooms[i] + "\t\t" + r_charges[i] + "\t" + (avail[i] ? "YES" : "NO"));
            }
            System.out.println("\nPress Enter to continue:");
            br.readLine();
        }
        catch(Exception e)
        {
            System.out.println("Invalid Input!");
        }
    }
    // Method to Allocate the Room.
    void roomAllocation() throws Exception
    {
        try
        {
            System.out.print("\n\nEnter the room number in which you want to stay :- ");
            int r_no = user_input.nextInt();
            if(r_no>0 && r_no<5)
            {
                r_no--;
                if(avail[r_no])
                {
                    cust[r_no] = new Customer();
                    System.out.print("\nEnter the Name of Customer :- ");
                    cust[r_no].name = user_input.next();
                    System.out.print("\nEnter the name of City :- ");
                    cust[r_no].city = user_input.next();
                    System.out.print("\nEnter Nationality :- ");
                    cust[r_no].nation = user_input.next();
                    System.out.print("\nFor how many days customer want the room :- ");
                    cust[r_no].days = user_input.nextInt();
                    System.out.print("\nEnter total members in your group :- ");
                    cust[r_no].members = user_input.nextInt();
                    if(cust[r_no].members>5 || cust[r_no].members<1)
                    {
                        System.out.println("\n\n\t" + cust[r_no].members + " members not allowed..It should be in range 1-5..");
                    }
                    else
                    {
                        boolean a=true,b=true,c=true;
                        System.out.println("\nEnter the date of arrival :- ");
                        System.out.println("-------------------------------- ");
                        while(a)
                        {
                        System.out.print("Date :- ");
                        cust[r_no].date = user_input.nextInt();
                        if(cust[r_no].date<=30)
                        a=false;
                        else
                        System.out.println("Date Should Be Less Than 30");
                        }
                        while(b)
                        {
                        System.out.print("\nMonth :- ");
                        cust[r_no].month = user_input.nextInt();
                        if(cust[r_no].month<=12)
                            b=false;
                        else
                            System.out.println("Month Should Be Less Than 13");
                        }
                        while(c)
                        {
                        System.out.print("\nYear :- ");
                        cust[r_no].year = user_input.nextInt();
                        if(cust[r_no].year<2015)
                            System.out.println("Enter A Year Greater Than 2015");
                        else
                            c=false;
                        }
                        System.out.println("\n\tNote :- Room No. - " + (r_no+1) + " is allocated to " + cust[r_no].name + " for " + cust[r_no].days + " days..");
                        System.out.println("\tAdvance : " + advance[r_no]);
                        //--------------------AADITY END----------------------------------------------------
                        cust[r_no].charges = r_charges[r_no]*cust[r_no].days;
                        cust[r_no].stay_charge = cust[r_no].charges;
                        avail[r_no] = false;
                    }
                }
                else
                {
                    System.out.println("\n\nNote : Room No. - " + (r_no+1) + " is not available to you..\n\tIt is already given to other customer..");
                }
            }
            else
            {
                System.out.println("Note : Invalid room no..It should be in range 1-4\n");
            }
            System.out.println("\nPress Enter to continue:");
            br.readLine();
        }
        catch(Exception e)
        {
            System.out.println("Invalid Input!");
        }
    }
    // Method to Display the Customer Details.
    void showCustomerData() throws Exception
    {
        try
        {
            System.out.print("\n\nEnter the room number :- ");
            int r_no = user_input.nextInt();
            if(r_no>0 && r_no<5)
            {
                r_no--;
                if(!avail[r_no])
                {
                    System.out.println("\nRoom No        :" + (r_no+1));
                    System.out.println("Customer Name    :" + cust[r_no].name);
                    System.out.println("Duration(days)    :" + cust[r_no].days);
                    System.out.println("City        :" + cust[r_no].city);
                    System.out.println("Nationality    :" + cust[r_no].nation);
                    System.out.println("Arrival Date    :" + cust[r_no].date + "/" + cust[r_no].month + "/" + cust[r_no].year);
                }
                else
                {
                    System.out.println("\n\tNote : Room No. - " + (r_no+1) + " not allocated to any customer..\n\t\tSo there is no data..");
                }
            }
            else
            {
                System.out.println("Note : Invalid room no..It should be in range 1-4\n");
            }
            System.out.println("\nPress Enter to continue:");
            br.readLine();
        }
        catch(Exception e)
        {
            System.out.println("Invalid Input!");
        }
    }
    // Method to place an Order for Food.
    //-----------------HARSH-------
    void restaurentOrder() throws Exception
    {
        try
        {
           
            f.display();
            System.out.print("\n\nEnter the room number :- ");//the room of the User.
            int r_no = user_input.nextInt();   
            int code;    //code variable for the food Order.
            if(r_no>0 && r_no<5)    //This condition for the Room no.
            {
                r_no--;
                if(!avail[r_no])   
                {
                    System.out.print("\nEnter Code :- ");
                    code = user_input.nextInt();
                    while(code!=11)
                    {   
                        if(code>0 && code<12)
                        {
                            int x=0;
                            code--;               
                            //THis Method For the Food Order for the User Who is Stay in Room. The Total Biil will add in Final Bill.
                            System.out.print("\nEnter Quantity of item " + f.food_item[code] + " :- ");
                            int quantity = user_input.nextInt();
                            x += quantity*f.food_charges[code];
                            cust[r_no].finalfoodbill+=x;
                        }
                        else
                        {
                            System.out.println("\nNote : Invalid code..");
                        }
                        System.out.print("\n\nEnter Code :- ");
                        code = user_input.nextInt();
                    }
                    System.out.print("\n\tBill = " + cust[r_no].finalfoodbill);
                }
                else
                {
                    System.out.println("Note : Room No. - " + (r_no+1) + " is not allocated to any customer..");
                }
            }
            else
            {
                System.out.println("Note : Invalid room no..It should be in range 1-4\n");
            }
            System.out.println("\nPress Enter to continue:");
            br.readLine();
        }
        catch(Exception e)
        {
            System.out.println("Invalid Input!");
        }
    }
   
    // Method to De-Allocate the Room.
    //When The USer Select this option Then Stay charge and Food Charge will be calculated And the Final bill will be generated .
    void roomDeallocation() throws Exception
    {
        try
        {
            System.out.print("\nEnter the room number :- ");
            int r_no = user_input.nextInt();
            if(r_no>0 && r_no<5)
            {
                r_no--;
                if(!avail[r_no])
                {
                    System.out.print("\nEnter the name of customer :- ");
                    String name = user_input.next();
                    if((cust[r_no].name).equals(name))
                    {
                        cust[r_no].charges += cust[r_no].finalfoodbill;
                        System.out.println("###############################################\n");
                        System.out.println("Customer Name : " + cust[r_no].name);
                        System.out.println("\n###############################################");
                        System.out.println("\n\t\tStay Charges : " + cust[r_no].stay_charge);
                        System.out.println("\n\t\tFood Charges : " + cust[r_no].finalfoodbill);
                        System.out.println("\n\t\t\t      ------");
                        System.out.println("\n\t\t Grand Total : " + cust[r_no].charges);
                        System.out.println("\n\t      (-)Advance Paid: " + advance[r_no]);
                        System.out.println("\n\t    Remaining Amount : " + (cust[r_no].charges-advance[r_no]));
                        System.out.println("\n###############################################");
                       
                        avail[r_no] = true;
                        int len = name.length();
                        if(len < 4)
                        {
                            bw.write("\n\n" + name + "\t\t\t\t\tStay Charges : " + cust[r_no].stay_charge);
                            bw.write("\n\t\t\t\t\tFood Charges : " + cust[r_no].finalfoodbill);
                            bw.write("\n\t\t\t\t\t\t\t\t  ------");
                            bw.write("\n\t\t\t\t\tGrand Total  : " + cust[r_no].charges);
                        }
                        else if(len>3 && len<8)
                        {
                            bw.write("\n\n" + name + "\t\t\t\tStay Charges : " + cust[r_no].stay_charge);
                            bw.write("\n\t\t\t\t\tFood Charges : " + cust[r_no].finalfoodbill);
                            bw.write("\n\t\t\t\t\t\t\t\t  ------");
                            bw.write("\n\t\t\t\t\tGrand Total  : " + cust[r_no].charges);
                        }
                        else
                        {
                            bw.write("\n\n" + name + "\t\t\tStay Charges : " + cust[r_no].stay_charge);
                            bw.write("\n\t\t\t\t\tFood Charges : " + cust[r_no].finalfoodbill);
                            bw.write("\n\t\t\t\t\t\t\t\t  ------");
                            bw.write("\n\t\t\t\t\tGrand Total  : " + cust[r_no].charges);
                        }
                        bw.flush();
                        System.out.println("Thank You for staying in Hotel Fern... ");
                    }
                    else
                    {
                        System.out.println("\nNote : Wrong Customer Name\n");
                    }
                }
                else
                {
                    System.out.println("\nNote : Room No. - " + (r_no+1) +  " not booked yet..So there is no question of cancellawtion..\n");
                }
            }
            else
            {
                System.out.println("Note : Invalid room no..It should be in range 1-4\n");
            }
            System.out.println("\nPress Enter to continue:");
            br.readLine();
        }
        catch(Exception e)
        {
            System.out.println("Invalid Input!");
        }
    }
}
// Class for Food. This contains the details of Dishes that are Available in the Restaurants.
class Food
{
    int food_code[] = {1,2,3,4,5,6,7,8,9,10};
    String food_item[] = {"Paneer Masala","Malai Kofta","Veg. Jaipuri","Panner Bhurji","Jeera Rice","Dal Fry","Butter Naan","Butter Roti","Papad","Butter Milk"};
    int food_charges[] = {200,210,200,220,170,120,30,25,15,15};
    // Display the Food Menu.
    void display()
    {
        System.out.println("Code\tFood_Item\tCharges\n");
        for(int i=0;i<10;i++)
        {
            if(i==5 || i==8)
            {
                System.out.println(food_code[i] + "\t" + food_item[i] + "\t\t" + food_charges[i]);
            }
            else
            {
                System.out.println(food_code[i] + "\t" + food_item[i] + "\t" + food_charges[i]);
            }
        }
        System.out.println("11\tExit");
    }
}
//-----------------------HARSH---------------------------
// Customer Class, Stores the Details of Customer.
class Customer
{
    int days,members,date,month,year,charges,food_bill,stay_charge,finalfoodbill;
    String name,city,nation;
    Customer()
    {
        days=0;
        members=0;
        date=0;
        month=0;
        year=0;
        charges=0;
        food_bill=0;
        stay_charge=0;
        finalfoodbill=0;
        name=null;
        city=null;
        nation=null;
    }
}
// Room Interface with DisplayDetails Method, that should be Overridden By Every Type of Room Class.
interface Room
{
    void displayDetail();
}
// Suite Room Class, that must Override the displayDetail();
class Suite implements Room
{
    public void displayDetail()
    {
        System.out.println("\n Room number            >>>1\n");
        System.out.println("\n Advance                >>>1200\n\n");
        System.out.println("\n                      FEATURES OF THIS ROOM                       ");
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("\n\n Room Type            >>> Suite                                    ");
        System.out.println("\n\n Room charges         >>>Rs.2500 per day");
        System.out.println("\n\n 1. Bed               >>>      3");
        System.out.println("\n\n 2.Capacity           >>>      5");
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("\n                    ADDITIONAL FEATURES                        ");
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("\n\n 1.A/C available   ");
        System.out.println("\n\n 2.Geyser available");
        System.out.println("\n\n 3.TV available      ");
        System.out.println("\n-------------------------------------------------------------------");
    }
}

// Sp_Delux Room Class, that must Override the displayDetail();
class Sp_Delux implements Room
{
    public void displayDetail()
    {
        System.out.println("\n Room number            >>>2\n");
        System.out.println("\n Advance                >>>750\n\n");
        System.out.println("\n                      FEATURES OF THIS ROOM                       ");
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("\n\n Room Type            >>> Sp.delux");
        System.out.println("\n\n Room charges         >>> Rs.1500 per day");
        System.out.println("\n\n 1. Bed               >>>      2");
        System.out.println("\n\n 2.Capacity           >>>      5");
        System.out.println("\n\n 3.Balcony available     ");
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("\n                     ADDITIONAL FEATURES                        ");
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("\n\n 1.A/C  available ");
        System.out.println("\n\n 2.Geyser available");
        System.out.println("\n\n 3.TV available      ");
        System.out.println("\n------------------------------------------------------------------");
    }
}

// Delux Room Class, that must Override the displayDetail();
class Delux implements Room
{
    public void displayDetail()
    {
        System.out.println("\n Room number            >>>3\n");
        System.out.println("\n Advance                >>>500\n\n");
        System.out.println("\n                      FEATURES OF THIS ROOM                       ");
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("\n\n Room Type            >>> Delux                                      ");
        System.out.println("\n\n Room charges         >>>Rs.1000 per day");
        System.out.println("\n\n 1. Bed               >>>      2");
        System.out.println("\n\n 2.Capacity           >>>      5");
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("\n                    ADDITIONAL FEATURES                        ");
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("\n\n 1.A/C available   ");
        System.out.println("\n\n 2.Geyser available");
        System.out.println("\n\n 3.TV available      ");
        System.out.println("\n-------------------------------------------------------------------");
    }
}

// General Room Class, that must Override the displayDetail();
class General implements Room
{
    public void displayDetail()
    {
        System.out.println("\n Room number            >>>4\n\n");
        System.out.println("\n Advance                >>>400\n\n");
        System.out.println("\n                      FEATURES OF THIS ROOM                       ");
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("\n\n Room Type            >>> General                                    ");
        System.out.println("\n\n Room charges         >>>Rs.750 per day");
        System.out.println("\n\n 1. Bed               >>>      2");
        System.out.println("\n\n 2.Capacity           >>>      5");
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("\n                    ADDITIONAL FEATURES                        ");
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("\n\n 1.Geyser available      ");
        System.out.println("\n-------------------------------------------------------------------");
    }
}
	
Click here to Reply, Reply to all, or Forward
3.92 GB (26%) of 15 GB used
Manage
Terms - Privacy
Last account activity: 2 hours ago
Details
	
	
