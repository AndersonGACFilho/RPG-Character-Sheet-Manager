import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { finalize } from 'rxjs';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  loginForm!: FormGroup;
  loading: boolean = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {
    if (this.authService.isAuthenticated) {
      this.router.navigate(['/home']);
    }	

    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  get emailControls() {
    return this.loginForm.controls['email'];
  }

  get passwordControls() {
    return this.loginForm.controls['password'];
  }

  onSubmit() {
    if (!this.loading && this.loginForm.valid) {
      this.loading = true;
      this.authService
        .login({
          email: this.loginForm.get('email')?.value,
          password: this.loginForm.get('password')?.value,
        })
        .pipe(finalize(() => (this.loading = false)))
        .subscribe({ 
          next: (result: string) => {
            if (result.length > 0) {
              this.authService.saveLogin(result);
              this.router.navigate(['/home']);
            }
            this.toastr.error('Usuário ou Senha inválido.');
          },
          error: () => this.toastr.error('Usuário ou Senha inválido.')
        });
    }
  }

  criarUsuario() {
    this.router.navigate(['/login/novoUsuario']);
  }
}
