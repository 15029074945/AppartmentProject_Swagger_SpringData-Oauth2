package com.spd.controller;

import com.spd.dto.UserInformationDTO;
import com.spd.dto.UserRegistrationDTO;
import com.spd.entity.User;
import com.spd.mapper.BookingMapper;
import com.spd.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@Api(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingMapper bookingMapper;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "registration", httpMethod = "POST")
    public void registration(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        if (userRegistrationDTO.isTermsChecked()) {

            User user = bookingMapper.map(userRegistrationDTO, User.class);

            userService.saveUser(user);

            // TODO
            // send email verification
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete user", httpMethod = "DELETE")
    public void /*ResponseEntity.BodyBuilder*/ deleteUser(@RequestParam int id) {

        boolean resultRequest = userService.deleteUser(id);

        /*if (resultRequest) {
            return ResponseEntity.ok();
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
    }

    //@PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "get user", httpMethod = "GET")
    public UserInformationDTO getUser(@RequestParam int id) {

        User user = userService.getById(id);
        UserInformationDTO userInformationDTO = bookingMapper.map(user, UserInformationDTO.class);

        return userInformationDTO;
    }

    @RequestMapping(value = "/{id}/{property}/{value}", method = RequestMethod.PUT)
    @ApiOperation(value = "change property user", httpMethod = "PUT")
    public void changeUser(@RequestParam int id, @RequestParam String property, @RequestParam String value) {
        User user = userService.getById(id);
        // TODO
        // replace switch
        switch (property) {
            case "email":
                user.setEmail(value);
                break;
            case "password":
                user.setPassword(value);
                break;
            case "firstName":
                user.setFirstName(value);
                break;
            case "lastName":
                user.setLastName(value);
                break;
            // TODO
            // add image
            default:
                return;
        }
        userService.updateUser(user);
    }

    @RequestMapping(value = "/{id}/emails/{email}", method = RequestMethod.POST)
    @ApiOperation(value = "add extra email", httpMethod = "POST")
    public void addExtraEmail(@RequestParam int id, @RequestParam String email) {
        userService.addExtraEmailByUser(id, email);
    }

    @RequestMapping(value = "/{id}/emails/{email}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete extra email", httpMethod = "DELETE")
    public void deleteExtraEmail(@RequestParam int id, @RequestParam String email) {
        userService.deleteExtraEmailByUser(id, email);
    }

    @RequestMapping(value = "/{id}/telephones/{telephone}", method = RequestMethod.POST)
    @ApiOperation(value = "add extra telephone", httpMethod = "POST")
    public void addExtraTelephone(@RequestParam int id, @RequestParam String telephone) {
        userService.addExtraTelephoneByUser(id, telephone);
    }

    @RequestMapping(value = "/{id}/telephones/{telephone}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete extra telephone", httpMethod = "DELETE")
    public void deleteExtraTelephone(@RequestParam int id, @RequestParam String telephone) {
        userService.deleteExtraTelephoneByUser(id, telephone);
    }

}
