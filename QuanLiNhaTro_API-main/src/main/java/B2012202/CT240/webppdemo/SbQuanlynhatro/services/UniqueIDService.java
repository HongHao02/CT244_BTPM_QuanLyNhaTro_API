package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.UniqueID;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.UniqueIDRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniqueIDService {
    @Autowired
    private UniqueIDRepository uniqueIDRepository;

    @Transactional
    public Integer getUniqueIDInteger() {
        UniqueID uniqueID = new UniqueID();
        UniqueID saved = uniqueIDRepository.save(uniqueID);
        return saved.getId();
    }
}
