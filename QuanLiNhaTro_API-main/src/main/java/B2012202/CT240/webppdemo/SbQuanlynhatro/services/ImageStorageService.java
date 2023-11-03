package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

//Thuc thi cac method trong IStogareService
@Service
//Cho javaSpring hieu day la Mot service
public class ImageStorageService implements IStogareService {

    Logger logger = LoggerFactory.getLogger(ImageStorageService.class);
    private final Path stogareFolder = Paths.get("uploads");//Tham chieu den thu muc chua anh, neu chua co
    //thu muc thi Java Spring se tu tao

    //constructor
    //duoc goi 1 lan duy nhat khi app chung ta chay, lan sau chay moi goi chay tiep, neu thu muc tao roi thi khong xoa
    public ImageStorageService() {
        try {
            Files.createDirectories(stogareFolder);
        } catch (IOException e) {
            throw new RuntimeException("Cannot initialize stogare", e);
        }
    }

    /*Kiem tra xem file name dua vao co duoi nam trong so cac duoi file cho phep duoi day hay khong*/
    private boolean isImageFile(MultipartFile file) {
        //use commons-io to check file name
//        <dependency>
//            <groupId>commons-io</groupId>
//            <artifactId>commons-io</artifactId>
//            <version>2.11.0</version>
//        </dependency>

        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename()); //lay duoi file
        return Arrays.asList(new String[]{"png", "jpg", "jpeg", "bmp"}).contains(fileExtension.trim().toLowerCase());

    }

    //Luu du lieu vao trong file
    //storeFile kiem tra xem file gui tu request co rong khong
    @Override
    public String storeFile(MultipartFile file) {
        try {
            System.out.println("File load from client/ request~~~>");
            if (file.isEmpty()) {
//                throw new RuntimeException("Fail to store empty file");
                logger.error("Fail to store empty file");
                return "";
            }
            //check file is Image?
            if (!isImageFile(file)) {
//              throw new RuntimeException("You can only upload image file");
                logger.error("You can only upload image file");
                return "";
            }
            //file must be <= 5Mb
            float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
            if (fileSizeInMegabytes > 5.0f) {
//                throw new RuntimeException("File must be <= 5Mb");
                logger.error("File must be <= 5Mb");
                return "";
            }
            //file must be rename, boi vi khi ma gui len server chung ta phai luu file nay duoi dang ten khac, neu khong
            //se xay ra hien tuong trung file tren server lam file bi ghi de, xoa file cu
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename()); //lay duoi file
            String generatedFileName = UUID.randomUUID().toString().replace("-", "");
            generatedFileName = generatedFileName + "." + fileExtension;
            Path destinationFilePath = this.stogareFolder.resolve(Paths.get(generatedFileName)).normalize().toAbsolutePath();

            if (!destinationFilePath.getParent().equals(this.stogareFolder.toAbsolutePath())) {
//                throw new RuntimeException("Cannot store file outside directory");
                logger.error("Cannot store file outside directory");
                return "";
            }
            //neu ma co ton tai thi thay the, ghi de len
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;//Trong truong hop thanh cong
        } catch (IOException e) {
//            throw new RuntimeException("Fail to store file.", e);
//            e.printStackTrace();
            logger.error("The field file1 exceeds its maximum permitted size of 1048576 bytes.");
            return "";
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            //list all file in stogare folder
            //ham walk duyet ben trong folder voi do sau la 1, khong duyet cac chau, chat. Chi duyet con gan nhat

            /*CODE BEFORE DEBUG TO REMOVE ALL FILE HAVE `._` in path*/
//            return Files.walk(this.stogareFolder,1)
//                    .filter(path -> {
//                        ///boc trong khoi lenh(blocK) de loai bo cac path chua `.-` khong phu hop
//                        //before
////                        return !path.equals(this.stogareFolder);
//                        //after debug to remove file contain(`._`)
//                        return !path.equals(this.stogareFolder) && !path.toString().contains("._");
//                    })
//                    .map(this.stogareFolder::relativize);

            /*CODE AFTER DEGUG*/

            return Files.walk(this.stogareFolder, 1)
                    .filter(path -> !path.equals(this.stogareFolder) && !path.toString().contains("._"))
                    .map(this.stogareFolder::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load store files ", e);
        }
    }

    @Override
    public Stream<Path> loadFile(String fileName) {
        Path filePath = this.stogareFolder.resolve(fileName);
        if (Files.exists(filePath)) {
            // Kiểm tra xem tệp tồn tại trong thư mục lưu trữ hay không
            return Stream.of(filePath);
        } else {
            throw new RuntimeException("File not found: " + fileName);
        }
    }

    //Chuyen ma file thanh url
    @Override
    public byte[] readFileContent(String fileName) {
        try {
            Path file = stogareFolder.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            } else {
                throw new RuntimeException("Could not read file " + fileName);
            }

        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + fileName, e);
        }
    }

    @Override
    public void deleteAllFiles() {

    }


}
