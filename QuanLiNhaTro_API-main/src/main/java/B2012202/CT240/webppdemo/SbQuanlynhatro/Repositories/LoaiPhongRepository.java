package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.LoaiNhaTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.LoaiPhong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoaiPhongRepository extends JpaRepository<LoaiPhong, Integer> {
    LoaiPhong findByTenLoaiP(String tenLoaiP);

    LoaiPhong findByIdLoai(String idLoai);
}
