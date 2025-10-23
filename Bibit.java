public class Bibit extends Barang {
    private String jenisTanaman; // e.g., "Padi", "Jagung"

    public Bibit(String nama, double harga, String jenisTanaman) {
        super(nama, harga);
        this.jenisTanaman = jenisTanaman;
    }

    public String getJenisTanaman() { return jenisTanaman; }
}