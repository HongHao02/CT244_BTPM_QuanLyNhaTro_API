package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import java.time.LocalDateTime;

public class PhieuDangKyDTO {
    private Integer sttPhieu;
    private Long idUser;
    private String makt;
    private LocalDateTime thoiGian;

    public PhieuDangKyDTO() {
    }

    public PhieuDangKyDTO(Integer sttPhieu, Long idUser, String makt, LocalDateTime thoiGian) {
        this.sttPhieu = sttPhieu;
        this.idUser = idUser;
        this.makt = makt;
        this.thoiGian = thoiGian;
    }

    public Integer getSttPhieu() {
        return sttPhieu;
    }

    public void setSttPhieu(Integer sttPhieu) {
        this.sttPhieu = sttPhieu;
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

    public LocalDateTime getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(LocalDateTime thoiGian) {
        this.thoiGian = thoiGian;
    }
}
