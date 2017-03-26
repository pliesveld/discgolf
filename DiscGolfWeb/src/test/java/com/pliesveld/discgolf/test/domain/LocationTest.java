package com.pliesveld.discgolf.test.domain;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pliesveld.discgolf.common.domain.test.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class LocationTest {
    final static private Logger LOG = LogManager.getLogger();

    static abstract class GeoJsonPointMixin {
        GeoJsonPointMixin(@JsonProperty("longitude") double x, @JsonProperty("latitude") double y) {}
    }

    private ObjectMapper objectMapper = newObjectMapper();

    private static ObjectMapper newObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(GeoJsonPoint.class, GeoJsonPointMixin.class);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Test
    public void givenLocationJson_whenDeserialize_shouldConvertToObject() throws Exception {
        String locationStr = "{ \"location\": {\"latitude\":40.759686, \"longitude\":-73.985235} }";
        Location location = objectMapper.readValue(locationStr, Location.class);
        assertNotNull(location);
        assertNotNull(location.getLocation());
        LOG.debug(location);
    }
}
