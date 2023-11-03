package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PostFilesID {
    private Integer idBaiViet;
    private Integer idFile;

    public PostFilesID() {
    }

    public PostFilesID(Integer idBaiViet, Integer idFile) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostFilesID that = (PostFilesID) o;
        return Objects.equals(idBaiViet, that.idBaiViet) && Objects.equals(idFile, that.idFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBaiViet, idFile);
    }
}
