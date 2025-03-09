import { BreakpointObserver } from '@angular/cdk/layout';
import { NgIf, NgOptimizedImage } from '@angular/common';
import { Component } from '@angular/core';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { fadeInAnimation } from '../../shared/animations/fade-in-animation';
import { ResponsiveComponent } from '../../shared/common/responsive.component';
import { SegmentButtonComponent } from '../../shared/components/buttons/segment-button/segment-button.component';
import { SegmentButton } from '../../shared/components/buttons/segment-button/segment-button.interface';
import {
  AnimatedBackgroundComponent
} from '../../shared/components/common/animated-background/animated-background.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { SignupFormComponent } from './signup-form/signup-form.component';

@Component({
  selector: 'fe-login',
  standalone: true,
  imports: [AnimatedBackgroundComponent, NgIf, TranslatePipe, NgOptimizedImage, SegmentButtonComponent, AnimatedBackgroundComponent, AnimatedBackgroundComponent, LoginFormComponent, SignupFormComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  animations: [fadeInAnimation]
})
export class LoginComponent extends ResponsiveComponent {

  active!: SegmentButton;
  segments!: SegmentButton[];

  constructor(private translateService: TranslateService, override breakpointObserver: BreakpointObserver) {
    super(breakpointObserver);

    this.segments = [
      {text: this.translateService.instant('login.types.login'), value: 'LOGIN'},
      {text: this.translateService.instant('login.types.signup'), value: 'SIGNUP'}
    ];

    this.active = this.segments[0];
  }

  onSegmentChange(active: SegmentButton) {
    this.active = active;
  }
}
