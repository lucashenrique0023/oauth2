package lab.lhss.school.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

    @interface Student {
        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('SEARCH_STUDENT')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface Search { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('CREATE_STUDENT')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface Create { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_STUDENT')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface Edit { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('DELETE_STUDENT')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface Delete { }
    }
}
