import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private httpClient: HttpClient) { }

  get<T>(
    endpoint: string,
    params?: unknown,
    options: object = { observe: 'body' }
  ): Observable<T> {
    return this.httpClient.get<T>(`${environment.api.url}${endpoint}`, {
      ...options,
      params: this.createHttpParams(params),
      headers: { Authorization: `Bearer ${localStorage.getItem('login')}` },
    });
  }

  post<T>(
    endpoint: string,
    data?: unknown,
    options: object = { observe: 'body' }
  ): Observable<T> {
    return this.httpClient.post<T>(`${environment.api.url}${endpoint}`, data, {
      ...options,
      headers: {Authorization: `Bearer ${localStorage.getItem('login')}`},
    });
  }

  put<T>(endpoint: string, data?: unknown | undefined): Observable<T> {
    return this.httpClient.put<T>(`${environment.api.url}${endpoint}`, data, {
      headers: { Authorization: `Bearer ${localStorage.getItem('login')}` },
    });
  }

  patch<T>(endpoint: string, data?: unknown | undefined): Observable<T> {
    return this.httpClient.patch<T>(`${environment.api.url}${endpoint}`, data, {
      headers: { Authorization: `Bearer ${localStorage.getItem('login')}` },
    });
  }

  delete<T>(endpoint: string, params?: unknown): Observable<T> {
    return this.httpClient.delete<T>(`${environment.api.url}${endpoint}`, {
      params: this.createHttpParams(params),
      headers: { Authorization: `Bearer ${localStorage.getItem('login')}` },
    });
  }

  createHttpParams(params?: any): HttpParams {
    let httpParams: HttpParams = new HttpParams();
    if (!params) {
      return httpParams;
    }
    Object.keys(params).forEach((param) => {
      if (params[param] != null) {
        httpParams = httpParams.set(param, params[param]);
      }
    });

    return httpParams;
  }
}
