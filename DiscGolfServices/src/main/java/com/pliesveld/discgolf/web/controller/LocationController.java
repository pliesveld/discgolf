package com.pliesveld.discgolf.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pliesveld.discgolf.domain.test.Location;
import com.pliesveld.discgolf.web.controller.base.AbstractDiscGolfController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/location")
public class LocationController extends AbstractDiscGolfController {
    final static private Logger LOG = LogManager.getLogger();

    @PostMapping
    public ResponseEntity<?> handleLocation(@RequestBody Location location) {
        LOG.debug("Location = {}", location);
        return ResponseEntity.ok(location);
    }
}
