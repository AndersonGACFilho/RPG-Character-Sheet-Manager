import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-login-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.scss',
})
export class CadastroComponent {
  userForm!: FormGroup;
  loading: boolean = false;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private toastr: ToastrService,
    private router: Router,
  ) {    
    this.userForm = this.fb.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required]],
        username: ['', [Validators.required]],
        firstName: ['', [Validators.required]],
        lastName: ['', [Validators.required]],
    });
  }

  get emailControls() {
    return this.userForm.controls['email'];
  }

  get passwordControls() {
    return this.userForm.controls['password'];
  }

  get usernameControls() {
    return this.userForm.controls['username'];
  }

  get firstNameControls() {
    return this.userForm.controls['firstName'];
  }

  get lastNameControls() {
    return this.userForm.controls['lastName'];
  }

  onSubmit() {
    if(this.userForm.valid && !this.loading) {
      this.loading = true;
      this.userService.criarUsuario({
        email: this.userForm.get('email')?.value,
        password: this.userForm.get('password')?.value,
        username: this.userForm.get('username')?.value,
        firstName: this.userForm.get('firstName')?.value,
        lastName: this.userForm.get('lastName')?.value,
      })
      .pipe(finalize(() => this.loading = false))
      .subscribe({
        next: () => {
          this.toastr.success('Usuário criado com sucesso.');
          this.router.navigate(['home']);
        },
        error: () => {
          this.toastr.error('Erro ao cadastrar usuário.');
        }
      });
    }
  }

  login() {
    this.router.navigate(['login']);
  }
}