package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChuTroID implements Serializable {
    private String maChuTro;
    private Long idUser;


    public ChuTroID() {

    }


    public ChuTroID(String maChuTro, Long user_id) {
        this.maChuTro = maChuTro;
        this.idUser = user_id;
    }

    public String getMaChuTro() {
        return maChuTro;
    }

    public void setMaChuTro(String maChuTro) {
        this.maChuTro = maChuTro;
    }

    public Long getUser_id() {
        return idUser;
    }

    public void setUser_id(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChuTroID chuTroID = (ChuTroID) o;
        return Objects.equals(maChuTro, chuTroID.maChuTro) && Objects.equals(idUser, chuTroID.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maChuTro, idUser);
    }
}
