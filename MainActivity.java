package com.example.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int quantity=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     * This method is called when order button is clicked.
     */
    public void submitOrder(View view)
    {
        // Reads name from EditText Field
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        // getText() has return data type is 'Editable'.
        // toString() is called on the Editable object to make return type as String because Editable object cannot be stored in a String variable.
        // toString() returns a String that can be stored in name object.
        // nameField.getText().toString();  This is called Chaining method calls.
        // Log message is used to check that the code is working or not.
        // Log message is added to verify that the 'name' actually calls the 'name_field'.
        // Log.v("MainActivity", "Name: " + name);

        // Figure out if the user wants whipped cream toppinig
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        // Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);
        int price=calculatePrice();
        // Log.v("MainActivity", "The price is "+ price);
        String priceMessage=createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    /*
     * This method calculates price of the order based on the current quantity.
     *
     * @returns total price
     */
    private int calculatePrice()
    {
       return quantity*5;
    }

    /*
     * This method creates summary of the order.
     *
     * @param name of the customer
     * @param price of the order
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate)
    {
       String priceMessage="Name: "+name;
       priceMessage+="\nAdd Whipped Cream? "+ addWhippedCream;
       priceMessage+="\nAdd Chocolate? "+ addChocolate;
       priceMessage+="\nQuantity: "+quantity;
       priceMessage+="\nTotal: $"+price;
       priceMessage+="\nThank You!";
       return priceMessage;
    }

    /*
     * This method is called when minus button is clicked.
     */
    public void decrement(View view)
    {
        quantity-=1;
        displayQuantity(quantity);
    }

    /*
     * This method is called when plus button is clicked.
     */
    public void increment(View view)
    {
        quantity+=1;
        displayQuantity(quantity);
    }

    /*
     * This method displays the given quantity value on screen
     */
    private void displayQuantity(int numberOfCoffees)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+numberOfCoffees);
    }

    /*
     * This method displays given quantity value on screen
     */
    private void displayMessage(String message)
    {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}