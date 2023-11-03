package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Day;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.DayID;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Lau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DayRepository extends JpaRepository<Day, DayID> {
    @Query("SELECT d FROM Day d WHERE d.id.sttLau = :sttLau AND d.id.idNhaTro = :idNhaTro AND d.id.sttDay = :sttDay")
    Day findDayByAllInfo(@Param("sttLau") Integer sttLau, @Param("idNhaTro") Integer idNhaTro, @Param("sttDay") Integer sttDay);
}
