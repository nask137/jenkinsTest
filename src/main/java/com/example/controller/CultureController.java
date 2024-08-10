package com.example.controller;


import com.example.context.BaseContext;
import com.example.pojo.Result;
import com.example.pojo.entity.Culture;
import com.example.service.ICultureService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 非遗文化 前端控制器
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@RestController
@Data
@RequestMapping("/culture")
public class CultureController {

    private final ICultureService cultureService;

    @PostMapping
    public Result<String> save(@RequestBody Culture culture) {
        culture.setLikes(0);
        culture.setUserId(BaseContext.getCurrentId());
        cultureService.save(culture);
        return Result.success("保存成功!");
    }

    @PutMapping
    public Result<String> update(@RequestBody Culture culture) {
        cultureService.updateById(culture);
        return Result.success("修改成功!");

    }

    @GetMapping("/{id}")
    public Result<Culture> getById(@PathVariable Long id) {
        return Result.success(cultureService.getById(id));
    }

    @GetMapping
    public Result<List<Culture>> getAll() {
        return Result.success(cultureService.list());
    }
    @DeleteMapping
    public Result<String> delete(@RequestParam Long id) {
        Culture one = cultureService.getById(id);
        if(!Objects.equals(one.getUserId(), BaseContext.getCurrentId()))return Result.error("权限不足!");
        cultureService.removeById(id);
        return Result.success("删除成功!");
    }

}
