import { BreakpointObserver } from '@angular/cdk/layout';
import { NgIf, NgOptimizedImage } from '@angular/common';
import { Component } from '@angular/core';
import { TranslatePipe } from '@ngx-translate/core';
import { fadeInAnimation } from '../../shared/animations/fade-in-animation';
import { ResponsiveComponent } from '../../shared/common/responsive.component';
import { AnimatedBackgroundComponent } from './animated-background/animated-background.component';

@Component({
  selector: 'fe-login',
  standalone: true,
  imports: [AnimatedBackgroundComponent, NgIf, TranslatePipe, NgOptimizedImage],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  animations: [fadeInAnimation]
})
export class LoginComponent extends ResponsiveComponent {

  constructor(override breakpointObserver: BreakpointObserver) {
    super(breakpointObserver);
  }
}
