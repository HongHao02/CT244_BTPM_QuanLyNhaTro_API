package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import java.time.LocalDateTime;

public class ChiTietDangKyDTO {
    private Integer sttPhieu;
    private Integer idNhaTro;
    private Integer sttLau;
    private Integer sttDay;
    private Integer sttPhong;

    private LocalDateTime thoiGianDangKy;
    private float tienCoc;
    private boolean trangThaiDuyet;

    private KhachThue updateKhachThue;

    public ChiTietDangKyDTO() {
        this.updateKhachThue = new KhachThue();
    }

    public ChiTietDangKyDTO(Integer sttPhieu, Integer idNhaTro, Integer sttLau, Integer sttDay, Integer sttPhong, LocalDateTime thoiGianDangKy, float tienCoc, boolean trangThaiDuyet) {
        this.sttPhieu = sttPhieu;
        this.idNhaTro = idNhaTro;
        this.sttLau = sttLau;
        this.sttDay = sttDay;
        this.sttPhong = sttPhong;
        this.thoiGianDangKy = thoiGianDangKy;
        this.tienCoc = tienCoc;
        this.trangThaiDuyet = trangThaiDuyet;
    }

    public Integer getSttPhieu() {
        return sttPhieu;
    }

    public void setSttPhieu(Integer sttPhieu) {
        this.sttPhieu = sttPhieu;
    }

    public Integer getIdNhaTro() {
        return idNhaTro;
    }

    public void setIdNhaTro(Integer idNhaTro) {
        this.idNhaTro = idNhaTro;
    }

    public Integer getSttLau() {
        return sttLau;
    }

    public void setSttLau(Integer sttLau) {
        this.sttLau = sttLau;
    }

    public Integer getSttDay() {
        return sttDay;
    }

    public void setSttDay(Integer sttDay) {
        this.sttDay = sttDay;
    }

    public Integer getSttPhong() {
        return sttPhong;
    }

    public void setSttPhong(Integer sttPhong) {
        this.sttPhong = sttPhong;
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

    public KhachThue getUpdateKhachThue() {
        return updateKhachThue;
    }

    public void setUpdateKhachThue(KhachThue updateKhachThue) {
        this.updateKhachThue = updateKhachThue;
    }
}
