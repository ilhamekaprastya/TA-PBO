import java.util.ArrayList;
import java.util.Scanner;

abstract class Film {
    protected String judul;
    protected String jamTayang;
    protected String studio;

    public Film(String judul, String jamTayang, String studio) {
        this.judul = judul;
        this.jamTayang = jamTayang;
        this.studio = studio;
    }

    public abstract void displayInfo();
}

class FilmTayang extends Film {
    public FilmTayang(String judul, String jamTayang, String studio) {
        super(judul, jamTayang, studio);
    }

    @Override
    public void displayInfo() {
        System.out.println("Film        : " + judul);
        System.out.println("Jam Tayang  : " + jamTayang);
        System.out.println("Studio      : " + studio);
    }
}

class Transaksi {
    private String namaPengunjung;
    private Film film;
    private String jamTayang;
    private String studio;

    public Transaksi(String namaPengunjung, Film film, String jamTayang, String studio) {
        this.namaPengunjung = namaPengunjung;
        this.film = film;
        this.jamTayang = jamTayang;
        this.studio = studio;
    }

    public String getNamaPengunjung() {
        return namaPengunjung;
    }

    public Film getFilm() {
        return film;
    }

    public String getJamTayang() {
        return jamTayang;
    }

    public String getStudio() {
        return studio;
    }
}

class Bioskop {
    private ArrayList<FilmTayang> daftarFilm = new ArrayList<>();
    private ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();

    public void tambahFilm(String judul, String jamTayang, String studio) {
        FilmTayang filmBaru = new FilmTayang(judul, jamTayang, studio);
        daftarFilm.add(filmBaru);
        System.out.println("Film berhasil ditambahkan.");
    }

    public void lihatDaftarFilm() {
        System.out.println("Daftar Film yang Tayang :");
        for (int i = 0; i < daftarFilm.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarFilm.get(i).displayInfo();
            System.out.println("-----------------------------");
        }
    }

    public void pesanTiket(String namaPengunjung, int indexFilm) {
        if (indexFilm >= 0 && indexFilm < daftarFilm.size()) {
            FilmTayang filmDipesan = daftarFilm.get(indexFilm);
            Transaksi transaksiBaru = new Transaksi(namaPengunjung, filmDipesan, filmDipesan.jamTayang, filmDipesan.studio);
            daftarTransaksi.add(transaksiBaru);
            System.out.println("Tiket berhasil dipesan.");
        } else {
            System.out.println("Film tidak ditemukan.");
        }
    }

    public void lihatDetailTransaksi() {
        System.out.println("Daftar Detail Transaksi :");
        for (Transaksi transaksi : daftarTransaksi) {
            System.out.println("Nama Pengunjung : " + transaksi.getNamaPengunjung());
            transaksi.getFilm().displayInfo();
            System.out.println("-----------------------------");
        }
    }

    public ArrayList<FilmTayang> getDaftarFilm() {
        return daftarFilm;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bioskop bioskop = new Bioskop();
        int role;

        do {
            System.out.println("Pilih Login : ");
            System.out.println("1. Admin");
            System.out.println("2. Pengunjung");
            System.out.println("0. Keluar");
            System.out.print("Pilihan : ");
            role = scanner.nextInt();
            scanner.nextLine();

            if (role != 1 && role != 2 && role != 0) {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (role != 1 && role != 2 && role != 0);

        while (role != 0) {
            if (role == 1) {
                // Login admin
                System.out.print("Masukkan username admin: ");
                String usernameAdmin = scanner.nextLine();
                System.out.print("Masukkan password admin: ");
                String passwordAdmin = scanner.nextLine();

                if (usernameAdmin.equals("ilham") && passwordAdmin.equals("admin1")) {
                    System.out.println("Login admin berhasil!");

                    int pilihanAdmin;
                    do {
                        System.out.println("\nMenu Admin:");
                        System.out.println("1. Tambah Film");
                        System.out.println("2. Lihat Daftar Film");
                        System.out.println("0. Keluar");
                        System.out.print("Pilihan: ");
                        pilihanAdmin = scanner.nextInt();
                        scanner.nextLine();

                        switch (pilihanAdmin) {
                            case 1:
                                System.out.print("Masukkan judul film   : ");
                                String judulFilm = scanner.nextLine();
                                System.out.print("Masukkan jam tayang   : ");
                                String jamTayangFilm = scanner.nextLine();
                                System.out.print("Masukkan studio       : ");
                                String studioFilm = scanner.nextLine();
                                bioskop.tambahFilm(judulFilm, jamTayangFilm, studioFilm);
                                break;
                            case 2:
                                bioskop.lihatDaftarFilm();
                                break;
                            case 0:
                                System.out.println("Keluar dari Menu Admin.");
                                break;
                            default:
                                System.out.println("Pilihan tidak valid!");
                                break;
                        }
                    } while (pilihanAdmin != 0);
                } else {
                    System.out.println("Login admin gagal. Program berhenti.");
                    System.exit(0);
                }
            } else if (role == 2) {
                // Login pengunjung
                System.out.print("Masukkan nama pengunjung : ");
                String namaPengunjung = scanner.nextLine();

                int pilihanPengunjung;
                do {
                    System.out.println("\nMenu Pengunjung:");
                    System.out.println("1. Pesan Tiket");
                    System.out.println("2. Cari Film");
                    System.out.println("3. Lihat Detail Transaksi");
                    System.out.println("0. Kembali");
                    System.out.print("Pilihan: ");
                    pilihanPengunjung = scanner.nextInt();
                    scanner.nextLine();

                    switch (pilihanPengunjung) {
                        case 1:
                            bioskop.lihatDaftarFilm();
                            System.out.print("Pilih film (masukkan nomor film): ");
                            int indexFilmDipesan;
                            do {
                                indexFilmDipesan = scanner.nextInt();
                                scanner.nextLine();
                                if (indexFilmDipesan < 1 || indexFilmDipesan > bioskop.getDaftarFilm().size()) {
                                    System.out.println("Nomor film tidak valid. Silakan coba lagi.");
                                }
                            } while (indexFilmDipesan < 1 || indexFilmDipesan > bioskop.getDaftarFilm().size());
                            bioskop.pesanTiket(namaPengunjung, indexFilmDipesan - 1);
                            break;
                        case 2:
                            System.out.print("Masukkan judul film yang dicari : ");
                            String judulCari = scanner.nextLine();
                            boolean filmDitemukan = false;
                            for (FilmTayang film : bioskop.getDaftarFilm()) {
                                if (film.judul.equalsIgnoreCase(judulCari)) {
                                    film.displayInfo();
                                    filmDitemukan = true;
                                    break;
                                }
                            }
                            if (!filmDitemukan) {
                                System.out.println("Film tidak ditemukan.");
                            }
                            break;
                        case 3:
                            bioskop.lihatDetailTransaksi();
                            break;
                        case 0:
                            System.out.println("Kembali ke Menu Login.");
                            break;
                        default:
                            System.out.println("Pilihan tidak valid!");
                            break;
                    }
                } while (pilihanPengunjung != 0);
            }

            do {
                System.out.println("Pilih Login : ");
                System.out.println("1. Admin");
                System.out.println("2. Pengunjung");
                System.out.println("0. Keluar");
                System.out.print("Pilihan : ");
                role = scanner.nextInt();
                scanner.nextLine();

                if (role != 1 && role != 2 && role != 0) {
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } while (role != 1 && role != 2 && role != 0);
        }
        System.out.println("Program berhenti.");
    }
}