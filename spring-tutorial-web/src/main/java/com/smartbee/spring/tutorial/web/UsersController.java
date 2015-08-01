package com.smartbee.spring.tutorial.web;

import com.smartbee.spring.tutorial.domain.Users;
import com.smartbee.spring.tutorial.repositories.roles.RoleService;
import com.smartbee.spring.tutorial.repositories.users.UserService;
import com.smartbee.spring.tutorial.util.Pagination;
import com.smartbee.spring.tutorial.util.PagingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by galihlasahido on 10/4/14.
 */
@Controller
public class UsersController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('ROLE_USER_VIEW')")
    @RequestMapping(value = {"/user/list","/user"}, method = RequestMethod.GET)
    public String user(Model model, Pageable pageable) {
        String template = "user/user.list";
        Page<Users> page = userService.findAllUsers(pageable);

        if(page==null)
            return template;

        Pagination pagination = PagingUtil.getPagination(page, "/user/list");

        model.addAttribute("paginationData", pagination);
        model.addAttribute("data", userService.findAllUsers(pageable).getContent());
        return template;
    }

    @PreAuthorize("hasAuthority('ROLE_USER_INSERT')")
    @RequestMapping(value = "/user/insert", method = RequestMethod.GET)
    public String userInsert(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "user/user.insert";
    }

    @PostAuthorize("hasAuthority('ROLE_USER_INSERT')")
    @RequestMapping(value = "/user/action.insert", method = RequestMethod.POST)
    public String userActionInsert(@Valid @ModelAttribute Users dto, BindingResult bindingResult, Model model, Principal principal) {
        String url = "user/user.insert";

        model.addAttribute("roles", roleService.findAll());
        try {
            this.validate(dto, bindingResult);
            if(bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                List<FieldError> aList = bindingResult.getFieldErrors();
                for(FieldError bList : aList) {
                    errorMessage.append("<div>"+bList.getDefaultMessage()+"</div>");
                    url = "user/user.insert";
                }
                model.addAttribute("message", errorMessage.toString());
            } else {
                try {
                    ShaPasswordEncoder encoder = new ShaPasswordEncoder(512);
                    String hashedPass = encoder.encodePassword(dto.getPassword(), null);
                    dto.setPassword(hashedPass);
                    dto.setCreatedby(principal.getName());
                    userService.save(dto);
                    url = "redirect:/user/insert?status=true";
                } catch (Exception e) {
                    StringBuilder ste = new StringBuilder();
                    ste.append(e.getMessage());
                    for(StackTraceElement element : e.getStackTrace()) {
                        ste.append(element.toString()+"\n");
                    }
                    model.addAttribute("message", ste.toString());
                }
            }
        } catch (Exception e) {
            StringBuilder ste = new StringBuilder();
            ste.append(e.getMessage());
            for(StackTraceElement element : e.getStackTrace()) {
                ste.append(element.toString()+"\n");
            }
            model.addAttribute("message", ste.toString());
        }

        return url;
    }

    private void validate(Users account, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "usernameEmpty", "username isrequired");

        if(account.getPassword().isEmpty()) {
            errors.rejectValue("password",null, "password isrequired");
        } else {
            if(account.getPassword().length()<6) {
                errors.rejectValue("password",null, "password less than 6");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullname", "fullnameEmpty", "fullname isrequired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "idroleEmpty", "idrole isrequired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branch", "idbranchEmpty", "idbranch isrequired");

        Users users = userService.findUsersByUsername(account.getUsername().trim());
        if(users!=null) {
            errors.rejectValue("username",null, "username error.exist");
        }

    }

    private void validateEdit(Users account, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "usernameEmpty", "username isrequired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullname", "fullnameEmpty", "fullname isrequired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "idroleEmpty", "idrole isrequired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branch", "idbranchEmpty", "idbranch isrequired");

        Users users = userService.findUsersByUsernameAndNotThisId(account.getUsername().trim(), account.getId());
        if(users!=null) {
            errors.rejectValue("username",null, "username exist");
        }

    }


    @PreAuthorize("hasAuthority('ROLE_USER_EDIT')")
    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
    public String userEdit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("data", userService.findUsersById(id));
        return "user/user.edit";
    }

    @PostAuthorize("hasAuthority('ROLE_USER_EDIT')")
    @RequestMapping(value = "/user/action.edit/{id}", method = RequestMethod.POST)
    public String userActionEdit(@PathVariable("id") Long id, @Valid @ModelAttribute Users dto, BindingResult bindingResult, Model model) {
        String url = "user/user.edit";

        model.addAttribute("roles", roleService.findAll());
        try {
            this.validateEdit(dto, bindingResult);
            if(bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                List<FieldError> aList = bindingResult.getFieldErrors();
                for(FieldError bList : aList) {
                    errorMessage.append("<div>"+bList.getDefaultMessage()+"</div>");
                }
                model.addAttribute("message", errorMessage.toString());
            } else {
                try {
                    userService.updateProfile(dto);
                    url = "redirect:/user/edit/"+id+"?status=true";
                } catch (Exception e) {
                    StringBuilder ste = new StringBuilder();
                    ste.append(e.getMessage());
                    for(StackTraceElement element : e.getStackTrace()) {
                        ste.append(element.toString()+"\n");
                    }
                    model.addAttribute("message", ste.toString());
                }
            }
        } catch (Exception e) {
            StringBuilder ste = new StringBuilder();
            ste.append(e.getMessage());
            for(StackTraceElement element : e.getStackTrace()) {
                ste.append(element.toString()+"\n");
            }
            model.addAttribute("message", ste.toString());
        }

        return url;
    }

    @PreAuthorize("hasAuthority('ROLE_USER_DELETE')")
    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    public String userDelete(@PathVariable("id") Long id) {
        String url = "redirect:/user/list";
        Users users = new Users();
        users.setId(id);
        userService.delete(users);
        return url;
    }

    @PreAuthorize("hasAuthority('ROLE_USER_SEARCH')")
    @RequestMapping(value = "/user/search", method = RequestMethod.GET)
    public String userSearch(Model model, Pageable pageable, HttpServletRequest request) {
        String template = "user/user.search";

        Page<Users> page = userService.searchUsers(pageable, request.getParameter("mode"), request.getParameter("value"));

        if(page==null)
            return template;

        Pagination pagination = PagingUtil.getPagination(page, "/user/search");

        model.addAttribute("paginationData", pagination);
        model.addAttribute("data", userService.searchUsers(pageable, request.getParameter("mode"), request.getParameter("value")).getContent());

        return template;
    }
}
