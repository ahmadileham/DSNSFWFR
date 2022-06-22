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
            System.out.println("Pending queue is empty.");
        } else if (ConfessionPageJavaFX.pending.getSize() <= 5) {
            Confession a = ConfessionPageJavaFX.pending.peek();
            System.out.println("Enqueue \"" + a.getConfession() + "\" in 15 minutes..");

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
                try {
                    TimeUnit.MINUTES.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ConfessionPageJavaFX.notApprove.enqueue(ConfessionPageJavaFX.pending.dequeue());
                System.out.println("\"" + a.getConfession() + "\"" + "has been added into the queue...");

            } catch (Exception ex) {
                System.out.println("error running thread " + ex.getMessage());
            }


        } else if (ConfessionPageJavaFX.pending.getSize() <= 10 ) {
            Confession a = ConfessionPageJavaFX.pending.peek();
            System.out.println("Enqueue \"" + a.getConfession() + "\"in 10 minutes...");

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
                try {
                    TimeUnit.MINUTES.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ConfessionPageJavaFX.notApprove.enqueue(ConfessionPageJavaFX.pending.dequeue());

            } catch (Exception ex) {
                System.out.println("error running thread " + ex.getMessage());
            }


        } else if (ConfessionPageJavaFX.pending.getSize() > 10) {
            Confession a = ConfessionPageJavaFX.pending.peek();
            System.out.println("Enqueue \"" + a.getConfession() + "\"in 5 minutes...");

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
                try {
                    TimeUnit.MINUTES.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ConfessionPageJavaFX.notApprove.enqueue(ConfessionPageJavaFX.pending.dequeue());

            } catch (Exception ex) {
                System.out.println("error running thread " + ex.getMessage());
            }


        }
    }
}

