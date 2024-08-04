import { inject } from "@angular/core";
import { CanActivateFn, Router } from "@angular/router";

export const authGuard: CanActivateFn = () => {
    debugger
    if ((localStorage.getItem('login')?.length ?? 0) > 0) {
        return true;
    }

    localStorage.removeItem('login');
    return inject(Router).navigate(['login']);
}