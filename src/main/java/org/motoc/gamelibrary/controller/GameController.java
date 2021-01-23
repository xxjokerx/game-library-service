package org.motoc.gamelibrary.controller;

import org.motoc.gamelibrary.business.GameService;
import org.motoc.gamelibrary.dto.GameDto;
import org.motoc.gamelibrary.dto.GameNameDto;
import org.motoc.gamelibrary.dto.GameOverviewDto;
import org.motoc.gamelibrary.mapper.GameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Defines game endpoints
 *
 * @author RouzicJ
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    private final GameService service;

    private final GameMapper mapper;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
        this.mapper = GameMapper.INSTANCE;
    }

    @GetMapping("/admin/games/count")
    Long count() {
        logger.trace("count called");
        return service.count();
    }

    @GetMapping("/admin/games/{id}")
    GameDto findById(@PathVariable Long id) {
        logger.trace("findById(id) called");
        return mapper.gameToDto(service.findById(id));
    }

    @GetMapping("/admin/games/page")
    Page<GameDto> findPage(Pageable pageable) {
        logger.trace("findPage(pageable) called");
        return mapper.pageToPageDto(service.findPage(pageable));
    }

    @GetMapping("/admin/games/page/overview")
    Page<GameOverviewDto> findPagedOverview(Pageable pageable,
                                            @RequestParam(name = "search", required = false) String keyword) {
        logger.trace("findPagedOverview(pageable) called");
        return service.findPagedOverview(pageable, keyword);
    }

    @PostMapping("/admin/games")
    GameDto save(@RequestBody @Valid GameDto gameDto) {
        logger.trace("save(game) called");
        return mapper.gameToDto(service.save(mapper.dtoToGame(gameDto)));
    }

    @GetMapping("/admin/games/names")
    List<GameNameDto> findNames() {
        logger.trace("findNames() called");
        return service.findNames();
    }
}
