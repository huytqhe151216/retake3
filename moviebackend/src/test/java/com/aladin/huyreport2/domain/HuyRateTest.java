package com.aladin.huyreport2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aladin.huyreport2.web.rest.TestUtil;

public class HuyRateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HuyRate.class);
        HuyRate huyRate1 = new HuyRate();
        huyRate1.setId(1L);
        HuyRate huyRate2 = new HuyRate();
        huyRate2.setId(huyRate1.getId());
        assertThat(huyRate1).isEqualTo(huyRate2);
        huyRate2.setId(2L);
        assertThat(huyRate1).isNotEqualTo(huyRate2);
        huyRate1.setId(null);
        assertThat(huyRate1).isNotEqualTo(huyRate2);
    }
}
