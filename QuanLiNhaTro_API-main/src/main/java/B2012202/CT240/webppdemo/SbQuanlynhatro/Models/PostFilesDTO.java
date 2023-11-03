package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

public class PostFilesDTO {
    private Integer idBaiViet;
    private Integer idFile;

    public PostFilesDTO() {
    }

    public PostFilesDTO(Integer idBaiViet, Integer idFile) {
        this.idBaiViet = idBaiViet;
        this.idFile = idFile;
    }

    public Integer getIdBaiViet() {
        return idBaiViet;
    }

    public void setIdBaiViet(Integer idBaiViet) {
        this.idBaiViet = idBaiViet;
    }

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
    }
}
