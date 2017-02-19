package com.pliesveld.discgolf.web.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pliesveld.discgolf.exception.GameException;

@RestController
@RequestMapping("/fake")
public class FakeExceptionController {

    @GetMapping("/exception/game")
    public ResponseEntity fakeGameException() {

        if (1 == 1)
            throw new GameException("Fake Game exception.");

        return ResponseEntity.ok().build();
    }

}
