package com.example.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        //Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);
        int price=calculatePrice();
        // Log.v("MainActivity", "The price is "+ price);
        String priceMessage=createOrderSummary(price, hasWhippedCream);
        displayMessage(priceMessage);
    }

    /*
     * This method calculates price of the order based on the current quantity.
     *
     * @returns total price
     */
    private int calculatePrice()
    {
        int price=quantity*5;
        return price;
    }

    /*
     * This method creates summary of the order.
     *
     * @param price of the order
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @return text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream)
    {
       String priceMessage="Name: Kanika";
       priceMessage+="\nAdd Whipped Cream? "+ addWhippedCream;
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