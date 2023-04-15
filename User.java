import java.io.Serializable;
import java.util.*;
public class User implements Serializable {
    Scanner sc = new Scanner(System.in);
    int userId;
    String password;
    String role;

    public void getUserDetails(){
        //System.out.println("Enter password"); this.password = sc.next();
        System.out.println("Enter your role\n 1.Admin\n2.User");
        int n = sc.nextInt();
        while(true){
        if(n==1) {role = "Admin";break;}
        if(n==2) {role = "User";break;}
        else System.out.println("Incorrect option");
        System.out.println("Enter again");
        }
    }  
}
