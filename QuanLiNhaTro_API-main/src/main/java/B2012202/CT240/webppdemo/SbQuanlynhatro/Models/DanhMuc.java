package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "DanhMuc")
public class DanhMuc {
    @Id
    @SequenceGenerator(
            name = "dm_sequence",
            sequenceName = "dm_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dm_sequence"
    )
    private Integer idDM;

    private Integer tenDM;
    private String moTa;

    @OneToMany(mappedBy = "danhMuc", cascade = CascadeType.ALL)
    private List<BaiViet> baiViets;

    public DanhMuc() {
    }

    public Integer getIdDM() {
        return idDM;
    }

    public void setIdDM(Integer idDM) {
        this.idDM = idDM;
    }

    public Integer getTenDM() {
        return tenDM;
    }

    public void setTenDM(Integer tenDM) {
        this.tenDM = tenDM;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<BaiViet> getBaiViets() {
        return baiViets;
    }

    public void setBaiViets(List<BaiViet> baiViets) {
        this.baiViets = baiViets;
    }
}
