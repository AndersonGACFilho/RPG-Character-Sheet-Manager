import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FichaComponent } from './edicao/ficha.component';
import { FichaListagemComponent } from './listagem/ficha-listagem.component';

const routes: Routes = [
    {
        path: '',
        component: FichaListagemComponent
    },
    {
        path: 'edit',
        component: FichaComponent
    }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class FichaRoutingModule { }
