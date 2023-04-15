import java.io.Serializable;

class Book implements Serializable{
    int bookId;
    public String bookName;
    public String writerName;
    public int price;
    public int quantity;
    
    public Book(int bookId,String bookName,String writerName,int price,int quantity){
        this.bookName = bookName;
        this.writerName = writerName;
        this.price = price;
        this.quantity = quantity;
    }

    public void bookDetails(){
        System.out.println("------------------");
        System.out.println("bookId "+ bookId);
        System.out.println("writerName "+ writerName);
        System.out.println("bookName "+ bookName);
        System.out.println("price "+ price);
        System.out.println("quantity "+ quantity);
        System.out.println("------------------");
    }
}
