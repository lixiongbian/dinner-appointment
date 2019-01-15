package com.appointment.dinner.user.controller;

import com.appointment.dinner.exception.LogicalVerificationException;
import com.appointment.dinner.message.R;
import com.appointment.dinner.user.model.SysUser;
import com.appointment.dinner.user.service.TestService;
import com.appointment.dinner.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author Tony
 * @Time 2019/1/15
 */
@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/v1/{userId}")
    public R<SysUser> getUserById(@PathVariable("userId")Long userId){
        return new R<>(testService.getUserById(userId));
    }

    @GetMapping("/v1/list")
    public R<List<SysUser>> list(){
        return new R<>(testService.getUserList());
    }

    @GetMapping("/v1/pages")
    public R<PageVO<SysUser>> pages(@RequestParam(value = "offset",defaultValue = "1")int offset,
                                    @RequestParam(value = "limit",defaultValue = "10")int limit){
        return new R<>(new PageVO<>(testService.getUserPages(offset,limit)));
    }

    @PutMapping("/v1")
    public R<String> updateUser(@Valid @RequestBody SysUser sysUser){
        int i = testService.updateUser(sysUser);
        if(i>0){
            return new R<>("更新成功");
        }else{
            return new R<>(new LogicalVerificationException("更新失败"));
        }
    }

    @DeleteMapping("/v1/{userId}")
    public R<String> deleteUser(@PathVariable("userId")Long userId){
        int i = testService.deleteUserById(userId);
        if(i>0){
            return new R<>("删除成功");
        }else{
            return new R<>(new LogicalVerificationException("删除失败"));
        }
    }



}
