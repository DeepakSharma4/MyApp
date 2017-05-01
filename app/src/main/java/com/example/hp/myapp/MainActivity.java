package com.example.hp.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtx;
    private Spinner sp;
    private Button btn;
    DatabaseReference db;
    private ListView listView;
    List<student> artistslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FIRE DATA BASE INITILIZE
        db = FirebaseDatabase.getInstance().getReference("student");  //student write in method so it display in DB NODE

        listView = (ListView) findViewById(R.id.listview);

        edtx = (EditText) findViewById(R.id.edtx);
        sp = (Spinner) findViewById(R.id.spinner);
        btn = (Button) findViewById(R.id.btn);
        //list to store artists
        artistslist = new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                black();
            }
        });
    }

    //GETTING DATA
    @Override
    protected void onStart() {
        super.onStart();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistslist.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting student
                    student ss = postSnapshot.getValue(student.class);
                    //adding artist to the list
                    artistslist.add(ss);
                }


                //creating adapter
                list artistAdapter = new list(MainActivity.this, artistslist); //my adpter list name
                //attaching adapter to the listview
                listView.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

//sending data to firebase
    private void black() {
        //Fetching Values
        String name = edtx.getText().toString().trim();
        String list = sp.getSelectedItem().toString();
        if (!TextUtils.isEmpty(name)) {
            String ID = db.push().getKey();  //push method for creating unique id(student) in DATABASE
            student ss = new student(ID, name, list);
            db.child(ID).setValue(ss);   //setvalues and child methods used for store values in DB like tree shape child ist node.
            Toast.makeText(MainActivity.this, "student added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Enter Your Name", Toast.LENGTH_LONG).show();
        }

    }
}
