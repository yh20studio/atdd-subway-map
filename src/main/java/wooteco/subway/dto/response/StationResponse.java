package wooteco.subway.dto.response;

import wooteco.subway.domain.Station;

public class StationResponse {

    private final Long id;
    private final String name;

    private StationResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static StationResponse of(Station station){
        return new StationResponse(station.getId(), station.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
