import java.util.ArrayList;
import java.util.List;

public class Pemain {
    // Atribut 
    private String nama;
    private double uang;
    private int energi;
    // Relasi Agregasi: Pemain Punya Inventori & Lahan [cite: 18]
    private Inventori inventori;
    private List<Lahan> daftarLahan = new ArrayList<>();

    public Pemain(String nama, double uang, int energi) {
        this.nama = nama;
        this.uang = uang;
        this.energi = energi;
        this.inventori = new Inventori();
        // Pemain mulai dengan 2 lahan
        this.daftarLahan.add(new Lahan("Sawah"));
        this.daftarLahan.add(new Lahan("Tegalan"));
    }

    // Getter/Setter
    public String getNama() { return nama; }
    public double getUang() { return uang; }
    public void tambahUang(double jumlah) { this.uang += jumlah; }
    public void kurangiUang(double jumlah) { this.uang -= jumlah; }
    public List<Lahan> getDaftarLahan() { return daftarLahan; }
    public Inventori getInventori() { return inventori; }

    // Aksi Pemain
    public void beliBibit(Toko t, String namaBibit, int jumlah) {
        Bibit bibit = t.prosesBeli(this, namaBibit);
        if (bibit != null) {
            inventori.tambahBarang(bibit, jumlah);
        }
    }

    public void jualHasil(Toko t, String namaHasil, int jumlah) {
        Barang barang = inventori.ambilBarang(namaHasil, jumlah);
        if (barang instanceof HasilPanen) {
            t.prosesJual(this, (HasilPanen) barang, jumlah);
        } else if (barang != null) {
            // Jika terambil, tapi bukan hasil panen, kembalikan
            inventori.tambahBarang(barang, jumlah);
            System.out.println(namaHasil + " bukan hasil panen yang bisa dijual!");
        }
    }

    public void menanam(int nomorLahan, String namaBibit) {
        if (nomorLahan < 0 || nomorLahan >= daftarLahan.size()) {
            System.out.println("Nomor lahan tidak valid."); return;
        }
        Lahan l = daftarLahan.get(nomorLahan);
        if (!l.isKosong()) {
            System.out.println("Lahan sudah ditanami."); return;
        }
        
        // Ambil bibit dari inventori
        Barang barang = inventori.ambilBarang("Bibit " + namaBibit, 1);
        if (barang instanceof Bibit) {
            Bibit bibit = (Bibit) barang;
            if (bibit.getJenisTanaman().equalsIgnoreCase("Padi")) {
                l.menanam(new Padi());
            } else if (bibit.getJenisTanaman().equalsIgnoreCase("Jagung")) {
                l.menanam(new Jagung());
            } else {
                System.out.println("Jenis bibit tidak dikenal.");
                inventori.tambahBarang(bibit, 1); // Kembalikan bibit
            }
        }
    }

    public void memanen(int nomorLahan) {
        if (nomorLahan < 0 || nomorLahan >= daftarLahan.size()) {
            System.out.println("Nomor lahan tidak valid."); return;
        }
        Lahan l = daftarLahan.get(nomorLahan);
        if (l.isKosong()) {
             System.out.println("Lahan kosong, tidak ada yang bisa dipanen."); return;
        }
        if (!l.getTanaman().isSiapPanen()) {
            System.out.println("Tanaman belum siap panen."); return;
        }

        HasilPanen hasil = l.getTanaman().panen();
        l.cabutTanaman(); // Kosongkan lahan
        if (hasil != null) {
            inventori.tambahBarang(hasil, 10); // Asumsi 1x panen dapat 10 unit
            System.out.println("Berhasil panen " + hasil.getNama() + "!");
        }
    }
    
    // Info status pemain
    public void infoPemain() {
        System.out.println("=== Info Pemain: " + nama + " ===");
        System.out.println("Uang: " + uang);
        inventori.lihatIsi();
        System.out.println("--- Info Lahan ---");
        for (int i = 0; i < daftarLahan.size(); i++) {
            System.out.print((i) + ": ");
            daftarLahan.get(i).infoLahan();
        }
        System.out.println("===========================");
    }
}