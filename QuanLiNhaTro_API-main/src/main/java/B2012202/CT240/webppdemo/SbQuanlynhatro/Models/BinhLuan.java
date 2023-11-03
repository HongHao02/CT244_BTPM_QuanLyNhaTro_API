package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "BinhLuan")
public class BinhLuan {
    @EmbeddedId
    private BinhLuanID id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idUser", referencedColumnName = "idUser", insertable = false, updatable = false),
            @JoinColumn(name = "makt", referencedColumnName = "makt", insertable = false, updatable = false)
    })
    private KhachThue khachThue;

    //    @MapsId("idBaiViet")
    @ManyToOne
    @MapsId("idBaiViet")
    @JoinColumn(name = "idBaiViet")
    private BaiViet baiViet;

    private String ndBinhLuan;

    public BinhLuan() {
        this.id = new BinhLuanID();
//        this.id.setIdBinhLuan((int) idBinhLuanCounter.incrementAndGet());
//        System.out.println("idBinhLuan " + this.id.getIdBinhLuan());
    }

    public BinhLuanID getId() {
        return id;
    }

    public void setId(BinhLuanID id) {
        this.id = id;
    }

    public KhachThue getKhachThue() {
        return khachThue;
    }

    public void setKhachThue(KhachThue khachThue) {
        this.khachThue = khachThue;
    }

    public BaiViet getBaiViet() {
        return baiViet;
    }

    public void setBaiViet(BaiViet baiViet) {
        this.baiViet = baiViet;
    }

    public String getNdBinhLuan() {
        return ndBinhLuan;
    }

    public void setNdBinhLuan(String ndBinhLuan) {
        this.ndBinhLuan = ndBinhLuan;
    }
}
