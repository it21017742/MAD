package com.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView nameOnCard ;
    TextView cardNumber ;
    TextView dateOfExpire ;
    TextView CVC ;
    MaterialButton payButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       nameOnCard = findViewById(R.id.nameOnCard);
       cardNumber = findViewById(R.id.cardNumber);
         dateOfExpire = findViewById(R.id.dateOfExpir);
         CVC =findViewById(R.id.cvc);
         payButton = (MaterialButton) findViewById(R.id.paybttn);

        payButton.setOnClickListener(view -> {
            String name=nameOnCard.getText().toString();
            String number=cardNumber.getText().toString();
            String DOE=dateOfExpire.getText().toString();
            String cvc=CVC.getText().toString();

            if(name.isEmpty()||number.isEmpty()||DOE.isEmpty()||cvc.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
            }
            else{
                System.out.println(name+number+DOE+cvc);
                Intent intent=new Intent(this,BeforePayment.class);
                Bundle bundle=new Bundle();
                bundle.putString("name",name);
                bundle.putString("number",number);
                bundle.putString("DOE",DOE);
                bundle.putString("CVC",cvc);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }
}