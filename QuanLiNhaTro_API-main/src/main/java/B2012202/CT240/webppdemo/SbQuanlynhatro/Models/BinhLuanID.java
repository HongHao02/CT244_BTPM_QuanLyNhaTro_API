package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Objects;

@Embeddable
public class BinhLuanID {
    private Long idUser;
    private String makt;
    private Integer idBaiViet;
    private Integer idBinhLuan;

    public BinhLuanID() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinhLuanID that = (BinhLuanID) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(makt, that.makt) && Objects.equals(idBaiViet, that.idBaiViet) && Objects.equals(idBinhLuan, that.idBinhLuan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, makt, idBaiViet, idBinhLuan);
    }
}
