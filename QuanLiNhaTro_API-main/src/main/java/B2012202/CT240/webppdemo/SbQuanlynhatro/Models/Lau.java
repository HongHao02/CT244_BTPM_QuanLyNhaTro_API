package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Lau")
public class Lau {
    @EmbeddedId
    private LauID id;

    @MapsId("idNhaTro")
    @ManyToOne
    @JoinColumn(name = "idNhaTro")
    private NhaTro nhaTro;

    private Integer tongDay;

    @OneToMany(mappedBy = "lau", cascade = CascadeType.ALL)
    private List<Day> days;

    public Lau() {
    }

    public Lau(LauID id, NhaTro nhaTro, Integer tongDay) {
        this.id = id;
        this.nhaTro = nhaTro;
        this.tongDay = tongDay;
    }

    public Lau(Integer tongDay) {
        this.tongDay = tongDay;
    }

    public LauID getId() {
        return id;
    }

    public void setId(LauID id) {
        this.id = id;
    }

    public NhaTro getNhaTro() {
        return nhaTro;
    }

    public void setNhaTro(NhaTro nhaTro) {
        this.nhaTro = nhaTro;
    }

    public Integer getTongDay() {
        return tongDay;
    }

    public void setTongDay(Integer tongDay) {
        this.tongDay = tongDay;
    }
}
