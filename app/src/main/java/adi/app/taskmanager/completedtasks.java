package adi.app.taskmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class completedtasks extends Activity {
    ArrayAdapter<String> hello;
    ListView list;
    //DataToFile dtf = new DataToFile();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("In other completedtasks");
        setContentView(R.layout.completedtasks);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String datestr = format.format(date);
        final Intent intent = getIntent();
        final TextView invisible = findViewById(R.id.textView8);
        if(DataToFile.completedlist.toArray().length != 0){
            invisible.setVisibility(View.INVISIBLE);
        }
        System.out.println("length of completedlist: " + DataToFile.completedlist.size());
        hello = new AlterAdapter<String>(this,android.R.layout.simple_list_item_1,DataToFile.completedlist);
        list = findViewById(R.id.ListViews);
        list.setClickable(true);
        list.setAdapter(hello);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popupMenu = new PopupMenu(completedtasks.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.completemenu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String helloo = parent.getItemAtPosition(position).toString();
                        if (item.getTitle().equals("Delete")){
                            hello.remove(helloo);
                            if(DataToFile.completedlist.toArray().length == 0){
                                invisible.setVisibility(View.VISIBLE);
                            }


                        }
                        return false;
                    }

                });
            }
        });

        //finish();

    }
    protected void onStop() {
        super.onStop();
        System.out.println("going into onStop completed task");
        DataToFile.put(this,DataToFile.completedlist,"File2.txt");
    }

}