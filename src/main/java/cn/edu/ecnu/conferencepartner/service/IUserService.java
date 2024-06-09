package cn.edu.ecnu.conferencepartner.service;

import cn.edu.ecnu.conferencepartner.common.dto.UserLoginDTO;
import cn.edu.ecnu.conferencepartner.common.dto.UserPageQueryDTO;
import cn.edu.ecnu.conferencepartner.common.dto.UserRegisterDTO;
import cn.edu.ecnu.conferencepartner.common.po.User;
import cn.edu.ecnu.conferencepartner.common.vo.PageVO;
import cn.edu.ecnu.conferencepartner.common.vo.UserVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 龚奕玮
 * @since 2024-06-04
 */
public interface IUserService extends IService<User> {

    UserVO login(UserLoginDTO loginDTO) throws Exception;

    void register(UserRegisterDTO userRegisterDTO);

    UserVO queryById(Long id);

    PageVO<UserVO> queryByConditionAndPage(UserPageQueryDTO query);

    void freezeById(Long id);

    void unfreezeById(Long id);
}
