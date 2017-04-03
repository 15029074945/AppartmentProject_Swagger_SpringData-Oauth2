package com.spd.controller;

import com.spd.bean.FavoriteBean;
import com.spd.service.FavoriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/favorites")
@Api(value = "users")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get favorites", httpMethod = "GET")
    public List<FavoriteBean> getFavorites(Authentication authentication) {
        return favoriteService.getFavorites(authentication.getName());
    }

    @RequestMapping(value = "/{idAnnouncement}", method = RequestMethod.POST)
    @ApiOperation(value = "create favorite", httpMethod = "POST")
    public FavoriteBean createFavorite(Authentication authentication, @PathVariable("idAnnouncement") Integer idAnnouncement) {
        return favoriteService.createFavorite(authentication.getName(), idAnnouncement);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete favorite", httpMethod = "DELETE")
    public void deleteFavorite(@PathVariable("id") Integer id) {
        favoriteService.deleteFavorite(id);
    }

}
