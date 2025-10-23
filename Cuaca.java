import java.util.Random;

public class Cuaca {
    private int suhu;
    private int curahHujan;
    private Random rand = new Random();

    public Cuaca() {
        gantiCuaca("Kemarau"); // Default
    }

    // Cuaca berganti tergantung musim
    public void gantiCuaca(String musim) {
        if (musim.equals("Hujan")) {
            this.suhu = 20 + rand.nextInt(5); // 20-24
            this.curahHujan = 60 + rand.nextInt(41); // 60-100%
        } else { // Kemarau
            this.suhu = 28 + rand.nextInt(7); // 28-34
            this.curahHujan = 0 + rand.nextInt(31); // 0-30%
        }
    }

    public int getCurahHujan() { return curahHujan; }
    public int getSuhu() { return suhu; }

    public void infoCuaca() {
        System.out.println("Cuaca: " + suhu + "°C, Curah Hujan: " + curahHujan + "%");
    }
}