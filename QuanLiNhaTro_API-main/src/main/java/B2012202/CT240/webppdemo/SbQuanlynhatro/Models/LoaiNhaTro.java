package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "LoaiNhaTro")
public class LoaiNhaTro {
    @Id
    @SequenceGenerator(
            name = "lnt_sequence",
            sequenceName = "lnt_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lnt_sequence"
    )
    private Integer idLoai;

    private String tenLoaiNT;

    public LoaiNhaTro() {
    }

    public LoaiNhaTro(String tenLoaiNT) {
        this.idLoai = idLoai;
        this.tenLoaiNT = tenLoaiNT;
    }

    public Integer getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(Integer idLoai) {
        this.idLoai = idLoai;
    }

    public String getTenLoaiNT() {
        return tenLoaiNT;
    }

    public void setTenLoaiNT(String tenLoaiNT) {
        this.tenLoaiNT = tenLoaiNT;
    }
}
