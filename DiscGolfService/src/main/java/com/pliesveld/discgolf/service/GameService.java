package com.pliesveld.discgolf.service;

import org.springframework.validation.annotation.Validated;

import com.pliesveld.discgolf.persistence.domain.Course;
import com.pliesveld.discgolf.persistence.domain.Player;
import com.pliesveld.discgolf.common.exception.GameException;
import com.pliesveld.discgolf.common.exception.PlayerException;

import javax.validation.constraints.NotNull;

@Validated
public interface GameService {

    boolean isCurrentlyPlaying(@NotNull Player player) throws PlayerException;

    void beginGame(@NotNull Player player, @NotNull Course course) throws GameException;

    void abortGame(@NotNull Player player) throws GameException;

    void recordNextHole(@NotNull Player player, int strokes) throws GameException;

}
