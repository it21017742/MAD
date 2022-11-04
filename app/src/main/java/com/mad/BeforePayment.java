package com.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.model.payment;

public class BeforePayment extends AppCompatActivity {
    TextView amount;
    String name,number,DOE,cvc,payAmount;
    MaterialButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_payment);

        amount=findViewById(R.id.amount);
        button=findViewById(R.id.paybttn);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        name=bundle.getString("name");
        number=bundle.getString("number");
        DOE=bundle.getString("DOE");
        cvc=bundle.getString("CVC");

        button.setOnClickListener(view -> {
            payAmount=amount.getText().toString();
            if(payAmount.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
            }
            else{
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("/Payments");
                DatabaseReference newRef=myRef.push();
                newRef.setValue(new payment(name,number,DOE,cvc,payAmount),new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            System.out.println("Data could not be saved " + databaseError.getMessage());
                        } else {
                            System.out.println("Data saved successfully.");
                            Toast.makeText(getApplicationContext(),"Payment Successful",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),AfterPayment.class);
                            intent.putExtra("key",newRef.getKey());
                            startActivity(intent);

                        }
                    }
                });
            }
        });
    }
}