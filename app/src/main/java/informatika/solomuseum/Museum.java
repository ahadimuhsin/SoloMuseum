package informatika.solomuseum;

//Parcel untuk mengirimkan pesan secara ringan
import android.os.Parcel;
import android.os.Parcelable;

public class Museum implements Parcelable {
    int idMuseum;
    String namaMuseum;
    String deskripsiMuseum;
    String alamatMuseum;
    String jamMuseum;
    String latitudeMuseum;
    String longitudeMuseum;
    String gambarMuseum;

    public String getLatitudeMuseum() {
        return latitudeMuseum;
    }

    public void setLatitudeMuseum(String latitudeMuseum) {
        this.latitudeMuseum = latitudeMuseum;
    }

    public String getLongitudeMuseum() {
        return longitudeMuseum;
    }

    public void setLongitudeMuseum(String longitudeMuseum) {
        this.longitudeMuseum = longitudeMuseum;
    }



    public String getGambarMuseum() {
        return gambarMuseum;
    }

    public void setGambarMuseum(String gambarMuseum) {
        this.gambarMuseum = gambarMuseum;
    }


    public int getIdMuseum() {
        return idMuseum;
    }

    public void setIdMuseum(int idMuseum) {
        this.idMuseum = idMuseum;
    }

    public String getNamaMuseum() {
        return namaMuseum;
    }

    public void setNamaMuseum(String namaMuseum) {
        this.namaMuseum = namaMuseum;
    }

    public String getDeskripsiMuseum() {
        return deskripsiMuseum;
    }

    public void setDeskripsiMuseum(String deskripsiMuseum) {
        this.deskripsiMuseum = deskripsiMuseum;
    }

    public String getAlamatMuseum() {
        return alamatMuseum;
    }

    public void setAlamatMuseum(String alamatMuseum) {
        this.alamatMuseum = alamatMuseum;
    }

    public String getJamMuseum() {
        return jamMuseum;
    }

    public void setJamMuseum(String htmMuseum) {
        this.jamMuseum = htmMuseum;
    }





    public Museum (){}
    protected Museum (Parcel in){
        idMuseum = in.readInt();
        namaMuseum = in.readString();
        deskripsiMuseum = in.readString();
        alamatMuseum = in.readString();
        jamMuseum = in.readString();
        latitudeMuseum = in.readString();
        longitudeMuseum = in.readString();
        gambarMuseum = in.readString();
    }

    public static final Creator <Museum> CREATOR =
            new Creator<Museum>(){
        public Museum createFromParcel (Parcel in){
            return new Museum(in);
        }
        public Museum[] newArray (int size){
            return new Museum[size];
        }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(idMuseum);
    parcel.writeString(namaMuseum);
    parcel.writeString(deskripsiMuseum);
    parcel.writeString(alamatMuseum);
    parcel.writeString(jamMuseum);
    parcel.writeString(latitudeMuseum);
    parcel.writeString(longitudeMuseum);
    parcel.writeString(gambarMuseum);
    }
}
