package com.example.hungnt.controllers;

import com.example.hungnt.Services.UserService;
import com.example.hungnt.models.UsersEntity;
import com.example.hungnt.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/ListUsers")
   public ModelAndView getAllUsers(@Param("keyword") String keyword){
        List<UsersEntity> users = userService.listUser(keyword);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ListUsers");
        mv.addObject("users",users);
       return mv;
   }
   @GetMapping("/user/{id}")
    public Optional<UsersEntity> getUserById(@PathVariable long id){
        return userService.getUserById(id);
   }
   @RequestMapping(value = "user",method = RequestMethod.POST)
    public Response addUser(@RequestBody @Valid UsersEntity user, BindingResult bindingResult ) throws Exception {
       String email = user.getEmail();
       System.out.println(email);
       UsersEntity u = new UsersEntity();
       u.setEmail(user.getEmail());
       u.setAddress(user.getAddress());
       u.setPhone(user.getPhone());
        userService.addUser(user);
        Response res = new Response();
        res.setStatus("200");
        res.setMessage("Thêm user thành công!");
       if (bindingResult.hasErrors()){
           throw new Exception("...");
       }
        return  res;
    }
   @RequestMapping(value = "user/{id}",method = RequestMethod.DELETE)
    public Response deleteUser(@PathVariable long id){
        Optional<UsersEntity> user = userService.getUserById(id);
        Response res = new Response();
        if(user.isPresent()){
            userService.deleteUserById(id);
            res.setStatus("200");
            res.setMessage("Delete success");
            return res;
        }
        res.setStatus("404");
        res.setMessage("User not found");
        return res;
   }
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public Response updateUser(@RequestBody UsersEntity user){
        Response res = new Response();
        if(userService.getUserById(user.getId()).isEmpty()){
            res.setMessage("Không tìm thấy user!");
            res.setStatus("404");
        }else{
            userService.updateUser(user);
            res.setMessage("Cập nhật user thành công.");
            res.setStatus("200");
        }
        return res;
    }

}
