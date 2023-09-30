package org.example;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();

        Connection connection = connector.getConnection();

        connector.closeConnection();
    }
}