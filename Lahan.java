/**
 * [Sesuai source: 10, 11]
 * [Memenuhi syarat Implementasi Interface - cite: 22]
 */
public class Lahan implements Perawatan {
    private String jenisTanah;
    private Tanaman tanamanDitanam; // Relasi: Lahan punya 1 Tanaman [cite: 18]

    public Lahan(String jenisTanah) {
        this.jenisTanah = jenisTanah;
        this.tanamanDitanam = null;
    }

    public boolean isKosong() {
        return tanamanDitanam == null;
    }
    
    public Tanaman getTanaman() { return tanamanDitanam; }

    public void menanam(Tanaman t) {
        if (isKosong()) {
            this.tanamanDitanam = t;
            System.out.println(t.getNama() + " berhasil ditanam di lahan " + jenisTanah);
        } else {
            System.out.println("Lahan sudah terisi.");
        }
    }

    public void cabutTanaman() {
        this.tanamanDitanam = null;
        System.out.println("Tanaman telah dicabut, lahan kembali kosong.");
    }

    // [Metode dari Interface Perawatan - cite: 25]
    @Override
    public void menyiram() {
        if (!isKosong()) {
            tanamanDitanam.beriAir();
        } else {
            System.out.println("Lahan kosong, tidak perlu disiram.");
        }
    }

    // [Metode dari Interface Perawatan - cite: 25]
    @Override
    public void memupuk() {
        if (!isKosong()) {
            System.out.print("Menggunakan pupuk standar. ");
            tanamanDitanam.beriPupuk();
        } else {
            System.out.println("Lahan kosong, tidak perlu dipupuk.");
        }
    }

    /**
     * [Memenuhi syarat Overloading - cite: 21]
     * Nama metode sama (memupuk), tapi parameter berbeda.
     */
    public void memupuk(String jenisPupuk) {
        if (!isKosong()) {
            System.out.print("Menggunakan pupuk " + jenisPupuk + ". ");
            tanamanDitanam.beriPupuk(); // Efeknya sama untuk demo ini
        } else {
            System.out.println("Lahan kosong, tidak perlu dipupuk.");
        }
    }

    // Info status lahan
    public void infoLahan() {
        if (isKosong()) {
            System.out.println("Lahan (" + jenisTanah + ") masih kosong.");
        } else {
            System.out.printf("Lahan (%s) ditanami %s. Status tumbuh: %.0f%%. Sakit: %s\n",
                jenisTanah,
                tanamanDitanam.getNama(),
                tanamanDitanam.getStatusTumbuh() * 100,
                tanamanDitanam.isSakit ? "Ya" : "Tidak"
            );
        }
    }
}