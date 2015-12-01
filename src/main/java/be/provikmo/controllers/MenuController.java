/**
 * (c) 2015 ADMB. All rights reserved.
 */
package be.provikmo.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import be.admb.alm.registration.HazelcastRegister;
import be.admb.security.util.SecurityUtils;
import be.admb.sso.model.Userdetails;
import be.provikmo.model.IconMenuItem;
import be.provikmo.model.User;
import be.provikmo.util.MenuItemUtil;

/**
 * @author infglef
 *
 */
@Controller
public class MenuController {

	@Resource(name = "hazelcastClientRegister")
	private HazelcastRegister register;

	@RequestMapping("/sidemenu")
	public @ResponseBody List<IconMenuItem> getMenu() {
		return MenuItemUtil.registerApplicationMetaModelMenuItems(register);
	}

	@RequestMapping("/currentUser")
	public ResponseEntity<User> getCurrentUser() {
		return new ResponseEntity<User>(getCurrentUserName(), HttpStatus.OK);
	}

	private User getCurrentUserName() {
		User user = new User();
		Userdetails details = SecurityUtils.getUserdetails();
		if (details != null) {
			user.setFirstname(details.getFirstname());
			user.setLastname(details.getLastname());
		}
		return user;
	}
}
