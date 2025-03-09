import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { select, Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from '../../../environments/environment';
import { SELECT_APP_STATE } from '../../store/app/app.selectors';
import { AppState } from '../../store/app/app.state';
import { LoginRequest, LoginResponse, SignupRequest, SignupResponse } from './auth.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl = `${environment.backendUrl}/api/auth`;
  token: string | undefined = '';

  constructor(private store: Store, private httpClient: HttpClient) {
    this.store.pipe(
      takeUntilDestroyed(),
      select(SELECT_APP_STATE),
      tap((state: AppState) => this.token = state?.user?.token),
    ).subscribe();
  }

  signup(request: SignupRequest): Observable<SignupResponse> {
    return this.httpClient.post<SignupResponse>(`${this.baseUrl}/signup`, request);
  }

  login(request: LoginRequest): Observable<LoginResponse> {
    return this.httpClient.post<LoginResponse>(`${this.baseUrl}/login`, request);
  }
}
