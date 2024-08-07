import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { ISession, ISessionList } from '../models/session.type';
import { map } from 'rxjs';
import { IPage } from '../models/pageable.type';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  constructor(private apiService: ApiService) {}

  criarSessao(session: ISession) {
    return this.apiService.post('/game-sessions/master/', session);
  }

  obterSessoes(page: number) {
    return this.apiService.get<IPage<ISessionList>>(`/game-sessions/master/page/${page}`)
      .pipe(map(response => response.content as ISession[] ));
  }

  atualizarSessao(session: ISession) {
    return this.apiService.put(`/game-sessions/master/${session.id}`, session);
  }

  deletarSessao(id: number) {
    return this.apiService.delete(`/game-sessions/${id}`);
  }

  obterSessao(id: number) {
    return this.apiService.get<ISession>(`/game-sessions/${id}`);
  }
}
