import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.*;
class LMS implements Serializable{
    String adminPass = "Admin*123";
    static Scanner sc = new Scanner(System.in);
    ArrayList<IssueBook> issuedList = new ArrayList<IssueBook>();
    ArrayList<User> usersList = new ArrayList<User>();
    ArrayList<Book> booksList = new ArrayList<Book>();
    static int bookNo = 0;
    static int quantity = 0;
    public static void main(String[] args){
        LMS lms = new LMS();
        try{
        BufferedReader br = new BufferedReader(new FileReader("BooksList.txt"));
        String row;
        while((row = br.readLine())!=null){
            String[] datas = row.split(",");
            Book boook = new Book(Integer.parseInt(datas[0]), datas[1], datas[2], Integer.parseInt(datas[3]), Integer.parseInt(datas[4]));
            lms.booksList.add(boook);
        }
        br.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        while(true){
            boolean isAdmin = false;
            User user = new User();
            user.getUserDetails();
            String pass;
            if(user.role.equals("Admin")){System.out.println("Enter the password");
                pass = sc.nextLine();
                if(pass.equals(lms.adminPass)){
                    System.out.println("Admin logged in successfully!!");
                    isAdmin = true;
                }
                else {
                    System.out.println("Try again");
                    continue;
                }
            }
            lms.usersList.add(user);
            System.out.println("Welcome to LIBRARY MANAGEMENT SYSTEM\nWhat do you want to do?\n1.Add Book\n2.Delete Book by Name\n3.Search a Book\n4.Issue a Book\n5.Print Books\n6.Exit");
            int n = sc.nextInt();
            int t=0;
        switch(n){
            case 1:
                if(isAdmin)
                lms.addBook();
                break;
            case 2:
                if(isAdmin)
                System.out.println("Enter the Name");
                String str = sc.next();
                lms.deleteBook(str);
                break;
            case 3:
                System.out.println("Enter the Book Name to Search");
                String stri = sc.nextLine();
                lms.isPresentbkName(stri);
                break;
            case 4:
                if(isAdmin)
                lms.issueBook();
                break;
            case 5:
                lms.printBooks();
                break;
            case 6:
                t=1;
                
                break;
        }
        if(t==1)break;
    }
}
    public void issueBook(){
        while(true){
            User user = new User();
            user.getUserDetails();
            IssueBook bok = new IssueBook();
            if(user.role.equals("Admin")){
                System.out.println("Welcome Admin\nPlease enter the User ID");
                bok.userId = sc.nextInt();
                System.out.println("Please enter the book ID");
                bok.bookId = sc.nextInt();
                issuedList.add(bok);
                System.out.println("Book issued Successfully!");
                deleteBook(bok.bookId);
            }

            else{
                System.out.println("Only Admins can issue Books\n1.retry\n2.exit");
                int n = sc.nextInt();
                if(n==2)break;
            }
        }
    }

    public void addBook(){
        System.out.println("Enter Book Name");String bookName = sc.next();
        System.out.println("Enter Book WriterName");String writerName = sc.next();
        System.out.println("Enter Book Price");int price = sc.nextInt();
        System.out.println("Enter Book Quantity");int quantity = sc.nextInt();
        booksList.add(new Book(++bookNo,bookName,writerName,price,quantity));
        System.out.println("Book added Successfully!");
    }

    public void deleteBook(String bookName){
        for(int i=0 ; i<booksList.size();i++){
            Book bk = booksList.get(i);
            if(bk.bookName.equals(bookName)){
                if(bk.quantity>0)bk.quantity--;
                else booksList.remove(i);
            }
        }
    }

    public void deleteBook(int Id){
        for(int i=0 ; i<booksList.size();i++){
            Book bk = booksList.get(i);
            if(bk.bookId==Id){
                if(bk.quantity>0)bk.quantity--; 
                else booksList.remove(i);
            }
        }
    }    

    public void printBooks(){
        for(int i=0;i<booksList.size();i++){
            Book bok = booksList.get(i);
            bok.bookDetails();    
        }
    }

    public boolean isPresentbkName(String bkName){
        for(int i=0;i<booksList.size();i++){
            if(booksList.get(i).bookName.equalsIgnoreCase(bkName))return true;
        }
        return false;
    }

    public boolean isPresentwrName(String wrName){
        for(int i=0;i<booksList.size();i++){
            if(booksList.get(i).writerName.equalsIgnoreCase(wrName))return true;
        }
        return false;
    } 
}