import java.util.HashMap;
import java.util.Map;

public class Inventori {
    // Map<Nama Barang, Detail Barang>
    private Map<String, Barang> daftarBarang = new HashMap<>();
    // Map<Nama Barang, Jumlah>
    private Map<String, Integer> jumlahBarang = new HashMap<>();

    public void tambahBarang(Barang b, int jumlah) {
        String namaBarang = b.getNama();
        
        daftarBarang.put(namaBarang, b); // Simpan/update detail barang
        int jumlahSaatIni = jumlahBarang.getOrDefault(namaBarang, 0);
        jumlahBarang.put(namaBarang, jumlahSaatIni + jumlah);

        System.out.println(jumlah + " " + namaBarang + " ditambahkan ke inventori.");
    }

    public Barang ambilBarang(String namaBarang, int jumlah) {
        int jumlahSaatIni = jumlahBarang.getOrDefault(namaBarang, 0);
        
        if (jumlahSaatIni < jumlah) {
            System.out.println("Stok " + namaBarang + " tidak mencukupi (hanya ada " + jumlahSaatIni + ").");
            return null;
        }
        
        jumlahBarang.put(namaBarang, jumlahSaatIni - jumlah);
        
        if (jumlahBarang.get(namaBarang) == 0) {
            jumlahBarang.remove(namaBarang);
            // Jangan remove dari daftarBarang, agar detail harganya tetap ada
        }
        System.out.println(jumlah + " " + namaBarang + " diambil dari inventori.");
        return daftarBarang.get(namaBarang);
    }
    
    public void lihatIsi() {
        System.out.println("--- ISI INVENTORI ---");
        if (jumlahBarang.isEmpty()) {
            System.out.println("Inventori kosong.");
        } else {
            for (String nama : jumlahBarang.keySet()) {
                System.out.println("- " + nama + ": " + jumlahBarang.get(nama) + " unit");
            }
        }
        System.out.println("-----------------------");
    }
}