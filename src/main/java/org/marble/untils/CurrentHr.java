package org.marble.untils;

import org.marble.pojo.Hr;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentHr {

    public static Hr currentHr(){
        return ((Hr) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal());
    }
}
