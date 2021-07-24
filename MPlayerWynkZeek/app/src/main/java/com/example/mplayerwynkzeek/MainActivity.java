package com.example.mplayerwynkzeek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] items;

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId()==android.R.id.home){
//            onBackPressed();
//        }
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().setTitle("WynkZeek");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView=(ListView)findViewById(R.id.listView);
        runtimePermission();
    }

    public void runtimePermission() {
        Dexter.withContext(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO).withListener(new MultiplePermissionsListener() {
            //after requesting permission display song stored in externaage directory
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                displaySong();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();

    }

//    public void runtimePermission() {
//        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
//            @Override
//            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                displaySong();
//            }
//
//            @Override
//            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//
//            }
//
//            @Override
//            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                permissionToken.continuePermissionRequest();
//            }
//        }).check();
//
//    }
//    PermissionListener dialogPermissionListener = DialogOnDeniedPermissionListener.Builder.withContext(this)
//                    .withTitle("Media Player Permission")
//                    .withMessage("Media permission is needed to load media")
//                    .withButtonText(android.R.string.ok)
//                    .build();

    public ArrayList<File> findSong(File file) {
        ArrayList<File> arrayList=new ArrayList<>();
        File[] files=file.listFiles();

        for(File singleFile : files) {
            if(singleFile.isDirectory() && !singleFile.isHidden()) {
                arrayList.addAll(findSong(singleFile));
            }
            else {
                if(singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")) {
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }

    public void displaySong() {
        //my songs contain dynamic array of songs in external storage directory
        final ArrayList<File> mySongs=findSong(Environment.getExternalStorageDirectory());

        //items is static array of strings, initialise it with size of songs found in device
        items=new String[mySongs.size()];

        for (int i=0;i<mySongs.size() ;i++) {

            //from arraylist place the songs in string array and replace mp3 and wav with null
            items[i]=mySongs.get(i).getName().toString().replace("mp3","").replace("wav","");
        }

        //customAdapter used to customise the list view
        customAdapter customAdapter=new customAdapter();
        listView.setAdapter(customAdapter);

        //when particular list item is clicked get the adapter view of custom adapter
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //set song name to current position
                String songName=(String) listView.getItemAtPosition(position);
                //start playing activity by passing the arrayList of songs,current songname and position
                startActivity(new Intent(getApplicationContext(),PlayingActivity.class).putExtra("songs", mySongs)
                        .putExtra("songname",songName)
                        .putExtra("pos", position)
                );
            }
        });
    }

    class customAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        //setting the view of list view in main activity
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Instantiates a layout XML file into its corresponding View objects. It is never used directly. Instead, use Activity.getLayoutInflater() or Context#getSystemService to retrieve a standard LayoutInflater instance that is already hooked up to the current context and correctly configured for the device you are running on.
            //sets the view to list_item.xml file - parameters are resource and root(used to set the view to parent item
            View view=getLayoutInflater().inflate(R.layout.list_item,null);
            //get view of song name
            TextView txtSong=view.findViewById(R.id.txtSong);
            //enable selection
            txtSong.setSelected(true);
            //set the index to current position of view
            txtSong.setText(items[position]);
            return view;
        }
    }
}