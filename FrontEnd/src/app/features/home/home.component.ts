import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-jogos',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class JogosHomeComponent {
  constructor(private router: Router) {}

  criarNovaSessao() {
    this.router.navigate(['home', 'jogos', 'new']);
  }

  listarSessoes() {
    this.router.navigate(['home', 'jogos']);
  }
}
