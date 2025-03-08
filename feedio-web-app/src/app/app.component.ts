import { BreakpointObserver } from '@angular/cdk/layout';
import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { select, Store } from '@ngrx/store';
import { tap } from 'rxjs/operators';
import { ContentComponent } from './core/content/content.component';
import { I18nService } from './core/i18n/I18nService';
import { LoginComponent } from './core/login/login.component';
import { Router } from '@angular/router';
import { ThemeService } from './core/theme/theme.service';
import { ResponsiveComponent } from './shared/common/responsive.component';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { SELECT_USER_STATE } from './store/app/app.selectors';
import { UserState } from './store/app/app.state';

@Component({
  selector: 'fe-root',
  standalone: true,
  imports: [LoginComponent, NgIf, ContentComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent extends ResponsiveComponent {

  logged: boolean = true;

  constructor(private store: Store, private router: Router, private i18nService: I18nService, private themeService: ThemeService, override breakpointObserver: BreakpointObserver) {
    super(breakpointObserver);

    this.i18nService.applyLanguage();
    this.themeService.applyTheme();

    this.store.pipe(
      takeUntilDestroyed(),
      select(SELECT_USER_STATE),
      tap((state: UserState) => this.logged = !!state.token),
      tap(() => {
        if (!this.logged) {
          this.router.navigate(['/login']).then();
        }
      })
    ).subscribe();
  }
}
