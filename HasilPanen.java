public class HasilPanen extends Barang {
    private String kualitas;

    public HasilPanen(String nama, double harga, String kualitas) {
        super(nama, harga);
        this.kualitas = kualitas;
    }

    public String getKualitas() { return kualitas; }
    
    @Override
    public String toString() {
        return super.toString() + " [Kualitas: " + kualitas + "]";
    }
}