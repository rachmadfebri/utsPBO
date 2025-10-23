/**
 * [Memenuhi syarat Inheritance - cite: 19]
 */
public class Padi extends Tanaman {
    
    public Padi() {
        // nama, waktu panen (hari), keb. air
        super("Padi", 60, 1.5); 
    }

    /**
     * [Memenuhi syarat Overriding - cite: 21]
     * Implementasi tumbuh spesifik untuk Padi.
     * Padi suka banyak air (hujan).
     */
    @Override
    public void tumbuh(Cuaca cuaca) {
        if (!isSakit) {
            double lajuTumbuh = 1.0 / waktuPanen;
            
            // Padi suka cuaca hujan [cite: 26]
            if (cuaca.getCurahHujan() > 70) {
                statusTumbuh += lajuTumbuh * kebutuhanAir; // Tumbuh subur
            } else if (cuaca.getCurahHujan() > 30) {
                statusTumbuh += lajuTumbuh; // Normal
            } else {
                statusTumbuh += lajuTumbuh * 0.5; // Lambat
            }
        }
        
        if(statusTumbuh > 1.0) statusTumbuh = 1.0;
        cekKondisi(); // Cek apakah jadi sakit
    }

    @Override
    public HasilPanen panen() {
        if (isSiapPanen()) {
            String kualitas = isSakit ? "Biasa" : "Bagus";
            return new HasilPanen("Gabah", 1500, kualitas);
        }
        return null;
    }
}