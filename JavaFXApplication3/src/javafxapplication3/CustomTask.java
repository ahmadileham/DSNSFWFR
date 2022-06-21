package javafxapplication3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class CustomTask extends TimerTask {

    public CustomTask() {
        //Constructor

    }

    public void run() {
        if (ConfessionPageJavaFX.pending.isEmpty()) {
            System.out.println("test");
        } else if (ConfessionPageJavaFX.pending.getSize() <= 5) {
            Confession a = ConfessionPageJavaFX.pending.peek();
            System.out.println("5 seconds");

            int ID = 0;
            try {
                Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
                Statement myStmt = connection.createStatement();
                Statement myStmt2 = connection.createStatement();
                Statement myStmt3 = connection.createStatement();

                ResultSet myRs = myStmt2.executeQuery("SELECT * FROM not_approve ORDER BY confessionID DESC LIMIT 1");

                while (myRs.next()) {
                    ID = myRs.getInt("confessionID") + 1;
                }

                myStmt.executeUpdate("insert into not_approve(confessionID, confession, date_post, reply_ID) values(" + ID + ",'" + a.getConfession() + "','" + a.getDate() + "',"+a.getReply_ID()+")");

                myRs.close();
                myStmt.close();
                myStmt2.close();
                myStmt3.close();
                ConfessionPageJavaFX.notApprove.enqueue(ConfessionPageJavaFX.pending.dequeue());

            } catch (Exception ex) {
                System.out.println("error running thread " + ex.getMessage());
            }

            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (ConfessionPageJavaFX.pending.getSize() <= 10 ) {
            Confession a = ConfessionPageJavaFX.pending.peek();
            System.out.println("5 seconds");

            int ID = 0;
            try {
                Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
                Statement myStmt = connection.createStatement();
                Statement myStmt2 = connection.createStatement();
                Statement myStmt3 = connection.createStatement();

                ResultSet myRs = myStmt2.executeQuery("SELECT * FROM not_approve ORDER BY confessionID DESC LIMIT 1");

                while (myRs.next()) {
                    ID = myRs.getInt("confessionID") + 1;
                }

                myStmt.executeUpdate("insert into not_approve(confessionID, confession, date_post, reply_ID) values(" + ID + ",'" + a.getConfession() + "','" + a.getDate() + "',"+a.getReply_ID()+")");

                myRs.close();
                myStmt.close();
                myStmt2.close();
                myStmt3.close();
                ConfessionPageJavaFX.notApprove.enqueue(ConfessionPageJavaFX.pending.dequeue());

            } catch (Exception ex) {
                System.out.println("error running thread " + ex.getMessage());
            }

            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (ConfessionPageJavaFX.pending.getSize() > 10) {
            Confession a = ConfessionPageJavaFX.pending.peek();
            System.out.println("5 seconds");

            int ID = 0;
            try {
                Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
                Statement myStmt = connection.createStatement();
                Statement myStmt2 = connection.createStatement();
                Statement myStmt3 = connection.createStatement();

                ResultSet myRs = myStmt2.executeQuery("SELECT * FROM not_approve ORDER BY confessionID DESC LIMIT 1");

                while (myRs.next()) {
                    ID = myRs.getInt("confessionID") + 1;
                }

                myStmt.executeUpdate("insert into not_approve(confessionID, confession, date_post, reply_ID) values(" + ID + ",'" + a.getConfession() + "','" + a.getDate() + "',"+a.getReply_ID()+")");

                myRs.close();
                myStmt.close();
                myStmt2.close();
                myStmt3.close();
                ConfessionPageJavaFX.notApprove.enqueue(ConfessionPageJavaFX.pending.dequeue());

            } catch (Exception ex) {
                System.out.println("error running thread " + ex.getMessage());
            }

            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

