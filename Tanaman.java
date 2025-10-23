/**
 * Kelas induk abstract untuk semua tanaman.
 * [Memenuhi syarat Abstract Class - cite: 22]
 * [Kelas Induk Tanaman - cite: 8]
 */
public abstract class Tanaman {
    // Atribut [cite: 9, 17]
    protected String nama;
    protected int waktuPanen; // dalam hari
    protected double kebutuhanAir; // faktor pengali
    protected double statusTumbuh; // 0.0 -> 1.0 (siap panen)
    protected boolean isSakit;
    protected int hariTanpaAir;

    public Tanaman(String nama, int waktuPanen, double kebutuhanAir) {
        this.nama = nama;
        this.waktuPanen = waktuPanen;
        this.kebutuhanAir = kebutuhanAir;
        this.statusTumbuh = 0.0;
        this.isSakit = false;
        this.hariTanpaAir = 0;
    }

    // Getter
    public String getNama() { return nama; }
    public double getStatusTumbuh() { return statusTumbuh; }
    public boolean isSiapPanen() {
        return statusTumbuh >= 1.0;
    }

    // Metode konkret untuk aksi [cite: 9]
    public void beriAir() {
        this.hariTanpaAir = 0; // Reset counter kekeringan
        this.isSakit = false; // Sembuh dari sakit (jika karena kering)
        System.out.println(nama + " telah disiram.");
    }
    
    public void beriPupuk() {
        // Memberi pupuk mempercepat pertumbuhan sedikit
        statusTumbuh += 0.2;
        if (statusTumbuh > 1.0) statusTumbuh = 1.0;
        System.out.println(nama + " diberi pupuk, pertumbuhan meningkat!");
    }
    
    // Metode untuk simulasi harian [cite: 24]
    public void cekKondisi() {
        hariTanpaAir++;
        // Jika terlalu lama tidak disiram, tanaman sakit [cite: 9]
        if (hariTanpaAir > 3) { 
            this.isSakit = true;
            System.out.println(nama + " sakit karena kekeringan!");
        }
    }

    /**
     * [Memenuhi syarat Polimorfisme & Overriding - cite: 20, 21]
     * Metode abstract yang implementasinya akan berbeda di kelas turunan.
     * Pertumbuhan dipengaruhi cuaca [cite: 26]
     */
    public abstract void tumbuh(Cuaca cuaca);

    /**
     * Metode abstract untuk dipanen
     */
    public abstract HasilPanen panen();
}