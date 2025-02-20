package com.ahd.embarkinginfxwithfile.Controller;

import com.ahd.embarkinginfxwithfile.Classes.Dress;
import com.ahd.embarkinginfxwithfile.RunApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class DressController implements Initializable {
    // Dress name TextField
    @FXML
    private TextField dressNameField;

    // Dress name Error message Label (Below TextField)
    @FXML
    private Label dressNameLabel;

    // Dress type ChoiceBox
    @FXML
    private ChoiceBox<String> dressTypeBox;

    // Dress type Error message Label (Below ChoiceBox)
    @FXML
    private Label dressTypeLabel;

    // Dress size ComboBox
    @FXML
    private ComboBox<String> dressSizeBox;

    // Dress size Error message Label (Below ComboBox)
    @FXML
    private Label dressSizeLabel;

    // Dress color ColorPicker
    @FXML
    private ColorPicker dressColorPicker;

    // Dress color Error message Label (Below ColorPicker)
    @FXML
    private Label dressColorLabel;

    // Dress details TextArea
    @FXML
    private TextArea dressDetailsArea;

    // Dress details Error message Label (Below TextArea)
    @FXML
    private Label dressDetailsLabel;

    // Dress price Slider
    @FXML
    private Slider dressPriceSlider;

    // Dress price Error and show price selection message Label (Below Slider)
    @FXML
    private Label dressPriceLabel;

    // Dress adding date DatePicker
    @FXML
    private DatePicker dressDatePicker;

    // Dress adding date Error message Label (Below DatePicker)
    @FXML
    private Label dressDateLabel;

    // Dress quantity Spinner
    @FXML
    private Spinner<Integer> dressQuantitySpinner;

    // Dress quantity Error message Label (Below Spinner)
    @FXML
    private Label dressQuantityLabel;

    // Dress discount code / Promo code PasswordField
    @FXML
    private PasswordField dressDiscountField;

    // Dress discount code / Promo code Error message Label (Below PasswordField)
    @FXML
    private Label dressDiscountLabel;

    /* Dress customer gander selection ToggleGroup
    (In Scene Builder (Looking at Right-Top > Inspector > Properties > Specific > Toggle Group))
    The RadioButton that you want to put in a group will have the same Toggle Group name.*/
    @FXML
    private ToggleGroup ganderToggle;

    // Dress customer gander Error message Label (Below RadioButton)
    @FXML
    private Label dressGanderLabel;

    // Facebook Boosted CheckBox
    @FXML
    private CheckBox boostedCheckBox;

    // Dress image ImageView
    @FXML
    private ImageView dressImageViewer;

    // Dress image Error message Label (Below Upload Button)
    @FXML
    private Label dressImageLabel;

    // Dress information save successfully or not, this message show Label, (Below Save button)
    @FXML
    private Label saveButtonLabel;

    @FXML
    void onImageUploadButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp"));
        // Show the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(null);
        // Load and display the selected image
        if (selectedFile != null) {
            Image selectedImage = new Image(selectedFile.toURI().toString());
            dressImageViewer.setImage(selectedImage);
            dressImageLabel.setText("");
        } else {
            dressImageLabel.setText("No image selected");
            dressImageLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    void onSaveButton() {
        String dressId = "P-" + new SimpleDateFormat("yyMMddHHmmss").format(new Date());

        String dressName = dressNameField.getText();
        if (dressName.isEmpty()) {
            dressNameLabel.setText("Enter Dress Name");
            dressNameLabel.setTextFill(Color.RED);
            return;
        } else {
            dressNameLabel.setText("");
        }

        String dressType = dressTypeBox.getValue();
        if (dressType == null) {
            dressTypeLabel.setText("Select Dress Type");
            dressTypeLabel.setTextFill(Color.RED);
            return;
        } else {
            dressTypeLabel.setText("");
        }

        String dressSize = dressSizeBox.getValue();
        if (dressSize == null) {
            dressSizeLabel.setText("Select Dress Size");
            dressSizeLabel.setTextFill(Color.RED);
            return;
        } else {
            dressSizeLabel.setText("");
        }

        Color color = dressColorPicker.getValue();
        String dressColor = toRGBCode(color);

        Double dressPrice = Double.parseDouble((String.format("%.2f", dressPriceSlider.getValue())));

        String dressDetails = dressDetailsArea.getText();
        if (dressDetails.isEmpty()) {
            dressDetailsLabel.setText("Enter Dress Details");
            dressDetailsLabel.setTextFill(Color.RED);
            return;
        } else if (dressDetails.length() > 50) {
            dressDetailsLabel.setText("Only 50 Characters are allowed");
            dressDetailsLabel.setTextFill(Color.RED);
            return;
        } else {
            dressDetailsLabel.setText("");
        }

        LocalDate date = dressDatePicker.getValue();
        String purchaseDate = String.valueOf(date);
        if (date == null) {
            dressDateLabel.setText("Select Dress Date");
            dressDateLabel.setTextFill(Color.RED);
            return;
        } else if (date.isAfter(LocalDate.now())) {
            dressDateLabel.setText("Can not be future date");
            dressDateLabel.setTextFill(Color.RED);
            return;
        } else {
            dressDateLabel.setText("");
        }

        int dressQuantity = dressQuantitySpinner.getValue();
        if (dressQuantity < 1) {
            dressQuantityLabel.setText("More than 0 Dress Quantity");
            dressQuantityLabel.setTextFill(Color.RED);
            return;
        } else {
            dressQuantityLabel.setText("");
        }

        String disCode = dressDiscountField.getText();

        RadioButton randomDressRadio = (RadioButton) ganderToggle.getSelectedToggle();
        String ganderType = randomDressRadio.getText();
        if (!randomDressRadio.isSelected()) {
            dressGanderLabel.setText("Select Gander Type");
            dressGanderLabel.setTextFill(Color.RED);
            return;
        } else {
            dressGanderLabel.setText("");
        }

        boolean boosted = boostedCheckBox.isSelected();

        Image image = dressImageViewer.getImage();
        String dressImage = String.valueOf(image);
        if (image == null) {
            dressImageLabel.setText("Select Dress Image");
            dressImageLabel.setTextFill(Color.RED);
            return;
        } else {
            dressImageLabel.setText("");
        }

        Dress dress = new Dress(dressId, dressName, dressType, dressSize, dressColor, dressPrice, dressDetails, purchaseDate, dressQuantity, disCode, ganderType, boosted, dressImage);
        dress.display();
        saveInFile(dress, "productDetails");
        saveButtonLabel.setText("Save Dress");
        saveButtonLabel.setTextFill(Color.GREEN);
    }

    public void saveInFile(Dress dress, String file) {
        String line = dress.getDressId() + ", " + dress.getDressName() + ", " + dress.getDressType() + ", " + dress.getDressSize() + ", " + dress.getDressColor() + ", " + dress.getDressPrice() + ", " + dress.getDressDetails() + ", " + dress.getPurchaseDate() + ", " + dress.getDressQuantity() + ", " + dress.getDiscountCode() + ", " + dress.getCustomerGander() + ", " + dress.getBoost() + ", " + dress.getDressImage() + "\n";
        try {
            RandomAccessFile raf = new RandomAccessFile(file + ".txt", "rw");
            raf.seek(raf.length());
            raf.writeBytes(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File Not Write");
        }
    }

    @FXML
    void onShowListButton() {
        RunApplication.changeScene("Info-FXML");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the Choice Box values
        ObservableList<String> type = FXCollections.observableArrayList();
        type.add("Hoodie");
        type.add("Sweater");
        type.add("T-Shirt");
        type.add("Shirt");
        type.add("Pant");
        dressTypeBox.setItems(type);

        // Initialize the Combo Box values
        ObservableList<String> size = FXCollections.observableArrayList();
        size.add("Small");
        size.add("Extra Small");
        size.add("Medium");
        size.add("Large");
        size.add("Extra Large");
        dressSizeBox.setItems(size);

        // Initialize the Spinner values
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500);
        dressQuantitySpinner.setValueFactory(valueFactory);

        dressPriceLabel.textProperty().bind(dressPriceSlider.valueProperty().asString("%.2f"));
    }

    public static String toRGBCode( Color color ) {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }
}
