import { Injectable } from '@angular/core';
import { IUser } from '../models/user.type';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private apiService: ApiService) { }

  login(user: IUser): Observable<string> {
    return this.apiService.post<string>('/users/login', {
      email: user.email,
      password: user.password
    }, {
      responseType: 'text' as 'json'
    });
  }

  saveLogin(token: string) {
    localStorage.setItem('login', token);
  }

  getUser() {
    return localStorage.getItem('login');
  }

  signOut() {
    localStorage.removeItem('login');
  }

  get isAuthenticated() {
    return (this.getUser()?.length ?? 0) > 0;
  }
}
