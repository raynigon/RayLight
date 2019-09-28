import { Component, Input } from '@angular/core';
import { IButtonPanel } from 'src/app/model/panels/IButtonPanel';
import { DMXService } from 'src/app/services/DMXService';
import { ShowService } from 'src/app/services/ShowService';


@Component({
  selector: 'app-buttonpanel',
  templateUrl: './buttonpanel.component.html',
  styleUrls: ['./buttonpanel.component.scss']
})
export class ButtonPanelComponent {

  @Input()
  private data: IButtonPanel;

  private active: boolean;

  private lastAction: number;

  constructor(private output: DMXService, private show: ShowService) {}

  public action(event) {
    event.preventDefault();
    event.stopPropagation();
    const now = (new Date()).getTime();
    if ((now - this.lastAction) < 10) {
      return;
    }
    this.lastAction = now;
    const action = this.data.action;
    if (action.type === 'set-dmx-value') {
      this.output.updateDmxValue(action.channel, action.value);
    } else if (action.type === 'toggle-dmx-value') {
      this.output.updateDmxValue(action.channel, this.active ? 0 : action.value);
      this.active = !this.active;
    } else if (action.type === 'play-cuelist') {
      this.show.playCuelist(action.cuelist.id);
    } else if (action.type === 'stop-cuelist') {
      this.show.stopCuelist(action.cuelist.id);
    } else if (action.type === 'go-to-cue') {
      this.show.goToCue(action.cuelist.id, action.cue);
    }
  }

  get color() {
    return this.active ? this.data.styles.colorActive : this.data.styles.colorInactive;
  }

  get backgroundColor() {
    return this.active ? this.data.styles.backgroundActive : this.data.styles.backgroundInactive;
  }

  get content() {
    return this.data.description;
  }
}
