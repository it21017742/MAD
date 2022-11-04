package com.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.model.payment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AfterPayment extends AppCompatActivity {
     payment paymentDetails;
     DatabaseReference databaseReference;
     TextView nameOnCard,payAmount,cardNumber,CVC,DOE;
    MaterialButton cancelButton,updateButton;
     String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_payment);
        Intent intent=getIntent();
        key=intent.getStringExtra("key");
        nameOnCard=findViewById(R.id.Name);
        payAmount=findViewById(R.id.amount);
        CVC=findViewById(R.id.CVC);
        DOE=findViewById(R.id.DOE);
        cardNumber=findViewById(R.id.Number);
        cancelButton=findViewById(R.id.dltbttn);
        updateButton=findViewById(R.id.UpdateBtn);
        databaseReference= FirebaseDatabase.getInstance().getReference("Payments/"+key);
        getData();
        cancelButton.setOnClickListener(view -> {
            databaseReference.setValue(null);
            Toast.makeText(getApplicationContext(),"Payment canceled Successful",Toast.LENGTH_SHORT).show();
            Intent intent2=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent2);
        });
        updateButton.setOnClickListener(view -> {
            payment newPay=new payment(nameOnCard.getText().toString(),cardNumber.getText().toString(),DOE.getText().toString(),CVC.getText().toString(),payAmount.getText().toString());
            databaseReference.setValue(newPay,new DatabaseReference.CompletionListener(){
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        System.out.println("Data could not be saved " + databaseError.getMessage());
                    } else {
                        System.out.println("Data saved successfully.");
                        Toast.makeText(getApplicationContext(),"Payment updated Successful",Toast.LENGTH_SHORT).show();


                    }
                }
            });
        });

    }

    private void getData(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              if(snapshot.exists()){
                  String amount=snapshot.child("amount").getValue().toString();
                  String name=snapshot.child("name").getValue().toString();
                  String cvc=snapshot.child("cvc").getValue().toString();
                  String doe=snapshot.child("doe").getValue().toString();
                  String number=snapshot.child("number").getValue().toString();
                  System.out.println(amount+" "+name+" "+number+" "+cvc+" "+doe+" ");
                  paymentDetails=new payment(name,number,doe,cvc,amount);
                  nameOnCard.setText(paymentDetails.getName());
                  payAmount.setText(paymentDetails.getAmount());
                  CVC.setText(paymentDetails.getCVC());
                  DOE.setText(paymentDetails.getDOE());
                  cardNumber.setText(paymentDetails.getNumber());
              }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Cannot read payment details",Toast.LENGTH_SHORT).show();
            }
        });
    }
}