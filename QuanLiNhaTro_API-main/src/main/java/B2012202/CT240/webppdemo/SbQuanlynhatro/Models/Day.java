package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Day")
public class Day {
    //primary key
    @EmbeddedId
    private DayID id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("idNhaTro")
    @JoinColumns({
            @JoinColumn(name = "idNhaTro", referencedColumnName = "idNhaTro", insertable = false, updatable = false),
            @JoinColumn(name = "sttLau", referencedColumnName = "sttLau", insertable = false, updatable = false)
    })
    private Lau lau;


    private Integer tongPhongD;
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private List<PhongTro> phongs;

    public Day() {
    }

    public Day(Integer tongPhongD) {
        this.tongPhongD = tongPhongD;
    }

    public Day(DayID id, Lau lau, Integer tongPhongD) {
        this.id = id;
        this.lau = lau;
        this.tongPhongD = tongPhongD;
    }

    public DayID getId() {
        return id;
    }

    public void setId(DayID id) {
        this.id = id;
    }

    public Lau getLau() {
        return lau;
    }

    public void setLau(Lau lau) {
        this.lau = lau;
    }

    public Integer getTongPhongD() {
        return tongPhongD;
    }

    public void setTongPhongD(Integer tongPhongD) {
        this.tongPhongD = tongPhongD;
    }
}
