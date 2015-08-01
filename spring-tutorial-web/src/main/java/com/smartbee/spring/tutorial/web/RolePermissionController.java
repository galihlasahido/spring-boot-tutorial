package com.smartbee.spring.tutorial.web;

import com.google.gson.Gson;
import com.smartbee.spring.tutorial.domain.Permission;
import com.smartbee.spring.tutorial.domain.Roles;
import com.smartbee.spring.tutorial.domain.RolesPermission;
import com.smartbee.spring.tutorial.repositories.permission.PermissionService;
import com.smartbee.spring.tutorial.repositories.roles.RoleService;
import com.smartbee.spring.tutorial.repositories.rolespermissions.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by galihlasahido on 10/8/14.
 */
@Controller
public class RolePermissionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;


    @PreAuthorize("hasAuthority('ROLE_ROLE_PERMISSION_VIEW')")
    @RequestMapping(value = {"/role/rolepermission/list","/role/rolepermission"}, method = RequestMethod.GET)
    public String user(Model model) {
        model.addAttribute("role", roleService.findAll());
        model.addAttribute("permission", permissionService.findAll());
        return "rolepermission/rolepermission.list";
    }

    @PreAuthorize("hasAuthority('ROLE_ROLE_PERMISSION_INSERT')")
    @RequestMapping(value = "/role/rolepermission/insert", method = RequestMethod.GET)
    public String userInsert(Model model) {
        return "rolepermission/rolepermission.insert";
    }

    @PostAuthorize("hasAuthority('ROLE_ROLE_PERMISSION_INSERT')")
    @RequestMapping(value = "/role/rolepermission/action.insert", method = RequestMethod.POST)
    public String userActionInsert(@Valid @ModelAttribute RolesPermission dto, BindingResult bindingResult, Model model) {
        String url = "rolepermission/rolepermission.insert";

        try {
            this.validate(dto, bindingResult);
            if(bindingResult.hasErrors()) {
                StringBuffer errorMessage = new StringBuffer();
                List<FieldError> aList = bindingResult.getFieldErrors();
                for(FieldError bList : aList) {
                    errorMessage.append("<div>"+bList.getDefaultMessage()+"</div>");
                    url = "rolepermission/rolepermission.insert";
                }
                model.addAttribute("message", errorMessage.toString());
            } else {
                try {
                    url = "redirect:/rolepermission/insert?status=true";
                } catch (Exception e) {
                    model.addAttribute("message", e.toString());
                    url = "rolepermission/rolepermission.insert";
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", e.toString());
            url = "rolepermission/rolepermission.insert";
        }

        return url;
    }

    private void validate(RolesPermission account, Errors errors) {
    }


    @PreAuthorize("hasAuthority('ROLE_ROLE_PERMISSION_EDIT')")
    @RequestMapping(value = "/role/rolepermission/edit/{id}", method = RequestMethod.GET)
    public String userEdit(@PathVariable("id") String id, Model model) {
        model.addAttribute("roles", roleService.findRolesById(id));
        return "rolepermission/rolepermission.edit";
    }

    @PreAuthorize("hasAuthority('ROLE_ROLE_PERMISSION_EDIT')")
    @RequestMapping(value = "/role/rolepermission/action.edit/{id}", method = RequestMethod.POST)
    public String userActionEdit(@PathVariable("id") Long id, @Valid @ModelAttribute RolesPermission dto, BindingResult bindingResult, Model model) {
        String url = "rolepermission/rolepermission.edit";

        try {
            this.validate(dto, bindingResult);
            if(bindingResult.hasErrors()) {
                StringBuffer errorMessage = new StringBuffer();
                List<FieldError> aList = bindingResult.getFieldErrors();
                for(FieldError bList : aList) {
                    errorMessage.append("<div>"+bList.getDefaultMessage()+"</div>");
                    url = "rolepermission/rolepermission.edit";
                }
                model.addAttribute("message", errorMessage.toString());
            } else {
                try {
                    url = "redirect:/rolepermission/edit?status=true";
                } catch (Exception e) {
                    model.addAttribute("message", e.toString());
                    url = "rolepermission/rolepermission.edit";
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", e.toString());
            url = "rolepermission/rolepermission.edit";
        }

        return url;
    }

    @PreAuthorize("hasAuthority('ROLE_ROLE_PERMISSION_DELETE')")
    @RequestMapping(value = "/role/rolepermission/delete/{id}", method = RequestMethod.GET)
    public String userDelete(@PathVariable("id") String id, final RedirectAttributes redirectAttributes) {
        String url = "redirect:/rolepermission/list";
        Roles data = new Roles();
        data.setId(id);
        roleService.delete(data);
        redirectAttributes.addFlashAttribute("flashmessage", "role permission is delete");
        return url;
    }

    @PreAuthorize("hasAuthority('ROLE_ROLE_PERMISSION_SEARCH')")
    @RequestMapping(value = "/role/rolepermission/search", method = RequestMethod.GET)
    public String userSearch(Model model, Pageable pageable, HttpServletRequest request) {
        String template = "rolepermission/rolepermission.list";
        return template;
    }

    @RequestMapping(value = "/role/rolepermission/permissionJSON", method = RequestMethod.GET)
    @ResponseBody
    public String jsonPermission() {
        List<Permission> list = permissionService.findAll();
        String[] strings = new String[list.size()];
        int i = 0;
        for(Permission row : list) {
            strings[i] = row.getId();
            i++;
        }
        Gson gson = new Gson();
        return gson.toJson(strings);
    }

    @RequestMapping(value = "/role/rolepermission/roleJSON", method = RequestMethod.GET)
    @ResponseBody
    public String jsonRole() {
        List<Roles> list = roleService.findAll();
        String[] strings = new String[list.size()];
        int i = 0;
        for(Roles row : list) {
            strings[i] = row.getId();
            i++;
        }
        Gson gson = new Gson();
        return gson.toJson(strings);
    }

    @RequestMapping(value = "/role/rolepermission/rolePermissionJSON", method = RequestMethod.GET)
    @ResponseBody
    public String jsonRolePermission() {
        List<RolesPermission> list = rolePermissionService.findAll();
        String[][] strings = new String[list.size()][3];
        int i = 0;
        for(RolesPermission row : list) {
            strings[i][0] = row.getPermission().getId();
            strings[i][1] = row.getRoles().getId();
            strings[i][2] = String.valueOf(row.getId());
            i++;
        }
        Gson gson = new Gson();
        return gson.toJson(strings);
    }

    @RequestMapping(value = "/role/rolepermission/updateRolePermissionJSON", method = RequestMethod.POST)
    @ResponseBody
    public String updateJsonRolePermission(HttpServletRequest request) {
        String[] data = request.getParameter("data").split("#");
        Boolean status = Boolean.valueOf(request.getParameter("status"));
        String id = null;
        RolesPermission rolesPermission = new RolesPermission();
        Permission permission = new Permission();
        Roles roles = new Roles();

        if(data.length>1) {
            permission.setId(data[0]);
            roles.setId(data[1]);
            rolesPermission.setPermission(permission);
            rolesPermission.setRoles(roles);

            if(status==true) {
                RolesPermission rp = rolePermissionService.save(rolesPermission);
                id = String.valueOf(rp.getId());
            } else {
                rolesPermission.setId(Long.valueOf(data[2]));
                rolePermissionService.delete(rolesPermission);
            }
        }

        return id;
    }

}
