package com.pliesveld.discgolf.service;

import org.springframework.validation.annotation.Validated;

import com.pliesveld.discgolf.domain.Course;
import com.pliesveld.discgolf.domain.Player;
import com.pliesveld.discgolf.exception.GameException;
import com.pliesveld.discgolf.exception.PlayerException;

import javax.validation.constraints.NotNull;

@Validated
public interface GameService {

    boolean isCurrentlyPlaying(@NotNull Player player) throws PlayerException;

    void beginGame(@NotNull Player player, @NotNull Course course) throws GameException;

    void abortGame(@NotNull Player player) throws GameException;

    void recordNextHole(@NotNull Player player, int strokes) throws GameException;

}
