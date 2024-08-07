import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JogosRoutingModule } from './jogos-routing.module';
import { JogosComponent } from './edicao/jogo.component';
import { JogosListagemComponent } from './listagem/jogos-listagem.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginRoutingModule } from '../../login/login-routing.module';
import { MenuModule } from '../../shared/shared.module';
import { SessionService } from '../../services/session.service';
import { BrowserModule } from '@angular/platform-browser';

@NgModule({
  declarations: [JogosComponent, JogosListagemComponent],
  imports: [
    CommonModule,
    JogosRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    LoginRoutingModule,
    MenuModule,
  ],
  providers: [SessionService],
})
export class JogosModule {}
