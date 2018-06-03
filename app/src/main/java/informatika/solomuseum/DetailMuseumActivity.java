package informatika.solomuseum;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailMuseumActivity extends AppCompatActivity {
    TextView tvNamaMuseum, tvAlamatMuseum, tvDeskripsiMuseum, tvJamMuseum;
    Button navigasi;
    String googlemaps = "com.google.android.apps.maps";
    Uri gmmIntentUri;
    Intent mapintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_museum);

        tvNamaMuseum =  findViewById(R.id.tvNamaMuseum);
        tvDeskripsiMuseum =  findViewById(R.id.tvDeskripsiMuseum);
        tvAlamatMuseum =  findViewById(R.id.tvAlamatMuseum);
        tvJamMuseum =  findViewById(R.id.tvJamMuseum);
        ImageView image =  findViewById(R.id.imageview);
        navigasi = findViewById(R.id.btn_maps);


        //mengambil intent dari main activity
        final Museum museum = getIntent().getParcelableExtra("museum");

        tvNamaMuseum.setText(museum.getNamaMuseum());
        tvAlamatMuseum.setText(museum.getAlamatMuseum());
        tvJamMuseum.setText(museum.getJamMuseum());
        tvDeskripsiMuseum.setText(museum.getDeskripsiMuseum());
//        navigasi.setText(museum.getKoordinatMuseum());
        Glide.with(DetailMuseumActivity.this).load(museum.getGambarMuseum()).into(image);

        navigasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gmmIntentUri = Uri.parse("google.navigation:q=" +museum.getLatitudeMuseum()+"," +museum.getLongitudeMuseum());

                mapintent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                mapintent.setPackage(googlemaps);

                if(mapintent.resolveActivity(getPackageManager()) != null){
                    startActivity(mapintent);
                }else{
                    Toast.makeText(DetailMuseumActivity.this, "Koneksi bermasalah",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
