package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

@Entity
@Table(name = "KhachThue")
public class KhachThue {

    @EmbeddedId
    private KhachThueID id;

    @MapsId("idUser")
    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "idNhaTro", referencedColumnName = "idNhaTro", insertable = false),
            @JoinColumn(name = "sttLau", referencedColumnName = "sttLau", insertable = false),
            @JoinColumn(name = "sttDay", referencedColumnName = "sttDay", insertable = false),
            @JoinColumn(name = "sttPhong", referencedColumnName = "sttPhong", insertable = false),
    })
    private PhongTro phongTro;

    @OneToMany(mappedBy = "khachThue", cascade = CascadeType.ALL)
    private List<PhieuDangKy> phieuDangKyes;

    @OneToMany(mappedBy = "khachThue", cascade = CascadeType.ALL)
    private List<BinhLuan> binhLuans;

    @PrePersist
    public void prePersist() {
        // Kiểm tra xem trường user có tồn tại
        if (user.getIdUser() != null) {
            // Tạo mã chủ trọ dựa trên id của user
            this.id.setMakt("KT" + user.getIdUser());
            this.id.setIdUser(user.getIdUser());//Có  dư thừa không?
        } else {
            // Xử lý trường hợp user không tồn tại (tùy ý)
            // Ví dụ: Ném một ngoại lệ để báo lỗi
            throw new RuntimeException("User của KhachThue không tồn tại.");
        }
    }

    public KhachThue() {
        this.id = new KhachThueID();
    }

    public KhachThue(KhachThueID id, User user, PhongTro phongTro, List<PhieuDangKy> phieuDangKyes) {
        this.id = id;
        this.user = user;
        this.phongTro = phongTro;
//        this.phieuDangKyes = phieuDangKyes;
    }

    public KhachThueID getId() {
        return id;
    }

    public void setId(KhachThueID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PhongTro getPhongTro() {
        return phongTro;
    }

    public void setPhongTro(PhongTro phongTro) {
        this.phongTro = phongTro;
    }

//    public List<PhieuDangKy> getPhieuDangKyes() {
//        return phieuDangKyes;
//    }
//
//    public void setPhieuDangKyes(List<PhieuDangKy> phieuDangKyes) {
//        this.phieuDangKyes = phieuDangKyes;
//    }
}
