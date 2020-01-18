package com.info.rating.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MovieParser {

    private static final String url = "jdbc:mysql://localhost:3306/MySQL";
    private static final String user = "root";
    private static final String password = "";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;

  //  private static Logger logger = Logger.getLogger(MovieParser.class.getName());

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://www.kinopoisk.ru/top/").get();
        Elements trs = doc.select("table.js-rum-hero tr");

        //remove header row
        trs.remove(0);

        for (Element tr : trs) {
            Elements tds = tr.getElementsByTag("td");
            Elements id = tds.eq(0);
            Elements movieOrDate = tds.eq(1);
            Elements raitingOrVoting = tds.eq(2);

            String index = id.text();
            String mod = movieOrDate.text();
            String rov = raitingOrVoting.text();

            for (String retval : rov.split("[\\(||\\)]")) {
                String[] text = new String[]{retval.replaceAll("\\s+", "")};
                List<String> list = new ArrayList<>();
                for (String element : text){
                    list.add(element);
                    System.out.println(list);
                }
            }
        }
    }

    public void insert(String name, Date date, double rating, int voters){
        String query = "INSERT INTO movie.kinopoisk_top (name, date, rating, voters)" + "VALUES(?,?,?,?)";
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, date);
            preparedStatement.setDouble(3, rating);
            preparedStatement.setInt(4, voters);
            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw new ExceptionInInitializerError(e);
        } finally {
            try { connection.close(); } catch (SQLException se) { se.getMessage(); }
            try { preparedStatement.close(); } catch (SQLException se) { se.getMessage(); }
        }
    }
}
