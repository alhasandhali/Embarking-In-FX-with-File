Dress Collection Management - JavaFX Application

Description

This JavaFX application allows users to store and manage a dress collection through an interactive GUI. Users can enter dress details in the first scene and view a list of stored dresses in the second scene. The application ensures data validation, error handling, and data persistence through a text file. It also includes filtering, dynamic color coding for quantity alerts, and interactive discount code visibility.

Features

Two Scenes: One for entering dress details and another for displaying the dress list.

Error Handling: Each input field has a specific error message that displays only when necessary.

Data Persistence: Information is stored in a text file and is loaded in the second scene.

Auto-loading Last Entry: The second scene automatically shows the last entered dress.

Table Selection: Clicking on a row in the table displays dress details.

Quantity Alert: If the quantity is less than 10, the text color turns red.

Discount Code Visibility Toggle: Initially hidden; users can show/hide it with a toggle button.

Edit Button: Displays an alert that the feature is coming soon.

Delete Button: Disabled by default.

Search & Filter: A text field allows filtering the list based on any input by pressing enter.

User-friendly UI: Clean and interactive interface.

Installation & Setup

Prerequisites

Java 17 or higher

JavaFX SDK

An IDE such as IntelliJ IDEA or Eclipse with JavaFX support

Steps to Run

Clone the repository:

git clone https://github.com/your-username/dress-collection-management.git

Open the project in your preferred Java IDE.

Configure JavaFX SDK in your project settings.

Run the RunApplication.java file to start the application.

Usage

Enter Dress Details: Provide dress name, category, price, quantity, and optional discount code.

Save Dress: Saves the dress information and transitions to the second scene.

View & Search Dresses: See a list of saved dresses, apply filters, and click on a row to view details.

Toggle Discount Code: Click the toggle button to show/hide discount codes.

Editing & Deleting: Editing will show a feature-coming-soon alert; deleting is disabled.

Contributing

Pull requests are welcome! If you have any suggestions or want to add more features, feel free to contribute.

Author

Developed by Al Hasan Dhali
