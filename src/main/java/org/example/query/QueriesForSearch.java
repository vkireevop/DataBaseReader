package org.example.query;

import org.example.util.DatabaseConnector;
import org.example.model.Buyer;
import org.example.util.ExceptionWritter;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueriesForSearch {
    public static List<Buyer> getBuyersByLastname(String lastName)  {
        List<Buyer> resultList = new ArrayList<>();
        DatabaseConnector connector = new DatabaseConnector();
        String query = "SELECT * FROM Buyers WHERE last_name = ?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(query)) {
            statement.setString(1, lastName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    resultList.add(new Buyer(resultSet.getString("first_name"),lastName));
                }
            } catch (SQLException e) {
                ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                        e.getLocalizedMessage());
                System.exit(1);
            }
        } catch (SQLException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
            System.exit(1);
        }
        return resultList;
    }
    public static List<Buyer> findBuyersByProductPurchaseCount( String productName, int count) {
        String query = "SELECT Buyers.first_name, Buyers.last_name " +
                "FROM Buyers " +
                "JOIN Purchases ON Buyers.id = Purchases.buyer_id " +
                "JOIN Products ON Purchases.product_id = Products.id " +
                "WHERE Products.name = ? " +
                "GROUP BY Buyers.id " +
                "HAVING COUNT(*) >= ?";
        List<Buyer> resultList = new ArrayList<>();
        DatabaseConnector connector = new DatabaseConnector();
        try (PreparedStatement statement = connector.getConnection().prepareStatement(query)) {
            statement.setString(1, productName);
            statement.setInt(2, count);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    resultList.add(new Buyer(resultSet.getString("first_name"),
                            resultSet.getString("last_name")));
                }
            }
        } catch (SQLException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
            System.exit(1);
        }
        return resultList;
    }
    public static List<Buyer> findBuyersByTotalCostRange(double minCost, double maxCost) {
        String query = "SELECT Buyers.first_name, Buyers.last_name, SUM(Products.price) as total_cost " +
                "FROM Buyers " +
                "JOIN Purchases ON Buyers.id = Purchases.buyer_id " +
                "JOIN Products ON Purchases.product_id = Products.id " +
                "GROUP BY Buyers.id " +
                "HAVING SUM(Products.price) >= ? AND SUM(Products.price) <= ?";
        List<Buyer> resultList = new ArrayList<>();
        DatabaseConnector connector = new DatabaseConnector();
        try (PreparedStatement statement = connector.getConnection().prepareStatement(query)) {
            statement.setDouble(1, minCost);
            statement.setDouble(2, maxCost);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    resultList.add(new Buyer(resultSet.getString("first_name"),resultSet.getString
                            ("last_name")));
                }
            }
        } catch (SQLException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
            System.exit(1);
        }
        return resultList;
    }
    public static List<Buyer> findInactiveBuyers( int limit) {
        String query = "SELECT Buyers.first_name, Buyers.last_name, COUNT(*) as purchase_count " +
                "FROM Buyers " +
                "JOIN Purchases ON Buyers.id = Purchases.buyer_id " +
                "GROUP BY Buyers.id " +
                "ORDER BY COUNT(*) ASC " +
                "LIMIT ?";
        List<Buyer> resultList = new ArrayList<>();
        DatabaseConnector connector = new DatabaseConnector();
        try (PreparedStatement statement = connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, limit);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    resultList.add(new Buyer(resultSet.getString("first_name"),resultSet.getString("last_name")));
                }
            }
        } catch (SQLException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
            System.exit(1);
        }
        return resultList;
    }

}
