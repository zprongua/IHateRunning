package com.ihaterunning.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ihaterunning.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RaceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Race.class);
        Race race1 = new Race();
        race1.setId(1L);
        Race race2 = new Race();
        race2.setId(race1.getId());
        assertThat(race1).isEqualTo(race2);
        race2.setId(2L);
        assertThat(race1).isNotEqualTo(race2);
        race1.setId(null);
        assertThat(race1).isNotEqualTo(race2);
    }
}
