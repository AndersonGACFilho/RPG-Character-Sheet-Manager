import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'login',
        loadChildren: () => import('./login/login.module').then(m => m.LoginModule)
    },
    {
        path: 'home',
        loadChildren: () => import('./features/features.module').then(m => m.FeaturesModule)
    },
    {
        path: '**',
        redirectTo: 'login'
    }
];
