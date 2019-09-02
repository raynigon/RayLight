import { Component, Input } from '@angular/core';
import { ISliderPanel } from 'src/app/model/panels/ISliderPanel';
import { DMXService } from 'src/app/services/DMXService';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-sliderpanel',
  templateUrl: './sliderpanel.component.html',
  styleUrls: ['./sliderpanel.component.scss']
})
export class SliderPanelComponent {
  private channelValue = 0;
  private transmit = false;

  @Input()
  private data: ISliderPanel;

  public initialOffset = null;
  public initialPageOffset = null;

  constructor(
    private outputService: DMXService,
    private sanitizer: DomSanitizer
  ) {}

  public get value() {
    return this.channelValue;
  }

  public set value(value: number) {
    this.channelValue = Math.round(Math.min(Math.max(0, value), 255));
    this.updateValue();
  }


  get knobPositionX() {
    if (this.orientation === 'horizontal') {
      return this.sanitizer.bypassSecurityTrustStyle(
        'calc(10px + ' + this.value / 255.0 + '*(100% - 60px))'
      );
    }
    return undefined;
  }

  get knobPositionY() {
    if (this.orientation === 'vertical') {
      return this.sanitizer.bypassSecurityTrustStyle(
        'calc(10px + ' + this.value / 255.0 + '*(100% - 60px))'
      );
    }
    return undefined;
  }

  get color() {
    return this.data.styles.color;
  }

  get backgroundColor() {
    return this.data.styles.background;
  }

  get orientation(): 'horizontal' | 'vertical' {
    return this.data.dimensions.width < this.data.dimensions.height
      ? 'vertical'
      : 'horizontal';
  }

  get content() {
    return this.data.description;
  }

  public startMove(event) {
    if (!event) {
      return;
    }
    this.initialOffset = event.offsetY;
    this.initialPageOffset = event.target.pageY;
    if (event.dataTransfer) {
      const img = new Image();
      img.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=';
      event.dataTransfer.setDragImage(img, 0, 0);
    }
  }

  public moveKnob(event) {
    event.preventDefault();
    event.stopPropagation();
    if (event.screenX === 0 && event.screenY === 0) {
      return;
    }
    if (!event || !event.target) {
      return;
    }
    const parentStart =
      this.orientation === 'vertical'
        ? event.target.parentElement.getBoundingClientRect().top + 20
        : event.target.parentElement.getBoundingClientRect().left + 20;
    const parentSize =
      this.orientation === 'vertical'
        ? event.target.parentElement.clientHeight - 40
        : event.target.parentElement.clientWidth - 40;
    const knobStart =
      this.orientation === 'vertical' ? event.pageY : event.pageX;
    this.value =
      255 -
      Math.max(0, Math.min(1, (knobStart - parentStart) / parentSize)) * 255;
  }

  private async updateValue() {
    if (this.transmit) {
      return;
    }
    const currentChannelValue = this.channelValue;
    this.transmit = true;
    const result = await this.outputService.updateDmxValue(
      this.data.action.channel,
      currentChannelValue
    );
    this.transmit = false;
    if (currentChannelValue !== this.channelValue) {
      this.updateValue();
    }
  }
}
