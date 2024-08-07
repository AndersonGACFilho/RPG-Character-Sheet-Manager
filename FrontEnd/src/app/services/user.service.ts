import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { IUserRegister } from '../models/user.type';


@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private apiService: ApiService) {}

  criarUsuario(user: IUserRegister) {
    return this.apiService.post('/user/register', user);
  }

}