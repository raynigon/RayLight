import { Component } from '@angular/core';
import { ControlPanelService } from 'src/app/services/ControlPanelService';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-controlpanel',
  templateUrl: './controlpanel.component.html',
  styleUrls: ['./controlpanel.component.scss']
})
export class ControlPanelComponent {
  public menuOpen = false;

  public controlPanel: any;

  constructor(private service: ControlPanelService, private route: ActivatedRoute) {
    this.load();
  }

  private async load() {
    this.controlPanel = await this.service.getById(this.route.snapshot.paramMap.get('id'));
  }

  public addPanel() {
    this.controlPanel.data.panels.push({
      type: 'button',
      dimensions: {
        x: 10,
        y: 10,
        width: 100,
        height: 100,
      },
      styles: {
        backgroundActive: 'red',
        backgroundInactive: '',
        colorActive: 'blue',
        colorInactive: '',
      }
    });
    this.service.updateControlPanel(this.controlPanel);
  }

  removePanel(panel) {
    const panels = this.controlPanel.data.panels as any[];
    const index = panels.indexOf(panel);
    panels.splice(index, 1);
    this.service.updateControlPanel(this.controlPanel);
  }
}
