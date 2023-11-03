package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.BaiViet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaiVietRepository extends JpaRepository<BaiViet, Integer> {
    BaiViet findByIdBaiViet(Integer idBaiViet);
}
