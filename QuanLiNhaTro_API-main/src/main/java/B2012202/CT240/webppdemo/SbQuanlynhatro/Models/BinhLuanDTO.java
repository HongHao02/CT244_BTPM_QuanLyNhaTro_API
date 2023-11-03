package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

public class BinhLuanDTO {
    private Long idUser;
    private String makt;
    private Integer idBaiViet;
    private Integer idBinhLuan;
    private String ndBinhLuan;

    public BinhLuanDTO() {
    }

    public BinhLuanDTO(Long idUser, String makt, Integer idBaiViet, Integer idBinhLuan, String ndBinhLuan) {
        this.idUser = idUser;
        this.makt = makt;
        this.idBaiViet = idBaiViet;
        this.idBinhLuan = idBinhLuan;
        this.ndBinhLuan = ndBinhLuan;
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

    public Integer getIdBaiViet() {
        return idBaiViet;
    }

    public void setIdBaiViet(Integer idBaiViet) {
        this.idBaiViet = idBaiViet;
    }

    public Integer getIdBinhLuan() {
        return idBinhLuan;
    }

    public void setIdBinhLuan(Integer idBinhLuan) {
        this.idBinhLuan = idBinhLuan;
    }

    public String getNdBinhLuan() {
        return ndBinhLuan;
    }

    public void setNdBinhLuan(String ndBinhLuan) {
        this.ndBinhLuan = ndBinhLuan;
    }
}
