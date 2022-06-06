package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Spinner spinner;
Button Add;
Button Delte;
Button Clear;
EditText input;
ListView lvTask;
ArrayList<String> TasksArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.editTextTextPersonName);
        spinner = findViewById(R.id.spinner);
        Add = findViewById(R.id.button);
        Delte = findViewById(R.id.button2);
        Clear = findViewById(R.id.button3);
        lvTask = findViewById(R.id.listview);
        ArrayAdapter Tasknow = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, TasksArray);
        lvTask.setAdapter(Tasknow);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String tasks = input.getText().toString();
                            TasksArray.add(tasks);
                            Tasknow.notifyDataSetChanged();

                            lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                    String task = TasksArray.get(position).toString();

                                }
                            });
                            Delte.setEnabled(false);
                        }
                    });
                    case 1:
                        input.setHint("Type in the index of the task to be removed");

                        Delte.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int tasks = Integer.parseInt(input.getText().toString());
                                if(TasksArray.isEmpty()){
                                    Toast.makeText(MainActivity.this, "You don't have any text to remove",Toast.LENGTH_SHORT).show();
                                }
                                else if(TasksArray.contains(tasks)) {
                                    TasksArray.remove(tasks);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Wrond index number", Toast.LENGTH_SHORT).show();
                                }
                                Tasknow.notifyDataSetChanged();
                            }
                        });
                        Add.setEnabled(false);



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}