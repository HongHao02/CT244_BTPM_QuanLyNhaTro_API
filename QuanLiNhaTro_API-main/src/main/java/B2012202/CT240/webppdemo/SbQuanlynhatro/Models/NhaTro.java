package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;
import org.springframework.data.relational.core.sql.In;

import java.util.List;


@Entity
@Table(name = "NhaTro")
public class NhaTro {

    @Id
    @SequenceGenerator(
            name = "nhatro_sequence",
            sequenceName = "nhatro_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "nhatro_sequence"
    )
    private Integer idNhaTro;

    @OneToOne
    @JoinColumn(name = "idLoai")
    private LoaiNhaTro loaiNhaTro; // Trường khóa ngoại (mục đích để name="idLoai" là do trong DTB ta đã có trường chứa khóa ngoại tên là idLoai

//    @OneToOne
//    @JoinColumn(name = "idUser")
//    private User user;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idUser", referencedColumnName = "idUser"),
            @JoinColumn(name = "maChuTro", referencedColumnName = "maChuTro")
    })
    private ChuTro chuTro;

    private String tenNT;
    private String diaChi;
    private Integer soPhong;
    private Integer tongPhong;


    @OneToMany(mappedBy = "nhaTro", cascade = CascadeType.ALL)
    private List<Lau> laus;

    public NhaTro() {
    }

    public NhaTro(LoaiNhaTro loaiNhaTro, ChuTro chuTro, String tenNT, String diaChi, Integer soPhong, Integer tongPhong) {
        this.loaiNhaTro = loaiNhaTro;
        this.chuTro = chuTro;
        this.tenNT = tenNT;
        this.diaChi = diaChi;
        this.soPhong = soPhong;
        this.tongPhong = tongPhong;
    }

    public Integer getIdNhaTro() {
        return idNhaTro;
    }

    public void setIdNhaTro(Integer idNhaTro) {
        this.idNhaTro = idNhaTro;
    }

    public LoaiNhaTro getLoaiNhaTro() {
        return loaiNhaTro;
    }

    public void setLoaiNhaTro(LoaiNhaTro loaiNhaTro) {
        this.loaiNhaTro = loaiNhaTro;
    }

    public ChuTro getChuTro() {
        return chuTro;
    }

    public void setChuTro(ChuTro chuTro) {
        this.chuTro = chuTro;
    }

    public String getTenNT() {
        return tenNT;
    }

    public void setTenNT(String tenNT) {
        this.tenNT = tenNT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(Integer soPhong) {
        this.soPhong = soPhong;
    }

    public Integer getTongPhong() {
        return tongPhong;
    }

    public void setTongPhong(Integer tongPhong) {
        this.tongPhong = tongPhong;
    }

    public List<Lau> getLaus() {
        return laus;
    }

    public void setLaus(List<Lau> laus) {
        this.laus = laus;
    }
}
