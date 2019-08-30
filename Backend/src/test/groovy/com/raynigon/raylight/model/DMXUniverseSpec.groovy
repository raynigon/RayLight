package com.raynigon.raylight.model

import spock.lang.Specification


class DMXUniverseSpec extends Specification {

    def 'Value to ArtNet Values'(){
        given:
        def universe = new DMXUniverse()
        universe.setValue(0, 1)
        universe.setValue(1, 255)
        universe.setValue(2, 128)
        universe.setValue(3, 129)

        when:
        byte[] result = universe.toArtNet();

        then:
        Integer.toBinaryString(result[0] & 0xFF) == "1"
        Integer.toBinaryString(result[1] & 0xFF) == "11111111"
        Integer.toBinaryString(result[2] & 0xFF) == "10000000"
        Integer.toBinaryString(result[3] & 0xFF) == "10000001"
    }
}
