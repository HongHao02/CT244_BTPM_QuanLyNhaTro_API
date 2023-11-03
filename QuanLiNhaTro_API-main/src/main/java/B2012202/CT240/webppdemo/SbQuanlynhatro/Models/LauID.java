package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LauID implements Serializable {

    private Integer idNhaTro;
    private Integer sttLau;

    public LauID() {
    }

    public LauID(Integer idNhaTro, Integer sttLau) {
        this.idNhaTro = idNhaTro;
        this.sttLau = sttLau;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LauID lauID = (LauID) o;
        return Objects.equals(idNhaTro, lauID.idNhaTro) && Objects.equals(sttLau, lauID.sttLau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNhaTro, sttLau);
    }
}
