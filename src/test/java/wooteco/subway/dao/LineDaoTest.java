package wooteco.subway.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wooteco.subway.domain.Line;

class LineDaoTest {

    @BeforeEach
    void setUp() {
        LineDao.deleteAllLines();
    }

    @DisplayName("라인을 저장한다.")
    @Test
    void lineSaveTest() {
        Line savedLine = LineDao.saveLine(new Line("신분당선", "bg-red-600"));
        assertThat(savedLine.getId()).isNotZero();
    }

    @DisplayName("전체 라인을 조회한다.")
    @Test
    void findAllLines() {
        LineDao.saveLine(new Line("신분당선", "bg-red-600"));
        assertThat(LineDao.findAllLines()).hasSize(1);
    }

    @DisplayName("특정 라인을 조회한다.")
    @Test
    void findById() {
        Line savedLine = LineDao.saveLine(new Line("신분당선", "bg-red-600"));
        Optional<Line> wrappedLine = LineDao.findById(savedLine.getId());
        assert(wrappedLine).isPresent();
        assertAll(
                () -> assertThat(wrappedLine.get().getName()).isEqualTo("신분당선"),
                () -> assertThat(wrappedLine.get().getColor()).isEqualTo("bg-red-600")
        );
    }
}
