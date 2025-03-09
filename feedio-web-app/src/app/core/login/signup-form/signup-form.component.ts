import { Component } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { select, Store } from '@ngrx/store';
import { TranslatePipe } from '@ngx-translate/core';
import { tap } from 'rxjs/operators';
import { ButtonComponent } from '../../../shared/components/buttons/button/button.component';
import { SelectInputComponent } from '../../../shared/components/inputs/select-input/select-input.component';
import { SelectInputOption } from '../../../shared/components/inputs/select-input/select-input.interface';
import { TextInputComponent } from '../../../shared/components/inputs/text-input/text-input.component';
import { emailRegex } from '../../../shared/const/regex.const';
import { SELECT_LANGUAGE } from '../../../store/app/app.selectors';
import { SignupRequest } from '../../auth/auth.interface';
import { AuthService } from '../../auth/auth.service';
import { I18nService } from '../../i18n/I18nService';

@Component({
  selector: 'fe-signup-form',
  standalone: true,
  imports: [ButtonComponent, TextInputComponent, TranslatePipe, SelectInputComponent],
  templateUrl: './signup-form.component.html',
  styleUrl: './signup-form.component.scss'
})
export class SignupFormComponent {

  form!: FormGroup;

  languages: SelectInputOption[] = [
    {text: 'English', value: 'en_US',},
    {text: 'Polski', value: 'pl_PL',},
  ];

  constructor(private authService: AuthService, private store: Store, private router: Router, private i18nService: I18nService, private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.pattern(emailRegex)]),
      password: new FormControl('', [Validators.required]),
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      language: new FormControl('', [Validators.required]),
    });

    this.store.pipe(
      takeUntilDestroyed(),
      select(SELECT_LANGUAGE),
      tap((language: string) => this.getControl('language').setValue(language))
    ).subscribe();

    this.form.valueChanges.pipe(
      tap((formValue: any) => this.i18nService.setLanguage(formValue.language))
    ).subscribe();
  }

  signup(): void {
    this.authService.signup(this.form.getRawValue() as SignupRequest).pipe(
      tap(() => this.router.navigate([''])),
    ).subscribe();
  }

  getControl(controlName: string): FormControl {
    return this.form.get(controlName) as FormControl;
  }
}
