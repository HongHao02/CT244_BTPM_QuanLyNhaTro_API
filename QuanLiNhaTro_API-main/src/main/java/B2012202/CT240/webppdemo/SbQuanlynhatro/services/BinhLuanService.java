package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.BaiViet;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.BinhLuan;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.BaiVietRepository;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.BinhLuanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BinhLuanService {

    @Autowired
    private BinhLuanRepository binhLuanRepository;

    Logger logger = LoggerFactory.getLogger(BinhLuanService.class);

    @Async
    public CompletableFuture<List<BinhLuan>> findAllBinhLuan() {
        logger.info("get list of BinhLuan by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of BinhLuan {}", start);
        List<BinhLuan> dsBinhLuan = binhLuanRepository.findAll();
        return CompletableFuture.completedFuture(dsBinhLuan);
    }

    public BinhLuan saveBinhLuan(BinhLuan binhLuan) {
        return binhLuanRepository.save(binhLuan);
    }
}
