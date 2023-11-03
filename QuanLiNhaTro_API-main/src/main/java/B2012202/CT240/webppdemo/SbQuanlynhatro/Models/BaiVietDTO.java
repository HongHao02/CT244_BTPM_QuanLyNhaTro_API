package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import java.time.LocalDateTime;

public class BaiVietDTO {

    private Integer idBaiViet;
    private Integer idDM;
    private Long idUser;
    private String maChuTro;
    private String tieuDe;
    private String ndBaiViet;
    private LocalDateTime thoiGianDang;
    private String fileUrl;

    public BaiVietDTO() {
    }

    public BaiVietDTO(Integer idBaiViet, Integer idDM, Long idUser, String maChuTro, String tieuDe, String ndBaiViet, LocalDateTime thoiGianDang) {
        this.idBaiViet = idBaiViet;
        this.idDM = idDM;
        this.idUser = idUser;
        this.maChuTro = maChuTro;
        this.tieuDe = tieuDe;
        this.ndBaiViet = ndBaiViet;
        this.thoiGianDang = thoiGianDang;
    }

    public Integer getIdBaiViet() {
        return idBaiViet;
    }

    public void setIdBaiViet(Integer idBaiViet) {
        this.idBaiViet = idBaiViet;
    }

    public Integer getIdDM() {
        return idDM;
    }

    public void setIdDM(Integer idDM) {
        this.idDM = idDM;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getMaChuTro() {
        return maChuTro;
    }

    public void setMaChuTro(String maChuTro) {
        this.maChuTro = maChuTro;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNdBaiViet() {
        return ndBaiViet;
    }

    public void setNdBaiViet(String ndBaiViet) {
        this.ndBaiViet = ndBaiViet;
    }

    public LocalDateTime getThoiGianDang() {
        return thoiGianDang;
    }

    public void setThoiGianDang(LocalDateTime thoiGianDang) {
        this.thoiGianDang = thoiGianDang;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
