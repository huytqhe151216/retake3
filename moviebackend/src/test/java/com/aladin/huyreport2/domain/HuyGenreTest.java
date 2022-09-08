package com.aladin.huyreport2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aladin.huyreport2.web.rest.TestUtil;

public class HuyGenreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HuyGenre.class);
        HuyGenre huyGenre1 = new HuyGenre();
        huyGenre1.setId(1L);
        HuyGenre huyGenre2 = new HuyGenre();
        huyGenre2.setId(huyGenre1.getId());
        assertThat(huyGenre1).isEqualTo(huyGenre2);
        huyGenre2.setId(2L);
        assertThat(huyGenre1).isNotEqualTo(huyGenre2);
        huyGenre1.setId(null);
        assertThat(huyGenre1).isNotEqualTo(huyGenre2);
    }
}
