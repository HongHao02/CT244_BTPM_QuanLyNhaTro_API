package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ChiTietDangKy")
public class ChiTietDangKy {
    @EmbeddedId
    private ChiTietDangKyID id;


    @MapsId("sttPhieu")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sttPhieu", referencedColumnName = "sttPhieu")
    private PhieuDangKy phieuDangKy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "idNhaTro", referencedColumnName = "idNhaTro", insertable = false, updatable = false),
            @JoinColumn(name = "sttLau", referencedColumnName = "sttLau", insertable = false, updatable = false),
            @JoinColumn(name = "sttDay", referencedColumnName = "sttDay", insertable = false, updatable = false),
            @JoinColumn(name = "sttPhong", referencedColumnName = "sttPhong", insertable = false, updatable = false)
    })
    private PhongTro phongTro;

    private LocalDateTime thoiGianDangKy;
    private float tienCoc;
    private boolean trangThaiDuyet;

    public ChiTietDangKy() {
        this.id = new ChiTietDangKyID();
    }

    public ChiTietDangKy(ChiTietDangKyID id, PhieuDangKy phieuDangKy, PhongTro phongTro, LocalDateTime thoiGianDangKy, float tienCoc, boolean trangThaiDuyet) {
        this.id = id;
        this.phieuDangKy = phieuDangKy;
        this.phongTro = phongTro;
        this.thoiGianDangKy = thoiGianDangKy;
        this.tienCoc = tienCoc;
        this.trangThaiDuyet = trangThaiDuyet;
    }

    public ChiTietDangKyID getId() {
        return id;
    }

    public void setId(ChiTietDangKyID id) {
        this.id = id;
    }

    public PhieuDangKy getPhieuDangKy() {
        return phieuDangKy;
    }

    public void setPhieuDangKy(PhieuDangKy phieuDangKy) {
        this.phieuDangKy = phieuDangKy;
    }

    public PhongTro getPhongTro() {
        return phongTro;
    }

    public void setPhongTro(PhongTro phongTro) {
        this.phongTro = phongTro;
    }

    public LocalDateTime getThoiGianDangKy() {
        return thoiGianDangKy;
    }

    public void setThoiGianDangKy(LocalDateTime thoiGianDangKy) {
        this.thoiGianDangKy = thoiGianDangKy;
    }

    public float getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(float tienCoc) {
        this.tienCoc = tienCoc;
    }

    public boolean isTrangThaiDuyet() {
        return trangThaiDuyet;
    }

    public void setTrangThaiDuyet(boolean trangThaiDuyet) {
        this.trangThaiDuyet = trangThaiDuyet;
    }
}
