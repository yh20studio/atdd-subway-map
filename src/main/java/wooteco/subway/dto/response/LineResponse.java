package wooteco.subway.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import wooteco.subway.domain.Line;

public class LineResponse {

    private final Long id;
    private final String name;
    private final String color;
    private final List<StationResponse> stations;

    private LineResponse(Long id, String name, String color,
                         List<StationResponse> stations) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.stations = stations;
    }

    public static LineResponse of(Line line) {
        List<StationResponse> stationResponses = line.getStations().stream()
                .map(StationResponse::of)
                .collect(Collectors.toList());

        return new LineResponse(line.getId(), line.getName(), line.getColor(), stationResponses);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<StationResponse> getStations() {
        return new ArrayList<>(stations);
    }
}
