import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SessionService } from '../../../services/session.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Route, Router } from '@angular/router';

@Component({
  selector: 'app-jogos',
  templateUrl: './jogo.component.html',
  styleUrl: './jogo.component.scss',
})
export class JogosComponent implements OnInit {
  sessaoForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private sessionService: SessionService,
    private toastr: ToastrService,
    private router: Router,
    private route: ActivatedRoute
  ) {    
    this.sessaoForm = this.fb.group({
      id: [this.route.snapshot.params['id']],
      name: ['', [Validators.required, Validators.maxLength(20)]],
      description: ['', [Validators.required, Validators.maxLength(100)]],
    });
  }

  ngOnInit(): void {
    if (this.sessaoForm.value.id) {
      this.sessionService.obterSessao(this.sessaoForm.value.id).subscribe({
        next: (response) => {
          this.sessaoForm.patchValue(response);
        },
      });
    }
  }

  onSubmit() {
    if (this.sessaoForm.valid) {
      if (this.sessaoForm.value.id) {
        this.sessionService.atualizarSessao(this.sessaoForm.value).subscribe({
          next: () => {
            this.toastr.success('Sessão atualizada com sucesso!', 'Sucesso!');
            this.router.navigate(['/home/jogos']);
          },
          error: () => {
            this.toastr.error('Ocorreu um erro ao atualizar a sessão!', 'Erro!');
          },
        });
      } else {
        this.sessionService.criarSessao(this.sessaoForm.value).subscribe({
          next: () => {
            this.toastr.success('Sessão salva com sucesso!', 'Sucesso!');
            this.router.navigate(['/home/jogos']);
          },
          error: () => {
            this.toastr.error('Ocorreu um erro ao salvar a sessão!', 'Erro!');
          },
        });
      }
    }
  }
}
