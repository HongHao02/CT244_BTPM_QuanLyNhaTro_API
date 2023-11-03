package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ChiTietDangKyID {
    private Integer sttPhieu;
    private Integer idNhaTro;
    private Integer sttLau;
    private Integer sttDay;
    private Integer sttPhong;

    public ChiTietDangKyID() {
    }

    public ChiTietDangKyID(Integer sttPhieu, Integer idNhaTro, Integer sttLau, Integer sttDay, Integer sttPhong) {
        this.sttPhieu = sttPhieu;
        this.idNhaTro = idNhaTro;
        this.sttLau = sttLau;
        this.sttDay = sttDay;
        this.sttPhong = sttPhong;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietDangKyID that = (ChiTietDangKyID) o;
        return Objects.equals(sttPhieu, that.sttPhieu) && Objects.equals(idNhaTro, that.idNhaTro) && Objects.equals(sttLau, that.sttLau) && Objects.equals(sttDay, that.sttDay) && Objects.equals(sttPhong, that.sttPhong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sttPhieu, idNhaTro, sttLau, sttDay, sttPhong);
    }
}
