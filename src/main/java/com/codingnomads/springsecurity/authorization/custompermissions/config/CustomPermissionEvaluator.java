/* CodingNomads (C)2024 */
package com.codingnomads.springsecurity.authorization.custompermissions.config;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import com.codingnomads.springsecurity.authorization.custompermissions.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final UserService userService;

    // better suited for @PostAuthorize as we have access to the return object
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) authentication.getAuthorities();

        try {
            // Some reflective work to get the ID in question
            Class<?> targetType = targetDomainObject.getClass();
            Method method = targetType.getMethod("getId");
            Long id = (Long) method.invoke(targetDomainObject);

            // compile GrantedAuthorityString
            String grantedAuthorityString = ((Long) id != -1L ? id : userService.getUser(authentication.getName()).getId()) +
                    "_" + targetType.getName() + "_" + permission;

            // check if user has matching authority. Return true if so false otherwise
            for (GrantedAuthority ga : grantedAuthorities) {
                if (ga.getAuthority().equalsIgnoreCase(grantedAuthorityString)) {
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    // better suited for @PreAuthorize as we just have the id of and type of target object
    @Override
    public boolean hasPermission(
            Authentication authentication, Serializable targetId, String targetType, Object permission) {
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) authentication.getAuthorities();

        try {
            // compile grantedAuthorityString
            String grantedAuthorityString = ((Long) targetId != -1L ? targetId : userService.getUser(authentication.getName()).getId())
                    + "_" + targetType + "_" + permission;

            // check if user has matching authority. Return true if so false otherwise
            for (GrantedAuthority ga : grantedAuthorities) {
                if (ga.getAuthority().equalsIgnoreCase(grantedAuthorityString)) {
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }
}
