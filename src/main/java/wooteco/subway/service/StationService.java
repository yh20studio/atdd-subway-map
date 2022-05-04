package wooteco.subway.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import wooteco.subway.dao.StationDao;
import wooteco.subway.domain.Station;
import wooteco.subway.dto.request.StationRequest;
import wooteco.subway.dto.response.StationResponse;

@Service
public class StationService {

    private static final String DUPLICATE_NAME_ERROR = "이미 같은 이름의 지하철역이 존재합니다.";
    private static final String NOT_EXIST_ERROR = "해당 지하철역이 존재하지 않습니다.";

    private final StationDao stationDao;

    public StationService(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    public StationResponse createStation(StationRequest stationRequest) {
        Station station = new Station(stationRequest.getName());
        Optional<Station> wrappedStation = stationDao.findByName(stationRequest.getName());
        if (wrappedStation.isPresent()) {
            throw new IllegalArgumentException(DUPLICATE_NAME_ERROR);
        }
        Station newStation = stationDao.save(station);
        return new StationResponse(newStation.getId(), newStation.getName());
    }

    public List<StationResponse> findAllStations() {
        List<Station> stations = stationDao.findAll();
        return stations.stream()
                .map(it -> new StationResponse(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }

    public void deleteStation(Long id){
        Optional<Station> wrappedStation = stationDao.findById(id);
        if (wrappedStation.isEmpty()) {
            throw new IllegalArgumentException(NOT_EXIST_ERROR);
        }
        stationDao.deleteById(id);
    }
}
