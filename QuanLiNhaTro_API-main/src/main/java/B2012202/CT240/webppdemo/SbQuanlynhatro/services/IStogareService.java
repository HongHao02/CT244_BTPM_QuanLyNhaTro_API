package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStogareService {
    public String storeFile(MultipartFile file);

    public Stream<Path> loadAll(); //load all file in the folder

    public Stream<Path> loadFile(String fileName); //load all file in the folder

    //Lay thong tin anh bang mang byte, co the gui request len server cho server tra ve mang byte tu
    //do ta co the xem thong tin truc tiep tren trinh duyet
    public byte[] readFileContent(String fileName);

    public void deleteAllFiles();
}
