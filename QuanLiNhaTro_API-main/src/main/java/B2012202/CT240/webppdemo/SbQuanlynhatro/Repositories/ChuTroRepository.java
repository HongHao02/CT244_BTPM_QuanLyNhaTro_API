package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChuTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChuTroID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//Khai báo Repository bằng class đại diện cho primary key
public interface ChuTroRepository extends JpaRepository<ChuTro, ChuTroID> {
    //    ChuTro findByMaChuTro(String maChuTro);error when query
    @Query("SELECT c FROM ChuTro c WHERE c.id.maChuTro = :maCT")
    ChuTro findByMaChuTro(@Param("maCT") String maCT);
}
