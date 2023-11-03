package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Files")
public class Files {
    @Id
    @SequenceGenerator(
            name = "file_sequence",
            sequenceName = "file_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "file_sequence"
    )
    private Integer idFile;

    private String url;

    @OneToMany(mappedBy = "files", cascade = CascadeType.ALL)
    private List<PostFiles> postFilesList;

    public Files() {
    }

    public Files(Integer idFile, String url, List<PostFiles> postFilesList) {
        this.idFile = idFile;
        this.url = url;
        this.postFilesList = postFilesList;
    }

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<PostFiles> getPostFilesList() {
        return postFilesList;
    }

    public void setPostFilesList(List<PostFiles> postFilesList) {
        this.postFilesList = postFilesList;
    }
}
