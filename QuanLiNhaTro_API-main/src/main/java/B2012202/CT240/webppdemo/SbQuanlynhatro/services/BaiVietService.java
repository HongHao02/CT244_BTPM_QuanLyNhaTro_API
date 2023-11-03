package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.BaiViet;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChuTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.BaiVietRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BaiVietService {
    @Autowired
    private BaiVietRepository baiVietRepository;

    Logger logger = LoggerFactory.getLogger(BaiVietService.class);

    @Async
    public CompletableFuture<List<BaiViet>> findAllBaiViet() {
        logger.info("get list of BaiViet by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of BaiViet {}", start);
        List<BaiViet> dsBaiViet = baiVietRepository.findAll();
        return CompletableFuture.completedFuture(dsBaiViet);
    }

    public BaiViet findBaiVietByIdBaiViet(Integer idBaiViet) {
        return baiVietRepository.findByIdBaiViet(idBaiViet);
    }

    public BaiViet saveBaiViet(BaiViet baiViet) {
        return baiVietRepository.save(baiViet);
    }
}
