import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JogosHomeComponent } from './home/home.component';

const routes: Routes = [
  {
    path: '',
    component: JogosHomeComponent
  },
  {
    path: 'ficha',
    loadChildren: () => import('./ficha/ficha.module').then(m => m.FichaModule)
  },
  {
    path: 'jogos',
    loadChildren: () => import('./jogos/jogos.module').then(m => m.JogosModule)
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class FeaturesRoutingModule { }
