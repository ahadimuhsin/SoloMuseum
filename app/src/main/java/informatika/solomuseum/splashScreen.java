package informatika.solomuseum;

import android.app.*;
import android.os.*;
import android.content.*;

//class untuk menampilkan splash screen
public class splashScreen extends Activity {

    protected void onCreate (Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.splash_screen);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               //setelah spalsh screem, tampil MainActivity
               Intent intent = new Intent (splashScreen.this,
                       MainActivity.class);
               startActivity(intent);
               finish();
           }
       }, 3000); //set waktu splash selama 3 detik
    }
}
