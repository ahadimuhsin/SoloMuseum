package informatika.solomuseum;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private static final String url = "http://muhsin1812.000webhostapp.com/museum_api/get_museum.php"; //link json

    //list data museum
    List <Record> recordList;
    List <Museum> museumList;

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    private RecordAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recordList = new ArrayList<>();
        museumList = new ArrayList<>();

        loadRecord();
        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);


//        loadRecord();


        setRecyclerViewClick();
    }

    private void loadRecord(){
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>(){
            public void onResponse (String response){
            try{
               JSONArray array = new JSONArray (response);

               for (int i=0; i<array.length(); i++){
                   JSONObject record = array.getJSONObject(i);

                   recordList.add(new Record(record.getString("gambarMuseum"), record.getString("namaMuseum")));

                   Museum museum = new Museum();
                   museum.setIdMuseum(Integer.parseInt(record.getString("idMuseum")));
                   museum.setAlamatMuseum(record.getString("alamatMuseum"));
                   museum.setDeskripsiMuseum(record.getString("deskripsiMuseum"));
                   museum.setJamMuseum(record.getString("jamMuseum"));
                   museum.setGambarMuseum(record.getString("gambarMuseum"));
                   museum.setNamaMuseum(record.getString("namaMuseum"));
                   museum.setLatitudeMuseum(record.getString("latitudeMuseum"));
                   museum.setLongitudeMuseum(record.getString("longitudeMuseum"));
                   museumList.add(museum);
               }
               RecordAdapter adapter = new RecordAdapter (MainActivity.this, recordList);
               recyclerView.setAdapter(adapter);
            } catch (JSONException e){
                e.printStackTrace();
            }
            }
                },
                new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
                }
        );
        Volley.newRequestQueue(this).add(stringRequest);
    }


    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView,
                                     final ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context,
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }

                        @Override
                        public void onLongPress(MotionEvent e) {
                            View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                            if (child != null && clicklistener != null) {
                                clicklistener.onLongClick(child,
                                        recycleView.getChildAdapterPosition(child));
                            }
                        }
                    });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

    private void setRecyclerViewClick(){
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                        recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent in = new Intent(MainActivity.this, DetailMuseumActivity.class);
                        in.putExtra("museum", museumList.get(position));
                        startActivity(in);
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                    }
                })
        );
    }
}

