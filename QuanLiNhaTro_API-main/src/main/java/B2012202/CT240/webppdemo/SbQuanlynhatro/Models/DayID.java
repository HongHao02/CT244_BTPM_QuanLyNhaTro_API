package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.Embeddable;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DayID implements Serializable {
    private Integer idNhaTro;
    private Integer sttLau;
    private Integer sttDay;

    public DayID() {
    }

    public DayID(Integer idNhaTro, Integer sttLau, Integer sttDay) {
        this.idNhaTro = idNhaTro;
        this.sttLau = sttLau;
        this.sttDay = sttDay;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayID dayID = (DayID) o;
        return Objects.equals(idNhaTro, dayID.idNhaTro) && Objects.equals(sttLau, dayID.sttLau) && Objects.equals(sttDay, dayID.sttDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNhaTro, sttLau, sttDay);
    }
}
