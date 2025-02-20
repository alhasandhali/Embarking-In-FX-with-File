package com.ahd.embarkinginfxwithfile.Controller;

import com.ahd.embarkinginfxwithfile.Classes.Dress;
import com.ahd.embarkinginfxwithfile.RunApplication;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InfoController implements Initializable {
    // Search TextField
    @FXML
    private TextField searchBar;

    // Table
    @FXML
    private TableView<Dress> productTable;

    // Table columns
    @FXML
    private TableColumn<Dress, String> nameCol;

    @FXML
    private TableColumn<Dress, String> typeCol;

    @FXML
    private TableColumn<Dress, String> colorCol;

    @FXML
    private TableColumn<Dress, Number> priceCol;

    @FXML
    private TableColumn<Dress, String> dateCol;

    @FXML
    private TableColumn<Dress, Number> quantityCol;

    @FXML
    private TableColumn<Dress, Boolean> boostedCol;

    @FXML
    private Label specialError;

    /* Every Label besides their heading Label
     such as:
     Dress ID: idShowLabel
     Dress Name: nameShowLabel
     ...........................*/
    @FXML
    private Label idShowLabel;

    @FXML
    private Label nameShowLabel;

    @FXML
    private Label typeShowLabel;

    @FXML
    private Label sizeShowLabel;

    @FXML
    private Label colorShowLabel;

    @FXML
    private Label priceShowLabel;

    @FXML
    private Label detailsShowLabel;

    @FXML
    private Label dateShowLabel;

    @FXML
    private Label quantityShowLabel;

    @FXML
    private Label quantityErrorLabel;

    @FXML
    private Label codeShowLabel;

    @FXML
    private Label ganderShowLabel;

    @FXML
    private Label boostedShowLabel;

    @FXML
    private ImageView imageShowLabel;

    private Dress dressShowLabel;

    @FXML
    void onBackButton() {
        RunApplication.changeScene("Dress-FXML");
    }

    @FXML
    void onDeleteButton() {
        Dress selectedDress = productTable.getSelectionModel().getSelectedItem();

        if (selectedDress != null) {
            // Remove from TableView
            productTable.getItems().remove(selectedDress);

            // Check if there are remaining items after deletion
            if (!productTable.getItems().isEmpty()) {
                // Select the first item
                productTable.getSelectionModel().selectFirst();
                // Update details
                showDressDetails(productTable.getSelectionModel().getSelectedItem());
            } else {
                // Clear details if no items are left
                clearDressDetails();
            }
        }
    }

    @FXML
    void onEditButton() {
        specialError.setText("We haven't learned how to update the text file yet.");
    }

    @FXML
    void onShowButton() {
        if (codeShowLabel.getText().equals("********")) {
            codeShowLabel.setText(dressShowLabel.getDiscountCode());
        } else {
            codeShowLabel.setText("********");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Dress> dresseObservableList = FXCollections.observableArrayList();
        List<Dress> dList = readFileForTable("productDetails");
        dresseObservableList.addAll(dList);
        productTable.setItems(dresseObservableList);

        nameCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDressName()));
        typeCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDressType()));
        colorCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDressColor()));
        priceCol.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getDressPrice()));
        dateCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPurchaseDate()));
        quantityCol.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getDressQuantity()));
        boostedCol.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().getBoost()));

//        idShowLabel.setText(dressShowLabel.getDressId());
//        nameShowLabel.setText(dressShowLabel.getDressName());
//        typeShowLabel.setText(dressShowLabel.getDressType());
//        sizeShowLabel.setText(dressShowLabel.getDressSize());
//        colorShowLabel.setText(dressShowLabel.getDressColor());
//        priceShowLabel.setText(dressShowLabel.getDressPrice() + "BDT");
//        detailsShowLabel.setText(dressShowLabel.getDressDetails());
//        dateShowLabel.setText(dressShowLabel.getPurchaseDate());
//        quantityShowLabel.setText(dressShowLabel.getDressQuantity() + "");
//        boostedShowLabel.setText(dressShowLabel.getBoost() + "");
//        codeShowLabel.setText("********");
//        ganderShowLabel.setText(dressShowLabel.getCustomerGander());
//        imageShowLabel.setImage(new Image(dressShowLabel.getDressImage()));

        // Ensure the table is not empty before selecting
        if (!productTable.getItems().isEmpty()) {
            productTable.getSelectionModel().selectFirst(); // Select the first row
            showDressDetails(productTable.getSelectionModel().getSelectedItem()); // Show details
        }

        // For Click on a table row and show row's information on below Field
        productTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldSelection, newSelection) -> {
            if (newSelection != null) {
                dressShowLabel = newSelection;
                showDressDetails(newSelection);
            }
        });

        //For searchbar
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {filterData(newValue);});
    }

    // Show dress information on below field
    private void showDressDetails(Dress dress) {
        idShowLabel.setText(dress.getDressId());
        nameShowLabel.setText(dress.getDressName());
        typeShowLabel.setText(dress.getDressType());
        sizeShowLabel.setText(dress.getDressSize());
        colorShowLabel.setText(dress.getDressColor());
        priceShowLabel.setText(dress.getDressPrice() + "BDT");
        detailsShowLabel.setText(dress.getDressDetails());
        dateShowLabel.setText(dress.getPurchaseDate());
        if (dress.getDressQuantity() < 10) {
            quantityErrorLabel.setText("Quantity is less than 10");
            quantityErrorLabel.setTextFill(Color.RED);
        } else {
            quantityErrorLabel.setText("");
        }
        quantityShowLabel.setText(dress.getDressQuantity() + "");
        boostedShowLabel.setText(dress.getBoost() + "");
        codeShowLabel.setText("********");
        ganderShowLabel.setText(dress.getCustomerGander());
//        imageShowLabel.setImage();
    }

    private void clearDressDetails() {
        idShowLabel.setText("");
        nameShowLabel.setText("");
        typeShowLabel.setText("");
        sizeShowLabel.setText("");
        colorShowLabel.setText("");
        priceShowLabel.setText("");
        detailsShowLabel.setText("");
        dateShowLabel.setText("");
        quantityShowLabel.setText("");
        boostedShowLabel.setText("");
        codeShowLabel.setText("********");
        ganderShowLabel.setText("");
//        imageShowLabel.setImage(null);
    }

    // Read the information from the text file
    private List<Dress> readFileForTable(String file) {
        List<Dress> dressList = new ArrayList<>();
        try {
            RandomAccessFile raf = new RandomAccessFile(file + ".txt", "r");
            String line;
            while ((line = raf.readLine()) != null) {
                String[] parts = line.split(",");
                String dressId = parts[0].trim();
                String dressName = parts[1].trim();
                String dressType = parts[2].trim();
                String dressSize = parts[3].trim();
                String dressColor = parts[4].trim();
                double dressPrice = Double.parseDouble(parts[5].trim());
                String dressDetails = parts[6].trim();
                String dressDate = parts[7].trim();
                int dressQuantity = Integer.parseInt(parts[8].trim());
                String dressDiscountCode = parts[9].trim();
                String dressCustomerGander = parts[10].trim();
                boolean dressBoost = Boolean.parseBoolean(parts[11].trim());
                String dressImage = parts[12].trim();

                dressShowLabel = new Dress(dressId, dressName, dressType, dressSize, dressColor, dressPrice, dressDetails, dressDate, dressQuantity, dressDiscountCode, dressCustomerGander, dressBoost, dressImage);

//                Dress dress = new Dress(dressId, dressName, dressType, dressSize, dressColor, dressPrice, dressDetails, dressDate, dressQuantity, dressDiscountCode, dressCustomerGander, dressBoost, dressImage);
                dressList.add(dressShowLabel);
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading file");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dressList;
    }

    //For searchbar
    private void filterData(String keyword) {
        ObservableList<Dress> filteredData = FXCollections.observableArrayList();
        for (Dress dress : readFileForTable("productDetails")) {
            if (dress.getDressName().toLowerCase().contains(keyword.toLowerCase()) || dress.getDressType().toLowerCase().contains(keyword.toLowerCase()) || dress.getDressId().toLowerCase().contains(keyword.toLowerCase()) || dress.getPurchaseDate().toLowerCase().contains(keyword.toLowerCase()) || String.valueOf(dress.getBoost()).contains(keyword)) {
                filteredData.add(dress);
            }
        }
        productTable.setItems(filteredData);
    }
}
