package Model;

/**
 *
 * @author AERO
 */
public class Kontak {
    private int id;
    private String nama;
    private String telepon;
    private String email;
    private String alamat;

    public Kontak(int id, String nama, String telepon, String email, String alamat) {
        this.id = id;
        this.nama = nama;
        this.telepon = telepon;
        this.email = email;
        this.alamat = alamat;
    }

    // Getter dan Setter
    public int getId() {
        return id; 
    }
    public void setId(int id) { 
        this.id = id;
    }

    public String getNama() { 
        return nama; 
    }
    public void setNama(String nama) { 
        this.nama = nama; 
    }

    public String getTelepon() { 
        return telepon; 
    }
    public void setTelepon(String telepon) { 
        this.telepon = telepon; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getAlamat() { 
        return alamat; 
    }
    public void setAlamat(String alamat) { 
        this.alamat = alamat; 
    }
}
