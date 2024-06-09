package cn.edu.ecnu.conferencepartner.service.impl;

import cn.edu.ecnu.conferencepartner.common.dto.AccountDTO;
import cn.edu.ecnu.conferencepartner.common.dto.PasswordDTO;
import cn.edu.ecnu.conferencepartner.common.exception.BaseException;
import cn.edu.ecnu.conferencepartner.common.po.User;
import cn.edu.ecnu.conferencepartner.common.vo.AccountVO;
import cn.edu.ecnu.conferencepartner.mapper.UserMapper;
import cn.edu.ecnu.conferencepartner.service.IAccountService;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class AccountServiceImpl extends ServiceImpl<UserMapper, User> implements IAccountService {
    @Override
    public AccountVO queryById(Long userId) {
        User user = this.getById(userId);
        if (user == null) {
            throw new BaseException("User ID does not exist.");
        }
        AccountVO accountVO = BeanUtil.copyProperties(user, AccountVO.class);
        accountVO.setRegisterDate(user.getCreateTime().toLocalDate());
        return accountVO;
    }

    @Override
    public void changePassword(Long userId, PasswordDTO passwordDTO) {
        String oldPassword = passwordDTO.getOldPassword();
        String newPassword = passwordDTO.getNewPassword();

        if (!this.getById(userId).getPassword().equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))) {
            throw new BaseException("密码错误");
        }

        this.updateById(User.builder()
                        .id(userId)
                        .password(DigestUtils.md5DigestAsHex(newPassword.getBytes()))
                        .updateTime(LocalDateTime.now())
                        .build());
    }

    @Override
    public void modifyInfo(Long userId, AccountDTO accountDTO) {
        User user = BeanUtil.copyProperties(accountDTO, User.class);
        user.setId(userId);
        user.setUpdateTime(LocalDateTime.now());
        this.updateById(user);
    }
}
