import { NgForOf, NgIf } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TranslatePipe } from '@ngx-translate/core';
import { IconComponent } from '../../common/icon/icon.component';
import { SegmentButton } from './segment-button.interface';

@Component({
  selector: 'fe-segment-button',
  standalone: true,
  imports: [NgForOf, NgIf, IconComponent, TranslatePipe],
  templateUrl: './segment-button.component.html',
  styleUrl: './segment-button.component.scss'
})
export class SegmentButtonComponent implements OnInit {

  @Input() segments!: SegmentButton[];
  @Input() active?: SegmentButton;

  @Input() icon: boolean = true;

  @Input() width: string = 'auto';

  @Output() segmentChange = new EventEmitter<SegmentButton>();

  ngOnInit(): void {
    if (this.active == null) {
      this.active = this.segments[0];
    }
  }

  setActive(active: SegmentButton): void {
    this.segmentChange.emit(this.active = active);
  }
}
