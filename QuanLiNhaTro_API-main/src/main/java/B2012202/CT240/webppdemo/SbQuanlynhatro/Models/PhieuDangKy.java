package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PhieuDangKy")
public class PhieuDangKy {
    @Id
    @SequenceGenerator(
            name = "pdk_sequence",
            sequenceName = "pdk_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pdk_sequence"
    )
    private Integer sttPhieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "idUser", referencedColumnName = "idUser"),
            @JoinColumn(name = "makt", referencedColumnName = "makt")
    })
    private KhachThue khachThue;

    private LocalDateTime thoiGian;

    @OneToOne(mappedBy = "phieuDangKy", cascade = CascadeType.ALL)
    private ChiTietDangKy chiTietDangKy;

    public PhieuDangKy() {
    }

    public PhieuDangKy(Integer sttPhieu, KhachThue khachThue, LocalDateTime thoiGian) {
        this.sttPhieu = sttPhieu;
        this.khachThue = khachThue;
        this.thoiGian = thoiGian;
    }

    public Integer getSttPhieu() {
        return sttPhieu;
    }

    public void setSttPhieu(Integer sttPhieu) {
        this.sttPhieu = sttPhieu;
    }

    public KhachThue getKhachThue() {
        return khachThue;
    }

    public void setKhachThue(KhachThue khachThue) {
        this.khachThue = khachThue;
    }

    public LocalDateTime getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(LocalDateTime thoiGian) {
        this.thoiGian = thoiGian;
    }
}
