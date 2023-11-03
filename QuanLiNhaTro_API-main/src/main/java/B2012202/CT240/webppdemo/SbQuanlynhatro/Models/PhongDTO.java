package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

public class PhongDTO {
    private Integer idNhaTro;
    private Integer sttLau;
    private Integer sttDay;
    private Integer sttPhong;
    private Integer idLoai;
    private String viTri;
    private Float giaPhong;
    private boolean tinhTrang;

    public PhongDTO() {
    }

    public PhongDTO(Integer idNhaTro, Integer sttLau, Integer sttDay, Integer sttPhong, Integer idLoai, String viTri, Float giaPhong, boolean tinhTrang) {
        this.idNhaTro = idNhaTro;
        this.sttLau = sttLau;
        this.sttDay = sttDay;
        this.sttPhong = sttPhong;
        this.idLoai = idLoai;
        this.viTri = viTri;
        this.giaPhong = giaPhong;
        this.tinhTrang = tinhTrang;
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

    public Integer getSttPhong() {
        return sttPhong;
    }

    public void setSttPhong(Integer sttPhong) {
        this.sttPhong = sttPhong;
    }

    public Integer getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(Integer idLoai) {
        this.idLoai = idLoai;
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

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
