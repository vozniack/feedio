import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { TranslatePipe } from '@ngx-translate/core';
import { tap } from 'rxjs/operators';
import { ButtonComponent } from '../../../shared/components/buttons/button/button.component';
import { TextInputComponent } from '../../../shared/components/inputs/text-input/text-input.component';
import { emailRegex } from '../../../shared/const/regex.const';
import { ACTION_LOGIN } from '../../../store/app/app.actions';
import { LoginRequest, LoginResponse } from '../../auth/auth.interface';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'fe-login-form',
  standalone: true,
  imports: [ButtonComponent, TextInputComponent, TranslatePipe],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.scss'
})
export class LoginFormComponent {

  form!: FormGroup;

  constructor(private authService: AuthService,  private store: Store, private router: Router, private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.pattern(emailRegex)]),
      password: new FormControl('', [Validators.required])
    });
  }

  login(): void {
    this.authService.login(this.form.getRawValue() as LoginRequest).pipe(
      tap((response: LoginResponse) => this.store.dispatch(ACTION_LOGIN({user: {token: response.token}}))),
      tap(() => this.router.navigate([''])),
    ).subscribe();
  }

  getControl(controlName: string): FormControl {
    return this.form.get(controlName) as FormControl;
  }
}
