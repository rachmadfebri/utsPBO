public class Barang {
    protected String nama;
    protected double harga;

    public Barang(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() { return nama; }
    public double getHarga() { return harga; }

    @Override
    public String toString() {
        return nama + " (Harga: " + harga + ")";
    }
}