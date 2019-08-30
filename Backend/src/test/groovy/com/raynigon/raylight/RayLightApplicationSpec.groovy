package com.raynigon.raylight

import org.springframework.beans.factory.annotation.Autowired
import testutils.AbstractITSpec


class RayLightApplicationSpec extends AbstractITSpec {

    @Autowired
    RayLightApplication application

    def 'test'() {
        expect:
        application != null
    }
}
