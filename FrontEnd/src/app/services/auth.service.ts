import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IUser } from '../models/user.type';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private httpClient: HttpClient) { }

  loginMock(user: IUser): Observable<string> {
    if (user.email == 'filipe@a' && user.password == 'test') {
      return of('token');
    }

    return of();
  }

  login(user: IUser): Observable<unknown> {
    return this.httpClient.post('http://localhost:8080/users/login', {
      email: user.email,
      password: user.password
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
