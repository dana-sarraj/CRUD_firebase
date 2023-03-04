package com.example.crud_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.core.View;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    CollectionReference collectionRef = db.collection("myCollection");

    Button btninsert ,btnview;



            EditText et_name;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                et_name = findViewById(R.id.et_user_name);
            }

            public  void saveToFirebase (View v){

                String username = et_name.getText().toString();
                Map<String, Object> user = new HashMap<>();
                if(!username.isEmpty()) {
                    user.put("name",username);

                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.e("dareen", "Data added successfully to database: ");
                                    openSecondSecreen();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("dareen", "Failed to add database", e);
                                }
                            });

                }else {
                    Toast.makeText(this,"please fill feilds" ,Toast.LENGTH_SHORT).show();
                }
            }
            public  void  openSecondSecreen(){
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
            }

        }
    }
