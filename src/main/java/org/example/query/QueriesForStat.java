package org.example.query;

import org.example.model.BuyerForStat;
import org.example.model.Purchase;
import org.example.util.DatabaseConnector;
import org.example.util.ExceptionWritter;

import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueriesForStat {
    public static List<BuyerForStat> getBuyersByPeriod(String startDate, String endDate) {
        DatabaseConnector connector = new DatabaseConnector();
        List<BuyerForStat> resultList = new ArrayList<>();
        String query = "SELECT "+
                "b.id," +
                "b.first_name,"+
                "b.last_name, "+
                "SUM(pr.price) AS total_purchase_price "+
                "FROM "+
                "purchases p "+
                "JOIN "+
                "products pr ON p.product_id = pr.id "+
                "JOIN "+
                "buyers b ON p.buyer_id = b.id "+
                "WHERE "+
                "p.purchase_date BETWEEN ? AND ? "+
                "GROUP BY "+
                "b.id, b.last_name,b.first_name; ";

        try (PreparedStatement preparedStatement = connector.getConnection().prepareStatement(query)) {
            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setDate(2, Date.valueOf(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BuyerForStat buyer = new BuyerForStat();
                buyer.setFirst_name(resultSet.getString("first_name"));
                buyer.setLast_name(resultSet.getString("last_name"));
                buyer.setId(resultSet.getInt("id"));
                buyer.setTotalExpense(resultSet.getDouble("total_purchase_price"));
                resultList.add(buyer);
            }
        } catch (SQLException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
        }
        return resultList;
    }
    public static double getProductBuyByName(String name, String startDate, String endDate) {
        DatabaseConnector connector = new DatabaseConnector();
        double result=0;
        String query = "SELECT SUM(pr2.price), " +
                "pr2.name " +
                "FROM purchases p2 " +
                "JOIN products pr2 ON p2.product_id = pr2.id " +
                "where pr2.name = ? AND p2.purchase_date BETWEEN ? AND ? " +
                "group by " +
                "product_id, pr2.name ";
        try (PreparedStatement preparedStatement = connector.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, Date.valueOf(startDate));
            preparedStatement.setDate(3, Date.valueOf(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getDouble("sum");
        } catch (SQLException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
            System.exit(1);
        }
        return result;
    }

        public static List<Purchase> getListPurchasesForBuyerByIdAndPeriod(int id, String startDate, String endDate) {
        DatabaseConnector connector = new DatabaseConnector();
        List<Purchase> resultList = new ArrayList<>();
        String query = "SELECT " +
                "pr.name AS product_name " +
                "FROM " +
                "purchases p " +
                "JOIN " +
                "products pr ON p.product_id = pr.id " +
                "JOIN "+
                "buyers b ON p.buyer_id = b.id "+
                "WHERE " +
                "b.id = ? " +
                "AND p.purchase_date BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connector.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setDate(2, Date.valueOf(startDate));
            preparedStatement.setDate(3, Date.valueOf(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Purchase purchase = new Purchase();
                purchase.setName(resultSet.getString("product_name"));
                resultList.add(purchase);
            }
        } catch (SQLException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
            System.exit(1);;
        }
            return resultList;
    }

    public static double totalExpenseForPeriod(String start, String end) {
        DatabaseConnector connector = new DatabaseConnector();
        String query = "SELECT " +
                "SUM(pr.price) AS total_purchase_cost " +
                "FROM " +
                "purchases p " +
                "JOIN " +
                "products pr ON p.product_id=pr.id " +
                "WHERE " +
                "p.purchase_date BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connector.getConnection().prepareStatement(query)) {
            preparedStatement.setDate(1, Date.valueOf(start));
            preparedStatement.setDate(2, Date.valueOf(end));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble(1);
        } catch (SQLException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
            System.exit(1);
        }
        return 0;
    }
    public static double AverageExpenseForPeriod(String start, String end) {
        DatabaseConnector connector = new DatabaseConnector();
        String query = "SELECT " +
                "AVG(pr.price) AS average_purchase_cost " +
                "FROM " +
                "purchases p " +
                "JOIN " +
                "products pr ON p.product_id=pr.id " +
                "WHERE " +
                "p.purchase_date BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connector.getConnection().prepareStatement(query)) {
            preparedStatement.setDate(1, Date.valueOf(start));
            preparedStatement.setDate(2, Date.valueOf(end));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble("average_purchase_cost");
        } catch (SQLException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
            System.exit(1);;
        }
        return 0;
    }
}