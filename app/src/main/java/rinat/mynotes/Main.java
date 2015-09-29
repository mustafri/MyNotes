package rinat.mynotes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "myLogs";

    Button addNote;
    final int REQUEST_CODE_ADD_NOTE=1;
    final int REQUEST_CODE_CHANGE_NOTE=2;
    final int CM_DELETE_ID =3;

    final String ATTRIBUTE_NAME_TOPIC = "topic";
    final String ATTRIBUTE_NAME_NOTE = "text";
    Context ctx=Main.this;
    SimpleAdapter sAdapter;
    Map<String, Object> m;

    ArrayList<Map<String, Object>>   data_storage ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(TAG, "MainActivity:onCreate");


        String[] from = {ATTRIBUTE_NAME_TOPIC, ATTRIBUTE_NAME_NOTE};
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = {R.id.topicText, R.id.noteText};
        data_storage = new ArrayList<Map<String, Object>>();

        addNote = (Button)findViewById(R.id.addNoteButton);
        addNote.setOnClickListener(this);

        sAdapter = new SimpleAdapter(this,data_storage, R.layout.item, from, to );
        ListView listMain = (ListView) findViewById(R.id.listMain);
        listMain.setAdapter(sAdapter);

        registerForContextMenu(listMain);// implement context menu to LIST
// Item selected in List -> ViewNote
        listMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(ctx, ViewNote.class);
                TextView getTopic= (TextView)view.findViewById(R.id.topicText);
                TextView getNote= (TextView)view.findViewById(R.id.noteText);

                intent.putExtra("Topic", getTopic.getText());
                intent.putExtra("Note", getNote.getText());
                startActivity(intent);

            }
        });
//item that come from ChangeNote
/*
       Intent viewNote=getIntent();
        String action = viewNote.getAction();
        Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
        if(action.equals("ViewNote")){
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TOPIC, viewNote.getStringExtra("Topic"));
            m.put(ATTRIBUTE_NAME_NOTE, viewNote.getStringExtra("Note"));
            data_storage.add(m);
            sAdapter.notifyDataSetChanged();
        }
*/
    }

    public void onClick(View v){  // What Buttons should to do
        switch (v.getId()){
            case R.id.addNoteButton:
                Intent intent = new Intent(this, AddNote.class);
                intent.setAction("Main");
                startActivityForResult(intent, REQUEST_CODE_ADD_NOTE);
                break;
            case R.id.settingsButton:
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode== REQUEST_CODE_ADD_NOTE ){// getting request from AddNote activity
        String aTopic = data.getStringExtra("Topic");
        String aNote = data.getStringExtra("Note");
        Toast.makeText(this,aTopic +" AND "+aNote, Toast.LENGTH_SHORT).show();
        m = new HashMap<String, Object>();
                  switch (resultCode) {
                        case RESULT_OK:
                            m.put(ATTRIBUTE_NAME_TOPIC, aTopic);
                            m.put(ATTRIBUTE_NAME_NOTE, aNote);
                            data_storage.add(m);
                            sAdapter.notifyDataSetChanged();
                            break;
                        case RESULT_CANCELED:
                            Toast.makeText(this, "Adding canceled", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                   ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Delete note"); // Context menu(button DELETE)
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {// action -> when pressed context button Delete
        if (item.getItemId() == CM_DELETE_ID) {
            AlertDialog.Builder adb=new AlertDialog.Builder(this);
            adb.setCancelable(false);  // чтобы пользователь не смог нажать назад
            adb.setTitle("Confirm delete");
            adb.setMessage("Are you sure you want to delete");
            adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {

                    // получаем инфу о пункте списка
                    AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    // удаляем Map из коллекции, используя позицию пункта в списке
                    data_storage.remove(acmi.position);
                    // уведомляем, что данные изменились
                    sAdapter.notifyDataSetChanged();

                }
            });
            adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    Toast.makeText(ctx, " Delete canceled", Toast.LENGTH_SHORT).show();
                }
            });
            adb.show();
            return true;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity:onResume");

       /*  Intent viewNote=getIntent();
        String action = viewNote.getAction();
        Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
        if(action.equals("ChangeNote")) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TOPIC, viewNote.getStringExtra("Topic"));
            m.put(ATTRIBUTE_NAME_NOTE, viewNote.getStringExtra("Note"));
            data_storage.add(m);
           sAdapter.notifyDataSetChanged();
        }
*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity: onStart()");
    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: onDestroy()");
    }
}
