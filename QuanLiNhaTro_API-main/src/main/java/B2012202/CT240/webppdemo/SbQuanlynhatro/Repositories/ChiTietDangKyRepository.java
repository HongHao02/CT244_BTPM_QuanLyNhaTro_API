package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChiTietDangKy;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChiTietDangKyID;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.PhongTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChiTietDangKyRepository extends JpaRepository<ChiTietDangKy, ChiTietDangKyID> {
    @Query("SELECT ctdk FROM ChiTietDangKy ctdk WHERE ctdk.id.sttPhieu = :sttPhieu AND ctdk.id.idNhaTro = :idNhaTro AND ctdk.id.sttLau = :sttLau AND ctdk.id.sttDay = :sttDay AND ctdk.id.sttPhong = :sttPhong")
    ChiTietDangKy findChiTietDangKyByAllInfo(@Param("sttPhieu") Integer sttPhieu,
                                             @Param("idNhaTro") Integer idNhaTro,
                                             @Param("sttLau") Integer sttLau,
                                             @Param("sttDay") Integer sttDay,
                                             @Param("sttPhong") Integer sttPhong);
}
