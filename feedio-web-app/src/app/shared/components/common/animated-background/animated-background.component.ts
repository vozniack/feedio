import { NgForOf } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'fe-animated-background',
  standalone: true,
  imports: [NgForOf],
  templateUrl: './animated-background.component.html',
  styleUrl: './animated-background.component.scss'
})
export class AnimatedBackgroundComponent {

  numbers: number[] = Array(128).fill(1);
}
