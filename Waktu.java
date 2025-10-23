public class Waktu {
    private int hari = 1;
    private String musim = "Kemarau";

    public int getHari() { return hari; }
    public String getMusim() { return musim; }

    public void nextDay() {
        hari++;
        // Ganti musim setiap 30 hari (untuk demo)
        if (hari % 30 == 0) {
            musim = musim.equals("Kemarau") ? "Hujan" : "Kemarau";
            System.out.println("Musim berganti menjadi: " + musim);
        }
    }
}