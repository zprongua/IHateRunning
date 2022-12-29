package com.ihaterunning.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ihaterunning.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RunTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Run.class);
        Run run1 = new Run();
        run1.setId(1L);
        Run run2 = new Run();
        run2.setId(run1.getId());
        assertThat(run1).isEqualTo(run2);
        run2.setId(2L);
        assertThat(run1).isNotEqualTo(run2);
        run1.setId(null);
        assertThat(run1).isNotEqualTo(run2);
    }
}
