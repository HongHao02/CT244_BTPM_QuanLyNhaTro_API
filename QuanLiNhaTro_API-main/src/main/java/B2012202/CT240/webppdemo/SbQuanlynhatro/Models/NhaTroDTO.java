package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

public class NhaTroDTO {
    private Integer idNhaTro;
    private LoaiNhaTro loaiNhaTro;
    private ChuTro chuTro;
    private String tenNT;
    private String diaChi;
    private Integer soPhong;
    private Integer tongPhong;

    public NhaTroDTO() {
    }

    public NhaTroDTO(Integer idNhaTro, LoaiNhaTro loaiNhaTro, ChuTro chuTro, String tenNT, String diaChi, Integer soPhong, Integer tongPhong) {
        this.idNhaTro = idNhaTro;
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
}
