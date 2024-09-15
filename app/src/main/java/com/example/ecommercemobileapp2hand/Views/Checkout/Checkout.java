package com.example.ecommercemobileapp2hand.Views.Checkout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ecommercemobileapp2hand.Controllers.UserOrderHandler;
import com.example.ecommercemobileapp2hand.Controllers.UserOrderProductsHandler;
import com.example.ecommercemobileapp2hand.Models.Bag;
import com.example.ecommercemobileapp2hand.Models.Singleton.UserAccountManager;
import com.example.ecommercemobileapp2hand.Models.UserAccount;
import com.example.ecommercemobileapp2hand.Models.UserAddress;
import com.example.ecommercemobileapp2hand.R;

import java.util.ArrayList;

public class Checkout extends AppCompatActivity {
    private ImageView imgBack;
    private RelativeLayout btnPlaceOrder;
    private TextView txtSubtotal,txtTotal,txtTax,txtShippingCost,txtPrice;
    private ArrayList<Bag> listOrder=new ArrayList<Bag>();
    private UserAccount userAccount;
    private UserAddress userAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);
        addControl();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (savedInstanceState == null) {
            loadFragmentAddress(new CheckoutAddressFragment());
            loadFragmentPayment(new CheckoutPaymentFragment());

        }
    }
    private void addControl(){
        imgBack = findViewById(R.id.imgBack);
        txtShippingCost=findViewById(R.id.txtShippingCost);
        txtSubtotal=findViewById(R.id.txtSubtotal);
        txtTax=findViewById(R.id.txtTax);
        txtTotal=findViewById(R.id.txtTotal);
        txtPrice=findViewById(R.id.txtPrice);
        btnPlaceOrder= findViewById(R.id.btnAddToBag);
    }
    @Override
    protected void onResume() {
        super.onResume();
        addEvent();

    }
    private void addEvent(){
        // Set the back button to handle click event
        imgBack.setOnClickListener(v -> onBackPressed());
        Intent intent = getIntent();
        txtSubtotal.setText(intent.getStringExtra("subtotal"));
        txtShippingCost.setText(intent.getStringExtra("shippingCost"));
        txtTax.setText(intent.getStringExtra("tax"));
        txtTotal.setText(intent.getStringExtra("total"));
        txtPrice.setText(intent.getStringExtra("total"));
        listOrder=(ArrayList<Bag>) intent.getSerializableExtra("listOrder");
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAccount= UserAccountManager.instance.getCurrentUserAccount();
                int userOrderID= UserOrderHandler.createUserOrder(userAccount.getUserId(),7);
                if (userOrderID>0){
                    for(Bag bag : listOrder){
                        UserOrderProductsHandler.createUserOrderProduct(userOrderID,bag.getProduct_details_size_id(),bag.getAmount());
                    }
                    Intent orderSuccess=new Intent(Checkout.this, OrderPlaceSuccessfullyActivity.class);
                    startActivity(orderSuccess);
                }

            }
        });
    }
    private void getUserAddress(){

    }

    private void loadFragmentAddress(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_Address, fragment);
        fragmentTransaction.commit();
    }
    private void loadFragmentPayment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_Payment, fragment);
        fragmentTransaction.commit();
    }
}