package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Lau;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.PostFiles;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.LauRepository;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.PostFilesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PostFilesService {
    @Autowired
    private PostFilesRepository postFilesRepository;

    Logger logger = LoggerFactory.getLogger(PostFilesService.class);

    @Async
    public CompletableFuture<List<PostFiles>> findAllPostFiles() {
        logger.info("get list of PostFiles by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of PostFile {}", start);
        List<PostFiles> postFilesList = postFilesRepository.findAll();
        return CompletableFuture.completedFuture(postFilesList);
    }

//    public PostFiles findLauBySttLauAndIdNhaTro(Integer sttLau, Integer idNhaTro) {
//        return lauRepository.findBySTTLauAndIdNhaTro(sttLau, idNhaTro);
//    }

//    public List<Lau> findAllLauByIdNhaTro(Integer idNhaTro) {
//        return lauRepository.findAllLauByIdNhaTro(idNhaTro);
//    }

    public PostFiles savePostFiles(PostFiles postFiles) {
        return postFilesRepository.save(postFiles);
    }
}
