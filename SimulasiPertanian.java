import java.util.Scanner;

/**
 * Kelas utama untuk menjalankan simulasi.
 * [Mengimplementasikan logika simulasi - cite: 23]
 */
public class SimulasiPertanian {

    public static void main(String[] args) {
        // Inisialisasi Objek Utama
        Pemain pemain = new Pemain("Petani", 1000, 100);
        Toko toko = new Toko();
        Waktu waktu = new Waktu();
        Cuaca cuaca = new Cuaca();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Selamat Datang di Simulasi Pertanian Sederhana!");
        boolean simulasiBerjalan = true;

        while (simulasiBerjalan) {
            System.out.println("\n--- Hari ke-" + waktu.getHari() + " | Musim: " + waktu.getMusim() + " ---");
            cuaca.infoCuaca();
            pemain.infoPemain();

            System.out.println("\nAksi yang tersedia:");
            System.out.println("1. Tanam (Padi/Jagung)");
            System.out.println("2. Siram Lahan [cite: 25]");
            System.out.println("3. Pupuk Lahan (Demo Overloading) [cite: 21, 25]");
            System.out.println("4. Panen Lahan");
            System.out.println("5. Pergi ke Toko (Beli/Jual)");
            System.out.println("6. Tidur (Lanjut ke hari berikutnya)");
            System.out.println("7. Keluar Simulasi");
            System.out.print("Pilih aksi (1-7): ");
            
            String pilihan = scanner.nextLine();
            
            try {
                switch (pilihan) {
                    case "1": // Tanam
                        System.out.print("Tanam apa? (Padi/Jagung): ");
                        String namaBibit = scanner.nextLine();
                        System.out.print("Di lahan nomor berapa? (0, 1, ...): ");
                        int lahanTanam = Integer.parseInt(scanner.nextLine());
                        pemain.menanam(lahanTanam, namaBibit);
                        break;
                    
                    case "2": // Siram [cite: 25]
                        System.out.print("Siram lahan nomor berapa? (0, 1, ...): ");
                        int lahanSiram = Integer.parseInt(scanner.nextLine());
                        pemain.getDaftarLahan().get(lahanSiram).menyiram();
                        break;
                        
                    case "3": // Pupuk (Demo Overloading) [cite: 21, 25]
                        System.out.print("Pupuk lahan nomor berapa? (0, 1, ...): ");
                        int lahanPupuk = Integer.parseInt(scanner.nextLine());
                        System.out.print("Mau pakai pupuk spesifik? (y/n): ");
                        if (scanner.nextLine().equalsIgnoreCase("y")) {
                            System.out.print("Jenis pupuk: ");
                            String jenisPupuk = scanner.nextLine();
                            // Memanggil metode overloading
                            pemain.getDaftarLahan().get(lahanPupuk).memupuk(jenisPupuk); 
                        } else {
                            // Memanggil metode standar dari interface
                            pemain.getDaftarLahan().get(lahanPupuk).memupuk(); 
                        }
                        break;
                        
                    case "4": // Panen 
                        System.out.print("Panen lahan nomor berapa? (0, 1, ...): ");
                        int lahanPanen = Integer.parseInt(scanner.nextLine());
                        pemain.memanen(lahanPanen);
                        break;
                    
                    case "5": // Toko
                        toko.lihatBarang();
                        System.out.print("Aksi di toko (beli/jual): ");
                        String aksiToko = scanner.nextLine();
                        if (aksiToko.equalsIgnoreCase("beli")) {
                            System.out.print("Beli bibit apa? (Padi/Jagung): ");
                            String beliApa = scanner.nextLine();
                            pemain.beliBibit(toko, beliApa, 1);
                        } else if (aksiToko.equalsIgnoreCase("jual")) {
                            System.out.print("Jual hasil apa? (Gabah/Jagung Manis): ");
                            String jualApa = scanner.nextLine();
                            System.out.print("Jumlah: ");
                            int jumlahJual = Integer.parseInt(scanner.nextLine());
                            pemain.jualHasil(toko, jualApa, jumlahJual);
                        }
                        break;

                    case "6": // Next Day [cite: 24]
                        waktu.nextDay();
                        cuaca.gantiCuaca(waktu.getMusim());
                        
                        // Simulasi pertumbuhan tanaman [cite: 24, 26]
                        // [Polimorfisme digunakan di sini - cite: 20]
                        System.out.println("Matahari terbenam, tanaman tumbuh...");
                        for (Lahan l : pemain.getDaftarLahan()) {
                            if (!l.isKosong()) {
                                // Memanggil .tumbuh() yang spesifik (Padi/Jagung)
                                l.getTanaman().tumbuh(cuaca); 
                            }
                        }
                        break;

                    case "7": // Keluar
                        simulasiBerjalan = false;
                        System.out.println("Terima kasih telah bermain!");
                        break;
                        
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (Exception e) {
                System.out.println("Terjadi error: " + e.getMessage() + ". Input tidak valid, coba lagi.");
            }
        }
        scanner.close();
    }
}