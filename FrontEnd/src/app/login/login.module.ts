import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { LoginRoutingModule } from './login-routing.module';
import { AuthService } from '../services/auth.service';
import { LoginComponent } from './login.component';
import { CadastroComponent } from './cadastro/cadastro.component';


@NgModule({
  declarations: [LoginComponent, CadastroComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    LoginRoutingModule,
  ],
  providers: [
    AuthService
  ]
})
export class LoginModule { }
