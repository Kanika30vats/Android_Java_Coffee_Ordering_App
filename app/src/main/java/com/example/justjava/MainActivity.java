package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        // Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);
        int price=calculatePrice(hasWhippedCream,hasChocolate);
        // Log.v("MainActivity", "The price is "+ price);
        String priceMessage=createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        // To ensure that the intent is handled only by an email app (and not other text messaging of social apps),
        // then use ACTION_SENDTO action and include "mailto:" data scheme.
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this.
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }

        displayMessage(priceMessage);
    }

    /*
     * This method calculates price of the order based on the current quantity.
     *
     * @returns total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate)
    {
        // Price of 1 cup of coffee
        int basePrice = 5;

        // Add $1 if the user wants whipped cream
        if (addWhippedCream)
        {
            basePrice += 1;
        }

        // Add $2 if the user wants chocolate
        if (addChocolate)
        {
            basePrice += 2;
        }

        // Calculate the total price by multiplying by quantity
        return quantity*basePrice;
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
       String priceMessage = getString(R.string.order_summary_name, name);
       // we are using order_summary_name as a string resource and we are filling in the blank using name variable.
       priceMessage += getString(R.string.order_summary_whipped_cream)+ addWhippedCream;
       priceMessage += getString(R.string.order_summary_chocolate)+ addChocolate;
       priceMessage += getString(R.string.order_summary_quantity)+quantity;
       priceMessage += getString(R.string.order_summary_total)+price;
       priceMessage += "\n" + getString(R.string.thank_you); // getString() takes input string resource and returns a String.
       return priceMessage;
    }

    /*
     * This method is called when minus button is clicked.
     */
    public void decrement(View view)
    {
        if (quantity==1)
        {
            // To show an error message as a toast.
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();

            // Exit this method early because there's nothing left to do.
            return;
        }
        quantity-=1;
        displayQuantity(quantity);
    }

    /*
     * This method is called when plus button is clicked.
     */
    public void increment(View view)
    {
        if (quantity==100)
        {
            // To show an error message as a toast.
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();

            // Exit this method early because there's nothing left to do.
            return;
        }
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