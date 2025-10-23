import java.util.HashMap;
import java.util.Map;

public class Toko {
    private Map<String, Bibit> daftarBibit = new HashMap<>();

    public Toko() {
        // Inisialisasi barang toko
        daftarBibit.put("PADI", new Bibit("Bibit Padi", 50, "Padi"));
        daftarBibit.put("JAGUNG", new Bibit("Bibit Jagung", 30, "Jagung"));
    }

    public void lihatBarang() {
        System.out.println("--- SELAMAT DATANG DI TOKO ---");
        for (Bibit b : daftarBibit.values()) {
            System.out.println("- " + b.getNama() + " (Harga: " + b.getHarga() + ")");
        }
        System.out.println("------------------------------");
    }

    // Relasi Asosiasi: Toko berinteraksi dengan Pemain [cite: 18]
    public Bibit prosesBeli(Pemain p, String namaBibit) {
        Bibit bibit = daftarBibit.get(namaBibit.toUpperCase());
        if (bibit == null) {
            System.out.println("Bibit tidak ditemukan.");
            return null;
        }
        
        if (p.getUang() >= bibit.getHarga()) {
            p.kurangiUang(bibit.getHarga());
            System.out.println(p.getNama() + " berhasil membeli " + bibit.getNama());
            // Kita return *copy* dari bibit, agar stok toko tidak habis
            return new Bibit(bibit.getNama(), bibit.getHarga(), bibit.getJenisTanaman());
        } else {
            System.out.println("Uang " + p.getNama() + " tidak cukup.");
            return null;
        }
    }

    public void prosesJual(Pemain p, HasilPanen h, int jumlah) {
        double totalPendapatan = h.getHarga() * jumlah;
        p.tambahUang(totalPendapatan);
        System.out.println(p.getNama() + " berhasil menjual " + h.getNama() + 
                           " (" + jumlah + " unit) seharga " + totalPendapatan);
    }
}