package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.MessageDto;
import com.itsol.recruit.dto.UserPaginationDto;
import com.itsol.recruit.entity.AcademicLevel;
import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.AcademicLevelService;
import com.itsol.recruit.service.ProfilesService;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.web.vm.SearchUserVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.USER)
public class UserController {

   public final UserService userService ;

   public final ProfilesService profilesService;

   public final AcademicLevelService academicLevelService;

    public UserController(UserService userService, ProfilesService profilesService, AcademicLevelService academicLevelService) {
        this.userService = userService;
        this.profilesService = profilesService;
        this.academicLevelService = academicLevelService;
    }

    @PostMapping(value = "")
    public ResponseEntity<User> add(@RequestBody User user){
        return ResponseEntity.ok().body( userService.save(user));
    }

    @GetMapping(value = "")
    public ResponseEntity<List<User>> getAllUser(){
        return  ResponseEntity.ok().body( userService.getAllUser());
    }

    @GetMapping(value = "/profiles/academicLevels")
    public ResponseEntity<List<AcademicLevel>> getAllAcademicLevel(){
        return  ResponseEntity.ok().body( academicLevelService.getAllAcademicLevel());
    }
    @GetMapping(value = "/id={id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return  ResponseEntity.ok().body( userService.findById(id));
    }

    @GetMapping(value = "/profiles={id}")
    public ResponseEntity<Profiles> findProfilesByUserId(@PathVariable Long id){
        return  ResponseEntity.ok().body( profilesService.getProfileByUserId(id));
    }

    @GetMapping(value = "/username={username}")
    public ResponseEntity<User> findUserByUserName(@PathVariable String username){
        return  ResponseEntity.ok().body( userService.findUserByUserName(username));
    }


    @GetMapping(value = "/role={role}")
    public ResponseEntity<List<User>> findUserByURole(@PathVariable String role){
        return  ResponseEntity.ok().body( userService.findUserByRole(role));
    }

    @PutMapping(value="")
  public ResponseEntity<MessageDto> updateUser(@RequestBody User user){
    return ResponseEntity.ok().body(userService.updateUser(user));}



    @PostMapping("/searches")
    public UserPaginationDto find(@RequestBody SearchUserVM searchUserVM, @RequestParam(name = "page") int pageNumber,
                                  @RequestParam(name = "size") int pageSize) {
        return userService.find(searchUserVM, pageNumber, pageSize);
    }

    @PutMapping("/deactivate")
    public  ResponseEntity<Boolean>
    find(@RequestBody Long userId) {
        return   ResponseEntity.ok().body( userService.detective(userId));
    }
}
