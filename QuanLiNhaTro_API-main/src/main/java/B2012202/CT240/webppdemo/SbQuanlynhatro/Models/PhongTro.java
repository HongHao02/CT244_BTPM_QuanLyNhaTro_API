package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PhongTro")
public class PhongTro {
    @EmbeddedId
    private PhongTroID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "idNhaTro", referencedColumnName = "idNhaTro", insertable = false, updatable = false),
            @JoinColumn(name = "sttLau", referencedColumnName = "sttLau", insertable = false, updatable = false),
            @JoinColumn(name = "sttDay", referencedColumnName = "sttDay", insertable = false, updatable = false),
    })
    private Day day;

    @OneToOne
    @JoinColumn(name = "idLoai")
    private LoaiPhong loaiPhong;

    private String viTri;
    private Float giaPhong;
    private boolean tinhTrang;

    @OneToMany(mappedBy = "phongTro", cascade = CascadeType.ALL)
    private List<KhachThue> khachthues;

    @OneToMany(mappedBy = "phongTro", cascade = CascadeType.ALL)
    private List<ChiTietDangKy> chiTietDangKys;

    public PhongTro() {
        this.id = new PhongTroID();
    }

    public PhongTro(PhongTroID id, Day day, LoaiPhong loaiPhong, String viTri, Float giaPhong, boolean tinhTrang) {
        this.id = id;
        this.day = day;
        this.loaiPhong = loaiPhong;
        this.viTri = viTri;
        this.giaPhong = giaPhong;
        this.tinhTrang = tinhTrang;
    }

    public PhongTroID getId() {
        return id;
    }

    public void setId(PhongTroID id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public Float getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(Float giaPhong) {
        this.giaPhong = giaPhong;
    }

    public boolean getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

}
