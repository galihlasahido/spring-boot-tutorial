package com.smartbee.spring.tutorial.web;

import com.smartbee.spring.tutorial.domain.Permission;
import com.smartbee.spring.tutorial.repositories.permission.PermissionService;
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
public class PermissionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private PermissionService permissionService;

    @PreAuthorize("hasAuthority('ROLE_PERMISSION_VIEW')")
    @RequestMapping(value = {"/role/permission/list","/role/permission"}, method = RequestMethod.GET)
    public String user(Model model, Pageable pageable) {
        String template = "permission/permission.list";
        Page<Permission> page = permissionService.findAll(pageable);

        if(page==null)
            return template;

        Pagination pagination = PagingUtil.getPagination(page, "/role/permission/list");

        model.addAttribute("paginationData", pagination);
        model.addAttribute("data", permissionService.findAll(pageable).getContent());
        return template;
    }

    @PreAuthorize("hasAuthority('ROLE_PERMISSION_INSERT')")
    @RequestMapping(value = "/role/permission/insert", method = RequestMethod.GET)
    public String userInsert(Model model) {
        return "permission/permission.insert";
    }

    @PostAuthorize("hasAuthority('ROLE_PERMISSION_INSERT')")
    @RequestMapping(value = "/role/permission/action.insert", method = RequestMethod.POST)
    public String userActionInsert(@Valid @ModelAttribute Permission dto, BindingResult bindingResult, Model model) {
        String url = "permission/permission.insert";

        try {
            this.validate(dto, bindingResult);
            if(bindingResult.hasErrors()) {
                StringBuffer errorMessage = new StringBuffer();
                List<FieldError> aList = bindingResult.getFieldErrors();
                for(FieldError bList : aList) {
                    errorMessage.append("<div>"+bList.getDefaultMessage()+"</div>");
                }
                model.addAttribute("message", errorMessage.toString());
            } else {
                try {
                    permissionService.save(dto);
                    url = "redirect:/role/permission/insert?status=true";
                } catch (Exception e) {
                    model.addAttribute("message", e.toString());
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", e.toString());
        }

        return url;
    }

    private void validate(Permission account, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "idEmpty", "ID isrequired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "label", "labelEmpty","label is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "valueEmpty", "value is required");

        Permission label = permissionService.findPermissionByLabel(account.getLabel());
        if (label != null) {
            errors.rejectValue("label", null, "label exist");
        }

        Permission value = permissionService.findPermissionByValue(account.getValue());
        if (value != null) {
            errors.rejectValue("value", null, "value exist");
        }
    }

    private void validateEdit(Permission account, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "idEmpty", "ID isrequired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "label", "labelEmpty","label is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "valueEmpty", "value is required");

        Permission value = permissionService.findPermissionByValueAndNotThisId(account.getValue(), account.getId());
        if(value!=null) {
            errors.rejectValue("value", null, "value exist");
        }



    }


    @PreAuthorize("hasAuthority('ROLE_PERMISSION_EDIT')")
    @RequestMapping(value = "/role/permission/edit/{id}", method = RequestMethod.GET)
    public String userEdit(@PathVariable("id") String id, Model model) {
        model.addAttribute("data", permissionService.findPermissionById(id));
        return "permission/permission.edit";
    }

    @PreAuthorize("hasAuthority('ROLE_PERMISSION_EDIT')")
    @RequestMapping(value = "/role/permission/action.edit/{id}", method = RequestMethod.POST)
    public String userActionEdit(@PathVariable("id") String id, @Valid @ModelAttribute Permission dto, BindingResult bindingResult, Model model) {
        String url = "permission/permission.edit";

        try {
            this.validateEdit(dto, bindingResult);
            if(bindingResult.hasErrors()) {
                StringBuffer errorMessage = new StringBuffer();
                List<FieldError> aList = bindingResult.getFieldErrors();
                for(FieldError bList : aList) {
                    errorMessage.append("<div>"+bList.getDefaultMessage()+"</div>");
                }
                model.addAttribute("message", errorMessage.toString());
            } else {
                try {
                    permissionService.save(dto);
                    url = "redirect:/role/permission/edit/"+dto.getId()+"?status=true";
                } catch (Exception e) {
                    model.addAttribute("message", e.toString());
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", e.toString());
        }

        return url;
    }

    @PreAuthorize("hasAuthority('ROLE_PERMISSION_DELETE')")
    @RequestMapping(value = "/role/permission/delete/{id}", method = RequestMethod.GET)
    public String userDelete(@PathVariable("id") String id, final RedirectAttributes redirectAttributes) {
        String url = "redirect:/role/permission/list";
        Permission data = new Permission();
        data.setId(id);
        permissionService.delete(data);
        return url;
    }

    @PreAuthorize("hasAuthority('ROLE_PERMISSION_SEARCH')")
    @RequestMapping(value = "/role/permission/search", method = RequestMethod.GET)
    public String userSearch(Model model, Pageable pageable, HttpServletRequest request) {
        String template = "permission/permission.list";
        Page<Permission> page = permissionService.searchPermission(pageable, request.getParameter("mode"), request.getParameter("value"));

        if(page==null)
            return template;

        Pagination pagination = PagingUtil.getPagination(page, "/role/permission/search");

        model.addAttribute("paginationData", pagination);
        model.addAttribute("data", permissionService.searchPermission(pageable, request.getParameter("mode"), request.getParameter("value")).getContent());

        return template;
    }
}
