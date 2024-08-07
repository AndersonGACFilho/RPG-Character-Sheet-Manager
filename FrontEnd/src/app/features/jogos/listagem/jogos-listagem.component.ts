import { Component, OnInit } from '@angular/core';
import { SessionService } from '../../../services/session.service';
import { ISession } from '../../../models/session.type';
import { Router } from '@angular/router';

@Component({
  selector: 'app-jogos-listagem',
  templateUrl: './jogos-listagem.component.html',
  styleUrl: './jogos-listagem.component.scss',
})
export class JogosListagemComponent implements OnInit {
  sessoes: ISession[] = [];

  constructor(private sessionService: SessionService, private router: Router) {}

  ngOnInit(): void {
    this.obterSessoes();
  }

  obterSessoes() {
    this.sessionService.obterSessoes(0).subscribe({
      next: (response) => {
        this.sessoes = response;
        console.log(this.sessoes);
      },
    });
  }

  deletarSessao(id: number) {
    this.sessionService.deletarSessao(id).subscribe({
      next: () => {
        this.obterSessoes();
      },
    });
  }

  alterarSessao(id: number) {
    this.router.navigate(['home', 'jogos', 'new', id]);
  }
}
