package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import java.security.SecureRandom;

public class KhachThueDTO {
    private Long idUser;
    private String makt;
    private Integer idNhaTro;
    private Integer sttLau;
    private Integer sttDay;
    private Integer sttPhong;

    public KhachThueDTO() {
    }

    public KhachThueDTO(Long idUser, String makt, Integer idNhaTro, Integer sttLau, Integer sttDay, Integer sttPhong) {
        this.idUser = idUser;
        this.makt = makt;
        this.idNhaTro = idNhaTro;
        this.sttLau = sttLau;
        this.sttDay = sttDay;
        this.sttPhong = sttPhong;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getMakt() {
        return makt;
    }

    public void setMakt(String makt) {
        this.makt = makt;
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
}
