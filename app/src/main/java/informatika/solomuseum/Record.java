package informatika.solomuseum;

public class Record {

    public String getGambarMuseum() {
        return gambarMuseum;
    }

    public String getNamaMuseum() {
        return namaMuseum;
    }

    private String gambarMuseum, namaMuseum;

    public Record (String gambarMuseum, String namaMuseum){
        this.gambarMuseum = gambarMuseum;
        this.namaMuseum = namaMuseum;
    }



}
