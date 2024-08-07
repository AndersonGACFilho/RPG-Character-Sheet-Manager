import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JogosListagemComponent } from './listagem/jogos-listagem.component';
import { JogosComponent } from './edicao/jogo.component';

const routes: Routes = [
    {
        path: '',
        component: JogosListagemComponent
    },
    {
        path: 'new',
        component: JogosComponent
    },
    {
        path: 'new/:id',
        component: JogosComponent
    }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class JogosRoutingModule { }
