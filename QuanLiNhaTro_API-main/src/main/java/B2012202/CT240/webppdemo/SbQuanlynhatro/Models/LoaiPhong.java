package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "LoaiPhong")
public class LoaiPhong {
    @Id
    @SequenceGenerator(
            name = "lp_sequence",
            sequenceName = "lp_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lp_sequence"
    )


    private Integer idLoai;

    private String tenLoaiP;

    @OneToMany(mappedBy = "loaiPhong", cascade = CascadeType.ALL)
    private List<PhongTro> phongs;

    public LoaiPhong() {
    }

    public LoaiPhong(String tenLoaiP) {
        this.tenLoaiP = tenLoaiP;
    }

    public LoaiPhong(Integer idLoai, String tenLoaiP) {
        this.idLoai = idLoai;
        this.tenLoaiP = tenLoaiP;
    }


    public Integer getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(Integer idLoai) {
        this.idLoai = idLoai;
    }

    public String getTenLoaiP() {
        return tenLoaiP;
    }

    public void setTenLoaiP(String tenLoaiP) {
        this.tenLoaiP = tenLoaiP;
    }
}
