package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

public class DayDTO {
    private Integer idNhaTro;
    private Integer sttLau;
    private Integer sttDay;
    private Integer tongPhongD;

    public DayDTO() {
    }

    public DayDTO(Integer idNhaTro, Integer sttLau, Integer sttDay, Integer tongPhongD) {
        this.idNhaTro = idNhaTro;
        this.sttLau = sttLau;
        this.sttDay = sttDay;
        this.tongPhongD = tongPhongD;
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

    public Integer getTongPhongD() {
        return tongPhongD;
    }

    public void setTongPhongD(Integer tongPhongD) {
        this.tongPhongD = tongPhongD;
    }
}
