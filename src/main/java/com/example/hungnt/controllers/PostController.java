package com.example.hungnt.controllers;
import com.example.hungnt.Services.CustomerService;
import com.example.hungnt.Services.PostService;
import com.example.hungnt.middleware.JwtUtils;
import com.example.hungnt.models.CustomersEntity;
import com.example.hungnt.models.PostsEntity;
import com.example.hungnt.utils.ResponsePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<List<PostsEntity>> getPost(){

        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<ResponsePost> addPost(@RequestBody @Valid PostsEntity postsEntity , HttpServletRequest request){
        JwtUtils jwtUtils = new JwtUtils();
        ResponsePost res = new ResponsePost();
        long id = jwtUtils.getTokenFromHeader(request);
        CustomersEntity customer = customerService.findCustomerById(id).orElse(null);
        if (customer == null) {
            res.setStatus(HttpStatus.UNAUTHORIZED);
            res.setMessage("Xác thực thất bại vui lòng kiểm tra đăng nhập.");
            return  new ResponseEntity<>(res,HttpStatus.UNAUTHORIZED);
        }
        postsEntity.setCustomer(customer);
        PostsEntity post = postService.addPost(postsEntity);
        res.setStatus(HttpStatus.OK);
        res.setMessage("Đăng bài thành công.");
        res.setData(post);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

}
