package com.example.hungnt.controllers;
import com.example.hungnt.Services.CustomerService;
import com.example.hungnt.middleware.JwtUtils;
import com.example.hungnt.models.CustomersEntity;
import com.example.hungnt.utils.RequestLogin;
import com.example.hungnt.utils.ResponseLogin;
import com.example.hungnt.utils.ResponseLoginData;
import com.example.hungnt.utils.ResponseRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<ResponseRegister> register(@RequestBody @Valid CustomersEntity customer){
        Optional<CustomersEntity> cus = Optional.ofNullable(customerService.registerCustomer(customer));
        ResponseRegister res = new ResponseRegister();
        if(cus.isPresent()){
            res.setMessage("Đăng ký khách hàng thành công.");
            res.setStatusCode(HttpStatus.OK);
            res.setData(cus);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }else {
            res.setMessage("Đăng ký khách hàng thất bại.");
            res.setStatusCode(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<ResponseLogin> login(@RequestBody @Valid RequestLogin req){
        Optional<CustomersEntity> cus = Optional.ofNullable(customerService.login(req));
        ResponseLogin res = new ResponseLogin();
        if(cus.isPresent()){
            res.setStatusCode(HttpStatus.OK);
            res.setMessage("Đăng nhập thành công.");
            ResponseLoginData responseLoginData = new ResponseLoginData(cus.get().generateJWT(),cus.get().getEmail(),cus.get().getPassword(),cus.get().getName(),cus.get().getAddress());
            res.setData(responseLoginData);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }else{
            res.setStatusCode(HttpStatus.BAD_GATEWAY);
            res.setMessage("Sai thông tin tài khoản hoặc mật khẩu.");
            return new ResponseEntity<>(res,HttpStatus.BAD_GATEWAY);
        }

    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public boolean deleteCustomer(@PathVariable long id, HttpServletRequest request){
        JwtUtils jwtUtils = new JwtUtils();
        if(customerService.findCustomerById(jwtUtils.getTokenFromHeader(request)).isPresent()){
            return true;
        }else{
            return false;
        }


    }

}
