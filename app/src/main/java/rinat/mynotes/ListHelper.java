package rinat.mynotes;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rinat on 20.09.2015.
 */
public class ListHelper  {


    final String ATTRIBUTE_NAME_TOPIC = "topic";
    final String ATTRIBUTE_NAME_TEXT = "text";
    Context ctx;

    ListHelper(Context _ctx) {
        ctx = _ctx;
    }

    SimpleAdapter sAdapter;
    Map<String, Object> m;

    ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

    SimpleAdapter getAdapter() {
         //data = new ArrayList<Map<String, Object>>();

          //  m  = new HashMap<String, Object>();

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = {ATTRIBUTE_NAME_TOPIC, ATTRIBUTE_NAME_TEXT};
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = {R.id.topicText, R.id.noteText};

        // создаем адаптер
         sAdapter = new SimpleAdapter(ctx, data, R.layout.item, from, to);

         return sAdapter;
    }



    public void addNote(String topic,String note){
       m  = new HashMap<String, Object>();
       m.put(ATTRIBUTE_NAME_TOPIC, topic);
       m.put(ATTRIBUTE_NAME_TEXT, note);
       data.add(m);
       // getAdapter().notifyDataSetChanged();

        AlertDialog.Builder adb=new AlertDialog.Builder(ctx);
        adb.setTitle("LVSelectedItemExample");
        adb.setMessage("topic = " + m.get(ATTRIBUTE_NAME_TOPIC) + " and text= " + m.get(ATTRIBUTE_NAME_TEXT));
        adb.setPositiveButton("Ok", null);
        adb.show();


        }

}