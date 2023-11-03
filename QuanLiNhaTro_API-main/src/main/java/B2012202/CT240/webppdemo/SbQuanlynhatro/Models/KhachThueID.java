package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class KhachThueID {
    private Long idUser;
    private String makt;

    public KhachThueID() {
    }

    public KhachThueID(Long idUser, String makt) {
        this.idUser = idUser;
        this.makt = makt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhachThueID that = (KhachThueID) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(makt, that.makt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, makt);
    }
}
