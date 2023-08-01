package com.example.ticketbooking;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.sql.*;

public class HelloController {


    @FXML
    private TextField ticketname;

    @FXML
    private TextField noOfTickets;

    @FXML
    private Text message;

    @FXML
    private Text errorMessage;

    @FXML
    protected void calculatePrice(){
        String SQL_SELECT = "Select price from ticket where ticket_type=?";
        double price=0;
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/booking_details", "root", null);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
                preparedStatement.setString(1,ticketname.getText());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                price = resultSet.getDouble("price");
            }

            if(price==0){
                errorMessage.setVisible(true);
                message.setVisible(false);
            }else{
                errorMessage.setVisible(false);
                message.setVisible(true);
                int quantity = Integer.parseInt(noOfTickets.getText());
                double totalPrice = price*quantity;

                message.setText("Your Total Purchase Cost is: $"+ totalPrice);
            }
            System.out.println("Your Ticket Cost is: "+ price);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}