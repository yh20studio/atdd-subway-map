package wooteco.subway.domain.station;

import java.util.Objects;

public class Station {

    private long id;
    private String name;

    public Station(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(String name) {
        this(0, name);
    }

    public boolean hasSameName(String name) {
        return this.name.equals(name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return id == station.id && name.equals(station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

