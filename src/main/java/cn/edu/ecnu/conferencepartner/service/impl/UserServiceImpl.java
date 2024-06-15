package cn.edu.ecnu.conferencepartner.service.impl;

import cn.edu.ecnu.conferencepartner.common.dto.UserLoginDTO;
import cn.edu.ecnu.conferencepartner.common.dto.UserPageQueryDTO;
import cn.edu.ecnu.conferencepartner.common.dto.UserRegisterDTO;
import cn.edu.ecnu.conferencepartner.common.enums.UserStatusType;
import cn.edu.ecnu.conferencepartner.common.exception.BusinessException;
import cn.edu.ecnu.conferencepartner.common.exception.DataNotFoundException;
import cn.edu.ecnu.conferencepartner.common.exception.ForbiddenException;
import cn.edu.ecnu.conferencepartner.common.po.User;
import cn.edu.ecnu.conferencepartner.common.utils.PageUtil;
import cn.edu.ecnu.conferencepartner.common.vo.PageVO;
import cn.edu.ecnu.conferencepartner.mapper.UserMapper;
import cn.edu.ecnu.conferencepartner.common.vo.UserVO;
import cn.edu.ecnu.conferencepartner.service.IUserService;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 龚奕玮
 * @since 2024-06-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserVO login(UserLoginDTO loginDTO) throws Exception {
        User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail, loginDTO.getEmail()));

        if (user == null) {
            //用户不存在
            throw new DataNotFoundException("用户不存在");
        }

        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes()))) {
            //密码错误
            throw new BusinessException("密码错误");
        }

        if (user.getStatus() == UserStatusType.FREEZED) {
            //用户被冻结
            throw new ForbiddenException("用户被冻结");
        }

        //修改最后登录时间
        this.updateById(User.builder()
                        .id(user.getId())
                        .lastLoginTime(LocalDateTime.now())
                        .build());

        return UserVO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .isAdmin(user.getIsAdmin())
                .build();
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        User user = BeanUtil.copyProperties(userRegisterDTO, User.class);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setStatus(UserStatusType.NORMAL);
        user.setIsAdmin(false);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setLastLoginTime(LocalDateTime.now());

        this.save(user);
    }

    @Override
    public UserVO queryById(Long id) {
        User user = this.getById(id);
        if (user == null) {
            throw new DataNotFoundException("用户不存在");
        }
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        userVO.setLastLoginDate(user.getLastLoginTime().toLocalDate());
        return userVO;
    }

    @Override
    public PageVO<UserVO> queryByConditionAndPage(UserPageQueryDTO query) {
        Page<User> page = PageUtil.toMybatisPlusPage(query);

        String email = query.getEmail();
        String name = query.getName();
        String institution = query.getInstitution();
        Integer status = query.getStatus();
        Boolean isAdmin = query.getIsAdmin();
        LocalDate lastLoginDateBegin = query.getLastLoginDateBegin();
        LocalDate lastLoginDateEnd = query.getLastLoginDateEnd();

        LocalDateTime lastLoginTimeBegin =
                lastLoginDateBegin != null ? LocalDateTime.of(lastLoginDateBegin, LocalTime.MIN) : null;
        LocalDateTime lastLoginTimeEnd =
                lastLoginDateEnd != null ? LocalDateTime.of(lastLoginDateEnd, LocalTime.MAX) : null;

        page = lambdaQuery()
                .like(email != null, User::getEmail, email)
                .like(name != null, User::getName, name)
                .like(institution != null, User::getInstitution, institution)
                .eq(status != null, User::getStatus, status)
                .eq(isAdmin != null, User::getIsAdmin, isAdmin)
                .gt(lastLoginTimeBegin != null, User::getLastLoginTime, lastLoginTimeBegin)
                .lt(lastLoginTimeEnd != null, User::getLastLoginTime, lastLoginTimeEnd)
                .page(page);

        return PageVO.of(page, user -> {
            UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
            userVO.setLastLoginDate(user.getLastLoginTime().toLocalDate());
            return userVO;
        });
    }

    @Override
    public void freezeById(Long id) {
        this.updateById(User.builder()
                .id(id)
                .status(UserStatusType.FREEZED)
                .build());
    }

    @Override
    public void unfreezeById(Long id) {
        this.updateById(User.builder()
                .id(id)
                .status(UserStatusType.NORMAL)
                .build());
    }
}
