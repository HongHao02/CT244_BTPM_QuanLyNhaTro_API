package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "BaiViet")
public class BaiViet {
    @Id
    @SequenceGenerator(
            name = "bv_sequence",
            sequenceName = "bv_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bv_sequence"
    )
    private Integer idBaiViet;

    @ManyToOne
    @JoinColumn(name = "idDM")
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idUser", referencedColumnName = "idUser"),
            @JoinColumn(name = "maChuTro", referencedColumnName = "maChuTro")
    })
    private ChuTro chuTro;

    private String tieuDe;
    private String ndBaiViet;
    private LocalDateTime thoiGianDang;
    @OneToMany(mappedBy = "baiViet", cascade = CascadeType.ALL)
    private List<PostFiles> postFilesList;

    @OneToMany(mappedBy = "baiViet", cascade = CascadeType.ALL)
    private List<BinhLuan> binhLuans;

    public BaiViet() {
    }

    public Integer getIdBaiViet() {
        return idBaiViet;
    }

    public void setIdBaiViet(Integer idBaiViet) {
        this.idBaiViet = idBaiViet;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public ChuTro getChuTro() {
        return chuTro;
    }

    public void setChuTro(ChuTro chuTro) {
        this.chuTro = chuTro;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNdBaiViet() {
        return ndBaiViet;
    }

    public void setNdBaiViet(String ndBaiViet) {
        this.ndBaiViet = ndBaiViet;
    }

    public LocalDateTime getThoiGianDang() {
        return thoiGianDang;
    }

    public void setThoiGianDang(LocalDateTime thoiGianDang) {
        this.thoiGianDang = thoiGianDang;
    }
}
