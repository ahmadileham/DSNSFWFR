package javafxapplication3;

import java.time.LocalDateTime;
import java.util.Date;

public class Confession {
    private int ID;
    private String confession;
    private Date date;
    private static int i=1;

    public Confession(String confession) {
        this.ID = i;
        this.confession = confession;
        date = new Date();
        i++;
    }

    public Confession(String confession, Date date) {
        this.confession = confession;
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
