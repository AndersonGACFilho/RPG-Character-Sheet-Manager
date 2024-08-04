import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { finalize } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm!: FormGroup;
  loading: boolean = false;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (!this.loading && this.loginForm.valid) {
      this.loading = true;
      this.authService.loginMock({
        email: this.loginForm.get('email')?.value,
        password: this.loginForm.get('password')?.value
      })
        .pipe(finalize(() => this.loading = false))
        .subscribe((result: string) => {
          if (result.length > 0) {
            this.authService.saveLogin(result);
            this.router.navigate(['/home']);
          }
          // falhou, informa o usuário.
        });
    }
  }
}
