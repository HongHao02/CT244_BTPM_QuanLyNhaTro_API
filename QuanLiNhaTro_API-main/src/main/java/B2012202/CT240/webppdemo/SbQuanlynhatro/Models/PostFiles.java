package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "PostFiles")
public class PostFiles {

    @EmbeddedId
    private PostFilesID id;

    @MapsId("idBaiViet")
    @ManyToOne
    @JoinColumn(name = "idBaiViet")
    private BaiViet baiViet;

    @MapsId("idFile")
    @ManyToOne
    @JoinColumn(name = "idFile")
    private Files files;

    public PostFiles() {
        this.id = new PostFilesID();
    }

    public PostFilesID getId() {
        return id;
    }

    public void setId(PostFilesID id) {
        this.id = id;
    }

    public BaiViet getBaiViet() {
        return baiViet;
    }

    public void setBaiViet(BaiViet baiViet) {
        this.baiViet = baiViet;
    }

    public Files getFiles() {
        return files;
    }

    public void setFiles(Files files) {
        this.files = files;
    }
}
