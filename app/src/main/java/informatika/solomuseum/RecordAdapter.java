package informatika.solomuseum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private Context context;
    private List<Record> museumList;

    public RecordAdapter (Context context, List<Record> museumList){
        this.context = context;
        this.museumList = museumList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_home, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.namaMuseum.setText (museumList.get(position).getNamaMuseum());
        Glide.with(context).load(museumList.get(position).getGambarMuseum()).into(holder.gambarMuseum);
    }


    public int getItemCount(){
        return museumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView namaMuseum;
        public ImageView gambarMuseum;

        public ViewHolder (View itemView){
            super(itemView);
            gambarMuseum = (ImageView) itemView.findViewById(R.id.gambar_record);
            namaMuseum = (TextView) itemView.findViewById(R.id.nama_museum);
        }
    }

}
