public class Jagung extends Tanaman {
    
    public Jagung() {
        // nama, waktu panen (hari), keb. air
        super("Jagung", 45, 0.8);
    }

    /**
     * [Memenuhi syarat Overriding - cite: 21]
     * Implementasi tumbuh spesifik untuk Jagung.
     * Jagung tidak suka terlalu banyak air.
     */
    @Override
    public void tumbuh(Cuaca cuaca) {
        if (!isSakit) {
            double lajuTumbuh = 1.0 / waktuPanen;

            // Jagung tidak suka terlalu basah [cite: 26]
            if (cuaca.getCurahHujan() > 80) {
                statusTumbuh += lajuTumbuh * 0.5; // Tumbuh lambat (banjir)
            } else if (cuaca.getCurahHujan() > 20) {
                statusTumbuh += lajuTumbuh * kebutuhanAir; // Subur
            } else {
                statusTumbuh += lajuTumbuh; // Normal
            }
        }

        if(statusTumbuh > 1.0) statusTumbuh = 1.0;
        cekKondisi();
    }

    @Override
    public HasilPanen panen() {
        if (isSiapPanen()) {
            String kualitas = isSakit ? "Jelek" : "Super";
            return new HasilPanen("Jagung Manis", 2000, kualitas);
        }
        return null;
    }
}