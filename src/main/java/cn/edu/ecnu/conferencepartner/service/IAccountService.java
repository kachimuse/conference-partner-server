package cn.edu.ecnu.conferencepartner.service;

import cn.edu.ecnu.conferencepartner.common.dto.AccountDTO;
import cn.edu.ecnu.conferencepartner.common.dto.PasswordDTO;
import cn.edu.ecnu.conferencepartner.common.po.User;
import cn.edu.ecnu.conferencepartner.common.vo.AccountVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IAccountService extends IService<User> {


    AccountVO queryById(Long userId);

    void changePassword(Long userId, PasswordDTO passwordDTO);

    void modifyInfo(Long userId, AccountDTO accountDTO);
}
