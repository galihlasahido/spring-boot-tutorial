package com.smartbee.spring.tutorial.web;

import com.smartbee.spring.tutorial.domain.Roles;
import com.smartbee.spring.tutorial.repositories.roles.RoleService;
import com.smartbee.spring.tutorial.util.Pagination;
import com.smartbee.spring.tutorial.util.PagingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by galihlasahido on 5/3/14.
 */
@Controller
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('ROLE_ROLE_VIEW')")
    @RequestMapping(value = {"/role/list","/role"}, method = RequestMethod.GET)
    public String role(Model model, Pageable pageable) {
        String template = "role/role.list";
        Page<Roles> page = roleService.findAll(pageable);

        if(page==null)
            return template;

        Pagination pagination = PagingUtil.getPagination(page, "/role/list");

        model.addAttribute("paginationData", pagination);
        model.addAttribute("data", roleService.findAll(pageable).getContent());
        return template;
    }

    @PreAuthorize("hasAuthority('ROLE_ROLE_INSERT')")
    @RequestMapping(value = "/role/insert", method = RequestMethod.GET)
    public String roleInsert(Model model) {
        return "role/role.insert";
    }

    @PostAuthorize("hasAuthority('ROLE_ROLE_INSERT')")
    @RequestMapping(value = "/role/action.insert", method = RequestMethod.POST)
    public String roleActionInsert(@Valid @ModelAttribute Roles dto, BindingResult bindingResult, Model model) {
        String url = "role/role.insert";

        try {
            this.validate(dto, bindingResult);
            if(bindingResult.hasErrors()) {
                StringBuffer errorMessage = new StringBuffer();
                List<FieldError> aList = bindingResult.getFieldErrors();
                for(FieldError bList : aList) {
                    errorMessage.append("<div>"+bList.getDefaultMessage()+"</div>");
                    url = "role/role.insert";
                }
                model.addAttribute("message", errorMessage.toString());
            } else {
                try {
                    roleService.save(dto);
                    url = "redirect:/role/insert?status=true";
                } catch (Exception e) {
                    model.addAttribute("message", e.toString());
                    url = "role/role.insert";
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", e.toString());
            url = "role/role.insert";
        }

        return url;
    }

    private void validate(Roles account, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "idEmpty", "idrole isrequired");
        Roles roles = roleService.findRolesById(account.getId().trim());
        if(roles!=null) {
            errors.rejectValue("id",null, "idrole exist");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "descriptionEmpty", "description isrequired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "nameEmpty", "name isrequired");


    }

    private void validateEdit(Roles account, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "idEmpty", "idrole isrequired");
        Roles roles = roleService.findRolesByNameAndNotThisId(account.getId().trim(), account.getId().trim());
        if(roles!=null) {
            errors.rejectValue("id",null, "idrole exist");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "descriptionEmpty", "description isrequired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "nameEmpty", "name isrequired");

    }


    @PreAuthorize("hasAuthority('ROLE_ROLE_EDIT')")
    @RequestMapping(value = "/role/edit/{id}", method = RequestMethod.GET)
    public String roleEdit(@PathVariable("id") String id, Model model) {
        model.addAttribute("data", roleService.findRolesById(id));
        return "role/role.edit";
    }

    @PreAuthorize("hasAuthority('ROLE_ROLE_EDIT')")
    @RequestMapping(value = "/role/action.edit/{id}", method = RequestMethod.POST)
    public String roleActionEdit(@PathVariable("id") String id, @Valid @ModelAttribute Roles dto, BindingResult bindingResult, Model model) {
        String url = "role/role.edit";

        try {
            this.validateEdit(dto, bindingResult);
            if(bindingResult.hasErrors()) {
                StringBuffer errorMessage = new StringBuffer();
                List<FieldError> aList = bindingResult.getFieldErrors();
                for(FieldError bList : aList) {
                    errorMessage.append("<div>"+bList.getDefaultMessage()+"</div>");
                    url = "role/role.edit";
                }
                model.addAttribute("message", errorMessage.toString());
            } else {
                try {
                    roleService.save(dto);
                    url = "redirect:/role/edit/"+id+"?status=true";
                } catch (Exception e) {
                    model.addAttribute("message", e.toString());
                    url = "role/role.edit";
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", e.toString());
            url = "role/role.edit";
        }

        return url;
    }

    @PreAuthorize("hasAuthority('ROLE_ROLE_DELETE')")
    @RequestMapping(value = "/role/delete/{id}", method = RequestMethod.GET)
    public String roleDelete(@PathVariable("id") String id, final RedirectAttributes redirectAttributes) {
        String url = "redirect:/role/list";
        Roles data = new Roles();
        data.setId(id);
        roleService.delete(data);
        redirectAttributes.addFlashAttribute("flashmessage", "role is delete");
        return url;
    }

    @PreAuthorize("hasAuthority('ROLE_ROLE_SEARCH')")
    @RequestMapping(value = "/role/search", method = RequestMethod.GET)
    public String roleSearch(Model model, Pageable pageable, HttpServletRequest request) {
        String template = "role/role.search";

        Page<Roles> page = roleService.searchRoles(pageable, request.getParameter("mode"), request.getParameter("value"));

        if(page==null)
            return template;

        Pagination pagination = PagingUtil.getPagination(page, "/role/search");

        model.addAttribute("paginationData", pagination);
        model.addAttribute("data", roleService.searchRoles(pageable, request.getParameter("mode"), request.getParameter("value")).getContent());

        return template;
    }
}
