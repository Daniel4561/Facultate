package PaooGame.DataBase;

import PaooGame.Entity.BOB;
import PaooGame.Entity.Zombie;
import PaooGame.Entity.ZombieSeter;
import PaooGame.Game;
import PaooGame.LoadLvl.LoadLvl;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccesDataBase {

    Game g;
    public AccesDataBase(Game g)
    {
        this.g = g;
    }

    public static void insertScore(String name, double time)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "INSERT INTO Scores (Name, Time) " +
                    "VALUES ('" + name + "' , " + time + ");";

            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public static void getScores(Graphics2D g2) {
        Connection c = null;
        Statement stmt = null;
        String[] sir = new String[2];

        final String QUERY = "SELECT Name, Time FROM Scores";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY + " ORDER BY Time");
            int counter = 0;
            int y = 200;
            while (rs.next() && counter != 7) {
                String Nume = rs.getString("Name");
                double Time = rs.getDouble("Time");

                DecimalFormat df = new DecimalFormat("#0.00");

                sir[0] = Nume;
                sir[1] = String.valueOf(df.format(Time));

                if (counter < 7) {
                    counter++;
                    g2.setColor(Color.white);
                    g2.drawString(sir[0], 180, y);
                    g2.drawString(sir[1], 930, y);
                    y += 60;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public static void saveToDataBase(String name, BOB bob,double time, int nivel, Zombie[] zombie)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            //data
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime data = LocalDateTime.now();

            String dataS = dtf.format(data);
            //zombies
            String M1 = "";
            String K53 = "";
            String R32 = "";
            int counter ;
            if(nivel == 1)
            {
                counter = 0;
                for(int i = 0; i < 5; ++i)
                {
                    if(zombie[i] != null) {
                        counter++;
                        M1 = M1 + String.valueOf(zombie[i].x);
                        M1 = M1 + ',';
                        M1 = M1 + String.valueOf(zombie[i].y);
                        M1 = M1 + ',';
                    }
                }
                M1 = M1 + String.valueOf(counter);

                counter = 0;
                for(int i = 5; i < 11; ++i)
                {
                    if(zombie[i] != null) {
                        counter++;
                        K53 = K53 + String.valueOf(zombie[i].x);
                        K53 = K53 + ',';
                        K53 = K53 + String.valueOf(zombie[i].y);
                        K53 = K53 + ',';
                    }
                }
                K53 = K53 + String.valueOf(counter);

                counter = 0;
                for(int i = 11; i < 17; ++i)
                {
                    if(zombie[i] != null) {
                        counter++;
                        R32 = R32 + String.valueOf(zombie[i].x);
                        R32 = R32 + ',';
                        R32 = R32 + String.valueOf(zombie[i].y);
                        R32 = R32 + ',';
                    }
                }
                R32 = R32 + String.valueOf(counter);
            }

            if(nivel == 2)
            {
                counter = 0;
                for(int i = 0; i < 7; ++i)
                {
                    if(zombie[i] != null) {
                        counter++;
                        M1 = M1 + String.valueOf(zombie[i].x);
                        M1 = M1 + ',';
                        M1 = M1 + String.valueOf(zombie[i].y);
                        M1 = M1 + ',';
                    }
                }
                M1 = M1 + String.valueOf(counter);


                counter = 0;
                for(int i = 7; i < 14; ++i)
                {
                    if(zombie[i] != null) {
                        counter++;
                        K53 = K53 + String.valueOf(zombie[i].x);
                        K53 = K53 + ',';
                        K53 = K53 + String.valueOf(zombie[i].y);
                        K53 = K53 + ',';
                    }
                }
                K53 = K53 + String.valueOf(counter);

                counter = 0;
                for(int i = 14; i < 20; ++i)
                {
                    if(zombie[i] != null) {
                        counter++;
                        R32 = R32 + String.valueOf(zombie[i].x);
                        R32 = R32 + ',';
                        R32 = R32 + String.valueOf(zombie[i].y);
                        R32 = R32 + ',';
                    }
                }
                R32 = R32 + String.valueOf(counter);

            }

            if(nivel == 3)
            {
                counter = 0;
                for(int i = 0; i < 10; ++i)
                {
                    if(zombie[i] != null) {
                        counter++;
                        M1 = M1 + String.valueOf(zombie[i].x);
                        M1 = M1 + ',';
                        M1 = M1 + String.valueOf(zombie[i].y);
                        M1 = M1 + ',';
                    }
                }
                M1 = M1 + String.valueOf(counter);

                counter = 0;
                for(int i = 10; i < 24; ++i)
                {
                    if(zombie[i] != null) {
                        counter++;
                        K53 = K53 + String.valueOf(zombie[i].x);
                        K53 = K53 + ',';
                        K53 = K53 + String.valueOf(zombie[i].y);
                        K53 = K53 + ',';
                    }
                }
                K53 = K53 + String.valueOf(counter);

                counter = 0;
                for(int i = 24; i < 39; ++i)
                {
                    if(zombie[i] != null) {
                        counter++;
                        R32 = R32 + String.valueOf(zombie[i].x);
                        R32 = R32 + ',';
                        R32 = R32 + String.valueOf(zombie[i].y);
                        R32 = R32 + ',';
                    }
                }
                R32 = R32 + String.valueOf(counter);
            }
            String sql = "INSERT INTO Load (Name, xPlayer, yPlayer, damage, lifes, timp, Nivel, Data, M1, K53, R32) " +
                    "VALUES ('"+ name +"', " + bob.x + "," + bob.y + "," + bob.damage + ","+ bob.lives +"," + time + ","+ nivel + ",'" + dataS + "','"+ M1 +"','"+ K53 +"','"+ R32 +"');";

            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public static void loadDataBase(Game g,Graphics2D g2)
    {
        Connection c = null;
        Statement stmt = null;
        String[] sir = new String[2];
        int y = 170;

        final String QUERY = "SELECT Name, xPlayer, yPlayer, damage, lifes, timp, Nivel, Data, M1, K53, R32 FROM Load";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY + " ORDER BY Data DESC");
            int counter = 0;
            while (rs.next() && counter < 5) {

                counter++;
                String name = rs.getString("Name");
                int xBob = rs.getInt("xPlayer");
                int yBob = rs.getInt("yPlayer");
                int damage = rs.getInt("damage");
                int lifes = rs.getInt("lifes");
                double time = rs.getDouble("timp");
                int nivel = rs.getInt("Nivel");
                String data = rs.getString("Data");
                String M1 = rs.getString("M1");
                String K53 = rs.getString("K53");
                String R32 = rs.getString("R32");

                g2.setFont(new Font("Arial", Font.ITALIC, 50));
                g2.setColor(Color.red);
                g2.drawString(name, 150, y +50);
                g2.drawString(String.valueOf(nivel), 600,y+50);
                g2.drawString(String.valueOf(new DecimalFormat("#0.00").format(time)), 1000,y+50);
                g2.setColor(Color.blue);
                g2.drawRect(90, y, 1100, 60);
                y+=100;

            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    public static void loadDataBaseUpdate(Game g, int number)
    {
        Connection c = null;
        Statement stmt = null;
        int y = 170;

        final String QUERY = "SELECT Name, xPlayer, yPlayer, damage, lifes, timp, Nivel, Data, M1, K53, R32 FROM Load";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY + " ORDER BY Data DESC");
            int counter = 0;
            while (rs.next() && counter < 5) {


                String name = rs.getString("Name");
                int xBob = rs.getInt("xPlayer");
                int yBob = rs.getInt("yPlayer");
                int damage = rs.getInt("damage");
                int lifes = rs.getInt("lifes");
                double time = rs.getDouble("timp");
                int nivel = rs.getInt("Nivel");
                String data = rs.getString("Data");
                String M1 = rs.getString("M1");
                String K53 = rs.getString("K53");
                String R32 = rs.getString("R32");

                if (number != -1 && counter == number) {
                    g.bob.Reset();
                    g.bob = BOB.getInstance(g, g.wnd);
                    g.bob.x = xBob;
                    g.bob.y = yBob;
                    g.bob.damage = damage;
                    g.bob.lives = lifes;
                    g.font.timeCounter = time;
                    g.buffer = name;
                    g.lvl = nivel;
                    switch (nivel) {
                        case 1:
                            System.out.println("Nivel 1 load");
                            g.loadLvl.loadLvl1(g, M1, K53, R32);
                            break;
                        case 2:
                            System.out.println("Nivel 2 load");
                            g.loadLvl.loadLvl2(g, M1, K53, R32);
                            break;
                        case 3:
                            System.out.println("Nivel 3 load");
                            g.loadLvl.loadLvl3(g, M1, K53, R32);
                            break;
                    }
                }
                counter++;
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
