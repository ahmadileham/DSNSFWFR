package javafxapplication3;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
/**
 * @author DSNSFWFR ( ILHAM , BO , CAPANG , SHAFIQ)
 **/
public class Confession {
    private int ID;
    private String confession;
    private String date;

    public Confession(String confession) {
        this.confession = confession;
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        date = simpleDateFormat.format(new Date());
    }

    public Confession(int ID,String confession, String date) {
        this.ID = ID;
        this.confession = confession;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getConfession() {
        return confession;
    }

    public void setConfession(String confession) {
        this.confession = confession;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\nConfession: \n" +
                "ID=" + ID +
                "\n, confession='" + confession + '\'' +
                "\n, date='"+date+'\''+
                '\n';
    }
}
