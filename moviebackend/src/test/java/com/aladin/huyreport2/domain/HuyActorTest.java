package com.aladin.huyreport2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aladin.huyreport2.web.rest.TestUtil;

public class HuyActorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HuyActor.class);
        HuyActor huyActor1 = new HuyActor();
        huyActor1.setId(1L);
        HuyActor huyActor2 = new HuyActor();
        huyActor2.setId(huyActor1.getId());
        assertThat(huyActor1).isEqualTo(huyActor2);
        huyActor2.setId(2L);
        assertThat(huyActor1).isNotEqualTo(huyActor2);
        huyActor1.setId(null);
        assertThat(huyActor1).isNotEqualTo(huyActor2);
    }
}
