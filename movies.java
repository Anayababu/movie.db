import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Movies {

         Connection conn = null;
    private Connection connect() {  //connection to database movies

        try {
            // db parameters
            String url = "jdbc:sqlite:Movies.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

          return conn;
    }

 public void create(){  //creating table movies

   String s1="CREATE TABLE IF NOT EXISTS movies(\n"+"ID INTEGER PRIMARY KEY,\n"+"NAME TEXT NOT NULL,\n"+"ACTOR TEXT,\n"+"ACTRESS TEXT,\n"+"YEAR TEXT,\n"+"DIRECTOR TEXT);";
   try{
        Statement smt = conn.createStatement();
        smt.execute(s1);
    } catch (SQLException e) {
   System.out.println(e.getMessage());
  }
 }

    public  void Insert(int ID,String NAME,String ACTOR,String ACTRESS,String YEAR,String DIRECTOR){   //inserting values into table movies

      String s2 = "INSERT INTO movies(ID,NAME,ACTOR,ACTRESS,YEAR,DIRECTOR) VALUES(?,?,?,?,?,?)";
      try (
          PreparedStatement pstmt = conn.prepareStatement(s2)) {
      pstmt.setInt(1, ID);
      pstmt.setString(2, NAME );
      pstmt.setString(3, ACTOR);
      pstmt.setString(4, ACTRESS);
      pstmt.setString(5, YEAR);
      pstmt.setString(6, DIRECTOR);
      pstmt.executeUpdate();
  } catch (SQLException e) {
      System.out.println(e.getMessage());
  }

}
public void select(String YEAR){        // select query for parameter actor
  String sql = "SELECT ID, NAME, ACTOR, ACTRESS, YEAR, DIRECTOR FROM movies WHERE YEAR="+"\""+YEAR+"\"";

  try (
       Statement stmt  = conn.createStatement();
       ResultSet rs    = stmt.executeQuery(sql)){
      System.out.println("DETAILS OF MOVIES RELEASED IN YEAR "+YEAR+"");
    System.out.println("ID"+"\t"+"NAME"+"\t"+"ACTOR"+"\t"+"ACTRESS"+"\t"+"YEAR"+"\t"+"DIRECTOR"+"\t");
      // loop through the result set
      while (rs.next()) {
          System.out.println(rs.getInt("ID") +  "\t" +
                             rs.getString("NAME") + "\t" +
                             rs.getString("ACTOR") + "\t" +
                             rs.getString("ACTRESS") + "\t" +
                             rs.getString("YEAR") + "\t" +
                             rs.getString("DIRECTOR"));
      }
  } catch (SQLException e) {
      System.out.println(e.getMessage());
  }


}
public void selectAll(){  // displays all data in table movies
        String sql = "SELECT ID, NAME, ACTOR, ACTRESS, YEAR, DIRECTOR FROM movies";

        try (
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
          System.out.println("ALL DATA IN MOVIES TABLE");
          System.out.println("ID"+"\t"+"NAME"+"\t"+"ACTOR"+"\t"+"ACTRESS"+"\t"+"YEAR"+"\t"+"DIRECTOR"+"\t");
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t" +
                                   rs.getString("NAME") + "\t" +
                                   rs.getString("ACTOR") + "\t" +
                                   rs.getString("ACTRESS") + "\t" +
                                   rs.getString("YEAR") + "\t" +
                                   rs.getString("DIRECTOR"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      }

    public static void main(String[] args) {

     Movies mov = new Movies();
     mov.connect();
     mov.create();
        mov.Insert(1,"Battleship","Taylor","Brooklyn","2012","Peter Berg");
        mov.Insert(2,"Avatar","Sam","Zoe","2009","James Cameron");
       mov.Insert(3,"Titanic","Leonardo","Kate","1997","James Cameron");
       mov.Insert(4,"The Darkest Hour","Emile","olivia","2011","Chris Gorak");
       mov.Insert(5,"Yeh Jawanni hey Dewanni","Ranbir Kapoor","Deepika Padikone","2013","Ayan Mukerji");
        mov.Insert(6,"A Quiet Place 2","Cillian","Emily","2021","John Krasinski");
       mov.Insert(7,"Banglore Days","Dulqer","Nazariya","2014","Anjali Menon");
       mov.Insert(8,"Spiderman NO WAY HOME","Tom","Zendaya","2021","Jon Watts");
       mov.Insert(9,"Shaolin Soccer","Stephen","Zhao","2001","Stephen Chow");
       mov.selectAll();
       mov.select("2021");
    }
}